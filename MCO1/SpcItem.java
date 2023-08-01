import java.util.ArrayList;
import java.util.List;

public class SpcItem extends RgItem {
    private ArrayList<String> incompatibleCakes;
    private String tag;
    private String flavorText;
/**
 * Constructs a new special cake item with the specified details.
 *
 * This constructor creates a new special cake add-on item with the given name, quantity, price,
 * calorie count, and description. Additionally, it assigns the specified tag and flavor text
 * to the special cake item.
 *
 * @param name The name of the special cake item add-on.
 * @param quantity The quantity of the special cake item add-on available in stock.
 * @param price The price of the special cake item add-on.
 * @param calorie The calorie count of the special cake item add-on.
 * @param description The description of the special cake item add-on.
 * @param tag The tag associated with the special cake item add-on.
 * @param flavorText The flavor text to be printed when this add-on item is bought 
 */
    public SpcItem(String name, int quantity, int price, int calorie, String description, String tag, String flavorText) {
        super(name, quantity, price, calorie, description);
        incompatibleCakes = new ArrayList<>();
        this.tag = tag;
        this.flavorText= flavorText;
    }
/**
 * Constructs a new cake add-on item with the specified details and inherits restrictions and flavor text.
 *
 * This constructor creates a new cake add-on item with the given name, quantity, price,
 * calorie count, and description. Additionally, it assigns the specified tag to the cake add-on item
 * and inherits restrictions and flavor text from the specified array of SpcItem objects.
 *
 * @param name The name of the cake add-on item.
 * @param quantity The quantity of the cake add-on item available in stock.
 * @param price The price of the cake add-on item.
 * @param calorie The calorie count of the cake add-on item.
 * @param description The description of the cake add-on item.
 * @param tag The tag associated with the cake add-on item.
 * @param inheritsFrom An array of SpcItem objects from which restrictions and flavor text will be inherited.
 */
    public SpcItem(String name, int quantity, int price, int calorie, String description, String tag, SpcItem[] inheritsFrom) {
        super(name, quantity, price, calorie, description);
        incompatibleCakes = new ArrayList<>();
        this.tag = tag;
        this.flavorText="";
        // Inherit restrictions from the specified SpcItem objects
        for (SpcItem item : inheritsFrom) {
            if (item != null) {
                incompatibleCakes.addAll(item.incompatibleCakes);
                this.flavorText=flavorText.concat(item.getFlavorText()+"\n");
            }
        }
    }
/**
 * Default constructor for a cake add-on item.
 *
 * This constructor creates a new cake add-on item with default values. The name, quantity, price, calorie count,
 * and description will be empty, and the tag and flavor text will be set to blank spaces.
 * The incompatibleCakes list will be initialized as an empty ArrayList.
 */
    public SpcItem() {
        incompatibleCakes = new ArrayList<>();
        this.tag = " ";
        this.flavorText=" ";
    }
/**
 * Adds the name of an incompatible cake to the list of incompatible cakes for this add-on item.
 * 
 * An incompatible cake is a cake with which this add-on item cannot be combined.
 *
 * @param cakeName The name of the cake that is incompatible with this add-on item.
 */
    public void addIncompatibleCake(String cakeName) {
        incompatibleCakes.add(cakeName);
    }
/**
 * Checks if this add-on item is compatible with a given cake name.
 * An add-on item is compatible with a cake if it can be added to the cake without any conflicts.
 *
 * @param cakeName The name of the cake to check compatibility with.
 * @return true if this add-on item is compatible with the cake, false otherwise.
 */
    public boolean isCompatible(String cakeName) {
        for (int i = 0; i < incompatibleCakes.size(); i++) {
            if (cakeName.equals(incompatibleCakes.get(i))) {
                return false;
            }
        }
        return true;
    }
/**
 * Get the list of cake names that are incompatible with this add-on item.
 *
 * This method returns an ArrayList containing the names of cakes that are incompatible
 * with this add-on item.
 *
 * @return An ArrayList of cake names that are incompatible with this add-on item.
 */
    public  ArrayList<String> getIncompatibleCakes() {
        return incompatibleCakes;
    }
/**
 * Check if this add-on item has a specific tag.
 *
 * This compares the tag of the add-on item with the given tagName and returns true if they are equal, indicating
 * that the add-on item has the specified tag. Otherwise, it returns false.
 *
 * @param tagName The tag to check for in the add-on item.
 * @return True if the add-on item has the specified tag, false otherwise.
 */
    public boolean hasTag(String tagName) {
        return tag.equals(tagName);
    }
/**
 * Get the flavor text of the cake add-on item.
 *
 * This method returns the flavor text associated with the cake add-on item. Flavor text is a short
 * description or text presented when this item is bought.
 *
 * @return The flavor text of the cake add-on item.
 */
    public String getFlavorText(){
        return flavorText;
    }

}
