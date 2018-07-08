# BikeStations_Microservice

The app to renting bikes.

<b>Endpoint to renting:</b> PATCH/api/stations/borrow/{rentStationId}/{UserId}/{bikeSerialNumber}<br/>
<b>Endpoint to giving a bike back:</b> PATCH/api/stations/gback/{backStationId}/{UserId}

BUILD'n'RUN project:

- For Unix:

1) Open a project's main folder in a terminal.
2) Use a command: "./mvnw spring-boot:run" .

<br/>

- For Windows:

1) Open a project's main folder in a console.
2) Use a command: ".\mvnw.cmd spring-boot:run" .