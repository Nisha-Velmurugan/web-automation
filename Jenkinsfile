pipeline {
    agent any
    
    environment {
        MAVEN_HOME = tool name: 'Maven 3', type: 'ToolLocation'
        SONARQUBE = 'SonarQube' 
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Nisha-Velmurugan/web-automation.git'
            }
        }
        
        stage('Build') {
            steps {
                script {
                    sh "'${MAVEN_HOME}/bin/mvn' clean install"
                }
            }
        }
        
        stage('Test') {
            steps {
                script {
                    sh "'${MAVEN_HOME}/bin/mvn' test"
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv(SONARQUBE) {
                        sh "'${MAVEN_HOME}/bin/mvn' sonar:sonar"
                    }
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                script {
                    def qualityGate = waitForQualityGate()
                    if (qualityGate.status != 'OK') {
                        error "Quality gate failed: ${qualityGate.status}"
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Build, Test, and SonarQube analysis completed successfully!'
        }
        failure {
            echo 'Build, Test, or SonarQube analysis failed.'
        }
    }
}
