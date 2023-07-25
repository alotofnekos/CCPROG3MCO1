import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener{
    private JFrame frame;
    private JMenuBar menu;
    private JPanel vendingP[];
    private JButton vendingB[];
    private JButton buy;
    private JButton insert;

    private JMenu maintMenu;
    private JMenuItem initializeMachine;
    private JMenuItem itemMaintenance;
    
    private int width;
    private int length;
    
    //Remove in the future
    private int loop;

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

        //Removable when MCO1 implementation is done
        vendingB = new JButton[20];
        vendingP = new JPanel[20];
        
    }

    public void Display() {
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

    public void vendingMachinePanel() {
        frame.getContentPane().setBackground(new Color(160,20,0));
        //XY position of buttons
        int x=30, y=50;

        userOptions();

        //Change value to No. of items in vending
        //Suppose Implement RgItem
        for(loop=0;loop<20;loop++){
           vendingMachine(loop, x, y);
           itemDetailPanel(loop);
           x += 100;
           if(x==530){
            x=30;
            y+=100;
           }
        }
    }

    //Button Allocation of Cakes
    public void vendingMachine(int Count, int x, int y) {
        vendingB[Count] = new JButton((Count+1)+".");
        vendingB[Count].setBounds(x, y, 75, 50);
        vendingB[Count].addActionListener(this);
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
    
    //Button for Primary Users
    public void userOptions() {

        buy.setBounds(750, 600, 200, 50);
        insert.setBounds(550, 600, 200, 50);

        buy.addActionListener(this);
        insert.addActionListener(this);

        frame.add(buy);
        frame.add(insert);
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if(click.getSource() == initializeMachine) {
            vendingMachinePanel();
        }
        if(click.getSource() == itemMaintenance) {
            
        }
        if(click.getSource() == insert) {

        }
        if(click.getSource() == buy) {
            
        }
        for(loop=0;loop<20;loop++) {
            if(click.getSource() == vendingB[loop]) {
                vendingP[loop].setVisible(true);
            }
        }
    }
}
