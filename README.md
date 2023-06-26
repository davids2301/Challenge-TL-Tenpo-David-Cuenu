# Sitios de referencia
A continuación se listan sitios de referencia donde se podrá documentar acerca de las arquitecturas limpias y concretamente podrá profundizar en la arquitectura hexagonal, para ender el porque la estructura del presente arquetipo.
    
    1. https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/?utm_content=buffer01772&utm_medium=social&utm_source=twitter.com&utm_campaign=buffer

    2. https://cloudappi.net/introduccion-a-la-arquitectura-hexagonal/

    3. https://medium.com/@mvtercero85/es-un-hex%C3%A1gono-un-puerto-una-cebolla-no-es-una-arquitectura-2282ac460c60

![alt text](https://docs.google.com/drawings/d/e/2PACX-1vQ5ps72uaZcEJzwnJbPhzUfEeBbN6CJ04j7hl2i3K2HHatNcsoyG2tgX2vnrN5xxDKLp5Jm5bzzmZdv/pub?w=960&h=657)


# Comandos para ejecutar el arquetipo
    Clonar proyecto $: git clone http://gitlab.tenpo.com/sbc-compras/backend/compras-desacoplamiento-spring-backend.git
    
    Compilar proyecto $: mvn clean install

    Ejecutar proyecto $: mvn spring-boot:run

## Pre Requisitos

    Cree las siguientes variables de entorno en su maquina local
        Nombre Variable: SERVER_PORT :: Valor Variable: 8080
        Nombre Variable: SPRING_DATASOURCE_USERNAME :: Valor Variable: "Su usuario de base de datos"
        Nombre Variable: SPRING_DATASOURCE_PASSWORD :: Valor Variable: "Su clave de usuario de base de datos"
        Nombre Variable: SPRING_DATASOURCE_URL Valor :: Variable: tenpoliferaydev-negocioaurorast-auroradbcluster-15yssl724vwn2.cluster-ct8zn6npetz3.us-east-1.rds.amazonaws.com
    
    Capas del proyecto segun orden de compilacion y dependencia
        1. Domain
        2. Aplication
        3. Infrastructure
    
# Importante

Muy importante recordar que la capa que se debe desplegar para que el proyecto funcione tanto localmente como en ambiente es la capa Infrastructure
