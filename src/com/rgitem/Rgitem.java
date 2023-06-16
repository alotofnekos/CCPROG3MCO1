package com.rgitem;
 /*
 * The class Rg item
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
 * @param strName the str name. 
 */
    public void setName(String strName){ 

        this.strName = strName;
    }
/*
 * Sets the description
 *
 * @param strDesc the str description. 
 */
    public void setDesc(String strDesc){ 

        this.strDesc = strDesc;
    }
/*
 * Sets the calorie
 *
 * @param intCalorie the int calorie. 
 */
    public void setCalorie(int intCalorie){ 

        this.intCalorie = intCalorie;
    }
/*
 * Sets the price
 *
 * @param intPrice the int price. 
 */
    public void setPrice(int intPrice){ 

        this.intPrice = intPrice;
    }
/*
 * Sets the quantity
 *
 * @param intQty the int quantity. 
 */
    public void setQty(int intQty){ 

        this.intQty = intQty;
    }
/*
 * Gets the name
 *
 * @return the name
 */
    public String getName(){ 

        return strName;
    }
/*
 * Gets the description
 *
 * @return the description
 */
    public String getDesc(){ 

        return strDesc;
    }
/*
 * Gets the calorie
 *
 * @return the calorie
 */
    public int getCalorie(){ 

        return intCalorie;
    }
/*
 * Gets the price
 *
 * @return the price
 */
    public int getPrice(){ 

        return intPrice;
    }
/*
 * Gets the quantity
 *
 * @return the quantity
 */
    public int getQty(){ 

        return intQty;
    }
/*
 * Changes the quantity through subtraction
 *
 * @param intQty the int quantity to be subtracted. 
 */
    public void changeQtySub(int intQty){ 

        this.intQty = this.intQty - intQty;
    }
/*
 * Changes the quantity through addition
 *
 * @param intQty the int quantity to be added.
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
