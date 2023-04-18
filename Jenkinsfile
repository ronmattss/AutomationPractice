pipeline {
  agent any
  
  stages {
   
    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
    
    stage('Cucumber') {
      steps {
        sh 'mvn test -Dcucumber.options="--tags @cucumber"'
      }
    }
  }
}
