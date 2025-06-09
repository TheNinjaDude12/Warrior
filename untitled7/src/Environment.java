public class Environment {
    private String environmentName = null;

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void environmentEffects(Warrior warrior, Opponent opponent, int turn) {
        if (environmentName.equals("Swamp")) {
            opponent.setAttack(opponent.getAttack()+ 1);
            System.out.println("Opponent ATK increased by 1!");
            warrior.setHitPoints(warrior.getHitPoints()-1);
            System.out.println("Warrior takes 1 DMG!");
        } else if (environmentName.equals("Colosseum")) {
            int addedDmg =  turn + turn - turn;
            System.out.println("Warrior ATK increased by 1!");
            warrior.setAttack(warrior.getAttack() + addedDmg);
            System.out.println("Opponent DEF reduced by 1!");
            opponent.setDefense(opponent.getDefense()-1);
        }


    }



}
