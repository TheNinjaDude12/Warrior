/**
 * Represents a Warrior in a combat scenario with attributes such as health points, attack, defense, speed, and equipment.
 * The warrior can perform actions such as attacking, defending, charging, and using weapon abilities.
 */
public class Warrior {
    // Base warrior statistics
    /**
     * Health points of the warrior. The warrior dies when this reaches 0.
     */
    private int hitPoints = 100;    // Health points - warrior dies when this reaches 0
    
    /**
     * Base attack value of the warrior. Modified by the weapon.
     */
    private int attack = 1;         // Base attack value (modified by weapon)
    
    /**
     * Base defense value of the warrior. Modified by the armor.
     */
    private int defense = 1;        // Base defense value (modified by armor)
    
    /**
     * Base speed value of the warrior. Modified by the equipment.
     */
    private int speed = 50;         // Base speed value (modified by equipment)

    // Equipment slots
    /**
     * Currently equipped armor. 
     */
    private Armor armor;            // Currently equipped armor
    
    /**
     * Currently equipped weapon. 
     */
    public Weapon weapon;           // Currently equipped weapon (public for easy access)

    // Battle state tracking
    /**
     * Tracks if the warrior defended on the previous turn. Used for weapon abilities like the Dagger's evade.
     */
    private boolean defendedLastTurn = false;  // Tracks if warrior defended on previous turn (for dagger ability)
    
    /**
     * True when the warrior is currently defending, reducing incoming damage.
     */
    private boolean isDefending = false;       // True when warrior is currently defending
    
    /**
     * True when the warrior is currently charging for the next attack (triple damage).
     */
    private boolean isCharging = false;        // True when warrior is currently charging for next attack

    // Getter methods for accessing private attributes
    /**
     * Retrieves the warrior's current hit points.
     *
     * @return The current hit points of the warrior.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Sets the warrior's current hit points.
     *
     * @param hitPoints The hit points to be set.
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Retrieves the warrior's current attack value.
     *
     * @return The current attack value of the warrior.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Sets the warrior's attack value.
     *
     * @param attack The attack value to be set.
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Retrieves the warrior's current defense value.
     *
     * @return The current defense value of the warrior.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the warrior's defense value.
     *
     * @param defense The defense value to be set.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Retrieves the warrior's current speed value.
     *
     * @return The current speed value of the warrior.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the warrior's speed value.
     *
     * @param speed The speed value to be set.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Charging state management
    /**
     * Sets the warrior's charging state, allowing triple damage on the next attack.
     *
     * @param charging True if the warrior is charging, false otherwise.
     */
    public void setCharging(boolean charging) {
        isCharging = charging;
    }

    /**
     * Checks if the warrior is currently charging for a triple damage attack.
     *
     * @return True if the warrior is charging, false otherwise.
     */
    public boolean isCharging() {
        return isCharging;
    }

    // Defense state management
    /**
     * Checks if the warrior is currently defending.
     *
     * @return True if the warrior is defending, false otherwise.
     */
    public boolean isDefending() {
        return isDefending;
    }

    /**
     * Resets the warrior's defending state to false.
     */
    public void setDefendingFalse() {
        isDefending = false;
    }

    // Defense state tracking methods (used for dagger weapon ability)
    /**
     * Resets the tracking of whether the warrior defended last turn.
     */
    public void resetDefendedLastTurn() {
        defendedLastTurn = false;
    }

    /**
     * Checks if the warrior defended on the last turn.
     *
     * @return True if the warrior defended last turn, false otherwise.
     */
    public boolean isDefendedLastTurn() {
        return defendedLastTurn;
    }

    // Equipment methods
    /**
     * Equips a weapon and applies its stat modifications to the warrior.
     *
     * @param weapon The weapon to be equipped.
     */
    public void equip(Weapon weapon) {
        this.weapon = weapon;
        this.attack = weapon.getAttack();                           // Set attack to weapon's attack value
        this.speed = this.speed - weapon.getSpeedPenalty();        // Reduce speed by weapon's penalty
    }

    /**
     * Retrieves the currently equipped weapon.
     *
     * @return The currently equipped weapon.
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Equips an armor and applies its stat modifications to the warrior.
     *
     * @param armor The armor to be equipped.
     */
    public void equip(Armor armor) {
        this.armor = armor;
        this.defense = armor.getDefense();                          // Set defense to armor's defense value
        this.speed = this.speed - armor.getSpeedPenalty();         // Reduce speed by armor's penalty
    }

    /**
     * Retrieves the currently equipped armor.
     *
     * @return The currently equipped armor.
     */
    public Armor getArmor() {
        return armor;
    }

    // Combat actions
    /**
     * Attacks an opponent and calculates the damage based on whether the warrior is charging
     * and whether the opponent is defending.
     *
     * @param opponent The opponent being attacked.
     */
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
            damage = damage / 2;                                      // Defending reduces damage by half
            opponent.setDefendingFalse();                           // Reset opponent's defending state
        }

        // Ensure damage is never negative
        if(damage < 0) {
            damage = 0;
        }

        System.out.printf("Warrior attacks %s for %d damage!\n", opponent.getName(), damage);
        opponent.setHitPoints(opponent.getHitPoints() - damage);    // Apply damage to opponent
    }

    /**
     * Activates the warrior's defense, reducing incoming damage by half and tracking defense state for weapon abilities.
     */
    public void defend() {
        defendedLastTurn = true;
        isDefending = true;
        System.out.println("WARRIOR IS DEFENDING!!!");
    }

    /**
     * Initiates a charge attack, setting up triple damage for the next attack.
     *
     * @return True if charging was successful, false if already charging.
     */
    public boolean charge() {
        if (isCharging) {
            return false; // Already charging
        }
        isCharging = true;
        System.out.println("WARRIOR IS CHARGING!!!");
        return true;
    }

    /**
     * Executes weapon-specific abilities based on the currently equipped weapon and battle conditions.
     *
     * @param opponent The opponent in combat.
     * @param turn The current turn number.
     */
    public void weaponAbility(Opponent opponent, int turn) {
        if (weapon == null) {
            return; // No weapon equipped
        }

        switch (weapon.getName()) {
            case "Dagger":
                // Dagger ability: Every other defend becomes a 100% evade
                if (defendedLastTurn && turn % 2 == 0) {
                    System.out.println("Dagger ability activated: 100% evade!");
                    // Implementation would involve setting a temporary evade flag
                }
                break;
                
            case "Sword":
                // Sword ability: Gain +10 attack when attacking
                if (!isDefending && !isCharging) {
                    this.attack += 10;
                    System.out.println("Sword ability activated: +10 attack!");
                }
                break;
                
            case "Axe":
                // Battleaxe ability: When charging, gain +5 speed and +5 attack next turn
                if (isCharging) {
                    this.speed += 5;
                    this.attack += 5;
                    System.out.println("Battleaxe ability activated: +5 speed and +5 attack!");
                }
                break;
        }
    }
}
