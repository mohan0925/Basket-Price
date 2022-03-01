import java.util.*;

public class PriceBasket {

    ArrayList<String> itemsList;
    PriceHelper priceHelper;
    double total;
    double subTotal;
    HashMap<String, Integer> itemCount;

    /**
     * default constructor to initialize the values to the variables
     */
    public PriceBasket(){
        itemsList = new ArrayList<String>();
        priceHelper = new PriceHelper();
        total = 0;
        subTotal = 0.0;
        itemCount = new HashMap<String, Integer>();
    }
    /**
     * Adds given items in the given itemslist, updates total cost of the items, number of items in list
     * Returns the size of the itemslist 
     * @param item
     * @return
     */
    public int addItemsInItemList(String item){
    	
        itemsList.add(item);
        this.updateListTotal(this.getItemPrice(item));
        this.updateCount(item);
        return itemsList.size();
    }
    /**
     * Updates the count of the item in the itemslist
     * @param item
     */
    public void updateCount(String item){
        if(this.itemCount.containsKey(item)) {
            this.itemCount.put(item, this.itemCount.get(item) + 1);
        }else{
            this.itemCount.put(item, 1);
        }
    }
    /**
     * Returns the given item price,if item is present in the itemslist
     * @param item
     * @return
     */
    public double getItemPrice(String item){
        if(this.priceHelper.contains(item)){
            return this.priceHelper.getGivenItemPrice(item);
        }
        return 0;
    }
    /**
     * Updates the list total price as number of items being added in the itemslist
     * @param price
     */
    public void updateListTotal(double price){
        this.total += price;
    }
    /**
     * Returns the total price of the itemslist
     * @return
     */
    public double getTotal(){
        return this.total;
    }
    /**
     * Returns the subtotal of the itemslist
     * @return
     */
    public double getSubTotal(){
        return this.subTotal;
    }
    /**
     * Returns an integer value of the discounted price
     * @param value
     * @return
     */
    public int getUpdatedValue(double value){
        int v = (int) (value * 100);
        return v;
    }
    /**
     * Discount of all the items will be performed
     * Calculating the discount, updating the price, writting the output statement according to offers
     * @return
     */
    public ArrayList<String> applyDiscounts(){
    	
    	// Initialising the discounthelper class to access all the functions and perform operations
        DiscountsHelper discountshelper = new DiscountsHelper();
        
        // Fetching list of all the items passed through argument
        ArrayList<Discount> discounts = discountshelper.getDiscountedItemList();
        
        // Initialising the hashset to store all the unique items which has offers
        HashSet<String> offerApplied = new HashSet<String>();
        
        // Initialising the arraylist to store the relevant output message
        ArrayList<String> appliedDiscounts = new ArrayList<String>();
        
        // Initialise the variable to store the totaldiscount
        double totalDiscountValue = 0.0;

        for(int index = 0; index < discountshelper.discounts.size(); index++){
        	
        	// Gets the data present at given index from the discounts arraylist
            Discount discount = discounts.get(index);
            double discountForThis = 0.0;
            int value;
            
            // Checking if any items that has offer or discount is present or not in the hashset
            if(offerApplied.contains(discount.offerOnItem) == false && offerApplied.contains(discount.discountOnItem) == false){

            	// Checking the if the item count has item , which has offer and count of the item 
                if(this.itemCount.containsKey(discount.offerOnItem) && this.itemCount.get(discount.offerOnItem).intValue() >= discount.minimumItemBuy){
                    
                	// checking if the item having offer matches 
                	if(discount.discountOnItem.equals(discount.offerOnItem)) {
                		
                		// calculating the offer price 
                        discountForThis = ((int) this.itemCount.get(discount.offerOnItem) / discount.minimumItemBuy) * discount.discountedValue;
                        // updating the discounted price, i.e; removing the price from the total
                        this.updateListTotal(-1 * discountForThis);
                        // adding the item having the offer in the hashmap, where unique values will be added
                        offerApplied.add(discount.offerOnItem);
                        offerApplied.add(discount.discountOnItem);
                        
                        // Calculating the total discount made for the items
                        totalDiscountValue += discountForThis;
                        // updating the total price, i.e; removing the total discount of items from the total basket 
                        value = getUpdatedValue(discountForThis);
                        // if the total value greater than 0, then writting the output statement and storing in arraylist
                        if(value > 0){
                            appliedDiscounts.add(String.format("%s with %d%% off : -%dp ", discount.offerOnItem, discount.discountedPercentage, value));
                        }
                    }
                	// checking discounted item is present in the itemcount
                    else if (this.itemCount.get(discount.discountOnItem) > 0) {
                    	// fetching all the discounted and offer items count 
                        int offerCount = this.itemCount.get(discount.offerOnItem);
                        int discountItem = this.itemCount.get(discount.discountOnItem);
                        // fetching the minimum buy of the item
                        int minimumItemBuy = discount.minimumItemBuy;
                        
                        // iterating the discount items and calculating the discount value and checking minimum buy items
                        while(offerCount >= minimumItemBuy && discountItem > 0){
                        	// adding the discounted value
                            discountForThis += discount.discountedValue;
                            // removing the minimum buy items from number of items
                            offerCount -= minimumItemBuy;
                            // removing the discount item 
                            discountItem -= 1;
                        }
                        // updating the total by removing the discounted price from the basket total price
                        this.updateListTotal(-1 * discountForThis);
                        
                        // adding the total discount value for the basket
                        totalDiscountValue += discountForThis;
                        
                        // fetching the total discounted price for the basket items
                        value = this.getUpdatedValue(discountForThis);
                        
                        // writting the output statement 
                        if(value > 0){
                            appliedDiscounts.add(String.format("%s with %d%% off : -%dp", discount.discountOnItem, discount.discountedPercentage, value));
                        }
                    }
                }
            }
        }
        
        // if no discounted or offer product is there in basket, output statement is written
        if(appliedDiscounts.isEmpty()) {
        	String noOffers = "(no offers available)";
        	appliedDiscounts.add(String.format("%s",noOffers));
        }
        // calculating the subtotal value of the basket 
        this.subTotal = this.total + totalDiscountValue;
        // returning the discounts output statement arraylist to print in the console
        return appliedDiscounts;
    }
}
