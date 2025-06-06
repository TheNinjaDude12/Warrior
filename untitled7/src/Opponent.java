public class Opponent {
    private String name;
    private int hitPoints;
    private int attack;
    private int defense;
    private int speed;
    private boolean isCharging = false;

    public String getName() {
        return name;
    }
    
    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void setCharging(boolean charging) {
        isCharging = charging;
    }
    
    public void attack(Warrior warrior) {

    }

    public void defend(Warrior warrior) {

    }

    public void charge() {

    }
    
    public void think() {
        
    }
}
