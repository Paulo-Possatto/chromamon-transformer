# [CHROMAMON] Transformer Service

Transformer service used by the whole Chromamon project.

## Tools Used

- PostgreSQL
- Kafka
- Actuator
- Flyway
- OAuth2
- Validator
- Prometheus
- Lombok
- JPA
- Open API
- Mapstruct
- Testcontainers
- Spring Web

## Variables

You can set the variables the way you want to, you just need to set a .env file followed by the environment that the application will be deployed, so:

- **.env.local**: For local environment variables.
- **.env.dev**: For development/test environment variables.
- **.env.qa**: For quality assurance environment variables.
- **.env.prod**: For production environment variables (careful with this one).

Each .env file must be stored under the [/envs](/envs) directory in the project root and can have the following variables (must have for production):

- **POSTGRES_HOST**: The Postgres database host, where the localhost is the default value;
- **POSTGRES_PORT**: The port in which the database is available, the default value is 5432;
- **POSTGRES_DB**: The custom database name, where the default value is "transformers";
- **POSTGRES_USERNAME**: The username to connect to the database, the default value is "postgres".
- **POSTGRES_PASSWORD**: The password used to connect to the database, where the default value is "password" (prod environment does not have a default value, leave it blank and an exception will be thrown).
- **HIKARI_MAX_POOL**: Defines the maximum number of connections to the database at the same time. The default value is 10.
- **HIKARI_MIN_IDLE**: Defines the minimum amount of idle connections. The default value is 5.
- **HIKARI_CONN_TIMEOUT**: Defines how long (in ms) a thread will wait for a connection to be stablished. De default value is 30000 miliseconds (or 30 seconds).
- **DDL_AUTO**: Controls how the database schema is managed automatically. Those can be:
  - *none* (can be used in production)
  - *validate* (can be used in production)
  - *update*
  - *create*
  - *create-drop*
- **SHOW_SQL**: Boolean that shows the SQL Query when used in the database. Default value is "true", except in production.
- **LOG_LEVEL**: The log level in root level, the default value is "INFO"
- **LOG_LEVEL_WEB**: The log level for the service level, the default value is "DEBUG".
- **LOG_LEVEL_HIBERNATE**: The log level for the hibernate level, the default value is "INFO".

What defines which .env file will be used is the following variable in the docker-compose under the environment property:

- **SPRING_PROFILES_ACTIVE**: The profile used for the environment (prod, qa, dev) where the "local" value is default;

### Template

#### .env

```text
POSTGRES_HOST=<host_uri: string>
POSTGRES_PORT=<port: integer>
POSTGRES_DB=<database_name: string>
POSTGRES_USERNAME=<user_name: string>
POSTGRES_PASSWORD=<password: string>
HIKARI_MAX_POOL=<pool_size: integer>
HIKARI_MIN_IDLE=<idle_size: integer>
HIKARI_CONN_TIMEOUT=<timeout: integer>
DDL_AUTO=<command: string>
SHOW_SQL=<value: boolean>
LOG_LEVEL=<level: string>
LOG_LEVEL_WEB=<level: string>
LOG_LEVEL_HIBERNATE=<level: string>
```

You can get the template [here](envs/.env.template), just create the .env files as previously said.

#### docker-compose.yaml

```yaml
services:
  transformer:
    build:
      context: .
    ports:
      - "8080:8080"
    environment:
        - SPRING_PROFILES_ACTIVE=dev # Uses the ..env.dev variables
    depends_on:
      db:
        condition: service_healthy
    networks:
      - chroma-network

  db:
     image: postgres
     restart: always
     user: postgres
     volumes:
       - db-data:/var/lib/postgresql/data
     environment:
       - POSTGRES_DB=${POSTGRES_DB}
       - POSTGRES_PASSWORD=${POSTGRES_PASSWORD}
       - POSTGRES_USER=${POSTGRES_USERNAME}
     expose:
       - 5432
     healthcheck:
       test: [ "CMD", "pg_isready" ]
       interval: 10s
       timeout: 5s
       retries: 5

volumes:
  db-data:

networks:
  chroma-network:
    driver: bridge

```

Setting the **SPRING_PROFILES_ACTIVE** value to "dev" will use the .env.dev variables, set "qa" to use the .env.qa variables and set to "prod" to use the .env.prod variables.

## License

This service is licensed by the [GNU GPL_v3 License](./LICENSE).
