import java.util.ArrayList;
import java.util.HashMap;

public class DiscountsHelper {

    ArrayList<Discount> discounts;
    PriceHelper pricehelper;
    
    /**
     * Default constructor will initialise the values of discounted items
     */
    public DiscountsHelper(){
        this.pricehelper = new PriceHelper();
        this.discounts = new ArrayList<Discount>();
        
        // adding the discounted item and offer item in the arraylist
        this.discounts.add(new Discount("Apples", "Apples", 1, this.pricehelper.getGivenItemPrice("Apples") * 0.10, 10));
        this.discounts.add(new Discount("Soup", "Bread", 2, this.pricehelper.getGivenItemPrice("Bread") * 0.5, 50));
    }
    /**
     * Returns list of all the discounted items
     * @return
     */
    public ArrayList<Discount> getDiscountedItemList(){
        return this.discounts;
    }
}