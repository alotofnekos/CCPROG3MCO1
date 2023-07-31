import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SpcGui extends Gui {
    private JButton[] spcButtons;
    private JPanel[] spcVendingP;
    private int xSpcPos;
    private int ySpcPos;
    private JTextArea[] spcItemInfoTextArea;
    private SpcVnd vnd;
    private ArrayList<String> spcitemNames = new ArrayList<>();
    private ArrayList<String> spcitemImageFileNames = new ArrayList<>();
    public SpcGui() {
        super();
        vnd = new SpcVnd();
        spcButtons =new JButton[16];
        spcVendingP =new JPanel[16];
        selectAddOn = new JButton("Select Add-ons");
        spcItemInfoTextArea = new JTextArea[20]; 
        vnd.setDefaults();
        int i=0;
        while(vnd.validSpcItem(i)){
            spcitemNames.add("AddOn#"+(i+1));
            i++;
        }
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\BDay.Topper.NoBg.png");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\MDay.Topper.NoBg.png");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\FDay.Topper.NoBg.png");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\candle.NoBg.jpg");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\Flower.Topper.NoBg.jpg");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\Heart.Topper.NoBg.jpg");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\Apple.Topping.NoBg.jpg");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\choco.NoBg.jpg");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\IceCream.Topping.NoBg.jpg");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\birthdaycake.jpg");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\alamodefruit.jpg");
        spcitemImageFileNames.add("C:\\Users\\Angel\\Downloads\\CCPROG3MCO1\\MCO2Assets\\alamodechoc.jpg");
        Arrays.fill(spcButtons, new JButton());
        Arrays.fill(spcVendingP, new JPanel());
    }
    @Override
    public void vendingMachinePanel() {
        menuInt = 0;
        setTitle("Special Cake Vending Machine");
        getContentPane().setBackground(new Color(11, 208, 230));
        inVisible();
        visible();
    }
    public void initializeSpcItemButtons() {
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
            if(itemIndex==19){
                System.out.println("xPos: "+ xPos+ " yPos: " + yPos);
            }
            /*if(itemIndex > 20&& (itemIndex-19) % 8 == 0){
                xPos=20;
                yPos+=120;
            }*/
        }
    }
}
