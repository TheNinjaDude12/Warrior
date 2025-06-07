public class Warrior {
    private int hitPoints = 100;
    private int attack = 1;
    private int defense = 1;
    private int speed = 50;
    private Armor armor;
    public Weapon weapon;
    private boolean defendedLastTurn = false;
    private boolean isDefending = false;
    private int evadeChance = 0;
    private boolean isCharging = false;

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }



    public int getDefense() {
        return defense;
    }


    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }



    public void setEvadeChance(int evadeChance) {
        this.evadeChance = evadeChance;

    }

    public void setCharging(boolean charging) {
        isCharging = charging;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void resetDefendedLastTurn() {
        defendedLastTurn = false;
    }

    public boolean isDefendedLastTurn() {
        return  defendedLastTurn;
    }



    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void equip(Weapon weapon) {
        this.weapon = weapon;
        this.attack = weapon.getAttack();
        this.speed = this.speed - weapon.getSpeedPenalty();

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void equip(Armor armor) {
        this.armor = armor;
        this.defense = armor.getDefense();
        this.speed = this.speed - armor.getSpeedPenalty();

    }

    public Armor getArmor() {
        return armor;
    }


    public void attack(Opponent opponent) {
        int damage = (int) (getAttack() - getAttack() * (getDefense()/100.0));
        if(opponent.isDefending()) {
            damage = damage/2;
        }
        System.out.printf("Warrior attakcs %s for %d damage!\n", opponent.getName(), damage);
        opponent.setHitPoints(opponent.getHitPoints() - damage );

    }
    public void defend() {
        defendedLastTurn = true;
        isDefending = true;

    }

    public boolean isDefending() {
        return isDefending;
    }

    public void charge() {
        isCharging = true;

    }



}
