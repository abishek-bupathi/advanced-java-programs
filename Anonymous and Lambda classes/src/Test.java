/*
 *  Name : Abishek Bupathi
 */


// Driver for Employee hierarchy

// Java core packages
import org.joda.money.Money;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
// Java extension packages
import javax.swing.JOptionPane;

public class Test {

    // test Employee hierarchy
    public static void main(String[] args) {

        // Create an instance of Tax Calculator class which implement the taxCalculator interface
        TaxCalculator tax_class = new TaxCalculator();

        // Creating an anonymous class to implement the taxCalculator interface
        taxCalculator tax_anonymous = new taxCalculator() {
            @Override
            public Money calculateTax(Money earnings, Money taxCredits) {
                return earnings.multipliedBy(0.2, RoundingMode.UNNECESSARY).minus(taxCredits);
            }
        };

        // Creating Lambda function to use implement the taxCalculator interface
        taxCalculator tax_lambda = (earnings, taxCredits) -> {
            return earnings.multipliedBy(0.2, RoundingMode.UNNECESSARY).minus(taxCredits);
        };

        Boss boss = new Boss("John", "Smith", Money.parse("USD 800.0"), LocalDate.of(2010, 3, 15), Money.parse("USD 10.0"));

        // Creating an instance of commission worker with the parameters that would throw LowWageException
        CommissionWorker commissionWorker =
                new CommissionWorker(
                "Sue", "Jones", Money.parse("USD 20.0"), Money.parse("USD 0.2"), 5, LocalDate.of(2018, 5, 29), Money.parse("USD 15.0"));

        PieceWorker pieceWorker =
                new PieceWorker("Bob", "Lewis", Money.parse("USD 2.5"), 200, LocalDate.of(2013, 1, 1), Money.parse("USD 6.0"));

        HourlyWorker hourlyWorker =
                new HourlyWorker("Karen", "Price", Money.parse("USD 10.10"), 20, LocalDate.of(2020, 2, 8), Money.parse("USD 5.0"));


        // Creating an array to store all the references of the employee class
        Employee employees[] = {boss, commissionWorker, pieceWorker, hourlyWorker};

        // testing the earnings() method with class implementation
        test_earnings(employees, tax_class, "Payroll with Tax calculated using Class implementation");

        // testing the earnings() method with anonymous class
        test_earnings(employees, tax_anonymous, "Payroll with Tax calculated using Anonymous class");

        // testing the earnings() method with Lambda function
        test_earnings(employees, tax_lambda, "Payroll with Tax calculated using Lambda function");


        System.exit(0);
    }


    /*
     * Function name : test_earnings
     * Parameters    : - employees[]: Employee objects references array list
     *                 - tax        : taxCalculator interface implementations
     *                 - msg        : Mesaage to be print out as the window title
     * Returns       : None
     * Description   : The method is used to test different implementations of the taxCalculator interface
     */
    static void test_earnings(Employee employees[],taxCalculator tax, String msg){

        String output = "";
        DecimalFormat precision2 = new DecimalFormat("0.00");

        for (int i = 0; i < employees.length; i++) {

            // try block to execute the earnings() method that may throw LowWageException
            try {

                // getting the employee name and his/her earnings to print in the payroll
                output += employees[i].toString() +" (ID: "+employees[i].getEmployeeId() +") earned $"
                        + precision2.format(employees[i].earnings(tax).getAmount()) + "\n";

            } catch (LowWageException ex){
                // Catch block to Print the info of the exception which LowWage exception is caught in the try block
                // Adding the exception details to print in the dialog
                output += "---------------------\n!!! Exception !!!\nEmployee ID: " + employees[i].getEmployeeId()+"\nEmployee name: "+ employees[i].getFirstName()+"\n"+ ex.getMessage()+ "\nDescription: The monthly wage for this employee is less than $100\n---------------------\n";
            }
        }

        JOptionPane.showMessageDialog(null, output,
                msg, JOptionPane.PLAIN_MESSAGE);
    }
} // end class Test
