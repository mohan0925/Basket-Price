import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	/**
	 * Main method execution starts
	 * Arguments are taken and will be passed to the itemslist
	 * @param args
	 */
    public static void main(String[] args) {
    	
    	// initialising the price basket where all the discount offer is calculated
        PriceBasket pricebasket = new PriceBasket();

        // iterating all the arguments passed and adding them to the itemlist
        for(int index = 1; index < args.length; index++){
            pricebasket.addItemsInItemList(args[index]);
        }
        
        // getting the output of the discounted items
        ArrayList<String> outputString = pricebasket.applyDiscounts();
        
        // printing the subtotal
        System.out.print("Subtotal: \u00A3");
        System.out.println(String.format("%.2f",pricebasket.getSubTotal()));
        
        // displaying the output statement by iterating the arraylist
        Iterator<String> iterator = outputString.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        
        // printing the total value
        System.out.print(String.format("Total: \u00A3%.2f",pricebasket.getTotal()));
    }
}

/* some tested testcases
PriceBasket Bread Milk
PriceBasket Apples Apples
PriceBasket Apples Apples Soup
PriceBasket Apples Apples Milk
PriceBasket Apples Soup Bread Milk
PriceBasket Apples Apples Soup Soup Bread
PriceBasket Apples Apples Soup Soup Soup Bread Bread Bread
 */

