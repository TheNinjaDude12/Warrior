public class Environment {
    private String environmentName = null;

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void damage(Warrior warrior) {
        warrior.setHitPoints(warrior.getHitPoints() - 1);
    }

    public void increaseAttack(Opponent opponent, Warrior warrior, String environmentName) {
        if (environmentName.equals("Swamp")) {
            opponent.setAttack(opponent.getAttack()+ 1);
        } else if (environmentName.equals("Colosseum")) {
            warrior.setAttack(warrior.getAttack()+ 1);
        }
    }

    public void decreaseDefense(Opponent opponent) {
        opponent.setDefense(opponent.getDefense() - 1);
    }

}
