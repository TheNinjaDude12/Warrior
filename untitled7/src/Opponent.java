public class Opponent {
    private String name;
    private int hitPoints;
    private int attack;
    private int defense;
    private int speed;
    private boolean isCharging = false;
    private boolean isDefending = false;

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
        int damage = (int) (getAttack() - getAttack() * (getDefense()/100.0));
        if(isCharging) {
            damage *= 2;
            isCharging = false;
        }
        if(warrior.isDefending()) {
            damage = damage/2;
            warrior.setDefendingFalse();
        }
        if(warrior.getEvadeChance() == 100) {
            damage = 0;
        }
        System.out.println(getName()+ " attacks warrior for " + damage + " damage!\n") ;
        warrior.setHitPoints(warrior.getHitPoints() - damage );

    }

    public void defend() {
        isDefending = true;

    }
    public boolean isDefending() {
        return isDefending;

    }

    public void setDefendingFalse() {
        isDefending = false;

    }

    public void charge() {
        isCharging = true;

    }
    
    public void think(Warrior warrior, int faux) {
        switch(getName()) {
            case "Thief":
                attack(warrior);
                break;
            case "Viking":
                switch (faux) {
                    case 1, 3:
                        attack(warrior);
                        break;
                    case 2:
                        System.out.println("VIKING IS DEFENDING!!!");
                        defend();
                        break;
                }
                break;
            case "Minotaur":
                switch (faux) {
                    case 1:
                        attack(warrior);
                        break;
                    case 2:
                        System.out.println("MINOTAUR IS CHARGING!!!");
                        charge();
                        break;
                    case 3:
                        attack(warrior);
                        break;
                }
                break;
        }

        }

}
