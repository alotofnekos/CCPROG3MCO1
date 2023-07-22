import java.util.List;

public class SpcStock extends RgStock {
    private SpcItem[] item;
    public SpcStock() {
        // Invoke the default constructor of RgStock
        super();
        item = new SpcItem[20]; 
        item[0] = new SpcItem("Happy Birthday Dedication", 0, 20, 74, "A special option for your cake, allowing you to greet your special person (or yourself) a Happy Birthday with this edible topper", "dedication");
        item[1] = new SpcItem("Happy Mothers Day Dedication", 0, 20, 74, "A special option for your cake, allowing you to greet your special person (or yourself) a Happy Mothers Day with this edible topper", "dedication");
        item[2] = new SpcItem("Happy Fathers Day Dedication", 0, 20, 74, "A special option for your cake, allowing you to greet your special person (or yourself) a Happy Fathers Day with this ", "dedication");
        item[3] = new SpcItem("Cake Candle", 0, 10, 0, "A special option for your cake, allowing you to bring a warm glow to your cake. Dont forget to make a wish before blowing!", "candle");
        item[4] = new SpcItem("Candy Flowers", 0, 40, 55, "A special option for your cake, allowing you to bring more color with these vibrant candied flowers. Not suitable for Crumbling cakes.", "topper");
        item[4].addIncompatibleCake("Angel's Apple Crumble");
        item[5] = new SpcItem("Candy Hearts", 0, 40, 60, "A special option for your cake, allowing you to bring more love with these cute candied hearts. Not suitable for Crumbling cakes.", "topper");
        item[5].addIncompatibleCake("Angel's Apple Crumble");
        item[5] = new SpcItem("Fruits", 0, 30, 24, "Add natural sweetness to your dessert with fruits! Tristan's Carrot Cake and Black Forest Cake cannot add fruit to the top of the cake.", "fruit");
        item[5].addIncompatibleCake("Tristan's Carrot Cake");
        item[5].addIncompatibleCake("Black Forest Cake");
        item[6] = new SpcItem("Chocolate", 0, 40, 70, "Add a hint of bitter-sweet to your day with these chocolate toppings. Unavailable to Chocolate Mousse Cake,Black Forest Cake, and Premium Chocolate Cake as these are already chocolate cakes.", "chocolate");
        item[6].addIncompatibleCake("Chocolate Mousse Cake");
        item[6].addIncompatibleCake("Black Forest Cake");
        item[6].addIncompatibleCake("Premium Chocolate Cake");
        item[7] = new SpcItem("Vanilla Ice Cream", 0, 30, 66, "Beat the heat by adding a scoop of fragrant and rich vanilla ice cream on your cake!Unavailable to Tristan's Carrot Cake, Black Forest Cake, Strawberry Cake, Chocolate Mousse Cake, and Lemon Blueberry Cake due to these cakes already have a delicate topping. ", "ice cream");
        item[7].addIncompatibleCake("Tristan’s Carrot Cake");
        item[7].addIncompatibleCake("Black Forest Cake");
        item[7].addIncompatibleCake("Strawberry Cake");
        item[7].addIncompatibleCake("Chocolate Mousse Cake");
        item[7].addIncompatibleCake("Lemon Blueberry Cake");
        item[8] = new SpcItem("Upgrade to B-Day Cake", 0, 40, 74, "Greet your loved ones with a Happy Birthday with this package! Includes a dedication topper and a candle.", "birthday package");
        item[9] = new SpcItem("Upgrade to Chocolate a la Mode", 0, 60, 136, "Ice Cream, Chocolate, and Cake? What a sweet combination! Inherits restrictions from Ice Cream and Chocolate.", "Ice Cream Chocolate Package", new SpcItem[]{item[6], item[7]});
        item[10] = new SpcItem("Upgrade to Fruit a la Mode", 0, 60, 90, "Ice Cream, Fruit, and Cake? What a refreshing combination! Inherits restrictions from Ice Cream and Fruits.", "Ice Cream Fruit Package", new SpcItem[]{item[5], item[7]});
        for (int i = 11; i < item.length; i++) {
            item[i] = new SpcItem(); // Initialize the remaining elements with default RgItem objects
        }
    }

    public boolean isCompatible(int index, String cakeName) {
        System.out.println("meow "+ item[index].isCompatible(cakeName));
        return item[index].isCompatible(cakeName);
    }

    @Override
    public void setStock(int intQty) {
        super.setStock(intQty); // Call the setStock method from the parent class to handle RgItem array

        if (intQty != 0) {
            for (int i = 0; i < item.length; i++) {
                if (!item[i].getName().equals(" ")) {
                    System.out.println(intQty + " " + item[i].getName() + "s added!");
                    item[i].setQty(intQty);
                }
            }
        } else {
            for (int i = 0; i < item.length; i++) {
                if (!item[i].getName().equals(" ")) {
                    item[i].setQty(intQty);
                }
            }
        }
    }

    @Override
    public void displayMenu(){
        for(int i = 0;i<item.length;i++){
            if(validItem(i)){
                int j = i+1;
                System.out.println("Cake number: "+ j);
                item[i].displayItem();
            }
        }        
    }
    @Override
    public boolean validItem(int cakeIndex) {
        return (cakeIndex >= 0 && cakeIndex < item.length && !item[cakeIndex].getName().equals(" ") && item[cakeIndex].getQty() > 0);
    }

    public String getName(int index){
        return item[index].getName();
    }

    public List<String> getIncompatibleCakes(int index) {
        return item[index].getIncompatibleCakes();
    }

}

