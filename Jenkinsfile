pipeline {
    agent any
    tools {
        maven 'sonarmaven' // Ensure the Maven tool is configured in Jenkins.
    }
    environment {
        SONAR_TOKEN = credentials('sonar-token') // Use Jenkins credentials for secure token handling.
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64' // Ensure Java 17 is installed.
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-scanner') {
                    sh """
                        mvn sonar:sonar \
                          -Dsonar.projectKey=web \
                          -Dsonar.host.url=http://localhost:9000 \
                          -Dsonar.login=${SONAR_TOKEN} \
                          -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                    """
                }
            }
        }

        stage('Archive JaCoCo Reports') {
            steps {
                echo 'Archiving JaCoCo report...'
                archiveArtifacts artifacts: 'target/site/jacoco/**/*.html', allowEmptyArchive: true
                archiveArtifacts artifacts: 'target/site/jacoco/jacoco.xml', allowEmptyArchive: true
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
