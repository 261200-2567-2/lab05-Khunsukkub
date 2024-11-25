public interface Item {
    public String getName();
    public Requirement getRequirement();
    int getLevel();
    float getMAXHP();
    float getMAXMana();
    float getSTR();
    float getCON();
    float getDEX();
}

class Requirement {
    int level;
    int STR;
    int CON;
    int DEX;

    Requirement(int level, int STR, int CON, int DEX) {
        this.level = level;
        this.STR = STR;
        this.CON = CON;
        this.DEX = DEX;
    }

    public boolean Checkrequire (int level, int STR, int CON, int DEX) {
        if(
                level >= this.level
                && STR >= this.STR
                && CON >= this.CON
                && DEX >= this.DEX
        ) {return true;}
        else return false;
    }
    public void TellRequire(Requirement requirement) {
        System.out.println("Requirement level: " + level);
        System.out.println("Requirement STR: " + STR);
        System.out.println("Requirement CON: " + CON);
        System.out.println("Requirement DEX: " + DEX);
    }
}

class Items implements Item , SpecialMove {
    private String name;
    private String SpecialMove;
    private Requirement requirement;
    private int level;
    private float maxHP;
    private float maxMana;
    private float str;
    private float con;
    private float dex;

    Items (String name,String SpecialMove ,int level,float maxHP, float maxMana, float str, float con, float dex , Requirement requirement) {
        this.name = name;
        this.SpecialMove = SpecialMove;
        this.level = level;
        this.maxHP = maxHP;
        this.maxMana = maxMana;
        this.str = str;
        this.con = con;
        this.dex = dex;
        this.requirement = requirement;
    }

    public String getName () {
        return name;
    }
    public String getSpecialMove() {
        return SpecialMove;
    }
    public Requirement getRequirement () {
        return requirement;
    }
    public int getLevel () {
        return level;
    }
    public float getMAXHP () {
        return maxHP;
    }
    public float getMAXMana () {
        return maxMana;
    }
    public float getSTR () {
        return str;
    }
    public float getCON () {
        return con;
    }
    public float getDEX () {
        return dex;
    }

    public String CheckSpecialMove() {
        if(SpecialMove.equals("NONE")) {
            return "NONE";
        } else {
            return SpecialMove.toString();
        }
    }

    public void stats() {
        System.out.println("------------------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("STR: " + str);
        System.out.println("CON: " + con);
        System.out.println("DEX: " + dex);
        System.out.println("Skill : " + SpecialMove);
        requirement.TellRequire(requirement);
        System.out.println("------------------------------------------");
    }
}