Zendesk Ticket Viewer

Application Overview:

This application is built for the Zendesk Coding Challenge following all the requirements. Frontend is designed using React and the server side code is developed in Spring Boot.

Requirements:

Zendesk is a customer service tool that allows the creation and management of support tickets. Your company needs you to build a Ticket Viewer that will:

	1. Connect to the Zendesk API
	2. Request all the tickets for your account
	3. Display them in a list
	4. Display individual ticket details
	5. Page through tickets when more than 25 are returned
  
Following need to be set up in order to set up the environment
  * Java
  * Maven
  * Node

Steps needed to run the code:
Backend:

Backend is needed to be set up inorder to make the API calls to Zendesk domain.

  1. Install the necessary software such as Java and Maven.
  2. Open the backend folder constisting of maven project and import this to some IDE such as Eclipse.
  3. Then, build the project which will fetch all the dependencies from maven central repository.
  4. This will install all the packages that are needed for the code to run.
  5. Then, run the project which will start the server and host it on "URL"

Frontend:

Once the server is up, Frontend code needs to be run

  1. We need to open the folder that contains the frontcode
  2. Open up the command prompt or terminal here
  3. Run the command - npm install to install all the necessary packages
  4. Once this is done, run the command - npm start
  5. This will start the server and host it on the url

Note:

In the application.properties in the backend code, we need to give the user specific details such as username, password and domain url

Example:

* domain = https://zcctest.zendesk.com/api/v2/tickets
* pagecount = 25
* user = testuser@gmail.com
* password = test123

Functionality:

Backend:

The backend contains the api that acts as interface between the frontend UI and the Zendesk APi services. It fetches the user information required for making the call from application.properties file and gets the data by connecting to Zendesk. On running the code, it exposes the endpoint /getAllTickets on http://localhost:8080.

Frontend:

The sole functionality of the UI code is to display the tickets. In order to fetch these tickets, the react code makes call to the backend. On success, it lists the tickets in the grid. At a time, 25 tickets will be visible to the user. There's a feasibility of getting next set of tickets by clicking on the ">" and "<" buttons. On clicking the particular ticket, additional details will be displayed in a popup window. If there's some issue with code, related error messages will be displayed in a pop up window.
