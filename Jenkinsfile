pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'anastasiia25/final-project'
       
        REGISTRY_CREDENTIALS = 'docker-hub-credentials'
    }

    stages {
       stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Building application...'
                
                sh 'mvn clean install -DskipTests=false'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker Image...'

                    dockerImage = docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}")
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    echo 'Pushing to Docker Hub...'
                
                    docker.withRegistry('', REGISTRY_CREDENTIALS) {
                       
                        dockerImage.push()
                        
                        dockerImage.push('latest')
                    }
                }
            }
        }
    }
}