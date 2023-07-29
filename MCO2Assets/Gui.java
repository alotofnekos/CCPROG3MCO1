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

public class Gui extends JFrame implements ActionListener {
    private JMenuBar menu;
    private JMenu maintMenu;
    private JMenuItem initializeMachine;
    private JMenuItem itemMaintenance;

    // Arrays
    private JButton[] buttons;
    private JPanel[] vendingP;
    private JButton buy;
    private JButton selectCake;
    private JButton coin;

    // Other variables
    private int width;
    private int length;
    private ArrayList<String> itemNames = new ArrayList<>();
    private ArrayList<String> itemImageFileNames = new ArrayList<>();
    private JLabel[] maintenanceL;
    private JPanel[] maintenanceP;
    private JButton[] maintenanceB;
    private JPanel mainMainP;
    private JPanel wallet;
    private JLabel walletLabel;
    // XY position of buttons
    int xPos = 20;
    int yPos = 30;
    // Menu indicator
    int menuInt;

    //NOT FOR GUI
    int[] vndBills = {0, 0, 0, 0, 0, 0};
    RgVnd vnd = new RgVnd();

    public Gui() {
        width = 1100;
        length = 600;
        menu = new JMenuBar();
        maintMenu = new JMenu("Admin");
        initializeMachine = new JMenuItem("Initialize Machine");
        itemMaintenance = new JMenuItem("Item Maintenance");
        buy = new JButton("Buy");
        selectCake = new JButton("Select Cake");
        coin = new JButton("Insert Coins & Bills!");
        buttons = new JButton[36];
        vendingP = new JPanel[36];
        maintenanceB = new JButton[5];
        maintenanceP = new JPanel[5];
        maintenanceL = new JLabel[5];
        mainMainP = new JPanel();
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
    

    public void Display() {
        initializeItemButtons(); // Call the itemButtons method to set up item buttons
        initializeMaintenanceButtons();
        initializeMaintenanceDataPanel();
        initializeUserOptions();
        createMenu();
        setupMainFrame();
    }

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

    public void createMenu() {
        initializeMachine.addActionListener(this);
        itemMaintenance.addActionListener(this);

        menu.add(maintMenu);
        maintMenu.add(initializeMachine);
        maintMenu.add(itemMaintenance);
    }

    public void initializeItemButtons() {
        String itemName;
        String imageFileName;
        ImageIcon imageIcon;
        for (int itemIndex = 0; itemIndex < 36; itemIndex++) {
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
            if(itemIndex > 20&& (itemIndex-19) % 8 == 0){
                xPos=20;
                yPos+=120;
            }
        }
    }
    
    
    private void setCustomButtonFont(Font font) {
        for (JButton button : buttons) {
            if (button != null) {
                button.setFont(font);
            }
        }
        
    }

    // Setup individual item buttons
    public void setupItemButton(int itemIndex, int x, int y, String itemName, ImageIcon imageIcon) {
        buttons[itemIndex] = new JButton(itemName);
        buttons[itemIndex].setBounds(x, y, 70, 75);
        buttons[itemIndex].addActionListener(this);
        buttons[itemIndex].setVisible(false);

        vendingP[itemIndex] = new JPanel(); // Initialize the vendingP array element here
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
            JTextArea itemInfoTextArea = new JTextArea();
            itemInfoTextArea.setEditable(false);
            itemInfoTextArea.setLineWrap(true);
            itemInfoTextArea.setWrapStyleWord(true);
    
            // Get the item information using getItemInfoString() method
            String itemInfo = vnd.getCakeDetails(itemIndex);
    
            // Set the item information as the text of the JTextArea
            itemInfoTextArea.setText(itemInfo);
    
            // Add the JTextArea to the SOUTH of the vendingP panel
            vendingP[itemIndex].add(itemInfoTextArea, BorderLayout.SOUTH);
    
            vendingP[itemIndex].setVisible(true);
            add(vendingP[itemIndex]);
        }
    
    

   
    // It resizes the image and handles the default cake image if the specified image is not found
    private ImageIcon loadImageIcon(String imageFileName,int width,int height) {
        // Load the original image
        BufferedImage originalImage;
        ImageIcon imageIcon = null;

        try {
            originalImage = ImageIO.read(new File(imageFileName));
            // Resize the image to the desired width and height
            int desiredWidth = width; // Set the desired width here
            int desiredHeight = height; // Set the desired height here
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

    // Setup maintenance buttons
    public void initializeMaintenanceButtons() {
        yPos = 50;
        for (int i = 0; i < 5; i++) {
            setupMaintenanceMenuAssets(i);
            yPos += 100;
        }
        yPos = 50;
    }

    // Setup individual maintenance buttons
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
    }

    private String getMaintenanceLabel(int index) {
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

    public void initializeMaintenanceDataPanel() {
        mainMainP.setBounds(750, 50, 250, 300);
        mainMainP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainMainP.setVisible(false);
        add(mainMainP);
    }

    public void initializeUserOptions() {
        selectCake.setBounds(600, 295, 125, 50);
        selectCake.addActionListener(this);
        selectCake.setVisible(false);
        add(selectCake);
        buy.setBounds(750, 295, 125, 50);
        buy.addActionListener(this);
        buy.setVisible(false);
        add(buy);
        wallet.setBounds(900, 295, 175, 50);
        wallet.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        wallet.setVisible(false);
        walletLabel = new JLabel("0 Pesos");
        wallet.add(walletLabel);
        add(wallet);

        xPos = 900;
        yPos = 380;
        coin.setBounds(xPos + 10, yPos, 150, 50);
        coin.addActionListener(this);
        coin.setVisible(false);
        add(coin);

    }

    public void itemToggle() {
        if (menuInt == 0) {
            selectCake.setVisible(true);
            buy.setVisible(true);
            wallet.setVisible(true);
            mainMainP.setVisible(false);
            for (int i = 0; i < buttons.length; i++) {
                vendingP[i].setVisible(false);
                buttons[i].setVisible(true);
                coin.setVisible(true);
    
                if (i < 5) {
                    maintenanceB[i].setVisible(false);
                    maintenanceP[i].setVisible(false);
                }
            }
        } else if (menuInt == 1) {
            selectCake.setVisible(false);
            buy.setVisible(false);
            wallet.setVisible(false);
            mainMainP.setVisible(true);
            for (int i = 0; i < buttons.length; i++) {
                vendingP[i].setVisible(false);
                buttons[i].setVisible(false);
                coin.setVisible(false);
                
                if (i < 5) {
                    maintenanceB[i].setVisible(true);
                    maintenanceP[i].setVisible(true);
                }
            }
        }
    }

    public void vendingMachinePanel() {
        setTitle("Cake Vending Machine");
        getContentPane().setBackground(new Color(150, 39, 0));
        itemToggle();
    }

    public void maintenanceMenu() {
        setTitle("Maintenance Menu");
        getContentPane().setBackground(new Color(38, 46, 90));
        itemToggle();
    }
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
                    int total = vnd.addUserBill(vndBills);
                    JOptionPane.showMessageDialog(null, "Total amount inserted: " + total);
                    walletLabel.setText(total + " Pesos");
        } else {
            System.out.println("Cancelled");
        }
    }
    private int parseFieldValue(JFormattedTextField field) {
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
    @Override
    public void actionPerformed(ActionEvent click) {
        if (click.getSource() == initializeMachine) {
            menuInt = 0;
            vendingMachinePanel();
        } else if (click.getSource() == itemMaintenance) {
            menuInt = 1;
            maintenanceMenu();
        } else if (click.getSource() == buy) {
            // Handle buy action
        } else if (click.getSource() == maintenanceB[0]) {
            // Handle maintenance action 1
        } else if (click.getSource() == maintenanceB[1]) {
            // Handle maintenance action 2
        } else if (click.getSource() == maintenanceB[2]) {
            // Handle maintenance action 3
        } else if (click.getSource() == maintenanceB[3]) {
            // Handle maintenance action 4
        } else if (click.getSource() == maintenanceB[4]) {
            // Handle maintenance action 5
        } else {
            for (int i = 0; i < buttons.length; i++) {
                if (click.getSource() == buttons[i] && menuInt == 0) {
                    for(int j=0;j<buttons.length;j++){
                        vendingP[j].setVisible(false);
                    }
                    vendingP[i].setVisible(true);
                }
            }
                if (click.getSource() == coin && menuInt == 0) {
                    // Handle adding coins to the wallet
                    setUserWallet();
                }
        }
    }
}
