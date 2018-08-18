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
  }
}
