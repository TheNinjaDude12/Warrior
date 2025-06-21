/**
 * Represents a battle environment that applies specific effects to combatants during battle.
 * Different environments provide various buffs, debuffs, or neutral conditions.
 */
public class Environment {
    /**
     * The name of the current environment.
     */
    private String environmentName;

    /**
     * Constructs an Environment with the specified name.
     *
     * @param environmentName The name of the environment.
     */
    public Environment(String environmentName) {
        this.environmentName = environmentName;
    }

    /**
     * Applies environment-specific effects to the warrior and opponent each turn.
     * Different environments have different effects:
     * - Swamp: Increases opponent's attack by 1 and damages warrior by 1 HP each turn
     * - Colosseum: Increases warrior's attack by 1 and reduces opponent's defense by 1 each turn
     * - Arena: No effects (neutral environment)
     *
     * @param warrior The warrior participating in combat.
     * @param opponent The opponent participating in combat.
     * @param turn The current turn number (currently not used in calculations but kept for future expansion).
     */
    public void environmentEffects(Warrior warrior, Opponent opponent, int turn) {
        // Check if current environment is Swamp
        if (environmentName.equals("Swamp")) {
            // Apply swamp effects: favors the opponent
            int attackBonus = 1; // Simplified from original turn calculation
            opponent.setAttack(opponent.getAttack() + attackBonus);
            System.out.println("Opponent ATK increased by 1!");
            
            warrior.setHitPoints(warrior.getHitPoints() - 1);
            System.out.println("Warrior takes 1 DMG!");
        }
        // Check if current environment is Colosseum
        else if (environmentName.equals("Colosseum")) {
            // Apply colosseum effects: favors the warrior
            int attackBonus = 1; // Simplified from original turn calculation
            System.out.println("Warrior ATK increased by 1!");
            warrior.setAttack(warrior.getAttack() + attackBonus);
            
            System.out.println("Opponent DEF reduced by 1!");
            opponent.setDefense(opponent.getDefense() - 1);
        }
        // Arena environment has no effects (neutral battlefield)
    }

    /**
     * Retrieves the name of the current environment.
     *
     * @return The name of the environment.
     */
    public String getEnvironmentName() {
        return environmentName;
    }

    /**
     * Sets the name of the environment.
     *
     * @param environmentName The name to set for the environment.
     */
    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }
}
