pipeline {
    agent any
    tools {
        maven 'sonarmaven'
    }
    environment {
        MAVEN_PATH = '/usr/bin/mvn'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        SONAR_TOKEN = credentials('sonar-token')
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Clean target folder') {
            steps {
                echo 'Cleaning target directory...'
                sh '''
                ${MAVEN_PATH} clean
                '''
            }
        }
        stage('Test') {
            steps {
                echo 'Testing the project and generating JaCoCo report...'
                sh '''
                ${MAVEN_PATH} test jacoco:report
                '''
            }
        }
        stage('Package') {
            steps {
                echo 'Packaging the compiled code...'
                sh '''
                ${MAVEN_PATH} package
                '''
            }
        }
        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                sh '''
                ${MAVEN_PATH} sonar:sonar \
                  -Dsonar.projectKey=web \
                  -Dsonar.host.url=http://localhost:9000 \
                  -Dsonar.login=${SONAR_TOKEN}
                '''
            }
        }
        stage('Archive JaCoCo Reports') {
            steps {
                echo 'Archiving JaCoCo report...'
                archiveArtifacts artifacts: 'target/site/jacoco/jacoco.html', allowEmptyArchive: true
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}
