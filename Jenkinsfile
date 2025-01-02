pipeline {
    agent any
    tools {
        maven 'Maven 3'  // Make sure this matches the name configured in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Nisha-Velmurugan/web-automation.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Build, Test, or SonarQube analysis completed.'
        }
    }
}
