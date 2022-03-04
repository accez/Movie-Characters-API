# Movie Characters API

Third Java assignment for Noroff winter 2022. Task is to create a Java Spring application to give the user functionality
to handel and look through a movie database.

## Description
The task is to create a RESTfull api using Java Spring to handle and use a postgres database seeded with movie data.

The database stores three different object movie, character and franchise.

Relations between this are that a movie can have many characters and a character can be in many movies, and a movie can
be in a franchise but a franchise can have many movies.

## Getting Started

### Dependencies
* JDK (to run project in IDE)
* Java IDE (IntelliJ of any other modern Java IDE)
* Postman
* Postgres running locally on port 5432

### Installing

* Clone project
* Open project in IDE and build

API Test

* Open Postman
* Under Collections import and enter https://www.getpostman.com/collections/f672b5e16f7035390446
* Double click folder and choose what base url to use
* Run Collection by hitting Run in folder or hit ... at end of Collection name and hit Run collection

or
* Navigate to [Swagger-UI link](https://movie-characters-java-noroff.herokuapp.com/swagger-ui/index.html)
* Right click method to test
* Hit execute and add property values to test with

### Executing program
- In ide run project
- Navigate to http://localhost:5000/home

#### Heruko

To run application from Heroku:
[Heroku base url](https://movie-characters-java-noroff.herokuapp.com)

## Authors

Gustav Eklund Kavtaradze [@meckan]

Simon Palmgren Arvidsson [@accez]
