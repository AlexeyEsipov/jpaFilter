pipeline {
    agent any  // Использовать любой доступный агент

    stages {
        stage('Сборка') {
            steps {
                // Команда для сборки вашего проекта
                sh 'mvn clean validate compile'  // Для Maven
            }
        }
         stage('Тестирование') {
            steps {
                // Команда для запуска тестов
                sh 'mvn test'  // Для Maven
            }
        }
        stage('Сборка') {
                    steps {
                        // Команда для запуска тестов
                        sh 'mvn package'  // Для Maven
                    }
                }
    }

    post {
        success {
            echo 'Сборка прошла успешно!'
        }
        failure {
            echo 'Сборка завершилась неудачно.'
        }
    }
}
