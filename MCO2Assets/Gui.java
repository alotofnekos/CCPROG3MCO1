import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class Gui handles the GUI for special vending machines.
 */
public class Gui extends JFrame implements ActionListener {
    protected JMenuBar menu;
    protected JMenu maintMenu;
    protected JMenuItem initializeMachine;
    protected JMenuItem itemMaintenance;

    // Arrays
    protected JButton[] buttons;
    protected JPanel[] vendingP;
    protected JButton buy;
    protected JButton coin;

    // Size variables
    protected int width;
    protected int length;

    // Item variables
    protected ArrayList<String> itemNames = new ArrayList<>();
    protected ArrayList<String> itemImageFileNames = new ArrayList<>();

    // User money variables
    protected JPanel wallet;
    protected JTextArea walletLabel;
    protected JTextArea amountTotal;
    protected JTextArea[] itemInfoTextArea;

    // Item bought variables
    protected String indexBought[];
    protected JTextArea[] indexBoughtInfo;

    // Maintenance menu variables
    protected JLabel[] maintenanceL;
    protected JButton[] maintenanceB;
    protected JPanel[] maintenanceP;
    protected JPanel[] mainMainP;

    // Add coin variables
    protected JButton editConfirmCoin;
    protected JFormattedTextField p10;
    protected JFormattedTextField p20;
    protected JFormattedTextField p50;
    protected JFormattedTextField p100;
    protected JFormattedTextField p200;
    protected JFormattedTextField p500;

    //Item maintenance variables
    protected JPanel addCake;
    protected JPanel delCake;
    protected JPanel ediCake;
    protected JButton addCakeButton;
    protected JButton deleCakeButton;
    protected JButton itemEditButton;
    protected JPanel added;

    // Add cake variables
    protected JButton addConfirm;
    protected JFormattedTextField addName;
    protected JFormattedTextField addDesc;
    protected JFormattedTextField addCalo;
    protected JFormattedTextField addPric;
    protected JFormattedTextField addQuan;

    // Edit cake variables
    protected JButton editConfirmItem;
    protected ButtonGroup editRadioButtons;
    protected JFormattedTextField inde;
    protected JFormattedTextField vari;
    
    // Item edit variables
    protected JRadioButton name;
    protected JRadioButton desc;
    protected JRadioButton calo;
    protected JRadioButton pric;
    protected JRadioButton quaA;
    protected JRadioButton quaM;

    // Delete cake variables
    protected JFormattedTextField delete;
    protected JButton delConfirm;

    // Collect profit and receipt variables
    protected JTextArea colReceipt;
    protected JLabel colProfits;
    protected String receipt;
    protected int bought;
    protected int profit;

    // XY position of buttons
    protected int xPos = 20;
    protected int yPos = 30;
    
    // Menu indicator
    int menuInt;

    //NOT FOR GUI
    protected int[] vndBills = {0, 0, 0, 0, 0, 0};
    protected RgVnd vnd = new RgVnd();
    protected int selectedCake =-1;
    protected int total = 0;
    protected int totalPrice = 0;
    protected int previousCake = 0;
    /**
     * The constructor for the `Gui` class. Initializes and sets up the graphical user interface
     * components for the regular cake vending machine.
     */
    public Gui() {
        // Size
        width = 1100;
        length = 600;
        // Frame initialization
        menu = new JMenuBar();
        maintMenu = new JMenu("Admin");
        initializeMachine = new JMenuItem("Initialize Machine");
        itemMaintenance = new JMenuItem("Item Maintenance");
        // Vending machine initialization
        buy = new JButton("Buy");
        coin = new JButton("Insert Coins & Bills!");
        buttons = new JButton[20];
        vendingP = new JPanel[20];
        // Maintenance menu initialization
        maintenanceB = new JButton[6];
        maintenanceP = new JPanel[6];
        maintenanceL = new JLabel[6];
        mainMainP = new JPanel[6];
        addCake = new JPanel();
        delCake = new JPanel();
        ediCake = new JPanel();
        itemEditButton = new JButton("Cake Edit Menu");
        deleCakeButton = new JButton("Delete Cake");
        addCakeButton = new JButton("Add Cake");
        // Purchase record initialization
        indexBought = new String[100];
        indexBoughtInfo = new JTextArea[100]; 
        bought = 0;
        // Item data panel intialization
        itemInfoTextArea = new JTextArea[20]; 
        for (int i = 0; i < 5; i++) {
            mainMainP[i] = new JPanel(); 
        }
        // User wallet intialization
        wallet = new JPanel();
        vnd.setDefaults();
        int i=0;
        while(vnd.validItem(i)){
            itemNames.add("Cake#"+(i+1));
            i++;
        }
        
      
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\AppleCrumble2.jpg");
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\BlackForestCake1.jpg");
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\PremiumChocolateCake3.jpg");
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\Strawberry Shortcake2.jpg");
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\LemonBlueberryCake.jpg");
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\ChocolateMousse2.jpg");
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\butterscotch2.jpg");
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\Mochadelight2.jpg");
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\mangocake.jpg");
        itemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\carrotcake.jpg");
        Arrays.fill(buttons, new JButton());
        Arrays.fill(vendingP, new JPanel());
    }
    
/**
 * This method initializes and displays the main vending machine user interface.
 * It sets up and displays the item buttons, maintenance buttons, coin maintenance,
 * item maintenance, default maintenance, cake added, user options, and the menu bar.
 */
    public void Display() {
        initializeItemButtons();
        initializeMaintenanceButtons();
        coinMaintenance();
        itemMaintenance();
        defaultMaintenance();
        
        collectPayments();
        itemBought();
        
        cakeAdded();
        initializeUserOptions();
        createMenu();
        setupMainFrame();

        vendingMachinePanel();
    }
/**
 * This method sets up the main frame of the vending machine user interface.
 */
    public void setupMainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setSize(width, length);
        setJMenuBar(menu);
        setVisible(true);
        Font customFont = new Font("Arial", Font.PLAIN, 8);
        setCustomButtonFont(customFont);
    }
/**
 * This method creates and sets up the menu bar for the vending machine user interface.
 * It adds action listeners to the "Initialize Machine" and "Item Maintenance" menu items.
 * The "Initialize Machine" and "Item Maintenance" menu items are added to the "Admin" menu.
 * The "Admin" menu is then added to the menu bar.
 */
    public void createMenu() {
        initializeMachine.addActionListener(this);
        itemMaintenance.addActionListener(this);

        menu.add(maintMenu);
        maintMenu.add(initializeMachine);
        maintMenu.add(itemMaintenance);
    }
/**
 * This method initializes and sets up the item buttons for the vending machine user interface.
 * It iterates through the item list and corresponding image file names, and creates buttons for each item.
 * If an item has a valid name and image file name, the button is created with the correct information.
 * If an item does not have a valid name or image file name, a default button is created.
 * The buttons are positioned based on the item index and arranged in rows of 10.
 */
    public void initializeItemButtons() {
        String itemName;
        String imageFileName;
        ImageIcon imageIcon;
        for (int itemIndex = 0; itemIndex < 20; itemIndex++) {
            if (itemIndex < itemNames.size() && itemIndex < itemImageFileNames.size()) {
                itemName = itemNames.get(itemIndex);
                imageFileName = itemImageFileNames.get(itemIndex);
                imageIcon = loadImageIcon(imageFileName,50,50);
            } else {
                itemName = "Default";
                imageFileName = "C:\\\\\\\\Users\\\\\\\\Angel\\\\\\\\Downloads\\\\\\\\CCPROG3MCO1\\\\\\\\MCO2Assets\\\\\\\\genericcake.jpg"; // Replace with the correct path to your default image
                imageIcon = loadImageIcon(imageFileName,50,50);
            }
            setupItemButton(itemIndex, xPos, yPos, itemName, imageIcon);
            if (itemIndex < itemImageFileNames.size()) {
                setupItemDetailPanel(itemIndex);
            }
            xPos += 70;
            if (itemIndex<=20 && (itemIndex + 1) % 10 == 0) {
                xPos = 20;
                yPos += 120;
            }
        }
    }
    
/**
 * Sets a custom font for all the buttons in the vending machine user interface.
 * @param font The Font object representing the custom font to be applied to the buttons.
 */  
    protected void setCustomButtonFont(Font font) {
        for (JButton button : buttons) {
            if (button != null) {
                button.setFont(font);
            }
        }
        
    }
/**
 * Sets up a individual item buttons and detail panels in the vending machine user interface.
 *
 * @param itemIndex The index of the item in the buttons array.
 * @param x The x-coordinate of the button's position.
 * @param y The y-coordinate of the button's position.
 * @param itemName The name of the item to be displayed on the button.
 * @param imageIcon The ImageIcon representing the image to be displayed on the button.
 */
    public void setupItemButton(int itemIndex, int x, int y, String itemName, ImageIcon imageIcon) {
        buttons[itemIndex] = new JButton(itemName);
        buttons[itemIndex].setBounds(x, y, 70, 75);
        buttons[itemIndex].addActionListener(this);
        buttons[itemIndex].setVisible(false);

        vendingP[itemIndex] = new JPanel(); 
        vendingP[itemIndex].setBounds(725, 30, 350, 250);
        vendingP[itemIndex].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vendingP[itemIndex].setLayout(new BorderLayout());

        JLabel itemImageLabel = new JLabel(imageIcon);
        JLabel textLabel = new JLabel(itemName);
        itemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vendingP[itemIndex].add(itemImageLabel, BorderLayout.CENTER);
        vendingP[itemIndex].add(textLabel, BorderLayout.SOUTH); // Add the textLabel to the SOUTH
        buttons[itemIndex].setMargin(new Insets(5, 5, 5, 5));
        buttons[itemIndex].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Set the layout to FlowLayout
        buttons[itemIndex].add(itemImageLabel, BorderLayout.NORTH);
        buttons[itemIndex].add(textLabel, BorderLayout.SOUTH);
        vendingP[itemIndex].setVisible(false);
        add(vendingP[itemIndex]);
        add(buttons[itemIndex]);
    }
/**
 * Sets up a panel to display detailed information for a specific item in the vending machine user interface.
 *
 * @param itemIndex The index of the item in the vendingP array.
 */
    public void setupItemDetailPanel(int itemIndex) {
        vendingP[itemIndex] = new JPanel();
        vendingP[itemIndex].setBounds(725, 30, 350, 250);
        vendingP[itemIndex].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vendingP[itemIndex].setLayout(new BorderLayout());
    
        // Load the image and create a JLabel as before
        ImageIcon itemImageIcon = loadImageIcon(itemImageFileNames.get(itemIndex), 125, 125);
        JLabel itemImageLabel = new JLabel(itemImageIcon);
        itemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vendingP[itemIndex].add(itemImageLabel, BorderLayout.CENTER);
    
        // Create a JTextArea to display the item information
        itemInfoTextArea[itemIndex] = new JTextArea();
        itemInfoTextArea[itemIndex].setEditable(false);
        itemInfoTextArea[itemIndex].setLineWrap(true);
        itemInfoTextArea[itemIndex].setWrapStyleWord(true);
    
        // Get the item information using getItemInfoString() method
        String itemInfo = vnd.getCakeDetails(itemIndex);
    
        // Set the item information as the text of the JTextArea
        itemInfoTextArea[itemIndex].setText(itemInfo);
    
        // Add the JTextArea to the SOUTH of the vendingP panel
        vendingP[itemIndex].add(itemInfoTextArea[itemIndex], BorderLayout.SOUTH);
    
        vendingP[itemIndex].setVisible(false);
        add(vendingP[itemIndex]);
        }
/**
 * Sets up a NEW panel to display detailed information for a NEW item in the vending machine user interface.
 *
 * @param itemIndex The index of the item in the vendingP array.
 */
    public void updateNewItemDetailPanel(int itemIndex) {
        // Create a JTextArea to display the item information
        buttons[itemIndex].setText("Cake#" + (itemIndex+1));

        itemInfoTextArea[itemIndex] = new JTextArea();
        itemInfoTextArea[itemIndex].setEditable(false);
        itemInfoTextArea[itemIndex].setLineWrap(true);
        itemInfoTextArea[itemIndex].setWrapStyleWord(true);
    
        // Get the item information using getItemInfoString() method
        String itemInfo = vnd.getCakeDetails(itemIndex);
    
        // Set the item information as the text of the JTextArea
        itemInfoTextArea[itemIndex].setText(itemInfo);
    
        // Add the JTextArea to the SOUTH of the vendingP panel
        vendingP[itemIndex].add(itemInfoTextArea[itemIndex], BorderLayout.SOUTH);
    
        vendingP[itemIndex].setVisible(false);
        add(vendingP[itemIndex]);
    }
/**
 * Updates the specific item panel to display NEW detailed information in the vending machine user interface.
 *
 * @param itemIndex The index of the item in the vendingP array.
 */
    public void updateItemDetailPanel(int itemIndex) {
        String itemInfo = vnd.getCakeDetails(itemIndex);
        itemInfoTextArea[itemIndex].setText(itemInfo);
        itemInfoTextArea[itemIndex].setEditable(false);
    }
/**
 * Loads and resizes an image file to create an ImageIcon with the specified width and height.
 *
 * @param imageFileName The file path of the image to load.
 * @param width         The desired width of the resized image.
 * @param height        The desired height of the resized image.
 * @return An ImageIcon created from the resized image, or the default cake image if loading fails.
 */
    protected ImageIcon loadImageIcon(String imageFileName,int width,int height) {
        // Load the original image
        BufferedImage originalImage;
        ImageIcon imageIcon = null;

        try {
            originalImage = ImageIO.read(new File(imageFileName));
            // Resize the image to the desired width and height
            int desiredWidth = width; 
            int desiredHeight = height; 
            Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
            // Create the ImageIcon from the resized image
            imageIcon = new ImageIcon(resizedImage);
        } catch (Exception ex) {
            // If the image file is not found or any other exception occurs, load the default cake image
            ex.printStackTrace();
            imageIcon = new ImageIcon("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\genericcake.jpg"); 
        }

        return imageIcon;
    }

/**
 * Initializes the maintenance menu buttons positions.
 */
    public void initializeMaintenanceButtons() {
        yPos = 50;
        for (int i = 0; i < 5; i++) {
            setupMaintenanceMenuAssets(i);
            yPos += 100;
        }
        yPos = 50;
    }
/**
 * Sets up the maintenance menu assets for the specified index.
 *
 * @param index The index of the maintenance menu asset.
 */
    public void setupMaintenanceMenuAssets(int index) {
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
 * Retrieves the maintenance label for the specified index.
 *
 * @param index The index of the maintenance label.
 * @return The maintenance label corresponding to the given index.
 */
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
            default:
                return "Maintenance " + (index + 1);
        }
    }
/**
 * Initializes the coin maintenance menu with text fields and buttons for adding coins.
 */
    public void coinMaintenance() {
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false); // Only allow valid integers

        p10 = new JFormattedTextField(formatter);
        p20 = new JFormattedTextField(formatter);
        p50 = new JFormattedTextField(formatter);
        p100 = new JFormattedTextField(formatter);
        p200 = new JFormattedTextField(formatter);
        p500 = new JFormattedTextField(formatter);
        editConfirmCoin = new JButton("Confirm Changes");
        editConfirmCoin.addActionListener(this);

        mainMainP[0].add(new JLabel("10 Peso Coins to be added:"));
        mainMainP[0].add(p10);
        mainMainP[0].add(new JLabel("20 Peso Coins to be added:"));
        mainMainP[0].add(p20);
        mainMainP[0].add(new JLabel("50 Peso Coins to be added:"));
        mainMainP[0].add(p50);
        mainMainP[0].add(new JLabel("100 Peso Coins to be added:"));
        mainMainP[0].add(p100);
        mainMainP[0].add(new JLabel("200 Peso Coins to be added:"));
        mainMainP[0].add(p200);
        mainMainP[0].add(new JLabel("500 Peso Coins to be added:"));
        mainMainP[0].add(p500);
        mainMainP[0].add(editConfirmCoin);
        mainMainP[0].setVisible(false);
    }
/**
 * Initializes the item maintenance menu with 3 buttons representing the 3 of its main functions add, edit, delete.
 */
    public void itemMaintenance() {
        addCake();
        deleteCake();
        editCake();

        itemEditButton.addActionListener(this);
        deleCakeButton.addActionListener(this);
        addCakeButton.addActionListener(this);
        
        mainMainP[1].add(itemEditButton);
        mainMainP[1].add(deleCakeButton);
        mainMainP[1].add(addCakeButton);
        mainMainP[1].setVisible(false);
    }
/**
 * Initializes the "Add Cake" menu with text fields and a button for adding a new cake to the vending machine.
 */
    public void addCake() {
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
        addCalo = new JFormattedTextField(formatter);
        addPric = new JFormattedTextField(formatter);
        addQuan = new JFormattedTextField(formatter);
        addConfirm = new JButton("Confirm");
        addConfirm.addActionListener(this);

        addCake.add(new JLabel("Enter New Cake Name:"));
        addCake.add(addName);
        addCake.add(new JLabel("Enter New Cake Description:"));
        addCake.add(addDesc);
        addCake.add(new JLabel("Enter New Cake Calorie Count:"));
        addCake.add(addCalo);
        addCake.add(new JLabel("Enter New Cake Price:"));
        addCake.add(addPric);
        addCake.add(new JLabel("Enter New Cake Stock [Max 10]:"));
        addCake.add(addQuan);
        addCake.add(addConfirm);

        addCake.setVisible(false);
    }
/**
 * Initializes the "Delete Cake" menu with a text field and a button for deleting a cake from the vending machine.
 */
    public void deleteCake() {
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

        delCake.add(new JLabel("Insert Cake Number To Delete"));
        delCake.add(delete);
        delCake.add(delConfirm);
        delCake.setVisible(false);
    }
/**
 * Initializes the "Edit Cake" menu with input fields and buttons for editing cake information in the vending machine.
 */
    public void editCake() {
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
        ediCake.add(new JLabel("Insert Cake Number"));
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
 * Sets up the "Default Maintenance" menu with a label indicating that default settings have been restored.
 */
    public void defaultMaintenance() {
        mainMainP[2].add(new JLabel("Defaults Settings Restored"), CENTER_ALIGNMENT);
        mainMainP[2].setVisible(false);
    }
/**
 * Sets the variables and label of Collect Payments to base as well turning its visibility to false
 */
    public void collectPayments() {
        profit = 0;
        colProfits = new JLabel("Profit: "+ profit);
        mainMainP[3].add(colProfits);
        mainMainP[3].setVisible(false);
    }
/**
 * Sets the variables and text area of Collect Receipt to base as well turning its visibility to false
 */
    public void itemBought() {
        receipt = " ";
        colReceipt = new JTextArea(receipt);
        mainMainP[4].add(colReceipt);
        mainMainP[4].setVisible(false);
    }
/**
 * Records a purchase of a cake item and adds it to the indexBought array.
 * 
 * @param itemIndex The index of the cake item that was purchased.
 */
    public void recordPurchase(int itemIndex) {
        String itemInfo = vnd.getCakeDetails(itemIndex);
        indexBought[bought] = itemInfo;
        bought++;
    }
/**
 * Initializes the user options for the vending machine.
 * This includes setting up the "Buy" button, the wallet display, and the coin insertion button.
 */
    public void initializeUserOptions() {
        buy.setBounds(750, 295, 125, 50);
        buy.addActionListener(this);
        buy.setVisible(false);
        add(buy);
        wallet.setBounds(900, 295, 175, 50);
        wallet.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        wallet.setVisible(false);
        JTextArea walletLabelText;
        walletLabelText = new JTextArea("Amount Inserted:");
        walletLabelText.setEditable(false);
        walletLabel = new JTextArea("0 Pesos");
        walletLabel.setEditable(false);
        JTextArea walletLabelText2;
        walletLabelText2 = new JTextArea("Total Price:");
        amountTotal = new JTextArea("0 Pesos");
        walletLabel = new JTextArea("0 Pesos");
        walletLabel.setEditable(false);
        wallet.add(walletLabelText);
        wallet.add(walletLabel);
        wallet.add(walletLabelText2);
        wallet.add(amountTotal);
        add(wallet);

        xPos = 900;
        yPos = 380;
        coin.setBounds(xPos + 10, yPos, 150, 50);
        coin.addActionListener(this);
        coin.setVisible(false);
        add(coin);

    }
/**
 * Makes the necessary components visible based on the current menu state (menuInt).
 * If menuInt is 0, it shows the regular vending machine interface with the "Buy" button, wallet display, coin insertion button, and cake buttons.
 * If menuInt is 1, it shows the maintenance menu with the maintenance buttons and panels.
 */
    public void visible() {
        if (menuInt == 0) {
            buy.setVisible(true);
            wallet.setVisible(true);
            for (int i = 0; i < buttons.length; i++) {;
                buttons[i].setVisible(true);
                buttons[i].setBackground(Color.LIGHT_GRAY);
                coin.setVisible(true);
            }
        } else if (menuInt == 1) {
            for (int i = 0; i < 5; i++) {
                maintenanceB[i].setVisible(true);
                maintenanceP[i].setVisible(true);
            }
        }
    }
/**
 * Makes the necessary components invisible. It hides the regular vending machine interface components such as the "Buy" button,
 * wallet display, cake add, delete, and edit panels, and also hides the maintenance menu components if applicable.
 */    
    public void inVisible() {
        buy.setVisible(false);
        wallet.setVisible(false);
        addCake.setVisible(false);
        delCake.setVisible(false);
        ediCake.setVisible(false);
        added.setVisible(false);

        for(int i = 0; i < buttons.length; i++) {
            vendingP[i].setVisible(false);
            buttons[i].setVisible(false);
            coin.setVisible(false);

            if (i < 5) {
                mainMainP[i].setVisible(false);
                maintenanceB[i].setVisible(false);
                maintenanceP[i].setVisible(false);
            }
        }
    }
/**
 * Toggles the visibility of maintenance menu panels based on the chosen option.
 * 
 * @param chosen The index of the maintenance menu panel to be made visible.
 */
    public void maintenanceMenuToggle(int Chosen) {
        addCake.setVisible(false);
        delCake.setVisible(false);
        ediCake.setVisible(false);
        
        for(int i = 0; i < 5; i++){
            mainMainP[i].setVisible(false);
        }
        mainMainP[Chosen].setVisible(true);
    }
/**
 * Toggles the visibility of item menu panels based on the chosen option.
 * 
 * @param chosen The index of the item menu panel to be made visible.
 */
    public void itemMenuToggle(int Chosen) {
        for(int i = 0; i < 5; i++){
            mainMainP[i].setVisible(false);
        }
        delCake.setVisible(false);
        addCake.setVisible(false);
        ediCake.setVisible(false);

        switch (Chosen) {
            case 1:
                ediCake.setVisible(true);
                break;
            case 2:
                delCake.setVisible(true);
                break;
            case 3:
                addCake.setVisible(true);
                break;
            default:
                break;
        }
    }
/**
 * Sets up the vending machine panel with the main menu for cake selection and purchase.
 * The main menu includes the cake buttons, wallet display, and buy button.
 */
    public void vendingMachinePanel() {
        menuInt = 0;
        setTitle("Cake Vending Machine");
        getContentPane().setBackground(new Color(150, 39, 0));
        inVisible();
        visible();
    }
/**
 * Sets up the maintenance menu for the cake vending machine.
 * The maintenance menu includes options for coin maintenance, item maintenance, and other maintenance actions.
 */
    public void maintenanceMenu() {
        menuInt = 1;
        setTitle("Maintenance Menu");
        getContentPane().setBackground(new Color(38, 46, 90));
        inVisible();
        visible();
    }
/**
 * Sets up the panel to display information about the cake that was added.
 * The panel will show the values of the newly added cake.
 */
    public void cakeAdded() {
        added = new JPanel();
        added.setBounds(400, 50, 600, 450);
        added.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        added.setLayout(new GridLayout(0,1,1,0));
        added.add(new JLabel("Values Changed"));
        added.setVisible(false);
        add(added);
    }
/**
 * Sets up a dialog for the user to input the amount of bills they want to add to their wallet.
 * The dialog allows the user to input the quantity of each bill denomination (10, 20, 50, 100, 200, 500 pesos).
 * The total amount of bills added to the wallet is calculated and displayed in a message dialog.
 */
    public void setUserWallet() {
        
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false); // Only allow valid integers

        JFormattedTextField field1 = new JFormattedTextField(formatter);
        JFormattedTextField field2 = new JFormattedTextField(formatter);
        JFormattedTextField field3 = new JFormattedTextField(formatter);
        JFormattedTextField field4 = new JFormattedTextField(formatter);
        JFormattedTextField field5 = new JFormattedTextField(formatter);
        JFormattedTextField field6 = new JFormattedTextField(formatter);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("10 Peso Coins to be added:"));
        panel.add(field1);
        panel.add(new JLabel("20 Peso Coins to be added:"));
        panel.add(field2);
        panel.add(new JLabel("50 Peso Coins to be added:"));
        panel.add(field3);
        panel.add(new JLabel("100 Peso Coins to be added:"));
        panel.add(field4);
        panel.add(new JLabel("200 Peso Coins to be added:"));
        panel.add(field5);
        panel.add(new JLabel("500 Peso Coins to be added:"));
        panel.add(field6);

        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
                    vnd.clearUserWallet();
                    vndBills[0] = parseFieldValue(field1);
                    vndBills[1] = parseFieldValue(field2);
                    vndBills[2] = parseFieldValue(field3);
                    vndBills[3] = parseFieldValue(field4);
                    vndBills[4] = parseFieldValue(field5);
                    vndBills[5] = parseFieldValue(field6);
                    total = vnd.addUserBill(vndBills);
                    JOptionPane.showMessageDialog(null, "Total amount inserted: " + total);
                    walletLabel.setText(total + " Pesos");
        } else {
            System.out.println("Cancelled");
        }
    }
/**
 * Parses the value from the formatted text field and returns it as an integer.
 *
 * @param field The formatted text field from which to extract the value.
 * @return The parsed integer value from the formatted text field if it is a positive integer, otherwise returns 0.
 */
    protected int parseFieldValue(JFormattedTextField field) {
        int result;
        try {
            result = Integer.parseInt(field.getText());
            if(result>0){
                return result;
            }
            else{
                return 0;
            }
        } catch (NumberFormatException e) {
            return 0; 
        }
    }
/**
 * Initiates the process of purchasing a cake and handles the purchase transaction.
 * Displays a message dialog to inform the user of the purchase result.
 * Resets the total amount inserted and selected cake after the purchase.
 * Updates the item information display and resets the selected cake button backgrounds to default.
 */
    public void buy() {
        String result = vnd.purchaseItem(total, selectedCake);
    
        if (result.contains("Payment successful")) {
            JOptionPane.showMessageDialog(null,result+"\nPlease reselect the cake before buying again.", "Purchase successful!", JOptionPane.INFORMATION_MESSAGE);
            recordPurchase(selectedCake);
        } else {
            JOptionPane.showMessageDialog(null, result+"\nPlease reselect the cake and add bills before trying to buy again.","Purchase failed!", JOptionPane.INFORMATION_MESSAGE);
        }
        total = 0;
        totalPrice =0;
        previousCake =0;
        itemInfoTextArea[selectedCake].setText(vnd.getCakeDetails(selectedCake));
        selectedCake = -1;
        for(int j=0;j<buttons.length;j++){
            buttons[j].setBackground(Color.LIGHT_GRAY);
        }
        walletLabel.setText(total + " Pesos");
        amountTotal.setText(totalPrice + " Pesos");
         
    }
/**
 * Handles the action events generated by different GUI components.
 *
 * @param click The ActionEvent representing the user's interaction with a GUI component.
 */
    @Override
    public void actionPerformed(ActionEvent click) {
        // Main Menu
        if (click.getSource() == initializeMachine) {
            vendingMachinePanel();
        } 
        else if (click.getSource() == itemMaintenance) {
            maintenanceMenu();
        } 
        // Buy Item
        else if (click.getSource() == buy) {
            buy();
        } 
        // Maintenance Menu Options
        else if (click.getSource() == maintenanceB[0]) {
            maintenanceMenuToggle(0);
        } 
        else if (click.getSource() == maintenanceB[1]) {
            maintenanceMenuToggle(1);
        } 
        else if (click.getSource() == maintenanceB[2]) {
            maintenanceMenuToggle(2);
            vnd.setDefaults();
        } 
        else if (click.getSource() == maintenanceB[3]) {
            maintenanceMenuToggle(3);
            profit = vnd.collectProfit();
            colProfits.setText("Profit: "+ profit);
            profit = 0;
        } 
        else if (click.getSource() == maintenanceB[4]) {
            maintenanceMenuToggle(4);
            receipt = vnd.receipt();
            colReceipt.setText(receipt);
            receipt = " ";
        } 
        // Item Maintenance Menu Options
        else if (click.getSource() == itemEditButton) {
            itemMenuToggle(1);
        } 
        else if (click.getSource() == deleCakeButton) {
            itemMenuToggle(2);
        } 
        else if (click.getSource() == addCakeButton) {
            itemMenuToggle(3);
        } 
        // Item Editing Allocation
        else if (click.getSource() == editConfirmItem) {
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
            ediCake.setVisible(false);
            added.setVisible(true);
        }
        // Add New Cake Into The System
        else if (click.getSource() == addConfirm) {
            int count = 10;
            addCake.setVisible(false);
            added.setVisible(true);
            vnd.addNewCake(addName.getText(), addDesc.getText(), parseFieldValue(addCalo), parseFieldValue(addPric), parseFieldValue(addQuan));
            updateNewItemDetailPanel(count);
            count++;
        } 
        // Add Coins Into The System
        else if (click.getSource() == editConfirmCoin) {
            int Bills[] = {parseFieldValue(p10), parseFieldValue(p20), parseFieldValue(p50), parseFieldValue(p100), parseFieldValue(p200), parseFieldValue(p500)};
            mainMainP[0].setVisible(false);
            added.setVisible(true);
            vnd.billMaintenance(Bills);
        }
        // Delete nth Cake using Index
        else if (click.getSource() == delConfirm) {
            vnd.deleteACake((parseFieldValue(delete)-1));
            setupItemDetailPanel((parseFieldValue(delete)-1));
        }
        // Item Buttons in Vending
        else {
            for (int i = 0; i < buttons.length; i++) {
                if (click.getSource() == buttons[i] && menuInt == 0) {
                    for(int j=0;j<buttons.length;j++){
                        vendingP[j].setVisible(false);
                        buttons[j].setBackground(Color.LIGHT_GRAY);
                    }
                    vendingP[i].setVisible(true);
                    selectedCake = i;
                    totalPrice -= previousCake;
                    totalPrice += vnd.getCakePrice(i);
                    previousCake = vnd.getCakePrice(i);
                    amountTotal.setText( totalPrice + "Pesos");
                    buttons[i].setBackground(Color.GREEN);
                }
            }
            if (click.getSource() == coin && menuInt == 0) {
                // Handle adding coins to the wallet
                setUserWallet();
            }
        }
    }
}
