public class Armor {
    public String name;
    public int defense;
    public int speedPenalty;


    public Armor(String name, int defense, int speedPenalty) {
        this.name = name;
        this.defense = defense;
        this.speedPenalty = speedPenalty;

    }

    public String getName() {
        return name;
    }
    public int getDefense() {
        return defense;
    }


    public int getSpeedPenalty() {
        return speedPenalty;
    }


}



