# Client-Server Application. Client

## Overview

This is a client-server application designed with a three-tier architecture, leveraging suitable Java technologies and libraries, specifically the Spring Framework. 
The application interacts with three entities from a relational database, performing all CRUD operations with a many-to-many relationship, which results in four tables in the relational database. 
It utilizes Object-Relational Mapping (ORM) with MySQL as the database.

## Client Side

The client side is an interactive console application built with Spring Shell. It communicates with the RESTful web service provided by the server. 
The client enables users to perform all CRUD operations on at least one entity, execute additional queries from the data layer, and access other application logic functionalities.

## Server Side

[Server Repository Link](https://github.com/bohpob/server-startup)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/bohpob/cli-client-startup.git
   ```
2. Navigate to the client directory and run:
   ```bash
    cd SpringShellDemoClient/
      ./gradlew bootRun
   ```

You can invoke the `help` command within the Spring Shell console to view all available commands and functionalities, providing an easy way to explore the application's capabilities.
   
