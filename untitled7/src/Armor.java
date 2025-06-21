/**
 * Represents an armor item with properties such as name, defense value, and speed penalty.
 * Armor can be equipped by a warrior to modify their defensive capabilities and movement speed.
 */
public class Armor {
    /**
     * The name of the armor piece.
     */
    private String name;           // Display name of the armor piece
    
    /**
     * The defense value provided by this armor.
     */
    private int defense;           // Defense value provided by this armor
    
    /**
     * The speed penalty caused by wearing this armor.
     */
    private int speedPenalty;      // Speed reduction caused by wearing this armor

    /**
     * Constructs an Armor object with the specified name, defense value, and speed penalty.
     *
     * @param name The name of the armor.
     * @param defense The defense value provided by this armor.
     * @param speedPenalty The speed penalty incurred by wearing this armor.
     */
    public Armor(String name, int defense, int speedPenalty) {
        this.name = name;
        this.defense = defense;
        this.speedPenalty = speedPenalty;
    }

    /**
     * Retrieves the name of the armor.
     *
     * @return The name of the armor.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the defense value of the armor.
     *
     * @return The defense value of the armor.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Retrieves the speed penalty associated with the armor.
     *
     * @return The speed penalty caused by the armor.
     */
    public int getSpeedPenalty() {
        return speedPenalty;
    }

    /**
     * Sets the name of the armor.
     *
     * @param name The name to set for the armor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the defense value of the armor.
     *
     * @param defense The defense value to set.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Sets the speed penalty of the armor.
     *
     * @param speedPenalty The speed penalty to set.
     */
    public void setSpeedPenalty(int speedPenalty) {
        this.speedPenalty = speedPenalty;
    }
}
