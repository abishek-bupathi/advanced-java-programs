import org.joda.money.Money;
import java.math.RoundingMode;

// Implementation of the taxCalculator interface
public class TaxCalculator implements taxCalculator{

    /*
     * Function name : calculateTax
     * Parameters    : - earnings   : total earning of the employee
     *                 - taxCredtis : tax credits for the employee
     * Returns       : Calculated tax amount
     * Description   : Implementation of calculateTax method from the interface
     */
    @Override
    public Money calculateTax(Money earnings, Money taxCredits) {
        return earnings.multipliedBy(0.2, RoundingMode.UNNECESSARY).minus(taxCredits);
    }
}
