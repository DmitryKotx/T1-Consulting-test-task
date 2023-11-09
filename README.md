# T1-Consulting-test-task

Проект является тестовым заданием, требования к которому описаны  
здесь: https://docs.google.com/document/d/1G_bNiKfEWXorOOry79s7UzPTNvYy4tRaYqnUnhPxuiU/edit

**Рекомендации по установке/скачиванию. Есть 2 варианта:**  

Первый вариант - склонировать репозиторий с GitHub  
Шаг 1. откройте терминал.  
Шаг 2. Перейдите в дирректорию, в которой вы хотите разместить репозиторий.  
Шаг 3. Выполните следующий скрипт для клонирования репозитория: git clone https://github.com/DmitryKotx/T1-Consulting-test-task

Второй вариант - скачать docker-образ с Docker Hub.  
Шаг 0. Скачайте Docker Desktop, если он у вас еще не установлен: https://www.docker.com/products/docker-desktop/  
Шаг 1. Запустите Docker Desktop, чтобы заработал Docker Engine, это необходимо.  
Шаг 2. откройте терминал.  
Шаг 3. Выполните следующий скрипт, скачивания docker-образа: docker pull dkotx/t1-consulting-test-task  

**После установки проекта, хотелось бы его запустить. Есть 3 варианта:**

Сначала убедитесь, что порт 8080 свободен, иначе запустить не получится!!!

Первый вариант (если репозиторий был склонирован с Github)  
Шаг 1. откройте терминал.  
Шаг 2. Выполните следующий скрипт для сборки проекта: ./mvnw clean install  
Шаг 3. Выполните следующий скрипт для запуска проекта: ./mvnw spring-boot:run  

Второй вариант (если репозиторий был склонирован с Github)  
Шаг 0. Скачайте среду разработки (IntelliJ IDEA, Eclipse и т.п.) для запуска, если она у вас еще не установлена  
Шаг 1. Откройте проект в среде разработки и запустите метод main в классе, доступному пу пути: src/main/java/ru/kotov/T1ConsultingTestTask/T1ConsultingTestTaskApplication  

Третий вариант (если образ скачивался с Docker Hub)  
Шаг 1. откройте терминал.  
Шаг 2. Выполните следующий скрипт для создания и запуска контейнера на основе docker-образа: docker run -p 8080:8080 dkotx/t1-consulting-test-task

**Рекомендации по использованию**  
Чтобы получить частоту символов в строке, необходимо эту строку передать в качетстве параметра последующему  
адресу :http://localhost:8080/api/character-frequencies?inputString=ваша-строка  
Более подробное описание работы данного api доступно по адресу: http://localhost:8080/swagger-ui/index.html  
