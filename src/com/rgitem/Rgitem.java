package com.rgitem;
 /*
 * The class  Rg item
 */ 
public class RgItem{
    
    private int intQty;
    private int intPrice;
    private int intCalorie;
    private String strDesc;
    private String strName;
/*
 * Sets the name
 *
 * @param strName  The String Name. 
 */
    public void setName(String strName){ 
        this.strName = strName;
    }
/*
 * Sets the description
 *
 * @param strDesc  The String Description. 
 */
    public void setDesc(String strDesc){ 
        this.strDesc = strDesc;
    }
/*
 * Sets the calorie
 *
 * @param intCalorie  The Integer Calorie. 
 */
    public void setCalorie(int intCalorie){ 
        this.intCalorie = intCalorie;
    }
/*
 * Sets the price
 *
 * @param intPrice  The Integer Price. 
 */
    public void setPrice(int intPrice){ 
        this.intPrice = intPrice;
    }
/*
 * Sets the quantity
 *
 * @param intQty  The Integer Quantity. 
 */
    public void setQty(int intQty){ 
        this.intQty = intQty;
    }
/*
 * Gets the name
 *
 * @return  The String Name
 */
    public String getName(){ 
        return strName;
    }
/*
 * Gets the description
 *
 * @return  The String Description
 */
    public String getDesc(){ 
        return strDesc;
    }
/*
 * Gets the calorie
 *
 * @return  The Integer Calorie
 */
    public int getCalorie(){ 
        return intCalorie;
    }
/*
 * Gets the price
 *
 * @return  The Integer Price
 */
    public int getPrice(){ 
        return intPrice;
    }
/*
 * Gets the quantity
 *
 * @return  The Integer Quantity
 */
    public int getQty(){ 
        return intQty;
    }
/*
 * Changes the quantity through subtraction
 *
 * @param intQty  The Integer quantity to be subtracted. 
 */
    public void changeQtySub(int intQty){ 
        this.intQty = this.intQty - intQty;
    }
/*
 * Changes the quantity through addition
 *
 * @param intQty  The Integer quantity to be added.
 */
    public void changeQtyAdd(int intQty){ 
        this.intQty = this.intQty + intQty;
    }
/*
 * Deletes cake
 */
    public void deleteCake(){ 
        intQty = 0;
        intPrice = 0;
        intCalorie = 0;
        strDesc = " ";
        strName = " ";
    }
/*
 * Display cake
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
