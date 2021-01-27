/*
 *  Name : Abishek Bupathi
 */


// Boss class derived from Employee.

import org.joda.money.Money;
import java.time.LocalDate;
import java.time.Period;

public final class Boss extends Employee {

    private Money weeklySalary;

    // constructor for class Boss
    public Boss(String first, String last, Money salary, LocalDate joinDate, Money taxCredits) {
        super(first, last, joinDate, taxCredits); // call superclass constructor
        setWeeklySalary(salary);
    }

    // set Boss's salary
    public void setWeeklySalary(Money salary) {
        weeklySalary = (salary.isPositive() ? salary : Money.parse("0"));
    }

    // The earnings() methods returns the total earning of Boss for a month and also throws LowWageException when the earnings is less than $100
    public Money earnings(taxCalculator calc) throws LowWageException{

        // Calculating monthly earning for Boss
        Money totalEarnings = weeklySalary.multipliedBy(4);

        // calulating the total earning after deduction taxes
        totalEarnings = totalEarnings.minus(calc.calculateTax(totalEarnings, getTaxCredits()));

        // Checking whether the employee had worked more than 5 years
        if (Period.between(getJoinDate(), LocalDate.now()).getYears() >= 5) {
            // $200 bonus
            totalEarnings = totalEarnings.plus(Money.parse("USD 200"));
        }

        // checking if the total earning is less than $100
        if(totalEarnings.isLessThan(Money.parse("USD 100"))){
            // throwing low wage exception
            throw new LowWageException("Calculated earning: $"+totalEarnings.getAmount());
        }

        return  totalEarnings;
    }

    // get String representation of Boss's name
    public String toString() {
        return "Boss: " + super.toString();
    }
} // end class Boss
