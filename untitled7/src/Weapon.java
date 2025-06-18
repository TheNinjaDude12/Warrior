public class Weapon {
    // Weapon properties (public for easy access, though private with getters would be better practice)
    public String name;           // Display name of the weapon
    public int attack;            // Attack value provided by this weapon
    public int speedPenalty;      // Speed reduction caused by wielding this weapon

    // Constructor to initialize weapon with its properties
    public Weapon(String name, int attack, int speedPenalty) {
        this.name = name;
        this.attack = attack;
        this.speedPenalty = speedPenalty;
    }

    // Getter method to retrieve attack value
    public int getAttack() {
        return attack;
    }

    // Getter method to retrieve speed penalty
    public int getSpeedPenalty() {
        return speedPenalty;
    }

    // Getter method to retrieve weapon name
    public String getName() {
        return name;
    }
}