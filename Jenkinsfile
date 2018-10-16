
pipeline {
	agent any
  
	tools {
		maven 'maven352'
	}

	stages {
		stage('Build') {
		  steps {
		    sh 'mvn clean install -DskipTests'
		  }
		}
		stage('Tests') {
		  steps {
		    sh 'mvn test'
		  }
		}
		stage('Docker Image') {
			steps {
			  sh 'echo $USER'  
			  sh 'docker build -t simple-game:latest .'
			}
		}
		stage('Docker Image Push to AWS ECR') {
			steps {
				script {
		  			docker.withRegistry("https://174713999784.dkr.ecr.us-west-2.amazonaws.com", "ecr:us-west-2:ecr-credentials") {
						docker.image("simple-game").push("latest")	
					}
				}
			}
	  	}
	}
}
