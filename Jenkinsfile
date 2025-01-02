pipeline {
    agent any
    tools {
        maven 'sonarmaven' // Ensure 'sonarmaven' is defined in Jenkins global tools
    }
    environment {
        SONAR_TOKEN = credentials('sonar-token') // Ensure 'sonar-token' exists in Jenkins credentials
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-scanner') { // Ensure 'sonarqube-scanner' is configured in Jenkins
                    sh 'mvn sonar:sonar -Dsonar.login=${SONAR_TOKEN}'
                }
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
