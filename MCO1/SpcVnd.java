    /**
     * Adds a new item to the vending machine's stock. Reciept's copy also adds the new cake to prevent discrepancies, but the quantity is set to zero since its not bought.
     * @param strName the name of the item
     * @param strDesc the description of the item
     * @param intCalorie the calorie amount of the item
     * @param intPrice the price of the item
     * @param intQuantity the amount of item to be added
     * @param strTag the item tag
     * @param strFT the Flavor Text of the item
     * @return the results of the transaction
     */
    public String addNewItem(String strName, String strDesc, int intCalorie, int intPrice, int intQuantity,String strTag, String strFT){
        String output="";
        if(profit!=0){
            output=output.concat("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            output=output.concat(strName + " was added!");
            spcItemStock.addNewSpcItem(strName,strDesc, intCalorie,intPrice, intQuantity, strTag, strFT);
            spcItemSold.addNewSpcItem(strName,strDesc, intCalorie,intPrice, intQuantity, strTag, strFT);
        }
        return output;
       
    }
    /**
     * Deletes the item at index. Reciept's copy also deletes the cake to prevent discrepancies.
     * @param index the index of the item to be deleted
     * @return the results of the transaction
     */
    public String deleteAnItem(int index){
        String output="";
        if(profit!=0){
            output=output.concat("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            output=output.concat(spcItemStock.getItemName(index) +" was deleted.");
            spcItemStock.deleteItem(index);
            spcItemSold.deleteItem(index);
        }
        return output;
    }
