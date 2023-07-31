import java.util.ArrayList;
import java.util.List;

public class SpcItem extends RgItem {
    private ArrayList<String> incompatibleCakes;
    private String strTag;
    private String flavorText;

    public SpcItem(String name, int quantity, int price, int calorie, String description, String strTag, String flavorText) {
        super(name, quantity, price, calorie, description);
        incompatibleCakes = new ArrayList<>();
        this.strTag = strTag;
        this.flavorText= flavorText;
    }

    public SpcItem(String name, int quantity, int price, int calorie, String description, String strTag, SpcItem[] inheritsFrom) {
        super(name, quantity, price, calorie, description);
        incompatibleCakes = new ArrayList<>();
        this.strTag = strTag;
        this.strTag="";
        // Inherit restrictions from the specified SpcItem objects
        for (SpcItem item : inheritsFrom) {
            if (item != null) {
                incompatibleCakes.addAll(item.incompatibleCakes);
                this.strTag=strTag.concat(item.getFlavorText()+"\n");
            }
        }
    }
    public SpcItem() {
        incompatibleCakes = new ArrayList<>();
        this.strTag = " ";
    }

    public void addIncompatibleCake(String cakeName) {
        incompatibleCakes.add(cakeName);
    }

    public boolean isCompatible(String cakeName) {
        for (int i = 0; i < incompatibleCakes.size(); i++) {
            if (cakeName.equals(incompatibleCakes.get(i))) {
                return false;
            }
        }
        return true;
    }

    public  ArrayList<String> getIncompatibleCakes() {
        return incompatibleCakes;
    }

    public boolean hasTag(String tagName) {
        return strTag.equals(tagName);
    }

    public String getFlavorText(){
        return flavorText;
    }

}
