# TitleBot

## Project Description
 - Create a web form that allows a user to type in a url to any website and have it then output the Title and favicon of that website on the same page.
 - This project was built using springboot for the java backend service and react for the frontend client service. The database in use is mysql to store data.

## Requirements
 - Use React for the front end
 - The backend should be RESTful

#### Swagger-Ui endpoint for this project can be accessed with this endpoint
- http://localhost:8080/swagger-ui/index.html#/

## API Endpoints
- /saveTitle
- /getTitlesByUser
- /getAllUrls
- /getAllTitles
- /deleteTitleById

## How to set up and run the application
 - Install Docker on your computer if it is not already installed. You can download it from the official website: https://www.docker.com/products/docker-desktop.

 - Clone the TitleBot project from the repository
   - [TitleBot](https://github.com/jjblues86/TitleBot)

 - Navigate to the TitleBot directory using the terminal.

 - Run the following command in the terminal to start the application:
   - `docker-compose build` 
   - `docker-compose up` 

 - Wait for Docker to download the necessary images and build the application. This may take several minutes.

 - Once the build process is complete, you can access the application by opening a web browser and entering the URL: http://localhost:3000.

 - To stop the application, run the following command in the terminal:
   - `docker-compose down`

## Libraries, frameworks
 - Springboot
 - React
 - React Bootstrap
 
