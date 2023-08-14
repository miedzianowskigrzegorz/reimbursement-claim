**Business Trip Reimbursement Calculation Application - Read Me**

This repository contains a Java application for managing reimbursement of expenses after a business trip. The application has both end user and administrator views with different capabilities.

**Setting Up the Environment on Windows System:**

1. Ensure you have Java 11 installed on your system.
2. Install Maven if not already installed.
3. Clone this repository to your local machine using `git clone https://github.com/your-username/repo-name.git`.

**Building and Running the Application:**

1. Navigate to the root directory of the cloned repository.
2. Open a terminal/command prompt.
3. Run the following command to build the application: `mvn clean install`.
4. To run the application, execute main method from ReimbursementApp class.

**Running Tests:**

1. Open a terminal/command prompt.
2. Navigate to the root directory of the cloned repository.
3. Run the following command to execute tests: `mvn test`.

**Application Overview:**

- **End User View:**
- `http://localhost:8080/reimbursement`
  - Input the trip date.
  - Add receipts from the dropdown list to claim refunds (e.g., taxi, hotel, etc.).
  - Claim daily allowance by inputting the number of days or time range of the trip. Check the checkbox to disable specific days.
  - Claim car usage reimbursement by entering the driven distance if exist.
  - Total reimbursement amount is displayed.
  - Click the "Create" button.

- **Administrator View:**
- `http://localhost:8080/admin`
  - Input rates for daily allowance and mileage.
  - Configure rates for daily allowance and for car mileage.
  - Edit the list of available receipts.
  - Define reimbursement limits for various calculation results.

**Unit Testing:**

The application includes some unit tests to verify its logic using jUnit. Tests can be executed using the `mvn test` command.

**Front-End Technology:**

The front-end UI has been kept basic and simple for testing purposes. I used bootstrap.

**GitHub Repository:**

The code for this application is hosted on GitHub. You can access the repository at: [GitHub Repository Link](https://github.com/your-username/repo-name)

Feel free to explore, experiment, and enhance the application as needed!
