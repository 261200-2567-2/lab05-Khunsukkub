// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Charactor c1 = new Charactor("John" , new Warrior());
        Charactor c2 = new Charactor("Jane" , new Mage());
        Items i1 = new Items("Ogre king's ring", "King's god SMASH" , 10 , 15 , 5 , 20 , 5 , -5
                            , new Requirement(2 , 3 , 5 , 0)
                            );
        Items i2 = new Items("Jacky's necklace" , "NONE" , 99 , 160 , 999 , 1200 , 5 , - 100
                            , new Requirement(3 , 0 , 0 , 0)
                            );

        c2.status();
        c1.levelUp();
        c2.levelUp();
        c1.equips(i1);
        c1.gives(c2,i1);
        c2.status();
    }
}