import java.util.ArrayList;
import java.util.List;

public class SpcItem extends RgItem {
    private List<String> incompatibleCakes;
    private String strTag;

    public SpcItem(String name, int quantity, int price, int calorie, String description, String strTag) {
        super(name, quantity, price, calorie, description);
        incompatibleCakes = new ArrayList<>();
        this.strTag=strTag;
    }
    public void addIncompatibleCake(String cakeName) {
        incompatibleCakes.add(cakeName);
    }

    public boolean isCompatible(String cakeName) {
        return !incompatibleCakes.contains(cakeName);
    }

    public boolean hasTag(String tagName) {
        return tag.equals(tagName);
    }
}
