package com.rgstock;

import com.rgitem.RgItem;

public class RgStock{
    public static void main(String[] args){

        int a;
        RgItem[] Item = new RgItem[100];

        for(a=0;a<20;a++){
            Item[a] = new RgItem();
        }
        
        Initialize(Item);

    }
    
    public static void Initialize(RgItem[] Item){

        Item[0].setName("Apple Crumble");
        Item[0].setDesc("Treat yourself with our irresistible apple crumble! Indulge in layers of tender apples, delicately spiced with cinnamon, and crowned with a mouthwatering crumbly crust.");
        Item[0].setCalorie(156);
        Item[0].setPrice(160);
        Item[0].setQty(0);

        Item[1].setName("Black Forest Cake");
        Item[1].setDesc("Savor the taste of the classic Black Forest Cake, where the tangy sweetness of cherries perfectly complements the rich chocolate layers, all brought together with light and fluffy cream.");
        Item[1].setCalorie(282);
        Item[1].setPrice(140);
        Item[1].setQty(0);
        
        Item[2].setName("Premium Chocolate Cake");
        Item[2].setDesc("Treat yourself with this moist, rich, and bittersweet Chocolate cake, adorned with a lucious dark chocolate ganache.");
        Item[2].setCalorie(389);
        Item[2].setPrice(190);
        Item[2].setQty(0);
        
        Item[3].setName("Strawberry Shortcake");
        Item[3].setDesc("Indulge in the freshness of Benguet's finest strawberries with our Strawberry Bliss Short Cake. Enjoy the perfect blend of delicate sponge cake, heavenly cream, and juicy strawberries, sourced directly from the strawberry capital of the Philippines.");
        Item[3].setCalorie(247);
        Item[3].setPrice(250);
        Item[3].setQty(0);

        Item[4].setName("Lemon Blueberry Cake");
        Item[4].setDesc("Enjoy the vibrant combination of zesty lemon-infused cake, bursting blueberries, and the smooth cream cheese frosting that creates a perfect balance of flavors with this Lemon Blueberry cake.");
        Item[4].setCalorie(244);
        Item[4].setPrice(240);
        Item[4].setQty(0);

        Item[5].setName("Chocolate Mousse Cake");
        Item[5].setDesc("Revel in joy with a decadent dessert featuring layers of moist chocolate cake and creamy chocolate mousse, altogether forming a rich and indulgent treat.");
        Item[5].setCalorie(349);
        Item[5].setPrice(220);
        Item[5].setQty(0);

        Item[6].setName("Butterscotch Cake");
        Item[6].setDesc("A delectable dessert with a moist and fluffy base infused with the rich and caramel-like flavor of butterscotch, topped with creamy butterscotch frosting and caramel sauce.");
        Item[6].setCalorie(379);
        Item[6].setPrice(280);
        Item[6].setQty(0);

        Item[7].setName("Mocha Delight Cake");
        Item[7].setDesc("Enjoy a heavenly dessert featuring layers of moist chocolate cake infused with the bold flavors of coffee and espresso, complemented by a velvety mocha frosting for a delightful and indulgent treat.");
        Item[7].setCalorie(308);
        Item[7].setPrice(210);
        Item[7].setQty(0);

        Item[8].setName("Mango Surprise Cake");
        Item[8].setDesc("A tropical delight that combines layers of moist mango-infused cake with a luscious mango filling, providing a delightful surprise of fruity sweetness in every bite.");
        Item[8].setCalorie(228);
        Item[8].setPrice(230);
        Item[8].setQty(0);

        Item[9].setName("Carrot Cake");
        Item[9].setDesc("Treat yourself with a moist and flavorful dessert made with grated carrots, warm spices, and a rich cream cheese frosting, offering a delightful combination of sweet and spiced flavors.");
        Item[9].setCalorie(266);
        Item[9].setPrice(180);
        Item[9].setQty(0);

    }
}
