# docker-spring-boot
В данном проекте показан пример сборки образа и запуска контейнера Docker c Spring Boot приложением.

## Сборка образа

Для сборки образа подключаем и настраиваем gradle плагин:
 
```
plugins {
    ...
    id 'com.palantir.docker-run' version "0.19.2" // плагин для docker https://github.com/palantir/gradle-docker
    ...
}

// конфигурация сборки образа
docker {
    name "${project.name}"  // название образа
    tags "${version}"       // дополнительный тег (добавляет отдельную задачу)
    files jar.archivePath   // передаем jar в контекст
    buildArgs([JAR_FILE: "${jar.archiveName}"]) // передаем имя jar в качестве аргумента сборки
    noCache true // опция --no-cache
}
```
После подключения плагина появятся задачи:
`gradlew tasks`

```
Docker tasks
------------
docker - Builds Docker image.
dockerClean - Cleans Docker build directory.
dockerfileZip - Bundles the configured Dockerfile in a zip file
dockerPrepare - Prepares Docker build directory.
dockerPush - Pushes named Docker image to configured Docker Hub.
dockerPush1.0 - Pushes the Docker image with tag '1.0' to configured Docker Hub
dockerTag - Applies all tags to the Docker image.
dockerTag1.0 - Tags Docker image with tag '1.0'
```

Заускаем сборку образа `gradlew build docker`

## Запуск контейнера

Для запуска контейнера подключаем и настраиваем gradle плагин:

```
plugins {
    ...
    id 'com.palantir.docker-run' version "0.19.2" // плагин для docker https://github.com/palantir/gradle-docker
    ...
}

// конфигурация запуска контейнера
dockerRun {
    name "${project.name}-inst" // опция --name
    image "${project.name}" // имя образа
    ports '8080:8080' // опция -p
    daemonize true // опция -d
    clean true // опция --rm
}
```

После подключения плагина появятся задачи: `gradlew tasks`

```
Docker Run tasks
----------------
dockerNetworkModeStatus - Checks the network configuration of the container
dockerRemoveContainer - Removes the persistent container associated with the Docker Run tasks
dockerRun - Runs the specified container with port mappings
dockerRunStatus - Checks the run status of the container
dockerStop - Stops the named container if it is running
```
Запускаем контейнер `gradlew dockerRun`;  останавливаем контейнер `gradlew dockerStop`

## Передача ENV в application.yaml
Механизм Spring properties поддерживает передачу в конфиг переменных окружения, в примере мы передаем `JAR_FILE` из контейнера:
```
app:
  file: ${JAR_FILE}
```