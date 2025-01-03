pipeline {
    agent any
    tools {
        maven 'sonarmaven'
    }
    environment {
        SONAR_TOKEN = credentials('sonar-token')
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
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
                    sh '''
                        mvn sonar:sonar \
                          -Dsonar.projectKey=mavencode1 \
                          -Dsonar.host.url=http://localhost:9000 \
                          -Dsonar.login=${SONAR_TOKEN}
                    '''
                }
            }
        }
        stage('Archive JaCoCo Reports') {
            steps {
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
