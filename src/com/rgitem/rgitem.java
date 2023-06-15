package mcone.data;

public class RGITEM {
    
    private int[] intQty;
    private int[] intPrice;
    private int[] intCalorie;
    private String[] strDesc;
    private String[] strName;

    public void setName(String strName, int intCounter){
        this.strName[intCounter] = strName;
    }

    public void setDesc(String strDesc, int intCounter){
        this.strDesc[intCounter] = strDesc;
    }

    public void setCalorie(int intCalorie, int intCounter){
        this.intCalorie[intCounter] = intCalorie;
    }

    public void setPrice(int intPrice, int intCounter){
        this.intPrice[intCounter] = intPrice;
    }

    public void setQty(int intQty, int intCounter){
        this.intCalorie[intCounter] = intQty;
    }

    public String getName(int intCounter){
        return strName[intCounter];
    }

    public String getDesc(int intCounter){
        return strDesc[intCounter];
    }

    public int getCalorie(int intCounter){
        return intCalorie[intCounter];
    }

    public int getPrice(int intCounter){
        return intPrice[intCounter];
    }

    public int getQty(int intCounter){
        return intCalorie[intCounter];
    }

    public void changeQuantitySub(int intSub, int intCounter){
        this.intQty[intCounter] = this.intQty[intCounter] - intSub;
    }

    public void changeQuantityAdd(int intAdd, int intCounter){
        this.intQty[intCounter] = this.intQty[intCounter] + intAdd;
    }

    public void deleteCake(int intCounter){
        this.intQty[intCounter] = 0;
        this.intPrice[intCounter] = 0;
        this.intCalorie[intCounter] = 0;
        this.strDesc[intCounter] = " ";
        this.strName[intCounter] = " ";
    }

    public void displayCake(int intCounter){
        System.out.println("Cake Name: " + this.strName[intCounter]);
        System.out.println("Description: " + this.strDesc[intCounter]);
        System.out.println("Calorie Count: " + this.intCalorie[intCounter]);
        System.out.println("Price: " + this.intPrice[intCounter]);
        System.out.println("Available Quantity: " + this.intQty[intCounter]);
    }
}
