public interface Classes {
    String getClassName();
    float getClassMaxHP();
    float getClassMaxMana();
    int getClassSTR();
    int getClassCON();
    int getClassDEX();
}

class Warrior implements Classes {
    String name = "Warrior";

    public String getClassName() {
        return name;
    }

    public float getClassMaxHP() {
        return 140;
    }
    public float getClassMaxMana() {
        return 50;
    }
    public int getClassSTR() {
        return 6;
    }
    public int getClassCON() {
        return 6;
    }
    public int getClassDEX() {
        return 2;
    }
}

class Mage implements Classes {
    String name = "Mage";

    public String getClassName() {
        return name;
    }
    public float getClassMaxHP() {
        return 80;
    }
    public float getClassMaxMana() {
        return 100;
    }
    public int getClassSTR() {
        return 10;
    }
    public int getClassCON() {
        return 4;
    }
    public int getClassDEX() {
        return 5;
    }
}

class Rogue implements Classes {
    String name = "Rogue";

    public String getClassName() {
        return name;
    }
    public float getClassMaxHP() {
        return 60;
    }
    public float getClassMaxMana() {
        return 70;
    }
    public int getClassSTR() {
        return 8;
    }
    public int getClassCON() {
        return 2;
    }
    public int getClassDEX() {
        return 9;
    }
}
