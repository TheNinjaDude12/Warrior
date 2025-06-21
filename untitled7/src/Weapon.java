/**
 * Represents a weapon with attributes such as name, attack value, and speed penalty.
 * A weapon can be equipped by a warrior to modify their combat statistics.
 */
public class Weapon {
    /**
     * The name of the weapon (e.g., "Sword", "Dagger", "Axe").
     */
    public String name;           // Display name of the weapon
    
    /**
     * The attack value provided by this weapon.
     */
    public int attack;            // Attack value provided by this weapon
    
    /**
     * The speed penalty caused by wielding this weapon.
     */
    public int speedPenalty;      // Speed reduction caused by wielding this weapon

    /**
     * Constructs a Weapon object with the specified name, attack value, and speed penalty.
     *
     * @param name The name of the weapon.
     * @param attack The attack value provided by the weapon.
     * @param speedPenalty The speed penalty caused by wielding the weapon.
     */
    public Weapon(String name, int attack, int speedPenalty) {
        this.name = name;
        this.attack = attack;
        this.speedPenalty = speedPenalty;
    }

    /**
     * Retrieves the attack value provided by this weapon.
     *
     * @return The attack value of the weapon.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Retrieves the speed penalty caused by wielding this weapon.
     *
     * @return The speed penalty of the weapon.
     */
    public int getSpeedPenalty() {
        return speedPenalty;
    }

    /**
     * Retrieves the name of the weapon.
     *
     * @return The name of the weapon.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the attack value of the weapon.
     *
     * @param attack The attack value to set.
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Sets the speed penalty of the weapon.
     *
     * @param speedPenalty The speed penalty to set.
     */
    public void setSpeedPenalty(int speedPenalty) {
        this.speedPenalty = speedPenalty;
    }

    /**
     * Sets the name of the weapon.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
