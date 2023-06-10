## Начало работы

***

Склонировать проект к себе на ПК из GitHub.

Для запуска тестов на вашем ПК должно быть установлено следующее ПО:
- IntelliJ IDEA
- Git
- Docker Desktop
- Браузер

---

## Установка и запуск

1. Запускаем контейнеры из файла docker-compose.yml командой в терминале IntelliJ IDEA:

```

docker-compose up
```

2. Запускаем SUT командой в терминале:

- для MySQL:

```
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
```

- для PostgreSQL:

```
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
```
Сервис будет доступен в браузере по адресу: http://localhost:8080/

3. Запускаем авто-тесты командой в терминале:

- для MySQL:

```
./gradlew clean test "-Ddatasource.url=jdbc:mysql://localhost:3306/app"
```

- для PostgreSQL:

```
./gradlew clean test "-Ddatasource.url=jdbc:postgresql://localhost:5432/app"
```

4. Генерируем отчёт по итогам тестирования с помощью Allure. Отчёт автоматически откроется в браузере с помощью команды в терминале:

```
./gradlew allureServe
```

