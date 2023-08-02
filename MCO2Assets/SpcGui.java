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
    }
    /**
     * Overrides the superclass's inVisible method to make certain components invisible.
     * It makes the "Clear Add-ons" button and all special item buttons invisible.
     */
    @Override
    public void inVisible() {
        super.inVisible();
        clearAddOn.setVisible(false);

        for(int i = 0; i < spcButtons.length; i++) {
            spcVendingP[i].setVisible(false);
            spcButtons[i].setVisible(false);
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
     * Overrides the superclass's itemMaintenance method to handle initializationo of Spc Item edit menus
     */
    @Override
    public void itemMaintenance() {
        addItem();
        deleteItem();
        editItem();

        itemEditButton.addActionListener(this);
        deleCakeButton.addActionListener(this);
        addCakeButton.addActionListener(this);
        
        mainMainP[1].add(itemEditButton);
        mainMainP[1].add(deleCakeButton);
        mainMainP[1].add(addCakeButton);
        mainMainP[1].setVisible(false);
    }
    /**
     * Menu for adding a new specific item in the system
     */
    public void addItem() {
        addCake.setBounds(400, 50, 600, 450);
        addCake.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        addCake.setLayout(new GridLayout(0,1,1,0));
        addCake.setVisible(false);
        add(addCake);

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

        addCake.add(new JLabel("Enter New Item Name:"));
        addCake.add(addName);
        addCake.add(new JLabel("Enter New Item Description:"));
        addCake.add(addDesc);
        addCake.add(new JLabel("Enter New Item Calorie Count:"));
        addCake.add(addCalo);
        addCake.add(new JLabel("Enter New Item Price:"));
        addCake.add(addPric);
        addCake.add(new JLabel("Enter New Item Stock [Max 10]:"));
        addCake.add(addQuan);
        addCake.add(new JLabel("Enter New Item Tag:"));
        addCake.add(addTag);
        addCake.add(new JLabel("Enter New Item Flavor Text:"));
        addCake.add(addFlav);
        addCake.add(addConfirm);

        addCake.setVisible(false);
    }
    /**
     * Menu for deleting a specific item in the system
     */
    public void deleteItem() {
        delCake.setBounds(400, 50, 600, 450);
        delCake.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        delCake.setLayout(new GridLayout(0,1,1,0));
        delCake.setVisible(false);
        add(delCake);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false); // Only allow valid integers

        delete = new JFormattedTextField(formatter);
        delConfirm = new JButton("Confirm");
        delConfirm.addActionListener(this);

        delCake.add(new JLabel("Insert Item Number To Delete"));
        delCake.add(delete);
        delCake.add(delConfirm);
        delCake.setVisible(false);
    }
    /**
     * Menu for editing a specific item in the system
     */
    public void editItem() {
        ediCake.setBounds(400, 50, 600, 450);
        ediCake.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ediCake.setLayout(new GridLayout(0,1,1,0));
        ediCake.setVisible(false);
        add(ediCake);

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
        ediCake.add(new JLabel("Insert Item Number"));
        ediCake.add(inde);
        
        editRadioButtons.add(name);
        editRadioButtons.add(desc);
        editRadioButtons.add(calo);
        editRadioButtons.add(pric);
        editRadioButtons.add(quaA);
        editRadioButtons.add(quaM);

        ediCake.add(new JLabel("What To Edit"));
        ediCake.add(name);
        ediCake.add(desc);
        ediCake.add(calo);
        ediCake.add(pric);
        ediCake.add(quaA);
        ediCake.add(quaM);

        ediCake.add(new JLabel("Enter New Data"));
        ediCake.add(vari);
        ediCake.add(editConfirmItem);
        ediCake.setVisible(false);
    }
    /**
    * Sets up a NEW panel to display detailed information for a NEW item in the vending machine user interface.
    *
    * @param itemIndex The index of the item in the vendingP array.
    */
    @Override
    public void setupItemDetailPanel(int itemIndex) {
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
    * Updates panel to display detailed information for a item in the vending machine user interface.
    *
    * @param itemIndex The index of the item in the spcVendingP array.
    */
    @Override
    public void updateNewItemDetailPanel(int itemIndex) {
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
        else if (click.getSource() == editConfirmItem) {
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
            updateItemDetailPanel(index);
            ediCake.setVisible(false);
            added.setVisible(true);
        }
        else if (click.getSource() == addConfirm) {
            int count = 12;
            addCake.setVisible(false);
            added.setVisible(true);
            vnd.addNewItem(addName.getText(), addDesc.getText(), parseFieldValue(addCalo), parseFieldValue(addPric), parseFieldValue(addQuan), addTag.getText(), addFlav.getText());
            setupItemDetailPanel(count);
            count++;
        } 
        else if (click.getSource() == delConfirm) {
            delCake.setVisible(false);
            added.setVisible(true);
            vnd.deleteAnItem((parseFieldValue(delete)));
            setupItemDetailPanel((parseFieldValue(delete)));
        }
        else if(click.getSource() == maintenanceB[2]) {
            maintenanceMenuToggle(2);
            vnd.setDefaults();
        }
        else if(click.getSource() == maintenanceB[3]) {
            int profit = vnd.collectProfit();
            colProfits.setText("Profit: "+ profit);
            profit = 0;
        }
        else if(click.getSource() == maintenanceB[4]) {
            String receipt = vnd.receipt();
            colReceipt.setText(receipt);
            receipt = " ";
        }
    }
}

