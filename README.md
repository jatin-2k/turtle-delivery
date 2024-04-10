# README

## Project Setup

This project uses Java, Spring Boot, and Gradle. Make sure you have the following installed:

- Java 17 or later
- Gradle 7.0 or later
- IntelliJ IDEA 2023.3.6 or later

## Project Includes

- Support for multiple delivery persons.
- Support for more than two restaurants and customers
- Documentation with Swagger
- Unit Tests
- Readable & Modular Code

## Project Assumptions

- We are getting order pickup locations {restaurants}, order drop off locations {customers}, delivery person location, delivery person id in inputs.
- Location inputs are on latitude and longitude basis.
- Speed of each delivery person is 20 km/h.
- R1, C1, ... are different for each delivery person in the order of the order assigned to them.

## Steps to Run the Application

1. **Clone the repository**

   Open Terminal and run the following command:

   ```
   git clone https://github.com/jatin-2k/turtle-delivery.git
   ```

2. **Open the project**

   Open IntelliJ IDEA and select `Open`. Navigate to the directory where you cloned the repository and select the project.

3. **Build the project**

   In IntelliJ IDEA, open the Gradle tab on the right side of the window. Expand `Tasks > build` and double-click on `build`. This will build the project and create a `.jar` file in the `build/libs` directory.

4. **Run the application**

   In the terminal, navigate to the `build/libs` directory and run the following command:

   ```
   java -jar <jar-file-name>.jar
   ```

   Replace `<jar-file-name>` with the name of the `.jar` file that was created when you built the project.

5. **Access the application**

   The application should now be running at `http://localhost:8080`.
   You should be able to access swagger documentation at `http://localhost:8080/swagger-ui/index.html#/`'

## How to Test the Application

- **POST /api/v1/deliveries/Aman**

  This endpoint is used to Assign orders to 'Aman'. The request body should contain the order details including the pickup location / restaurants, drop off location / customers, and delivery person location.

  Example request body:

  ```json
   {
   "restaurantLocations": [
      {
      "latitude": 77.614852,
      "longitude": 12.932464
      },
      {
      "latitude": 77.616333,
      "longitude": 12.932459
      }
   ],
   "customerLocations": [
      {
      "latitude": 77.617955,
      "longitude": 12.932458
      },
      {
      "latitude": 77.618934,
      "longitude": 12.932459
      }
   ],
   "deliveryPersonLocation": {
      "latitude": 77.611696,
      "longitude": 12.932575
      }
   }

  ```

- **GET /api/v1/deliveries/Aman/route**

    This endpoint is used to get the route for delivery person 'Aman'. It returns the order in which the delivery person should pick up and drop off the orders.
    
    Example response:
    
    ```json
    [
    "Go to R1 at 77.614852,12.932464",
    "Go to R2 at 77.616333,12.932459",
    "Go to C1 at 77.617955,12.932458",
    "Go to C2 at 77.618934,12.932459"
    ]
    ```