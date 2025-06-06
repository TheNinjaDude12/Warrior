public class Opponent {
    private int hitPoints;
    private int attack;
    private int defense;
    private int speed;
    private boolean isCharging = false;

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

    public void opponentChoice(Opponent opponent, int choice) {
        switch (choice) {
            case 1:
                opponent.setHitPoints(150);
                opponent.setAttack(20);
                opponent.setDefense(20);
                opponent.setSpeed(40);
                System.out.println("Thief Selected");
                break;
            case 2:
                opponent.setHitPoints(250);
                opponent.setAttack(30);
                opponent.setDefense(30);
                opponent.setSpeed(30);
                System.out.println("Viking Selected");
                break;
            case 3:
                opponent.setHitPoints(350);
                opponent.setAttack(40);
                opponent.setDefense(40);
                opponent.setSpeed(20);
                System.out.println("Minotaur Selected");
                break;
            default:
                System.out.println("Invalid Choice");
        }
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
