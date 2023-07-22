public class SpcStock extends RgStock {
    private SpcItem[] SpcItem;
    public SpcStock() {
        // Invoke the default constructor of RgStock
        super();
        SpcItem = new SpcItem[20]; 
        SpcItem[0] = new SpcItem("Happy Birthday Dedication", 0, 20, 74, "A special option for your cake, allowing you to greet your special person (or yourself) a Happy Birthday with this edible topper", "dedication");
        SpcItem[1] = new SpcItem("Happy Mothers Day Dedication", 0, 20, 74, "A special option for your cake, allowing you to greet your special person (or yourself) a Happy Mothers Day with this edible topper", "dedication");
        SpcItem[2] = new SpcItem("Happy Fathers Day Dedication", 0, 20, 74, "A special option for your cake, allowing you to greet your special person (or yourself) a Happy Fathers Day with this ", "dedication");
        SpcItem[3] = new SpcItem("Cake Candle", 0, 10, 0, "A special option for your cake, allowing you to bring a warm glow to your cake. Dont forget to make a wish before blowing!", "candle");
        SpcItem[4] = new SpcItem("Candy Flowers", 0, 40, 55, "A special option for your cake, allowing you to bring more color with these vibrant candied flowers. Not suitable for Crumbling cakes.", "topper");
        SpcItem[4].addIncompatibleCake("Angel's Apple Crumble");
        SpcItem[5] = new SpcItem("Candy Hearts", 0, 40, 60, "A special option for your cake, allowing you to bring more love with these cute candied hearts. Not suitable for Crumbling cakes.", "topper");
        SpcItem[5].addIncompatibleCake("Angel's Apple Crumble");
        SpcItem[5] = new SpcItem("Fruits", 0, 30, 24, "Add natural sweetness to your dessert with fruits! Tristan's Carrot Cake and Black Forest Cake cannot add fruit to the top of the cake.", "fruit");
        SpcItem[5].addIncompatibleCake("Tristan's Carrot Cake");
        SpcItem[5].addIncompatibleCake("Black Forest Cake");
        SpcItem[6] = new SpcItem("Chocolate", 0, 40, 70, "Add a hint of bitter-sweet to your day with these chocolate toppings. Unavailable to Chocolate Mousse Cake,Black Forest Cake, and Premium Chocolate Cake as these are already chocolate cakes.", "chocolate");
        SpcItem[6].addIncompatibleCake("Chocolate Mousse Cake");
        SpcItem[6].addIncompatibleCake("Black Forest Cake");
        SpcItem[6].addIncompatibleCake("Premium Chocolate Cake");
        SpcItem[7] = new SpcItem("Vanilla Ice Cream", 0, 30, 66, "Beat the heat by adding a scoop of fragrant and rich vanilla ice cream on your cake!Unavailable to Tristan\u2019s Carrot Cake, Black Forest Cake, Strawberry Cake, Chocolate Mousse Cake, and Lemon Blueberry Cake due to these cakes already have a delicate topping. ", "ice cream");
        SpcItem[7].addIncompatibleCake("Tristan’s Carrot Cake");
        SpcItem[7].addIncompatibleCake("Black Forest Cake");
        SpcItem[7].addIncompatibleCake("Strawberry Cake");
        SpcItem[7].addIncompatibleCake("Chocolate Mousse Cake");
        SpcItem[7].addIncompatibleCake("Lemon Blueberry Cake");
        SpcItem[8] = new SpcItem("Upgrade to B-Day Cake", 0, 40, 74, "Greet your loved ones with a Happy Birthday with this package! Includes a dedication topper and a candle.", "birthday package");
        SpcItem[9] = new SpcItem("Upgrade to Chocolate a la Mode", 0, 60, 136, "Ice Cream, Chocolate, and Cake? What a sweet combination! Inherits restrictions from Ice Cream and Chocolate.", "Ice Cream Chocolate Package", new SpcItem[]{SpcItem[6], SpcItem[7]});
        SpcItem[10] = new SpcItem("Upgrade to Fruit a la Mode", 0, 60, 90, "Ice Cream, Fruit, and Cake? What a refreshing combination! Inherits restrictions from Ice Cream and Fruits.", "Ice Cream Fruit Package", new SpcItem[]{SpcItem[5], SpcItem[7]});
        for (int i = 11; i < SpcItem.length; i++) {
            SpcItem[i] = new SpcItem(); // Initialize the remaining elements with default RgItem objects
        }
    }

    public boolean isCompatible(int index, String cakeName) {
        return SpcItem[index].isCompatible(cakeName);
    }
    @Override
    public void setStock(int intQty) {
        super.setStock(intQty); // Call the setStock method from the parent class to handle RgItem array

        if (intQty != 0) {
            for (int i = 0; i < SpcItem.length; i++) {
                if (!SpcItem[i].getName().equals(" ")) {
                    System.out.println(intQty + " " + SpcItem[i].getName() + "s added!");
                    SpcItem[i].setQty(intQty);
                }
            }
        } else {
            for (int i = 0; i < SpcItem.length; i++) {
                if (!SpcItem[i].getName().equals(" ")) {
                    SpcItem[i].setQty(intQty);
                }
            }
        }
    }
    @Override
    public void displayMenu(){
        for(int i = 0;i<SpcItem.length;i++){
            if(validItem(i)){
                int j = i+1;
                System.out.println("Cake number: "+ j);
                SpcItem[i].displayItem();
            }
        }        
    }
}

