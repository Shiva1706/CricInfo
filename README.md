# Cricket Live Score Dashboard

This project is a Cricket Live Score Dashboard that provides live match scores, data from previous matches, and the point table of current tournaments. The frontend is developed using Angular.js, while the backend APIs are powered by Spring Boot. The data is sourced from web scraping.

## Features

- **Live Match Scores:** Real-time scores for ongoing matches across all formats (T20, ODI, Test).
- **Previous Matches Data:** Historical data of previous matches.
- **Point Table:** Current tournament point tables.
- **Home Page:** A well-designed homepage that provides easy navigation and quick access to live scores and other features.

## Technologies Used

### Frontend
- Angular.js: For building the user interface.

### Backend
- Spring Boot: For developing RESTful APIs.

### Data Sourcing
- Web Scraping(JSoup): For extracting data from various cricket websites.

## Getting Started

### Prerequisites

- **Node.js** and **npm**: Required for Angular development. Download and install from [Node.js](https://nodejs.org/).
- **Java Development Kit (JDK)**: Required for Spring Boot. Download and install from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **Maven**: For building and managing the Spring Boot project. Download and install from [Maven](https://maven.apache.org/).
- **Git**: For version control. Download and install from [Git](https://git-scm.com/).

### Setup

#### Frontend

1. Clone the repository:
   ```bash
   git clone https://github.com/Shiva1706/CricInfo
   cd CrickInfo
2. Navigate to the Angular project directory:
   ```bash
   cd frontend
3. Install dependencies:
   ```bash
   npm install

4. Build the Angular project:
   ```bash
   ng build --prod

5. Start the Angular development server:
   ```bash
   ng serve

 The frontend will be available at http://localhost:4200.


#### Backend

1. **Navigate to the Spring Boot project directory:**
   ```bash
   cd backend

2. **Build the Spring Boot project:**
   ```bash
   mvn clean install
   
  This command will compile the project and package it into a JAR file. Make sure you have Maven installed and properly configured in your environment.

3. **Run the Spring Boot application:**
   ```bash
   mvn spring-boot:run

 This command will start the Spring Boot application. The backend APIs will be available at http://localhost:8080.


### Running the Application

After setting up both the frontend and backend, you can start using the CricInfo.
