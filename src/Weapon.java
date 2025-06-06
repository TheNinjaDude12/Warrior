public class Weapon {
    public String name;
    public int attack;
    public int speedPenalty;

    public Weapon(String name, int attack, int speedPenalty) {
        this.name = name;
        this.attack = attack;
        this.speedPenalty = speedPenalty;

    }

    public void weaponAbility(Warrior warrior) {
       switch (warrior.getWeapon().getName()){
           case "Dagger":
               if(warrior.isDefendedLastTurn()){
                   warrior.setEvadeChance(100);
                   warrior.resetDefendedLastTurn();

               }
               break;
           case "Sword":
               warrior.setAttack(30+10);
               break;
           case "Axe":
               if(warrior.isCharging()){
                   warrior.setSpeed(warrior.getSpeed() + 5);
                   warrior.setSpeed(warrior.getAttack() + 5);
               }
               break;

       }



    }

    public int getAttack() {
        return attack;
    }


    public int getSpeedPenalty() {
        return speedPenalty;
    }

    public String getName() {
        return name;
    }

}
