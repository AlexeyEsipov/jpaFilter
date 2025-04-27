pipeline {
    agent any  // Использовать любой доступный агент

    stages {
        stage('Сборка') {
            steps {
                // Команда для сборки вашего проекта
                sh 'mvn compile'  // Для Maven
            }
        }
         stage('Тестирование') {
            steps {
                // Команда для запуска тестов
                sh 'mvn test'  // Для Maven
            }
        }
        stage('package') {
                    steps {
                        // Команда для запуска тестов
                        sh 'mvn package'  // Для Maven
                    }
                }
    }
}
