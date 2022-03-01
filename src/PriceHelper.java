import java.util.HashMap;

public class PriceHelper{

	// Hashmap to store all unique items and price
    HashMap<String, Double> prices;

    /**
     * default constructor to initialize the Hashmap prices
     * Load the items and the prices
     */
    public PriceHelper(){
        prices = new HashMap<String, Double>();
        prices.put("Apples", 1.00);
        prices.put("Soup", 0.65);
        prices.put("Milk", 1.30);
        prices.put("Bread", 0.80);
    }
    
    /**
     * Checks whether the given item in argument contains in the item list
     * Returns true or false
     * @param item
     * @return true or false
     */
    public boolean contains(String item){
        return this.prices.containsKey(item);
    }
    
    /**
     * Gets the item price from the item list
     * @param item
     * @return price of the item if found
     */
    public double getGivenItemPrice(String item){
        if(prices.containsKey(item)){
            return prices.get(item);
        }else{
            return -1;
        }
    }
}