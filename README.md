## HBV202G-Lokaverkefni

This project is a simple library management system implemented in Java. The system allows users to borrow, return, and extend due dates for books. It is designed to manage books and library members.

## Maven Goals
mvn clean install: Clean the project and build it.

mvn javadoc:javadoc: Generate Javadoc documentation for the project.

mvn exec:java: Run the program using Maven.

mvn package: Package the project into a JAR file.

mvn site: Generate the project's site (including Javadoc and design documentation).


## How to Run

Follow the steps below to run the library system project.

1. Clone the Repository

Clone the project to your computer by running the following command in your terminal:

git clone <repository-url>

Replace <repository-url> with the actual URL of your GitHub repository.

2. Install Prerequisites

Install Maven

- If you don’t have Maven installed, download it from [Maven's official website](https://maven.apache.org/download.cgi) and follow the installation instructions for your operating system.
- Verify the installation of Maven by running: 
mvn -v

Install Java

- Ensure that you have Java 8 or later installed on your machine. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).
- Verify the installation of Java by running:
java -version

3. Navigate to the Project Directory

After cloning the repository, navigate into the project directory:

cd library-system

4. Build the Project

To build the project, run the following command in the terminal:

mvn clean install

This command will:
- Clean any previously compiled files.
- Download necessary dependencies.
- Compile the project.

5. Run the Program

After building the project, run the program by executing:

mvn exec:java

This will start the program and present a text-based user interface (TUI), where you can interact with the library system.


## Design Documentation
- For more detailed design information, including the UML class diagram and design patterns, check out the [design documentation](docs/design.md).
