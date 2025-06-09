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

    public int getEvadeChance() {
        return evadeChance;
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
        if(isCharging) {
            damage = damage * 3;
            isCharging = false;
        }
        if(opponent.isDefending()) {
            damage = damage/2;
            opponent.setDefendingFalse();
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

    public void setDefendingFalse() {
        isDefending = false;
    }

    public boolean charge() {
        if(!isCharging) {
            isCharging = true;
            return true;
        }
        else {
            System.out.println("Warrior is already charging!");
            return false;
        }


    }

    public void weaponAbility(Opponent opponent, int turn) {
        switch (getWeapon().getName()){
            case "Dagger":
                if(isDefendedLastTurn()) {
                    System.out.println("Evade Ready!!!");
                    if(isDefending) {
                        evadeChance = 100;
                        resetDefendedLastTurn();

                    }

                }

                break;
            case "Sword":
                if(turn == 1) {
                    setAttack(40);
                }

                break;
            case "Axe":
                if(isCharging()){
                    setSpeed(getSpeed() + 5);
                    System.out.println(getAttack());
                    setAttack(45);
                }
                else {
                    setAttack(40);
                    setSpeed(50 - armor.getSpeedPenalty() - weapon.getSpeedPenalty());
                }

                break;

        }



    }



}
