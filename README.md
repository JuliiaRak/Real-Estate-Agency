# Real estate agency
This project is a small console application for managing real estate information and real estate agency clients.

## Description
A real estate agency allows users to add, edit, and view real estate information, as well as transact with clients and agreements.

## DB scheme
(https://github.com/JuliiaRak/Real-Estate-Agency/blob/jrak2/src/main/resources/db_scheme.jpg)
<img width="982" alt="Знімок екрана 2023-09-13 о 12 44 05" src="https://github.com/JuliiaRak/Real-Estate-Agency/blob/jrak2/src/main/resources/db_scheme.jpg">

## Installation requirements
* Java SDK
* MySQL
* Maven (for building the project)

## Running with docker

Firstly, make sure that you have specified `config.docker.properties` as properties at `mybatis-config.xml`

Build docker compose with `docker compose build` command

Run docker compose in interactive mode for 'api' service with command `docker compose run --rm api`
