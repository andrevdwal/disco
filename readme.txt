This repo consists of two applications:

1) andrevdwal - This is the server maven application hosting the API. I used Eclipse.
2) client-app - This is an index.html file acting as a basic web client for the rest API

Task outline and comments:

1.	Persist the Graph into an in-memory database

The application uses Derby (embedded). Data takeon happens on startup with the schema.sql and import.sql scripts. 


2.	Read the file and import it into the DB

I could have read the provided *.xlsx file on startup and imported it but time constraints did not allow me to going down this avenue. I believe
there is an Apache library for this.


3.	Expose the database using a RESTful Webservice

http://localhost:8080/api/planets
http://localhost:8080/api/routes


4.	Implement the algorithm

I can now implement Djikstra's algorithm from memory on a piece of paper. Thank you.


5.	Expose the algorithm using a Document Literal Web service

http://localhost:8080/api/distance/calculate

For documenting the API I used Swagger:
http://localhost:8080/swagger-ui/index.html


6.	Create a front end to capture the source and destination and then print the shortest path

Front end is an index.html file that can be opened in the browser. It assumes the server URL is http://localhost:8080/

