import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener{
    private JFrame frame;
    private JMenuBar menu;
    private JPanel vendingP[];

    private JButton vendingB[];
    private JButton maintenance[];
    private JButton buy;
    private JButton insert;
    private JButton edit;
    private JButton coin;

    private JMenu maintMenu;
    private JMenuItem initializeMachine;
    private JMenuItem itemMaintenance;
    
    private int width;
    private int length;
    
    //Remove in the future
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
        itemMaintenance = new JMenuItem("Item Maintenance");
        buy = new JButton("Buy Cake");
        insert = new JButton("Insert Money");
        maintenance = new JButton[6];

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
        for(loop=0;loop<6;loop++){
            itemMaintenance(loop);
            y+=100;
        }
        y=50;
    }

    public void itemMaintenance(int Count) {
        maintenance[Count] = new JButton((Count+1)+".");
        maintenance[Count].setBounds(x, y, 75, 50);
        maintenance[Count].addActionListener(this);
        maintenance[Count].setVisible(false);
        frame.add(maintenance[Count]);
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

    public void editItemMenu() {
        edit.setBounds(750,600,200,50);

        edit.addActionListener(this);

        frame.add(edit);
    }

    public void itemToggle() {
        if(menuInt == 0){
            for(loop=0;loop<20;loop++){
                vendingB[loop].setVisible(true);
            }
        }
        else if(menuInt == 1){
            for(loop=0;loop<20;loop++){
                vendingB[loop].setVisible(false);
            }
        }
    }

    public void maintToggle() {
        for(loop=0;loop<6;loop++){
            maintenance[loop].setVisible(true);
        }
    }

    public void userOptionsToggle() {
        if(menuInt == 0) {
            buy.setVisible(true);
            insert.setVisible(true);
        }
        else if(menuInt == 1) {
            buy.setVisible(false);
            insert.setVisible(false);
        }
    }

    public void vendingMachinePanel() {
        frame.getContentPane().setBackground(Color.RED);
        itemToggle();
        userOptionsToggle();
    }
    
    public void maintenanceMenu() {
        frame.getContentPane().setBackground(Color.BLUE);
        itemToggle();
        maintToggle();
        userOptionsToggle();
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
                editItemMenu();
            }
        }
    }
}
