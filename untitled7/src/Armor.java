public class Armor {
    // Armor properties (public for easy access, though private with getters would be better practice)
    public String name;           // Display name of the armor piece
    public int defense;           // Defense value provided by this armor
    public int speedPenalty;      // Speed reduction caused by wearing this armor

    // Constructor to initialize armor with its properties
    public Armor(String name, int defense, int speedPenalty) {
        this.name = name;
        this.defense = defense;
        this.speedPenalty = speedPenalty;
    }

    // Getter method to retrieve armor name
    public String getName() {
        return name;
    }

    // Getter method to retrieve defense value
    public int getDefense() {
        return defense;
    }

    // Getter method to retrieve speed penalty
    public int getSpeedPenalty() {
        return speedPenalty;
    }
}