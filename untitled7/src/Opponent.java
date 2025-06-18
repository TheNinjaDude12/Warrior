public class Opponent {
    // Opponent statistics
    private String name;            // Name/type of the opponent (Thief, Viking, Minotaur)
    private int hitPoints;          // Health points - opponent dies when this reaches 0
    private int attack;             // Attack damage value
    private int defense;            // Defense value (reduces incoming damage)
    private int speed;              // Speed value (determines turn order)

    // Battle state tracking
    private boolean isCharging = false;     // True when opponent is charging for next attack
    private boolean isDefending = false;    // True when opponent is currently defending

    // Getter methods for accessing opponent statistics
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

    // Setter methods for modifying opponent statistics
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

    // Combat action: Attack the warrior
    public void attack(Warrior warrior) {
        int damage;

        // Check if this is a charged attack (triple damage)
        if(isCharging) {
            System.out.println(getName() + " CHARGED, ATTACK WILL DO TRIPLE DAMAGE!!!");
            damage = (getAttack() * 3 - warrior.getDefense());      // Triple damage minus warrior's defense
            isCharging = false;                                     // Reset charging state after use
        }
        else{
            damage = (getAttack() - warrior.getDefense());          // Normal damage calculation
        }

        // Check if warrior is defending (halves damage)
        if(warrior.isDefending()) {
            damage = damage/2;                                      // Defending reduces damage by half
            warrior.setDefendingFalse();                            // Reset warrior's defending state
        }

        // Ensure damage is never negative
        if(damage < 0) {
            damage = 0;
        }

        System.out.println(getName()+ " attacks Warrior for " + damage + " damage!\n") ;
        warrior.setHitPoints(warrior.getHitPoints() - damage);      // Apply damage to warrior
    }

    // Combat action: Defend (reduces incoming damage by half)
    public void defend() {
        isDefending = true;         // Set defending state
    }

    // Defense state management
    public boolean isDefending() {
        return isDefending;
    }

    public void setDefendingFalse() {
        isDefending = false;
    }

    // Combat action: Charge for next attack (enables triple damage on next attack)
    public void charge() {
        isCharging = true;          // Set charging state
    }

    // AI decision making - determines opponent's action based on their type and turn pattern
    public void think(Warrior warrior, int faux) {
        switch(getName()) {
            case "Thief":
                // Thief AI: Always attacks (aggressive glass cannon strategy)
                attack(warrior);
                break;

            case "Viking":
                // Viking AI: Alternates between attacking and defending
                switch (faux) {
                    case 1, 3:      // On turns 1 and 3 of the pattern
                        attack(warrior);
                        break;
                    case 2:         // On turn 2 of the pattern
                        System.out.println("VIKING IS DEFENDING!!!");
                        defend();
                        break;
                }
                break;

            case "Minotaur":
                // Minotaur AI: Attack, charge, attack pattern (uses charge for big damage)
                switch (faux) {
                    case 1:         // Turn 1: Attack
                        attack(warrior);
                        break;
                    case 2:         // Turn 2: Charge for next turn
                        System.out.println("MINOTAUR IS CHARGING!!!");
                        charge();
                        break;
                    case 3:         // Turn 3: Attack (will be charged attack from turn 2)
                        attack(warrior);
                        break;
                }
                break;
        }
    }
}