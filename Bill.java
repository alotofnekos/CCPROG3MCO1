

/**
 * The class Bill defines the Bills to be used for making transactions to the vending machine
 */ 
public class Bill {
    private int intQty;
    private int intValue;
 /** 
 *
 * This is a constructor. 
 *
 * @param intQty  the quantity of the bill to be stocked
 * @param intValue  the value of each individual bill (eg.&nbsp; 10 5 peso coins will be simulated as Bill(10,5))
 */
    public Bill(int intQty, int intValue) {
        this.intQty = intQty;
        this.intValue = intValue;
    }
/** 
 *
 * Gets the quantity of bills in stock
 *
 * @return the quantity of bills in stock
 */
    public int getQty() {
        return intQty;
    }
/** 
 *
 * Sets the quantity of bills in stock
 *
 * @param intQty  the quantity of bills in stock 
 */
    public void setQty(int intQty) {
        this.intQty = intQty;
    }
/** 
 *
 * Gets the value of the bill
 *
 * @return the value of the bill
 */
    public int getValue() {
        return intValue;
    }
}
