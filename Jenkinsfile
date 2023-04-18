pipeline {
  agent any
  
  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/ronmattss/AutomationPractice'
      }
    }
    
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
  }
}
