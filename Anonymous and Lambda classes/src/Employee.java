/*
 *  Name : Abishek Bupathi
 */


// Abstract base class Employee.

import org.joda.money.Money;
import java.time.LocalDate;

public abstract class Employee {

    private String firstName;
    private String lastName;

    private static int ctr=1827; // ctr is used to help assign unique id to the employees

    private LocalDate joinDate;

    private Money taxCredits;

    int employeeId;
    // constructor
    public Employee(String first, String last, LocalDate joinDate, Money taxCredits) {
        firstName = first;
        lastName = last;
        employeeId = ctr++; // assigning unique incrementing id to the employees
        this.joinDate = joinDate;
        this.taxCredits = taxCredits;
    }

    // get first name
    public String getFirstName() {
        return firstName;
    }

    // get last name
    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return firstName + ' ' + lastName;
    }

    // get Employee ID
    public int getEmployeeId(){
        return employeeId;
    }

    // get Joining date
    public LocalDate getJoinDate(){
        return joinDate;
    }

    // get the tax credits
    public Money getTaxCredits() {
        return taxCredits;
    }

    // abstract method earnings() is used to calculate each employee's monthly earnings and to throw error when the earning is less than $100
    public abstract Money earnings( taxCalculator calc ) throws LowWageException;
}
