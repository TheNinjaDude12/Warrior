public class Weapon {
    public String name;
    public int attack;
    public int speedPenalty;

    public Weapon(String name, int attack, int speedPenalty) {
        this.name = name;
        this.attack = attack;
        this.speedPenalty = speedPenalty;

    }



    public int getAttack() {
        return attack;
    }


    public int getSpeedPenalty() {
        return speedPenalty;
    }

    public String getName() {
        return name;
    }

}
