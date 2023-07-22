import java.util.ArrayList;
import java.util.List;

public class SpcItem extends RgItem {
    private List<String> incompatibleCakes;
    private String strTag;

    public SpcItem(String name, int quantity, int price, int calorie, String description, String strTag) {
        super(name, quantity, price, calorie, description);
        incompatibleCakes = new ArrayList<>();
        this.strTag = strTag;
    }

    public SpcItem(String name, int quantity, int price, int calorie, String description, String strTag, SpcItem[] inheritsFrom) {
        super(name, quantity, price, calorie, description);
        incompatibleCakes = new ArrayList<>();
        this.strTag = strTag;

        // Inherit restrictions from the specified SpcItem objects
        for (SpcItem item : inheritsFrom) {
            if (item != null) {
                incompatibleCakes.addAll(item.incompatibleCakes);
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
            System.out.println(cakeName + " vs " + incompatibleCakes.get(i));
            if (cakeName.equals(incompatibleCakes.get(i))) {
                return false;
            }
        }
        return true;
    }
    public List<String> getIncompatibleCakes() {
        return incompatibleCakes;
    }

    public boolean hasTag(String tagName) {
        return strTag.equals(tagName);
    }
}
