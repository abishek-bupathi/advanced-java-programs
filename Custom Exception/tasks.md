##Tasks

-[X] Create a Local date variable to store employee joining date

-[X] Change variable type double to money for all the variables handling with money   

-[X] Create a *static* variable to store incremental employee ID that is assigned automatically

-[X] Create an array of Employee variables to store references to the various employee objects

-[X] Using a loop
   - Calculate pay roll for each employee (processed once a month)
   - Add £200.00 bonus to employee's payroll is joining date if more than 5 years from the current date

-[X] Earnings() method to throw a user defined Exception called LowWageException
   - If the total earnings for an employee is less than €100 for the month
   - The exception should have a message with
       - employee's name
       - the calculated earnings
       - short description of the problem
   - Modify the test class to handle exceptions

-[X] Testing
   - When an exception is encountered in calculating an employee's earnings, the Test class should print out the error message and continue as normal with the next employees
   - change the Test class so that one of the employees will have low earnings which will cause the exception to he thrown 
