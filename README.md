## Console commands used to start a container postgreSQL:

`docker run --name lesson-6-pg-13.3 -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=liga2021 -e POSTGRES_DB=ligadb -d postgres:13.3`