Spring Security JWT

## Описание

Проект представляет собой пример веб-приложения с использованием Spring Security и JWT для аутентификации и авторизации пользователей. В проекте также используется Swagger/OpenAPI для документирования API.

## Стек технологий

- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- PostgreSQL
- Maven
- Swagger/OpenAPI

## Запуск проекта

### Предварительные условия

- Java 17
- Maven
- PostgreSQL

### Настройка базы данных

Создайте базу данных PostgreSQL с именем `banktest` и настройте пользователя и пароль в файле `application.properties`:


`spring.datasource.url=jdbc:postgresql://localhost:5432/banktest
spring.datasource.username=postgres
spring.datasource.password=GOGUDAserver123!`

Использование API
Регистрация пользователя

    URL: /api/v1/auth/signup
    Метод: POST
    Тело запроса:

    json

{
"login": "test@example.com",
"password": "password123"
}

Пример ответа:

json

    {
      "id": 1,
      "login": "test@example.com",
      "password": "$2a$10$D9qO.."
    }

Аутентификация пользователя

    URL: /api/v1/auth/signin
    Метод: POST
    Тело запроса:

    json

{
"login": "test@example.com",
"password": "password123"
}

Пример ответа:

json

    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5...",
      "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5..."
    }

Обновление JWT токена

    URL: /api/v1/auth/refresh
    Метод: POST
    Тело запроса:

    json

{
"token": "eyJhbGciOiJIUzI1NiIsInR5..."
}

Пример ответа:

json

    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5...",
      "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5..."
    }

Защищенные ресурсы
Доступ к ресурсам для администратора

    URL: /api/v1/admin
    Метод: GET
    Пример ответа:

    json

    {
      "message": "Доступ разрешен"
    }

Доступ к ресурсам для пользователя

    URL: /api/v1/user
    Метод: GET
    Пример ответа:

    json

    {
      "message": "проверка"
    }

Документация API

Документация API доступна по адресу http://localhost:8082/swagger-ui.html.

Контакты

Если у вас есть вопросы или предложения, пожалуйста, свяжитесь со мной по email@example.com.
