# Sitios de referencia
A continuación se listan sitios de referencia donde se podrá documentar acerca de las arquitecturas limpias y concretamente podrá profundizar en la arquitectura hexagonal, para ender el porque la estructura del presente arquetipo.

# Ubicacion del proyecto Challenge-TL-Tenpo-David-Cuenu
El proyecto se puede encontrar en GitHub en la url  que se muestra a continuacion:
- URL: https://github.com/davids2301/Challenge-TL-Tenpo-David-Cuenu

# Documentese acerca de las arquitecturas limpias o Clean Architecture de su nombre en ingles
#
#
## Acontinuacion algunos enlaces que te sera de utilidad
#

##### DDD, Hexagonal, Onion, Clean, CQRS, … How I put it all together: [ https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/?utm_content=buffer01772&utm_medium=social&utm_source=twitter.com&utm_campaign=buffer]( https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/?utm_content=buffer01772&utm_medium=social&utm_source=twitter.com&utm_campaign=buffer)

##### Introducción a la Arquitectura Hexagonal: [https://cloudappi.net/introduccion-a-la-arquitectura-hexagonal/](https://cloudappi.net/introduccion-a-la-arquitectura-hexagonal/)

##### ¿Es un hexágono, un puerto, una cebolla…? No, ¡es una arquitectura!: [https://medium.com/@mvtercero85/es-un-hex%C3%A1gono-un-puerto-una-cebolla-no-es-una-arquitectura-2282ac460c60](https://medium.com/@mvtercero85/es-un-hex%C3%A1gono-un-puerto-una-cebolla-no-es-una-arquitectura-2282ac460c60)
#
#
#
### Iluestrativo de la arquitectura hexagonal utilizada para este proyecto
#
![alt text](https://docs.google.com/drawings/d/e/2PACX-1vQ5ps72uaZcEJzwnJbPhzUfEeBbN6CJ04j7hl2i3K2HHatNcsoyG2tgX2vnrN5xxDKLp5Jm5bzzmZdv/pub?w=960&h=657)
#
## Pre Requisitos
    Cree las siguientes variables de entorno en su maquina local
        1. Tener instalado java 11 este puede ser descardado del sitio web oficila del Oracle: https://www.oracle.com/co/java/technologies/javase/jdk11-archive-downloads.html
        2. Tener instalado apache maven version 3 este puede ser descardado del sitio web oficila del Apache Software Foundation: https://maven.apache.org/docs/3.6.3/release-notes.html
        3. Tener una base de datos PostgreSql previamente instalada, se puede instalar con docker utilizando los comando que listaremos a continuacion o sr puede descargar el instalador para su sistema operativo directamente del sitio web de Postgres, https://www.postgresql.org/download/
        4. Tener una base de datos Redis previamente instalada, se puede instalar con docker utilizando los comando que listaremos a continuacion o sr puede descargar el instalador para su sistema operativo directamente del sitio web de Redis, https://redis.io/download/
#
# NOTA (IMPORTANTE)
* Si desea subir todo el proyecto utilizando Docker Compose deberia omitir el paso de creacion de las bases de datos tanto de forma nativa como dockerizada, Ya que el mismo agente de Docker Compose configurara todo lo necesario
  ![alt text](https://www.lucushost.com/blog/wp-content/uploads/2020/03/mensaje-advertencia-pagina-web.png)

# Para una instalacion nativa de las bases de datos
###### Enlace para descargar PostgreSql: [https://www.postgresql.org/download/](https://www.postgresql.org/download/)
![alt text](https://kinsta.com/wp-content/uploads/2022/04/postgres-logo.png)
###### Enlace para descargar Redis: [https://www.postgresql.org/download/](https://redis.io/download/)
![alt text](https://miro.medium.com/v2/resize:fit:1400/1*-VfZ76XK11_1fFUnKLiXSA.png)
# 
# Para subir las base de datos utilizando el motor de docker
### Comando para subir base de datos Postgres mediante Docker:
**Comando $:** docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=tenpo2023 -e POSTGRES_USER=tenpo -e POSTGRES_DB=TENPO_API_REST_SUM --name postgres-server postgres
![alt text](https://github.com/davids2301/images/blob/main/creacion_db_postgres.png?raw=true)
### Comando para subir base de datos Redis mediante Docker:
**Comando $:** docker run -p 6379:6379 --name redis-server -d redis
![alt text](https://github.com/davids2301/images/blob/main/creacion_db_redis.png?raw=true)
#
# Pasos para ejecutar este proyecto con en  ambiente local
#
#### 1. Clonar proyecto:
**Comando $:** git clone https://github.com/davids2301/Challenge-TL-Tenpo-David-Cuenu.git
![alt text](https://github.com/davids2301/images/blob/main/clonacion_proyecto.png?raw=true)
#### 2. Instalar el proyecto:
**Comando $:** mvn clean install
![alt text](https://github.com/davids2301/images/blob/main/compilacion_exitosa.png?raw=true)
#### 3. Ejecutar el servicio mock de porcentaje en una  nueva ventana:
**Comando $:** mvn spring-boot:run -pl tenpo-rest-api-percentage/infrastructure
![alt text](https://github.com/davids2301/images/blob/main/ejecucion_percentage_service.png?raw=true)

#### 4. Verificar que el servicio de porcentaje esta arriba a traves del swagger ui
**URL:** http://localhost:8081/swagger-ui/#
![alt text](https://github.com/davids2301/images/blob/main/swagger_percentage.png?raw=true)
#### 5. Ejecutar el servicio mock de suma en una  nueva ventana:
**Comando $:** mvn spring-boot:run -pl tenpo-rest-api-sum/infrastructure
![alt text](https://github.com/davids2301/images/blob/main/ejecucion_sum_service.png?raw=true)
**URL:** http://localhost:8080/swagger-ui/#
#### 6. Verificar que el servicio de suma esta arriba a traves del swagger ui
![alt text](https://github.com/davids2301/images/blob/main/swagger_sum_service.png?raw=true)
#
## Pre Requisitos

    Cree las siguientes variables de entorno en su maquina local
        1. Tener instalada la version mas reciente que pueda conseguir de Docker, este lo puede descargar directamente del sitio web de Docker, https://www.docker.com/
![alt text](https://curity.io/images/resources/operate/tutorials-docker.png)
#
# Pasos para ejecutar este proyecto con Docker Compose
![alt text](https://tcude.net/content/images/2022/01/MainImage-2.jpeg)
#### 1. Clonar proyecto:
**Comando $:** git clone https://github.com/davids2301/Challenge-TL-Tenpo-David-Cuenu.git
![alt text](https://github.com/davids2301/images/blob/main/clonacion_proyecto.png?raw=true)
#### 1. Ejecutar comando de compilacion para subir los contenedores todo en una linea:
**Comando $:** docker-compose up --build
#
# NOTA (IMPORTANTE)
* Una vez que los contenedores esten arriva puede verificar los servicios accediendo al swagger a traves de las siguientes URL
  ![alt text](https://www.lucushost.com/blog/wp-content/uploads/2020/03/mensaje-advertencia-pagina-web.png)
#
#### 4. Verificar que el servicio de porcentaje esta arriba a traves del swagger ui
**URL:** http://localhost:8081/swagger-ui/#
![alt text](https://github.com/davids2301/images/blob/main/swagger_percentage.png?raw=true)
**URL:** http://localhost:8080/swagger-ui/#
#### 6. Verificar que el servicio de suma esta arriba a traves del swagger ui
![alt text](https://github.com/davids2301/images/blob/main/swagger_sum_service.png?raw=true)

