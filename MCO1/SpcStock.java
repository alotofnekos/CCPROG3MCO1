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
        SpcItem[4] = new SpcItem("Candy Flowers", 0, 40, 0, "A special option for your cake, allowing you to bring more color with these vibrant candied flowers. Not suitable for Crumbling cakes.", "topper");
        SpcItem[4].addIncompatibleCake("Angel's Apple Crumble");
        SpcItem[5] = new SpcItem("Candy Hearts", 0, 40, 0, "A special option for your cake, allowing you to bring more love with these cute candied hearts. Not suitable for Crumbling cakes.", "topper");
        SpcItem[5].addIncompatibleCake("Angel's Apple Crumble");
    }
}

