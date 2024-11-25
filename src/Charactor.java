import static java.lang.Math.E;
import static java.lang.Math.abs;

class Charactor  {
    private String name;
    private Classes classes;
    private int level = 1;
    private int stat_point;
    private EquipmentSlot equipment_slot;

    private float maxHP;
    private float HP = 1;
    private float maxMana;
    private float Mana;
    private int STR;
    private int CON;
    private int DEX;

    private float addmaxHP = 0;
    private float addmaxMana = 0;
    private int addSTR = 0;
    private int addCON = 0;
    private int addDEX = 0;
    private float Wound;

    Charactor(String name, Classes classes) {
        this.name = name;
        this.classes = classes;
        this.equipment_slot = new Slot(4);
    }

    private boolean isDEAD () {
        if(this.HP == 0) return true;
        else return false;
    }

    private void CalculatingStat() {
        maxHP = (classes.getClassMaxHP() + ((level - 1) * 10)) + addmaxHP;
        maxMana = (classes.getClassMaxMana() + ((level - 1) * 10)) + addmaxMana;
        HP = maxHP-Wound;
        Mana = maxMana;
        STR = classes.getClassSTR() + (level - 1) + addSTR;
        CON = classes.getClassCON() + (level - 1) + addCON;
        DEX = classes.getClassDEX() + (level - 1) + addDEX;
    }

    private void UpdatedStats() {
        if(isDEAD()) {
            System.out.println(name + " is dead");
        }
        CalculatingStat();
    }

    public void status() {
        UpdatedStats();
        System.out.println("------------------------------------------");
        System.out.println(this.name + "'s status :");
        System.out.println("Class : " + classes.getClassName());
        System.out.println("Level : " + this.level);
        System.out.println("Health : " + maxHP + "/" + HP);
        System.out.println("Mana : " + maxMana + "/" + Mana);
        System.out.println("STR : " + STR);
        System.out.println("CON : " + CON);
        System.out.println("DEX : " + DEX);
        System.out.println("Point : " + stat_point);
        System.out.println("------------------------------------------");
    }

    public void levelUp() {
        System.out.println("------------------------------------------");
        System.out.println(this.name + " level ups to " + ++level);
        stat_point++;
        UpdatedStats();
        System.out.println("------------------------------------------");
    }

    private boolean isHavePoint() {
        if(stat_point <= 0)
            {
                System.out.println(this.name + " need more point to up stats");
                return false;
            }
        else return true;
    }

    public void UpStats(String this_stat) {
        System.out.println("------------------------------------------");

        if (!isHavePoint()) { return; }

        stat_point--;
        String this_stat_Upper = this_stat.toUpperCase();
        switch (this_stat_Upper) {
            case "STR":
                addSTR += 2;
                System.out.println(this_stat + " increases by 2");
                break;
            case "CON":
                addCON += 2;
                System.out.println(this_stat + " increases by 2");
                break;
            case "DEX":
                addDEX += 2;
                System.out.println(this_stat + " increases by 2");
                break;
            case "MAXHP":
                addmaxHP += 15;
                System.out.println(this_stat + " increases by 15");
                break;
            case "MAXMANA":
                addmaxMana += 5;
                System.out.println(this_stat + " increases by 5");
                break;
            default:
                System.out.println("Please enter valid stats (HP / Mana / STR / CON / DEX)");
        }
        System.out.println("------------------------------------------");
    }

    private boolean isWearable(Items item) {
        return item.getRequirement().Checkrequire(this.level, this.STR, this.CON, this.DEX);
    }

    public void equips(Items item) {
        System.out.println("------------------------------------------");
        if (isWearable(item)) {
            String checkSlot = equipment_slot.findEmptySlot(); //findEmptySlot will return "FULL" if it can't find empty slot and return first slot position if it finds
            if (!checkSlot.equals("FULL")) { // I tried to fix this to be better but I can't
                equipment_slot.setSlot(checkSlot, item);
                addmaxHP += item.getMAXHP();
                addmaxMana += item.getMAXMana();
                addSTR += item.getSTR();
                addCON += item.getCON();
                addDEX += item.getDEX();
                System.out.println(this.name + " equipped " + equipment_slot.getSlot(checkSlot).getName());
            } else if (checkSlot == "FULL") {
                System.out.println(this.name + "'s slots are FULL!");
                return;
            }
        } else {
            System.out.println(this.name + " can't equip " + item.getName());
        }
        System.out.println("------------------------------------------");
    }

    private boolean isWorn(Items item) {
        for (String slotName : equipment_slot.getSlotName()) {
            Items equipmentSlot = equipment_slot.getSlot(slotName);
            if (equipmentSlot != null && equipment_slot.getSlot(slotName).getName().equals(item.getName())) {
                equipment_slot.setSlot(slotName, null);
                addmaxHP -= item.getMAXHP();
                addmaxMana -= item.getMAXMana();
                addSTR -= item.getSTR();
                addCON -= item.getCON();
                addDEX -= item.getDEX();
                System.out.println(this.name + " unequipped " + item.getName());
                System.out.println("------------------------------------------");
                return true;
            }
        }
        return false;
    }

    public void unequips(Items item) {
        System.out.println("------------------------------------------");
        isWorn(item);
        if(!isWorn(item)) {
            System.out.println(this.name + " did not equip " + item.getName());
        }
        System.out.println("------------------------------------------");
    }

    private void CheckSlot() {
        int i = 1;
        for (String slotName : equipment_slot.getSlotName()) {

            Items item = equipment_slot.getSlot(slotName);
            if (item != null) {
                System.out.println("["+ i + "] " + item.getName() + " Skill : " + item.getSpecialMove());
            } else {
                System.out.println("["+ i + "] " + "[Slot's empty]");
            }
            i++;
        }
    }

    public void showEquipment() {
        System.out.println("------------------------------------------");
        System.out.println(this.name + "'s slots :");
        CheckSlot();
        System.out.println("------------------------------------------");
    }

    private void setStatsToDefault(Charactor charactor) {
        charactor.level = 1;
        charactor.UpdatedStats();
    }

    private void unEquipAll(Charactor charactor) {
        for(String slotName : charactor.equipment_slot.getSlotName()) {
            Items item = charactor.equipment_slot.getSlot(slotName);
            if(item == null) {continue;}
            else charactor.unequips(item);
        }
    }

    private void resetOpponent(Charactor charactor) {
        unEquipAll(charactor);
        setStatsToDefault(charactor);
    }

    private void drainStats(Charactor opponent) {
        this.addmaxHP += opponent.maxHP;
        this.addmaxMana += opponent.maxMana;
        this.addSTR += opponent.STR;
        this.addCON += opponent.CON;
        this.addDEX += opponent.DEX;
    }

    public void drains (Charactor opponent) {
        System.out.println("------------------------------------------");
        System.out.println(this.name +" drained " + opponent.name + "!!!");
        drainStats(opponent);
        resetOpponent(opponent);
        UpdatedStats();
        System.out.println("------------------------------------------");
    }

    private boolean isWorthyLevel(Charactor enemy) {
        if(this.level >= enemy.level) return true;
        else return false;
    }

    private int DURA(Charactor charactor) {
        return charactor.CON*7/10;
    }

    private void takenDMG(Charactor enemy) {
        int DMG = STR-DURA(enemy);
        enemy.Wound += DMG;
        System.out.println(this.name + " attacked " + enemy.name);
        enemy.UpdatedStats();
        System.out.println(enemy.name + " loses " + DMG + " HP");
    }

    public void hits(Charactor enemy) {
        System.out.println("------------------------------------------");
        if(isWorthyLevel(enemy)) {
            takenDMG(enemy);
        } else System.out.println(this.name + " is lower level than " + enemy.level);
        System.out.println("------------------------------------------");
    }

    private void setGiverSlotEmpty(Items item) {
        for (String slotName : equipment_slot.getSlotName()) {
            if(equipment_slot.getSlotName().equals(item.getName()))
                equipment_slot.setSlot(slotName, null);
        }
    }

    public void gives(Charactor opponent , Items item) {
        System.out.println("------------------------------------------");
        if(this.isWorn(item)){
            System.out.println(this.name + " gave " + item.getName() + " " + opponent.name);
            opponent.equips(item);
            setGiverSlotEmpty(item);
        } else {
            System.out.println(this.name + " did not equip " + item.getName());
        }
        System.out.println("------------------------------------------");
    }
}