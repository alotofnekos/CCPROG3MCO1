import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener{
    private JFrame frame;
    private JMenuBar menu;
    
    private JMenu maintMenu;
    private JMenuItem initializeMachine;
    private JMenuItem itemMaintenance;

    private JLabel maintenanceL[];
    private JPanel maintenanceP[];
    private JButton maintenanceB[];

    private JPanel mainMainP;

    private JPanel vendingP[];
    private JButton vendingB[];

    private JButton buy;
    private JPanel wallet;
    private JButton coin[];

    private JButton edit;
    
    private int width;
    private int length;
    private int loop;
    
    //XY position of buttons
    int x=30;
    int y=50;
    //Menu indicator
    int menuInt;

    public Gui(){
        frame = new JFrame();
        width = 1000;
        length = 800;

        menu = new JMenuBar(); 
        maintMenu = new JMenu("Admin");

        initializeMachine = new JMenuItem("Initialize Machine");
        wallet = new JPanel();
        coin = new JButton[6];
        buy = new JButton("Buy Cake");
        
        itemMaintenance = new JMenuItem("Item Maintenance");
        maintenanceL = new JLabel[5];
        maintenanceB = new JButton[5];
        maintenanceP = new JPanel[5];

        mainMainP = new JPanel();

        //Removable when MCO1 implementation is done
        vendingB = new JButton[20];
        vendingP = new JPanel[20];
    }

    public void Display() {
        itemButtons();
        maintButtons();
        maintenanceDataPanel();
        userOptions();
        menuCreation();
        mainFrame();
    }

    public void mainFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(width, length);
        frame.setJMenuBar(menu);
        frame.setVisible(true);
    }


    public void menuCreation() {
        initializeMachine.addActionListener(this);
        itemMaintenance.addActionListener(this);

        menu.add(maintMenu);
        maintMenu.add(initializeMachine);
        maintMenu.add(itemMaintenance);
    }

    public void itemButtons() {
        for(loop=0;loop<20;loop++){
            itemButton(loop, x, y);
            itemDetailPanel(loop);
            x+=100;
            if(x==530){
                x=30;
                y+=100;
            }
        }
        x=30;
        y=50;
    }

    //BUTTON ALLOCATION FOR CAKES
    public void itemButton(int Count, int x, int y) {
        vendingB[Count] = new JButton((Count+1)+".");
        vendingB[Count].setBounds(x, y, 75, 50);
        vendingB[Count].addActionListener(this);
        vendingB[Count].setVisible(false);
        frame.add(vendingB[Count]);
    }

    //PANEL FOR CAKE DATA
    public void itemDetailPanel(int Count) {
        vendingP[Count] = new JPanel();
        vendingP[Count].setBounds(550,50,400,500);
        vendingP[Count].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vendingP[Count].setVisible(false);
        frame.add(vendingP[Count]);
    }

    //MAINTENACE BUTTONS LOOP
    public void maintButtons() {
        //Suppose Implement RgVending
        for(loop=0;loop<5;loop++){
            maintenanceMenuAssets(loop);
            y+=100;
        }
        y=50;
    }

    //MAINTENANCE MENU PANELS, LABELS, BUTTONS
    public void maintenanceMenuAssets(int Count) {
        maintenanceB[Count] = new JButton((Count+1)+".");
        maintenanceB[Count].setBounds(x, y, 75, 50);
        maintenanceB[Count].addActionListener(this);
        maintenanceB[Count].setVisible(false);
        
        maintenanceP[Count] = new JPanel();
        maintenanceP[Count].setBounds(x+100, y, 200, 50);
        maintenanceP[Count].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        maintenanceP[Count].setVisible(false);

        if(Count == 0)
            maintenanceL[Count] = new JLabel("Coin Maintenance");
        else if(Count == 1)
            maintenanceL[Count] = new JLabel("Item Maintenance");
        else if(Count == 2)
            maintenanceL[Count] = new JLabel("Set Defaults");
        else if(Count == 3)
            maintenanceL[Count] = new JLabel("Collect Payments");
        else
            maintenanceL[Count] = new JLabel("Items Bought");

        maintenanceL[Count].setVisible(true);
        maintenanceP[Count].add(maintenanceL[Count]);
        frame.add(maintenanceP[Count]);
        frame.add(maintenanceB[Count]);
    }

    public void maintenanceDataPanel() {
        mainMainP.setBounds(400, 50, 550, 600);
        mainMainP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainMainP.setVisible(false);
        frame.add(mainMainP);
    }

    //BUTTONS IN VENDING MACHINE
    public void userOptions() {
        //BUY ITEM
        buy.setBounds(650, 600, 200, 50);
        buy.addActionListener(this);
        buy.setVisible(false);
        frame.add(buy);

        wallet.setBounds(30, 450, 475, 175);
        wallet.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        wallet.setVisible(false);
        frame.add(wallet);
        
        for(loop=0;loop<6;loop++) {
            coinButtons(loop);
            x+=75;
        }
        x=30;
    }

    //ADD COINS INTO THE MACHINE
    public void coinButtons(int Count) {
        coin[Count] = new JButton("+");
        coin[Count].setBounds(x + 25, 650, 50, 50);
        coin[Count].addActionListener(this);
        coin[Count].setVisible(false);
        coin[Count].repaint();
        frame.add(coin[Count]);
    }

    //SHOW OR HIDE NEEDED ASSETS FOR VENDING OR MAINTENANCE
    public void itemToggle() {
        if(menuInt == 0){
            buy.setVisible(true);
            wallet.setVisible(true);
            mainMainP.setVisible(false);
            for(loop=0;loop<20;loop++){
                vendingP[loop].setVisible(false);
                vendingB[loop].setVisible(true);
                if(loop < 6){
                    coin[loop].setVisible(true);
                }
                if(loop < 5){
                    maintenanceB[loop].setVisible(false);
                    maintenanceP[loop].setVisible(false);
                }
            }
        }
        else if(menuInt == 1){
            buy.setVisible(false);
            wallet.setVisible(false);
            mainMainP.setVisible(true);
            for(loop=0;loop<20;loop++){
                vendingP[loop].setVisible(false);
                vendingB[loop].setVisible(false);
                if(loop < 6){
                    coin[loop].setVisible(false);
                }
                if(loop < 5){
                    maintenanceB[loop].setVisible(true);
                    maintenanceP[loop].setVisible(true);
                }
            }
        }
    }

    public void vendingMachinePanel() {
        frame.setTitle("Cake Vending Machine");
        frame.getContentPane().setBackground(new Color(231,139,255);
        itemToggle();
    }
    
    public void maintenanceMenu() {
        frame.setTitle("Maintenance Menu");
        frame.getContentPane().setBackground(Color.BLUE);
        itemToggle();
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if(click.getSource() == initializeMachine) {
            menuInt = 0;
            vendingMachinePanel();
        }
        else if(click.getSource() == itemMaintenance) {
            menuInt = 1;
            maintenanceMenu();
        }
        else if(click.getSource() == buy) {
            
        }
        else if(click.getSource() == maintenanceB[0]) {
            
        }
        else if(click.getSource() == maintenanceB[1]) {

        }
        else if(click.getSource() == maintenanceB[2]) {

        }
        else if(click.getSource() == maintenanceB[3]) {

        }
        else if(click.getSource() == maintenanceB[4]) {

        }
        //LOOP FOR MULTI BUTTON PROMPTS
        for(loop=0;loop<20;loop++) {
            if((click.getSource() == vendingB[loop]) && (menuInt == 0)) {
                vendingP[loop].setVisible(true);
            }
        }
        for(loop=0;loop<6;loop++) {
            //FOR ADDING COINS TO WALLET
            if((click.getSource() == coin[loop]) && (menuInt == 0)) {
                
            }
        }
    }
}
