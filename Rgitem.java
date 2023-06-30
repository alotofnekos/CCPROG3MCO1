

 /**
 * The class  Rg item defines the regular items to be sold in the vending machine
 */ 
public class RgItem{
    
    private int intQty;
    private int intPrice;
    private int intCalorie;
    private String strDesc;
    private String strName;
/**
 * This is a constructor for a known item
 *
 * @param strName  The String Name of the item. 
 * @param intQty  The Integer Quantity of the item. 
 * @param intPrice  The Integer Price of the item.
 * @param intCalorie  The Integer Calorie of the item. 
 * @param strDesc  The String Description of the item. 
 */
    public RgItem(String strName, int intQty, int intPrice, int intCalorie, String strDesc){
        this.strName = strName;
        this.intQty = intQty;
        this.intPrice = intPrice;
        this.intCalorie=intCalorie;
        this.strDesc=strDesc;
   }
/**
 * This is a constructor for an empty item 
 */
    public RgItem(){ 
        intQty = 0;
        intPrice = 0;
        intCalorie = 0;
        strDesc = " ";
        strName = " ";
    }
/**
 * Sets the name.
 *
 * @param strName  The String Name of the item.  
 */
    public void setName(String strName){ 
        this.strName = strName;
    }
/**
 * Sets the description.
 *
 * @param strDesc  The String Description of the item.  
 */
    public void setDesc(String strDesc){ 
        this.strDesc = strDesc;
    }
/**
 * Sets the calorie.
 *
 * @param intCalorie  The Integer Calorie of the item.  
 */
    public void setCalorie(int intCalorie){ 
        this.intCalorie = intCalorie;
    }
/**
 * Sets the price.
 *
 * @param intPrice  The Integer Price of the item.  
 */
    public void setPrice(int intPrice){ 
        this.intPrice = intPrice;
    }
/**
 * Sets the quantity.
 *
 * @param intQty  The Integer Quantity of the item.  
 */
    public void setQty(int intQty){ 
        this.intQty = intQty;
    }
/**
 * Gets the name.
 *
 * @return  The String Name of the item. 
 */
    public String getName(){ 
        return strName;
    }
/**
 * Gets the description.
 *
 * @return  The String Description of the item. 
 */
    public String getDesc(){ 
        return strDesc;
    }
/**
 * Gets the calorie.
 *
 * @return  The Integer Calorie of the item. 
 */
    public int getCalorie(){ 
        return intCalorie;
    }
/**
 * Gets the price.
 *
 * @return  The Integer Price of the item. 
 */
    public int getPrice(){ 
        return intPrice;
    }
/**
 * Gets the quantity.
 *
 * @return  The Integer Quantity of the item. 
 */
    public int getQty(){ 
        return intQty;
    }
/**
 * Changes the quantity through subtraction.
 *
 * @param intQty  The Integer quantity to be subtracted. 
 */
    public void changeQtySub(int intQty){ 
        this.intQty = this.intQty - intQty;
    }
/**
 * Changes the quantity through addition.
 *
 * @param intQty  The Integer quantity to be added.
 */
    public void changeQtyAdd(int intQty){ 
        this.intQty = this.intQty + intQty;
    }
/**
 * Deletes cake.
 */
    public void deleteCake(){ 
        intQty = 0;
        intPrice = 0;
        intCalorie = 0;
        strDesc = " ";
        strName = " ";
    }
/**
 * Displays the  cake.
 */
    public void displayCake(){ 
        System.out.println("Cake Name: " + strName);
        System.out.println("Description: " + strDesc);
        System.out.println("Calorie Count: " + intCalorie);
        System.out.println("Price: " + intPrice);
        System.out.println("Available Quantity: " + intQty);
        System.out.println("------------------------");
    }
}
