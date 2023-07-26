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

    private JPanel vendingP[];
    private JButton vendingB[];

    private JButton buy;
    private JButton insert;
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
        insert = new JButton("Insert Money");
        buy = new JButton("Buy Cake");
        
        itemMaintenance = new JMenuItem("Item Maintenance");
        maintenanceL = new JLabel[5];
        maintenanceB = new JButton[5];
        maintenanceP = new JPanel[5];

        //Removable when MCO1 implementation is done
        vendingB = new JButton[20];
        vendingP = new JPanel[20];
    }

    public void Display() {
        itemButtons();
        maintButtons();
        userOptions();
        menuCreation();
        mainFrame();
    }

    public void mainFrame() {
        frame.setTitle("Cake Vending Machine");
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
        //Change value to No. of items in vending
        //Suppose Implement RgItem
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

    //Button Allocation of Cakes
    public void itemButton(int Count, int x, int y) {
        vendingB[Count] = new JButton((Count+1)+".");
        vendingB[Count].setBounds(x, y, 75, 50);
        vendingB[Count].addActionListener(this);
        vendingB[Count].setVisible(false);
        frame.add(vendingB[Count]);
    }

    //Panel for Cake Data
    public void itemDetailPanel(int Count) {
        vendingP[Count] = new JPanel();
        vendingP[Count].setBounds(550,50,400,500);
        vendingP[Count].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vendingP[Count].setVisible(false);
        frame.add(vendingP[Count]);
    }

    public void maintButtons() {
        //Suppose Implement RgVending
        for(loop=0;loop<5;loop++){
            itemMaintenance(loop);
            y+=100;
        }
        y=50;
    }

    public void itemMaintenance(int Count) {
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





    //Button for Primary Users
    public void userOptions() {
        buy.setBounds(750, 600, 200, 50);
        insert.setBounds(550, 600, 200, 50);

        buy.addActionListener(this);
        insert.addActionListener(this);

        buy.setVisible(false);
        insert.setVisible(false);

        frame.add(buy);
        frame.add(insert);
    }

    public void itemToggle() {
        if(menuInt == 0){
            buy.setVisible(true);
            insert.setVisible(true);
            for(loop=0;loop<20;loop++){
                vendingB[loop].setVisible(true);
                if(loop < 5){
                    maintenanceB[loop].setVisible(false);
                    maintenanceP[loop].setVisible(false);
                }
            }
        }
        else if(menuInt == 1){
            buy.setVisible(false);
            insert.setVisible(false);
            for(loop=0;loop<20;loop++){
                vendingB[loop].setVisible(false);
                if(loop < 5){
                    maintenanceB[loop].setVisible(true);
                    maintenanceP[loop].setVisible(true);
                }
            }
        }
    }

    public void vendingMachinePanel() {
        frame.getContentPane().setBackground(Color.RED);
        itemToggle();
    }
    
    public void maintenanceMenu() {
        frame.getContentPane().setBackground(Color.BLUE);
        itemToggle();
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if(click.getSource() == initializeMachine) {
            menuInt = 0;
            vendingMachinePanel();
        }
        if(click.getSource() == itemMaintenance) {
            menuInt = 1;
            maintenanceMenu();
        }
        if(click.getSource() == insert) {

        }
        if(click.getSource() == buy) {
            
        }
        for(loop=0;loop<20;loop++) {
            if(click.getSource() == vendingB[loop] && menuInt == 0) {
                vendingP[loop].setVisible(true);
            }
            else if(click.getSource() == vendingB[loop] && menuInt == 1) {
                vendingP[loop].setVisible(true);
            }
        }
    }
}
