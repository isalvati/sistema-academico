# sistema-academico
TCC Puc Minas - Arquitetura de Software Distribuído

### Dependências
Para rodar é necessário ter instalado:

`Java 1.8`
`Maven`
`Docker`
`Docker-compose`

## Criar o banco de dados local usando docker

#### Para rodar o banco de dados da aplicação em um docker deve seguir os passos:

Na pasta docker temos um docker-compose, `docker-compose-base.yml`, para criar a imagem e ligar um container rodar no diretório docker o comando:

`docker-compose --project-name base -f docker-compose-base.yml up -d`

Rodar script para criar usuário base_user para acesso da aplicação na base de dados

`cat create_user.sql | docker exec -i <CONTAINER ID> psql -U postgres db_sistema_academico`

#### O container pode ser gerenciado com os comandos básicos do docker.
Listar os containers, o usado por essa aplicação tem o nome: `base` o `CONTAINER ID` que podem ser usados nos demais comandos.

`docker container ls -a`

Para a execução do container

`docker stop <CONTAINER ID>`

Liga o contaniner

`docker start <CONTAINER ID>`

#### Para conectar ao banco de dados usar o comando:

`docker exec -it <CONTAINER ID> psql -U base db_sistema_academico PGPASSWORD='admin'`


