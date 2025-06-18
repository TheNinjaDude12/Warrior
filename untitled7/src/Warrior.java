public class Warrior {
    // Base warrior statistics
    private int hitPoints = 100;    // Health points - warrior dies when this reaches 0
    private int attack = 1;         // Base attack value (modified by weapon)
    private int defense = 1;        // Base defense value (modified by armor)
    private int speed = 50;         // Base speed value (modified by equipment)

    // Equipment slots
    private Armor armor;            // Currently equipped armor
    public Weapon weapon;           // Currently equipped weapon (public for easy access)

    // Battle state tracking
    private boolean defendedLastTurn = false;  // Tracks if warrior defended on previous turn (for dagger ability)
    private boolean isDefending = false;       // True when warrior is currently defending
    private boolean isCharging = false;        // True when warrior is currently charging for next attack

    // Getter methods for accessing private attributes
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Charging state management
    public void setCharging(boolean charging) {
        isCharging = charging;
    }

    public boolean isCharging() {
        return isCharging;
    }

    // Defense state tracking methods (used for dagger weapon ability)
    public void resetDefendedLastTurn() {
        defendedLastTurn = false;
    }

    public boolean isDefendedLastTurn() {
        return defendedLastTurn;
    }

    // Equipment methods
    // Equip weapon and apply its stat modifications
    public void equip(Weapon weapon) {
        this.weapon = weapon;
        this.attack = weapon.getAttack();                           // Set attack to weapon's attack value
        this.speed = this.speed - weapon.getSpeedPenalty();        // Reduce speed by weapon's penalty
    }

    public Weapon getWeapon() {
        return weapon;
    }

    // Equip armor and apply its stat modifications
    public void equip(Armor armor) {
        this.armor = armor;
        this.defense = armor.getDefense();                          // Set defense to armor's defense value
        this.speed = this.speed - armor.getSpeedPenalty();         // Reduce speed by armor's penalty
    }

    public Armor getArmor() {
        return armor;
    }

    // Combat action: Attack the opponent
    public void attack(Opponent opponent) {
        int damage;

        // Check if this is a charged attack (triple damage)
        if(isCharging) {
            System.out.println("WARRIOR CHARGED, ATTACK WILL DO TRIPLE DAMAGE!!!");
            damage = (getAttack() * 3 - opponent.getDefense());     // Triple damage minus opponent's defense
            isCharging = false;                                     // Reset charging state after use
        }
        else{
            damage = (getAttack() - opponent.getDefense());         // Normal damage calculation
        }

        // Check if opponent is defending (halves damage)
        if(opponent.isDefending()) {
            damage = damage/2;                                      // Defending reduces damage by half
            opponent.setDefendingFalse();                           // Reset opponent's defending state
        }

        // Ensure damage is never negative
        if(damage < 0) {
            damage = 0;
        }

        System.out.printf("Warrior attacks %s for %d damage!\n", opponent.getName(), damage);
        opponent.setHitPoints(opponent.getHitPoints() - damage);    // Apply damage to opponent
    }

    // Combat action: Defend (reduces incoming damage and enables special abilities)
    public void defend() {
        defendedLastTurn = true;        // Mark that warrior defended this turn (for weapon abilities)
        isDefending = true;             // Set defending state (reduces incoming damage)
    }

    // Defense state management
    public boolean isDefending() {
        return isDefending;
    }

    public void setDefendingFalse() {
        isDefending = false;
    }

    // Combat action: Charge for next attack (enables triple damage on next attack)
    public boolean charge() {
        if(!isCharging) {
            isCharging = true;          // Set charging state
            return true;                // Return true if charge was successful
        }
        else {
            System.out.println("Warrior is already charging!");
            return false;               // Return false if already charging
        }
    }

    // Apply weapon-specific special abilities each turn
    public void weaponAbility(Opponent opponent, int turn) {
        switch (getWeapon().getName()){
            case "Dagger":
                // Dagger ability: Every other defend becomes a 100% evade
                if(isDefendedLastTurn()) {
                    System.out.println("Evade Ready!!!");
                    if(isDefending) {
                        opponent.setAttack(0);          // Set opponent's attack to 0 (complete evasion)
                        resetDefendedLastTurn();        // Reset the defended last turn flag
                    }
                }
                break;

            case "Sword":
                // Sword ability: Gain +10 attack bonus (40 total instead of 30)
                if(turn == 1) {
                    setAttack(40);                      // Increase attack value on first turn
                }
                break;

            case "Axe":
                // Axe ability: When charging, gain +5 speed and +5 attack
                if(isCharging()){
                    setSpeed(getSpeed() + 5);           // Increase speed while charging
                    System.out.println(getAttack());   // Debug output showing current attack
                    setAttack(45);                      // Increase attack while charging (40 + 5)
                }
                else {
                    // Reset axe stats when not charging
                    setAttack(40);                      // Reset to base axe attack
                    setSpeed(50 - armor.getSpeedPenalty() - weapon.getSpeedPenalty()); // Recalculate speed
                }
                break;
        }
    }
}