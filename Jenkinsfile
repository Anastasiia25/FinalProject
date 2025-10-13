pipeline {
    agent any 
    
    stages {
        
        // 1: CHECKOUT
        stage('Checkout Code') {
            steps {
                echo 'Checking out source code from GitHub...'
                
                checkout scm 
            }
        }
        
        // 2: BUILD & TEST 
        stage('Build and Test') {
            steps {
                echo 'Building application with Maven...'
                
                sh 'mvn clean install -DskipTests=false' 
            }
        }

        // 3: DOCKER BUILD 
        
        stage('Docker Build') {
            steps {
                echo 'Building Docker Image...'
               
               script {
                    def appImage = docker.build("my-repo/expression-app:${env.BUILD_ID}")
                    
                    // send to the DockerHub:
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-credentials')    {
                         appImage.push()
                        }
                    
                }
            }
        }
        
        // 4: DEPLOY
        stage('Deployment') {
            steps {
                echo 'Deployment finished. Container image ready!'
               
                sh 'echo "Simulating deployment to target environment..."'
            }
        }
    }
}