pipeline {
    agent any  // Использовать любой доступный агент

    tools {
            maven 'M3'  // Имя Maven-инструмента, настроенного в Jenkins (Global Tool Configuration)
        }

    stages {
        stage('Сборка') {
            steps {
                // Команда для сборки вашего проекта
                bat 'mvn.cmd compile'  // Для Maven
            }
        }
         stage('Тестирование') {
            steps {
                // Команда для запуска тестов
                bat 'mvn.cmd test'  // Для Maven
            }
        }
        stage('package') {
                    steps {
                        // Команда для запуска тестов
                        bat 'mvn.cmd package'  // Для Maven
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
}
