
public class Discount {

    String offerOnItem;
    String discountOnItem;
    int minimumItemBuy;
    double discountedValue;
    int discountedPercentage;
    
    /**
     * Default constructor to initialise the discount values of the given list of items 
     * @param offerOnItem
     * @param discountOnItem
     * @param minimumItemBuy
     * @param discountedValue
     * @param discountedPercentage
     */
    public Discount(String offerOnItem, String discountOnItem, int minimumItemBuy, double discountedValue, int discountedPercentage){
        this.offerOnItem = offerOnItem;
        this.discountOnItem = discountOnItem;
        this.minimumItemBuy = minimumItemBuy;
        this.discountedValue = discountedValue;
        this.discountedPercentage = discountedPercentage;
    }
}