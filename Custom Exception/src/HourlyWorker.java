/*
 *  Name : Abishek Bupathi
 */


// Definition of class HourlyWorker

import org.joda.money.Money;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

public final class HourlyWorker extends Employee {

    private Money wage; // wage per hour
    private double hours; // hours worked for week

    // constructor for class HourlyWorker
    public HourlyWorker(String first, String last,
            Money wagePerHour, double hoursWorked, LocalDate joinDate) {
        super(first, last, joinDate); // call superclass constructor
        setWage(wagePerHour);
        setHours(hoursWorked);
    }

    // Set the wage
    public void setWage(Money wagePerHour) {
        wage = (wagePerHour.isPositive() ? wagePerHour : Money.parse("0"));
    }

    // Set the hours worked
    public void setHours(double hoursWorked) {
        hours = (hoursWorked >= 0 && hoursWorked < 168
                ? hoursWorked : 0);
    }

    // The earnings() methods returns the total earning of Hourly worker for a month and also throws LowWageException when the earnings is less than $100
    public Money earnings() throws LowWageException{

        // Calculating monthly earning for Hourly worker
        Money totalEarnings = wage.multipliedBy(hours, RoundingMode.UNNECESSARY).multipliedBy(4);

        // Checking whether the employee had worked more than 5 years
        if (Period.between(getJoinDate(), LocalDate.now()).getYears() >= 5) {
            // $200 bonus
            totalEarnings = totalEarnings.plus(Money.parse("USD 200"));
        }

        // checking if the total earning is less than $100
        if(totalEarnings.isLessThan(Money.parse("USD 100"))){
            // throwing low wage exception with the info of calculated earnings
            throw new LowWageException("Calculated earning: $"+totalEarnings.getAmount());
        }

        return  totalEarnings;

    }

    // get hourly worker's name
    public String toString() {
        return "Hourly worker: " + super.toString();
    }
}
