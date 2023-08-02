import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class SpcGui handles the GUI for special vending machines.
 */
public class SpcGui extends Gui {
    private JButton[] spcButtons;
    private JPanel[] spcVendingP;
    private JButton clearAddOn;
    private int xSpcPos=20;
    private int ySpcPos=270;
    private JTextArea[] spcItemInfoTextArea;
    private SpcVnd vnd;
    private ArrayList<String> spcitemNames = new ArrayList<>();
    private ArrayList<String> spcitemImageFileNames = new ArrayList<>();
    private ArrayList<Integer> selectedItems = new ArrayList<>();
    private JFormattedTextField addTag;
    private JFormattedTextField addFlav;
    private JButton spcItemEditButton;
    private JButton spcDeleItemButton;
    private JButton spcAddItemButton;
    private JPanel addItem;
    private JPanel delItem;
    private JPanel ediItem;
    private int wMenu;
    /**
     * Constructor for the SpcGui class.
     *
     * This constructor initializes the SpcGui object. It first calls the constructor of Gui class (RgVnd's GUI)
     * using the `super()` call. It creates a new `SpcVnd` object named `vnd`, which is an instance of a spc vending 
     * machine class. It initializes several arrays, such as `spcButtons`, `spcVendingP`, and `spcItemInfoTextArea`, which
     * stores buttons, panels, and text areas related to the special items in the GUI.
     *
     * It sets the defaults of the `vnd` object using the `setDefaults()` method, possibly to initialize the vending
     * machine with default settings. It then sets up the names and image file names for the special items. It loops through 
     * a list of special items and set corresponding names like "AddOn#1", "AddOn#2", etc., and image file paths for each special item.
     *
     */
    public SpcGui() {
        super();
        vnd = new SpcVnd();
        spcButtons =new JButton[16];
        spcVendingP =new JPanel[16];
        clearAddOn = new JButton("Clear Add-ons");
        spcItemInfoTextArea = new JTextArea[20]; 
        addItem = new JPanel();
        delItem = new JPanel();
        ediItem = new JPanel();
        vnd.setDefaults();
        int i=0;
        while(vnd.validSpcItem(i)){
            spcitemNames.add("AddOn#"+(i+1));
            i++;
        }
        i=0;
        while(vnd.validSpcItem(i)){
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\BDay.Topper.NoBg.png");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\MDay.Topper.NoBg.png");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\FDay.Topper.NoBg.png");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\candle.NoBg.png");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\Flower.Topper.NoBg.png");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\Heart.Topper.NoBg.png");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\Apple.Topping.NoBg.png");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\choco.NoBg.png");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\IceCream.Topping.NoBg.png");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\birthdaycake.jpg");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\alamodechoc.jpg");
            spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\alamodefruit.jpg");
            i++; 
        }
        Arrays.fill(spcButtons, new JButton());
        Arrays.fill(spcVendingP, new JPanel());
        spcItemEditButton = new JButton("Edit Special Item");
        spcDeleItemButton = new JButton("Delete Special Item");
        spcAddItemButton = new JButton("Add Special Item");
    }
    /**
     * Sets up the panel for the special cake vending machine.
     *
     * This method overrides the `vendingMachinePanel()` method from the superclass. It is responsible for setting up
     * the main panel of the special cake vending machine GUI. The only difference, being the color of the panels.
     */
    @Override
    public void vendingMachinePanel() {
        menuInt = 0;
        setTitle("Special Cake Vending Machine");
        getContentPane().setBackground(new Color(11, 208, 230));
        inVisible();
        visible();
    }
    /**
     * Initializes and sets up the special cake item buttons and detail panels in the GUI.
     */
    public void initializeSpcItemButtons() {
        String itemName;
        String imageFileName;
        ImageIcon imageIcon;
        for (int itemIndex = 0; itemIndex < 16; itemIndex++) {
            if (itemIndex < spcitemImageFileNames.size() && itemIndex < spcitemNames.size()) {
                itemName = spcitemNames.get(itemIndex);
                imageFileName = spcitemImageFileNames.get(itemIndex);
                imageIcon = loadImageIcon(imageFileName, 50, 50);
            } else {
                itemName = "Default";
                imageFileName = "C:\\\\\\\\Users\\\\\\\\Angel\\\\\\\\Downloads\\\\\\\\CCPROG3MCO1\\\\\\\\MCO2Assets\\\\\\\\genericcaketopper.jpg"; 
                imageIcon = loadImageIcon(imageFileName, 50, 50);
            }
            setupSpcItemButton(itemIndex, xSpcPos, ySpcPos, itemName, imageIcon);
            if (itemIndex < spcitemImageFileNames.size()) {
                setupSpcItemDetailPanel(itemIndex, imageFileName);
            }
            xSpcPos += 70;
            if (itemIndex == 7) {
                xSpcPos = 20;
                ySpcPos += 120;
            }
        }
    }    
    /**
     * Sets up an individual special cake add-on item button in the GUI.
     *
     * @param itemIndex The index of the special cake add-on  item.
     * @param x         The X-coordinate for positioning the button.
     * @param y         The Y-coordinate for positioning the button.
     * @param itemName  The name of the special cake add-on item.
     * @param imageIcon The image icon for the special cake add-on item.
     */
    public void setupSpcItemButton(int itemIndex, int x, int y, String itemName, ImageIcon imageIcon) {
        spcButtons[itemIndex] = new JButton();
        spcButtons[itemIndex].setBounds(x, y, 70, 75);
        spcButtons[itemIndex].addActionListener(this);
        spcButtons[itemIndex].setVisible(false);

        spcVendingP[itemIndex] = new JPanel(); // Initialize the spcVendingP array element here
        spcVendingP[itemIndex].setBounds(725, 30, 350, 250);
        spcVendingP[itemIndex].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        spcVendingP[itemIndex].setLayout(new BorderLayout());

        JLabel itemImageLabel = new JLabel(imageIcon);
        JLabel textLabel = new JLabel(itemName);
        itemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        spcVendingP[itemIndex].add(itemImageLabel, BorderLayout.CENTER);
        spcVendingP[itemIndex].add(textLabel, BorderLayout.SOUTH); // Add the textLabel to the SOUTH
        spcButtons[itemIndex].setMargin(new Insets(5, 5, 5, 5));
        spcButtons[itemIndex].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Set the layout to FlowLayout
        spcButtons[itemIndex].add(itemImageLabel, BorderLayout.NORTH);
        spcButtons[itemIndex].add(textLabel, BorderLayout.SOUTH);
        spcVendingP[itemIndex].setVisible(false);
        add(spcVendingP[itemIndex]);
        add(spcButtons[itemIndex]);
    }
    /**
     * Sets up the detail panel for an individual special cake add-on item.
     *
     * @param itemIndex     The index of the special cake add-on item.
     * @param imageFileName The file name of the image for the special cake add-on item.
     */
    public void setupSpcItemDetailPanel(int itemIndex,String imageFileName) {
        spcVendingP[itemIndex] = new JPanel();
        spcVendingP[itemIndex].setBounds(725, 30, 350, 250);
        spcVendingP[itemIndex].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        spcVendingP[itemIndex].setLayout(new BorderLayout());
    
        // Load the image and create a JLabel as before
        ImageIcon itemImageIcon = loadImageIcon(imageFileName, 125, 125);
        JLabel itemImageLabel = new JLabel(itemImageIcon);
        itemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        spcVendingP[itemIndex].add(itemImageLabel, BorderLayout.CENTER);
    
        // Create a JTextArea to display the item information
        spcItemInfoTextArea[itemIndex] = new JTextArea();
        spcItemInfoTextArea[itemIndex].setEditable(false);
        spcItemInfoTextArea[itemIndex].setLineWrap(true);
        spcItemInfoTextArea[itemIndex].setWrapStyleWord(true);
    
        // Get the item information using getItemInfoString() method
        String itemInfo = vnd.getItemDetails(itemIndex);
    
        // Set the item information as the text of the JTextArea
        spcItemInfoTextArea[itemIndex].setText(itemInfo);
    
        // Add the JTextArea to the SOUTH of the spcVendingP panel
        spcVendingP[itemIndex].add(spcItemInfoTextArea[itemIndex], BorderLayout.SOUTH);
    
        spcVendingP[itemIndex].setVisible(false);
        add(spcVendingP[itemIndex]);
    }
    /**
     * Adds the "Clear Add-ons" button to the GUI.
     * The button is used to clear the selected add-ons from the cake.
     */
    public void addOn(){
        clearAddOn.setBounds(600, 295, 125, 50);
        clearAddOn.addActionListener(this);
        clearAddOn.setVisible(false);
        add(clearAddOn);
    }
    /**
     * Displays the main user interface for the special cake vending machine.
     * It calls the superclass's Display method to set up the base GUI.
     * It then initializes the special item buttons and adds the "Clear Add-ons" button to the GUI.
     */
    @Override
    public void Display() {
        super.Display();
        initializeSpcItemButtons();
        itemSpcMaintenance();
        addOn();
    }
    /**
     * Overrides the superclass's visible method to make certain components visible.
     * If the value of menuInt is 0 [initialize machine], it makes the "Clear Add-ons" button and the special item buttons visible.
     * It also sets the background color of the special item buttons to LIGHT_GRAY.
     */
    @Override
    public void visible() {
        super.visible();
        if (menuInt == 0) {
            clearAddOn.setVisible(true);
            for (int i = 0; i < spcButtons.length; i++) {;
                spcButtons[i].setVisible(true);
                spcButtons[i].setBackground(Color.LIGHT_GRAY);
            }
        }
        else if (menuInt == 1) {
            for (int i = 0; i < 6; i++) {
                maintenanceB[i].setVisible(true);
                maintenanceP[i].setVisible(true);
            }
        }
    }
    /**
     * Overrides the superclass's inVisible method to make certain components invisible.
     * It makes the "Clear Add-ons" button and all special item buttons invisible.
     */
    @Override
    public void inVisible() {
        super.inVisible();
        clearAddOn.setVisible(false);
        addItem.setVisible(false);
        delItem.setVisible(false);
        ediItem.setVisible(false);

        for(int i = 0; i < spcButtons.length; i++) {
            spcVendingP[i].setVisible(false);
            spcButtons[i].setVisible(false);
        }
        for (int i = 0; i < 6; i++) {
            maintenanceB[i].setVisible(false);
            maintenanceP[i].setVisible(false);
        }
    }
    /**
     * Overrides the superclass's buy method to handle the purchase process suitable for special vending machines.
     */
    @Override
    public void buy() {
        String result = vnd.purchaseItem(total, selectedCake,selectedItems);
    
        if (result.contains("Payment successful")) {
            JOptionPane.showMessageDialog(null,result+"\nPlease reselect the cake and add ons before buying again.", "Purchase successful!", JOptionPane.INFORMATION_MESSAGE);
            recordPurchase(selectedCake);
        } else {
            JOptionPane.showMessageDialog(null, result+"\nPlease reselect the cake, the add ons, and add bills before trying to buy again.","Purchase failed!", JOptionPane.INFORMATION_MESSAGE);
        }
        total = 0;
        totalPrice = 0;
        for(int j=0;j<buttons.length;j++){
            buttons[j].setBackground(Color.LIGHT_GRAY);
        }
        for(int j=0;j<spcButtons.length;j++){
            spcButtons[j].setBackground(Color.LIGHT_GRAY);
            spcButtons[j].setEnabled(true);
        }
        if(selectedCake!=-1){
            itemInfoTextArea[selectedCake].setText(vnd.getCakeDetails(selectedCake));
        }
        for(int i: selectedItems){
            spcItemInfoTextArea[i].setText(vnd.getItemDetails(i));
        }
        walletLabel.setText(totalPrice + " Pesos");
        amountTotal.setText(total +" Pesos");
        selectedCake = -1;
        selectedItems.clear(); 
    }
    

    /**
    * Overrides the initialization of the maintenance menu buttons + positions.
    */
    @Override
    public void initializeMaintenanceButtons() {
        yPos = 50;
        for (int i = 0; i < 6; i++) {
            
            setupMaintenanceMenuAssets(i);
            yPos += 75;
            if(i == 1) {
                yPos += 75;
            }
            else if(i == 4) {
                yPos = 200;
            }
        }
        yPos = 50;
    }
    /**
    * Sets up the maintenance menu assets for the specified index.
    *
    * @param index The index of the maintenance menu asset.
    */
    @Override
    public void setupMaintenanceMenuAssets(int index) {
        if(index == 2)
            maintenanceB[index] = new JButton("4");
        else if(index == 5)
            maintenanceB[index] = new JButton("3");
        else if(index > 2)
            maintenanceB[index] = new JButton(index + 2 + "");
        else
            maintenanceB[index] = new JButton(index + 1 + "");

        maintenanceB[index].setBounds(xPos, yPos, 75, 50);
        maintenanceB[index].addActionListener(this);
        maintenanceB[index].setVisible(false);

        maintenanceP[index] = new JPanel();
        maintenanceP[index].setBounds(xPos + 100, yPos, 200, 50);
        maintenanceP[index].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        maintenanceP[index].setVisible(false);

        String maintenanceLabel = getMaintenanceLabel(index);
        maintenanceL[index] = new JLabel(maintenanceLabel);
        maintenanceL[index].setVisible(true);
        maintenanceP[index].add(maintenanceL[index]);
        add(maintenanceP[index]);
        add(maintenanceB[index]);

        mainMainP[index] = new JPanel();
        mainMainP[index].setBounds(400, 50, 600, 450);
        mainMainP[index].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainMainP[index].setLayout(new GridLayout(0,1,1,0));
        mainMainP[index].setVisible(false);

        add(mainMainP[index]);
    }
    /**
    * Overrieds and retrieves the maintenance label for the specified index.
    *
    * @param index The index of the maintenance label.
    * @return The maintenance label corresponding to the given index.
    */
    @Override
    protected String getMaintenanceLabel(int index) {
        switch (index) {
            case 0:
                return "Coin Maintenance";
            case 1:
                return "Item Maintenance";
            case 2:
                return "Set Defaults";
            case 3:
                return "Collect Payments";
            case 4:
                return "Items Bought";
            case 5:
                return "Spc Item Maintenance";
            default:
                return "Maintenance " + (index + 1);
        }
    }
    /**
     * Overrides and toggles the visibility of maintenance menu panels based on the chosen option.
    * 
    * @param chosen The index of the maintenance menu panel to be made visible.
    */
    @Override
    public void maintenanceMenuToggle(int Chosen) {
        addCake.setVisible(false);
        delCake.setVisible(false);
        ediCake.setVisible(false);

        addItem.setVisible(false);
        delItem.setVisible(false);
        ediItem.setVisible(false);

        for(int i = 0; i < 6; i++){
            mainMainP[i].setVisible(false);
        }
        mainMainP[Chosen].setVisible(true);
    }
    /**
    * Toggles the visibility of item menu panels based on the chosen option.
    * 
    * @param chosen The index of the item menu panel to be made visible.
    */
    public void spcItemMenuToggle(int Chosen) {
        for(int i = 0; i < 6; i++){
            mainMainP[i].setVisible(false);
        }
        delItem.setVisible(false);
        addItem.setVisible(false);
        ediItem.setVisible(false);

        switch (Chosen) {
            case 1:
                ediItem.setVisible(true);
                break;
            case 2:
                delItem.setVisible(true);
                break;
            case 3:
                addItem.setVisible(true);
                break;
            default:
                break;
        }
    }
    /**
     * handle initializationo of Spc Item edit menus
     */
    public void itemSpcMaintenance() {
        addItem();
        deleteItem();
        editItem();

        spcItemEditButton.addActionListener(this);
        spcDeleItemButton.addActionListener(this);
        spcAddItemButton.addActionListener(this);
        
        mainMainP[5].add(spcItemEditButton);
        mainMainP[5].add(spcDeleItemButton);
        mainMainP[5].add(spcAddItemButton);
        mainMainP[5].setVisible(false);
    }
    /**
     * Menu for adding a new specific item in the system
     */
    public void addItem() {
        addItem.setBounds(400, 50, 600, 450);
        addItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        addItem.setLayout(new GridLayout(0,1,1,0));
        addItem.setVisible(false);
        add(addItem);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false); // Only allow valid integers

        addName = new JFormattedTextField();
        addDesc = new JFormattedTextField();
        addCalo = new JFormattedTextField();
        addPric = new JFormattedTextField(formatter);
        addQuan = new JFormattedTextField(formatter);
        addTag = new JFormattedTextField();
        addFlav = new JFormattedTextField();
        addConfirm = new JButton("Confirm");
        addConfirm.addActionListener(this);

        addItem.add(new JLabel("Enter New Item Name:"));
        addItem.add(addName);
        addItem.add(new JLabel("Enter New Item Description:"));
        addItem.add(addDesc);
        addItem.add(new JLabel("Enter New Item Calorie Count:"));
        addItem.add(addCalo);
        addItem.add(new JLabel("Enter New Item Price:"));
        addItem.add(addPric);
        addItem.add(new JLabel("Enter New Item Stock [Max 10]:"));
        addItem.add(addQuan);
        addItem.add(new JLabel("Enter New Item Tag:"));
        addItem.add(addTag);
        addItem.add(new JLabel("Enter New Item Flavor Text:"));
        addItem.add(addFlav);
        addItem.add(addConfirm);

        addItem.setVisible(false);
    }
    /**
     * Menu for deleting a specific item in the system
     */
    public void deleteItem() {
        delItem.setBounds(400, 50, 600, 450);
        delItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        delItem.setLayout(new GridLayout(0,1,1,0));
        delItem.setVisible(false);
        add(delItem);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false); // Only allow valid integers

        delete = new JFormattedTextField(formatter);
        delConfirm = new JButton("Confirm");
        delConfirm.addActionListener(this);

        delItem.add(new JLabel("Insert Item Number To Delete"));
        delItem.add(delete);
        delItem.add(delConfirm);
        delItem.setVisible(false);
    }
    /**
     * Menu for editing a specific item in the system
     */
    public void editItem() {
        ediItem.setBounds(400, 50, 600, 450);
        ediItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ediItem.setLayout(new GridLayout(0,1,1,0));
        ediItem.setVisible(false);
        add(ediItem);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false); // Only allow valid integers

        inde = new JFormattedTextField();
        vari = new JFormattedTextField();
        editRadioButtons = new ButtonGroup();
        editConfirmItem = new JButton("Confirm Changes");
        editConfirmItem.addActionListener(this);

        name = new JRadioButton("Name");
        name.addActionListener(this);
        desc = new JRadioButton("Description");
        desc.addActionListener(this);
        calo = new JRadioButton("Calorie Count");
        calo.addActionListener(this);
        pric = new JRadioButton("Price");
        pric.addActionListener(this);
        quaA = new JRadioButton("Add Stock");
        quaA.addActionListener(this);      
        quaM = new JRadioButton("Remove Stock");
        quaM.addActionListener(this);
        ediItem.add(new JLabel("Insert Item Number"));
        ediItem.add(inde);
        
        editRadioButtons.add(name);
        editRadioButtons.add(desc);
        editRadioButtons.add(calo);
        editRadioButtons.add(pric);
        editRadioButtons.add(quaA);
        editRadioButtons.add(quaM);

        ediItem.add(new JLabel("What To Edit"));
        ediItem.add(name);
        ediItem.add(desc);
        ediItem.add(calo);
        ediItem.add(pric);
        ediItem.add(quaA);
        ediItem.add(quaM);

        ediItem.add(new JLabel("Enter New Data"));
        ediItem.add(vari);
        ediItem.add(editConfirmItem);
        ediItem.setVisible(false);
    }
    /**
     * Sets up the "Default Maintenance" menu with a label indicating that default settings have been restored.
     */
    @Override
    public void defaultMaintenance() {
        mainMainP[2].add(new JLabel("Defaults Settings Restored"), CENTER_ALIGNMENT);
        mainMainP[2].setVisible(false);
    }
    /**
    * Sets the variables and label of Collect Payments to base as well turning its visibility to false
    */
    @Override
    public void collectPayments() {
        profit = 0;
        colProfits = new JLabel("Profit: "+ profit);
        mainMainP[3].add(colProfits);
        mainMainP[3].setVisible(false);
    }
    /**
    * Sets the variables and text area of Collect Receipt to base as well turning its visibility to false
    */
    @Override
    public void itemBought() {
        receipt = " ";
        colReceipt = new JTextArea(receipt);
        colReceipt.setEditable(false);
        mainMainP[4].add(colReceipt);
        mainMainP[4].setVisible(false);
    }
    /**
    * Sets up a NEW panel to display detailed information for a NEW item in the vending machine user interface.
    *
    * @param itemIndex The index of the item in the vendingP array.
    */
    public void setupSpcItemInfoPanel(int itemIndex) {
        spcVendingP[itemIndex] = new JPanel();
        spcVendingP[itemIndex].setBounds(725, 30, 350, 250);
        spcVendingP[itemIndex].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        spcVendingP[itemIndex].setLayout(new BorderLayout());

        // Create a JTextArea to display the item information
        spcItemInfoTextArea[itemIndex] = new JTextArea();
        spcItemInfoTextArea[itemIndex].setEditable(false);
        spcItemInfoTextArea[itemIndex].setLineWrap(true);
        spcItemInfoTextArea[itemIndex].setWrapStyleWord(true);

        // Get the item information using getItemInfoString() method
        String itemInfo = vnd.getItemDetails(itemIndex);

        // Set the item information as the text of the JTextArea
        spcItemInfoTextArea[itemIndex].setText(itemInfo);
    
        // Add the JTextArea to the SOUTH of the spcVendingP panel
        spcVendingP[itemIndex].add(spcItemInfoTextArea[itemIndex], BorderLayout.SOUTH);
    
        spcVendingP[itemIndex].setVisible(false);
        add(spcVendingP[itemIndex]);
    }
    /**
    * Updates panel to display detailed information for an item in the vending machine user interface.
    *
    * @param itemIndex The index of the item in the vendingP array.
    */
    public void spcUpdateItemDetailPanel(int itemIndex) {
        String itemInfo = vnd.getItemDetails(itemIndex);
        spcItemInfoTextArea[itemIndex].setText(itemInfo);
        spcItemInfoTextArea[itemIndex].setEditable(false);
    }
    /**
     * Overrides the superclass's actionPerformed method to handle more button actions.
     */
    @Override
    public void actionPerformed(ActionEvent click) {
        super.actionPerformed(click);
        for (int i = 0; i < spcButtons.length; i++) {
            if (click.getSource() == spcButtons[i] && menuInt == 0) {
                for(int j=0;j<spcButtons.length;j++){
                    spcVendingP[j].setVisible(false);
                    //spcButtons[j].setBackground(Color.LIGHT_GRAY);
                }
                for(int j=0;j<vendingP.length;j++){
                    vendingP[j].setVisible(false);
                }
                spcVendingP[i].setVisible(true);
                totalPrice += vnd.getItemPrice(i);
                selectedItems.add(i);
                amountTotal.setText( totalPrice + "Pesos");
                spcButtons[i].setBackground(Color.GREEN);
                spcButtons[i].setEnabled(false);
            }    
        }
        if (click.getSource() == clearAddOn && menuInt == 0) {
            for(int i: selectedItems){
                totalPrice -= vnd.getItemPrice(i);
            }
            amountTotal.setText( totalPrice + "Pesos");
            selectedItems.clear();
            for(int j=0;j<spcButtons.length;j++){
                spcButtons[j].setEnabled(true);
                spcButtons[j].setBackground(Color.LIGHT_GRAY);
            }
        }
        else if (click.getSource() == spcItemEditButton) {
            spcItemMenuToggle(1);
        }
        else if (click.getSource() == spcDeleItemButton) {
            spcItemMenuToggle(2);
        }
        else if (click.getSource() == spcAddItemButton) {
            spcItemMenuToggle(3);
        }
        else if ((click.getSource() == editConfirmItem) && wMenu == 2) {
            String editValString = vari.getText();
            int editVal = parseFieldValue(vari);
            int choice;
            int index = (parseFieldValue(inde) - 1);
            if(name.isSelected()) {
                choice = 11;
                vnd.editItem(choice, index, editValString);
            }
            else if(desc.isSelected()) {
                choice = 12;
                vnd.editItem(choice, index, editValString);
            }
            else if(calo.isSelected()) {
                choice = 13;
                vnd.editItem(choice, index, editVal);
            }
            else if(pric.isSelected()) {
                choice = 14;
                vnd.editItem(choice, index, editVal);
            }
            else if(quaA.isSelected()) {
                choice = 15;
                vnd.editItem(choice, index, editVal);
            }
            else if(quaM.isSelected()) {
                choice = 16;
                vnd.editItem(choice, index, editVal);
            }
            spcUpdateItemDetailPanel(index);
            ediItem.setVisible(false);
            added.setVisible(true);
        }
        else if ((click.getSource() == addConfirm) && wMenu == 2) {
            int count = 12;
            addItem.setVisible(false);
            added.setVisible(true);
            vnd.addNewItem(addName.getText(), addDesc.getText(), parseFieldValue(addCalo), parseFieldValue(addPric), parseFieldValue(addQuan), addTag.getText(), addFlav.getText());
            setupSpcItemInfoPanel(count);
            count++;
        } 
        else if ((click.getSource() == delConfirm) && wMenu == 2) {
            delItem.setVisible(false);
            added.setVisible(true);
            vnd.deleteAnItem((parseFieldValue(delete)));
            spcUpdateItemDetailPanel((parseFieldValue(delete)));
        }
        else if ((click.getSource() == editConfirmItem) && wMenu == 1) {
            String editValString = vari.getText();
            int editVal = parseFieldValue(vari);
            int choice;
            int index = (parseFieldValue(inde) - 1);
            if(name.isSelected()) {
                choice = 1;
                vnd.editCake(choice, index, editValString);
            }
            else if(desc.isSelected()) {
                choice = 2;
                vnd.editCake(choice, index, editValString);
            }
            else if(calo.isSelected()) {
                choice = 3;
                vnd.editCake(choice, index, editVal);
            }
            else if(pric.isSelected()) {
                choice = 4;
                vnd.editCake(choice, index, editVal);
            }
            else if(quaA.isSelected()) {
                choice = 5;
                vnd.editCake(choice, index, editVal);
            }
            else if(quaM.isSelected()) {
                choice = 6;
                vnd.editCake(choice, index, editVal);
            }
            updateItemDetailPanel(index);
            ediItem.setVisible(false);
            added.setVisible(true);
        }
        else if ((click.getSource() == addConfirm) && wMenu == 1) {
            int count = 10;
            addItem.setVisible(false);
            added.setVisible(true);
            vnd.addNewCake(addName.getText(), addDesc.getText(), parseFieldValue(addCalo), parseFieldValue(addPric), parseFieldValue(addQuan));
            updateNewItemDetailPanel(count);
            count++;
        } 
        else if ((click.getSource() == delConfirm) && wMenu == 1) {
            vnd.deleteACake((parseFieldValue(delete)-1));
            setupItemDetailPanel((parseFieldValue(delete)-1));
        }
        else if(click.getSource() == maintenanceB[1]) {
            maintenanceMenuToggle(1);
            wMenu = 1;
        }
        else if(click.getSource() == maintenanceB[3]) {
            maintenanceMenuToggle(3);
            int profit = vnd.collectProfit();
            colProfits.setText("Profit: "+ profit);
            profit = 0;
        }
        else if(click.getSource() == maintenanceB[4]) {
            maintenanceMenuToggle(4);
            String receipt = vnd.receipt();
            colReceipt.setText(receipt);
            receipt = " ";
        }
        else if(click.getSource() == maintenanceB[5]) {
            maintenanceMenuToggle(5);
            wMenu = 2;
        }
    }
}
