public class Environment {
    // Store the name of the current environment
    private String environmentName;

    // Setter method to assign the environment name
    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    // Method to apply environment-specific effects to warrior and opponent
    public void environmentEffects(Warrior warrior, Opponent opponent, int turn) {
        // Check if current environment is Swamp
        if (environmentName.equals("Swamp")) {
            // Calculate damage modifier (currently always equals 1 due to turn + turn - turn = turn)
            int addedDmg = turn + turn - turn;
            // Increase opponent's attack power
            opponent.setAttack(opponent.getAttack() + addedDmg);
            System.out.println("Opponent ATK increased by 1!");
            // Reduce warrior's hit points by 1
            warrior.setHitPoints(warrior.getHitPoints() - 1);
            System.out.println("Warrior takes 1 DMG!");
        }
        // Check if current environment is Colosseum
        else if (environmentName.equals("Colosseum")) {
            // Calculate damage modifier (currently always equals 1 due to turn + turn - turn = turn)
            int addedDmg = turn + turn - turn;
            System.out.println("Warrior ATK increased by 1!");
            // Increase warrior's attack power
            warrior.setAttack(warrior.getAttack() + addedDmg);
            System.out.println("Opponent DEF reduced by 1!");
            // Reduce opponent's defense by 1
            opponent.setDefense(opponent.getDefense() - 1);
        }
        // Note: No effects applied if environment is neither Swamp nor Colosseum
    }
}