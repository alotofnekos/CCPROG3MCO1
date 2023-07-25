public class SpcVnd extends RgVnd {
    private SpcStock spcItemStock;
    private SpcStock spcItemSold;
    private RgStock vndItemStock;
    private RgStock vndItemSold;
    private BillWallet userWallet;
    private BillWallet vndStock;
    private int profit=0;

    /**
     * This is the constructor for the SpcVnd class that initializes the SpcStocks for
     * special items and the BillWallets for the vending machine and the user.
     */
    public SpcVnd() {
        super();
        spcItemStock = new SpcStock();
        spcItemSold = new SpcStock();
    }
   
    /**
     * Lets the user purchase an item at cakeIndex with userBills. Gets the change too.
     * @param userBills the array of bills corresponding to the number of [10, 20, 50, 100, 200, 500] peso bills
     * @param cakeIndex the index of the cake item to be bought.
     * @param itemIndex the array of special item indices to be bought.
     */
    public void purchaseItem(int[] userBills, int cakeIndex, int[] itemIndex) {
        boolean canGiveChange;
        boolean isItemValid = false;
        boolean isAddOnValid = false;
        boolean hasCakeInStock = vndItemStock.hasCakeInStock();
        
        if (!hasCakeInStock) {
            System.out.println("Can't buy items, no items in stock.");
            return;
        }

        if (vndStock.getTotalAmount() == 0) {
            System.out.println("The Vending Machine does not have money to process change right now");
            canGiveChange = false;
        } else {
            System.out.println("The Vending Machine can process change right now");
            canGiveChange = true;
        }

        if (userBills.length != 6) {
            System.out.println("Invalid bill quantities array length. Expected length: 6");
            return;
        }

        for (int i = 0; i < userBills.length; i++) {
            int billValue = vndStock.getBills()[i].getValue();
            userWallet.addBill(new Bill(userBills[i], billValue));
        }

        System.out.println("Total amount inserted: " + userWallet.getTotalAmount());

        isItemValid = vndItemStock.validItem(cakeIndex);
        if (!isItemValid) {
            System.out.println("Item is out of stock. Please try again");
            userWallet.clearWallet();
            return;
        }

        // Check if all special items are valid
        for (int i = 0; i < itemIndex.length; i++) {
            isAddOnValid = spcItemStock.validItem(itemIndex[i]);
            if (!isAddOnValid) {
                System.out.println("Special item at index " + itemIndex[i] + " is out of stock. Please try again");
                userWallet.clearWallet();
                return;
            }
        }

        int totalPrice = vndItemStock.getCakePrice(cakeIndex);
        for (int i = 0; i < itemIndex.length; i++) {
            totalPrice += spcItemStock.getItemPrice(itemIndex[i]);
        }

        if (canGiveChange == false) {
            if (userWallet.getTotalAmount() == totalPrice) {
                vndStock.pay(userWallet.getTotalAmount(), totalPrice);
                userWallet.clearWallet();
                profit += totalPrice;
                System.out.println("Payment successful. Dispensing " + vndItemStock.getCakeName(cakeIndex) + ".");
                vndItemStock.transferCake(vndItemSold, cakeIndex);
                for (int i = 0; i < itemIndex.length; i++) {
                    spcItemStock.transferItem(spcItemSold, itemIndex[i]);
                }
            } else if (userWallet.getTotalAmount() > totalPrice) {
                System.out.println("Please pay exact amount. No change available");
                userWallet.clearWallet();
            }
        } else {
            if (userWallet.getTotalAmount() < totalPrice) {
                System.out.println("Insufficient funds. Please add more bills");
                userWallet.clearWallet();
            } else if ((vndStock.pay(userWallet.getTotalAmount(), totalPrice)) && (userWallet.getTotalAmount() >= totalPrice)) {
                userWallet.clearWallet();
                profit += totalPrice;
                System.out.println("Payment successful. Dispensing " + vndItemStock.getCakeName(cakeIndex) + ".");
                vndItemStock.transferCake(vndItemSold, cakeIndex);
                for (int i = 0; i < itemIndex.length; i++) {
                    spcItemStock.transferItem(spcItemSold, itemIndex[i]);
                }
            } else {
                System.out.println("Please pay exact amount. No change available");
                userWallet.clearWallet();
            }
        }
    }
}
