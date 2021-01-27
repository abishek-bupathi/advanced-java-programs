/*
 *  Name : Abishek Bupathi
 */


// Driver for Employee hierarchy

// Java core packages
import org.joda.money.Money;
import java.text.DecimalFormat;
import java.time.LocalDate;
// Java extension packages
import javax.swing.JOptionPane;

public class Test {

    // test Employee hierarchy
    public static void main(String args[]) {

        String output = "";

        Boss boss = new Boss("John", "Smith", Money.parse("USD 800.0"), LocalDate.of(2010, 3, 15));

        // Creating an instance of commission worker with the parameters that would throw LowWageException
        CommissionWorker commissionWorker =
                new CommissionWorker(
                "Sue", "Jones", Money.parse("USD 20.0"), Money.parse("USD 0.2"), 5, LocalDate.of(2018, 5, 29));

        PieceWorker pieceWorker =
                new PieceWorker("Bob", "Lewis", Money.parse("USD 2.5"), 200, LocalDate.of(2013, 1, 1));

        HourlyWorker hourlyWorker =
                new HourlyWorker("Karen", "Price", Money.parse("USD 10.10"), 20, LocalDate.of(2020, 2, 8));

        DecimalFormat precision2 = new DecimalFormat("0.00");

        // Creating an array to store all the references of the employee class
        Employee employees[] = {boss, commissionWorker, pieceWorker, hourlyWorker};

        for (int i = 0; i < employees.length; i++) {

            // try block to execute the earnings() method that may throw LowWageException
            try {

                // getting the employee name and his/her earnings to print in the payroll
                output += employees[i].toString() +" (ID: "+employees[i].getEmployeeId() +") earned $"
                        + precision2.format(employees[i].earnings().getAmount()) + "\n";

            } catch (LowWageException ex){
                // Catch block to Print the info of the exception which LowWage exception is caught in the try block
                // Adding the exception details to print in the dialog
                output += "---------------------\n!!! Exception !!!\nEmployee ID: " + employees[i].getEmployeeId()+"\nEmployee name: "+ employees[i].getFirstName()+"\n"+ ex.getMessage()+ "\nDescription: The monthly wage for this employee is less than $100\n---------------------\n";
            }
        }

        JOptionPane.showMessageDialog(null, output,
                "Payroll", JOptionPane.PLAIN_MESSAGE);

        System.exit(0);
    }
} // end class Test
