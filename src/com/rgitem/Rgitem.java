package mcone.rgitem;

public class RgItem{
    
    private int intQty;
    private int intPrice;
    private int intCalorie;
    private String strDesc;
    private String strName;

    //Set
    public void setName(String strName){
        this.strName = strName;
    }

    public void setDesc(String strDesc){
        this.strDesc = strDesc;
    }

    public void setCalorie(int intCalorie){
        this.intCalorie = intCalorie;
    }

    public void setPrice(int intPrice){
        this.intPrice = intPrice;
    }

    public void setQty(int intQty){
        this.intQty = intQty;
    }

    //Get
    public String getName(){
        return strName;
    }

    public String getDesc(){
        return strDesc;
    }

    public int getCalorie(){
        return intCalorie;
    }

    public int getPrice(){
        return intPrice;
    }

    public int getQty(){
        return intCalorie;
    }

    //Quantity Add/Sub
    public void changeQuantitySub(int intQty){
        this.intQty = this.intQty - intQty;
    }

    public void changeQuantityAdd(int intQty){
        this.intQty = this.intQty + intQty;
    }

    public void deleteCake(){
        intQty = 0;
        intPrice = 0;
        intCalorie = 0;
        strDesc = " ";
        strName = " ";
    }

    public void displayCake(){
        System.out.println("Cake Name: " + strName);
        System.out.println("Description: " + strDesc);
        System.out.println("Calorie Count: " + intCalorie);
        System.out.println("Price: " + intPrice);
        System.out.println("Available Quantity: " + intQty);
    }
}
