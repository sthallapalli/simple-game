pipeline {
  agent any
  
  tools {
    maven 'maven352'
  }

  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Docker Image') {
	steps {
	  sh 'sudo docker build -t sthallapalli/simple-game:1.0.0 .'
	}
    }
  }
}
