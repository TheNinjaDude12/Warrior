import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Phase2();

    }

    public static void showStats(Warrior warrior) {
        System.out.println("CURRENT STATS");
        System.out.printf("HP %d\n", warrior.getHitPoints());
        System.out.printf("ATK %d\n", warrior.getAttack());
        System.out.printf("DEF %d\n", warrior.getDefense());
        System.out.printf("SPD %d\n", warrior.getSpeed());
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public static void showStats(Opponent opponent) {
        System.out.println("CURRENT STATS");
        System.out.printf("HP %d\n", opponent.getHitPoints());

    }

    public static void chooseArmor(Warrior warrior) {
        Armor lightArmor = new Armor("Light Armor", 20, 5);
        Armor mediumArmor = new Armor("Medium Armor", 30, 15);
        Armor heavyArmor = new Armor("Heavy Armor", 40, 25);
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Choose your armor
                1. Light Armor\s
                   20 DEF -5 SPD
                2. Medium Armor
                   30 DEF -15 SPD
                3. Heavy Armor
                   40 DEF -25 SPD"""
        );
        switch (sc.nextInt()) {
            case 1 -> warrior.equip(lightArmor);
            case 2 -> warrior.equip(mediumArmor);
            case 3 -> warrior.equip(heavyArmor);
        }
        System.out.println("Equipped " + warrior.getArmor().getName());
        sc.nextLine();
    }

    public static void chooseWeapon(Warrior warrior) {
        Weapon dagger = new Weapon("Dagger", 20, 0);
        Weapon sword = new Weapon("Sword", 30, 10);
        Weapon battleaxe = new Weapon("Battleaxe", 40, 20);
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your weapon");
        System.out.println("1.Dagger (+20 ATK)");
        System.out.print("""
                Weapon Ability:When defending, every other defend will become a 100% evade.
                
                """);
        System.out.println("2. Sword(+30 ATK -10 SPD)");
        System.out.print("""
                Weapon Ability: When attacking, gain an additional +10 attack.
                
                """);
        System.out.println("3. BattleAxe (+40 ATK -20 SPD)");
        System.out.print("""
                Weapon Ability: When charging, gain 5 speed and 5 attack in the next turn.
                """);
        switch (sc.nextInt()) {
            case 1:
                warrior.equip(dagger);
                break;
            case 2:
                warrior.equip(sword);
                break;
            case 3:
                warrior.equip(battleaxe);
        }
        System.out.println("Equipped " + warrior.getWeapon().getName());
    }

    public static Warrior characterCreation() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Warrior warrior = new Warrior();
            System.out.println("Welcome to Last Souls");
            System.out.println("Warrior Setup");
            showStats(warrior);
            chooseArmor(warrior);
            showStats(warrior);
            chooseWeapon(warrior);
            showStats(warrior);
            System.out.println("Proceed with current character?");
            System.out.println("1. Continue");
            System.out.println("2. Reset");
            System.out.print("Choice: ");
            String confirm = sc.nextLine();
            if (confirm.equals("1")) {
                return warrior;
            }
        }
    }

    public static Opponent chooseOpponent() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            Opponent opponent = new Opponent();
            System.out.println("""
                    Choose your Opponent
                    1. Thief
                        150 HP // 20 ATK // 20 DEF // 40 SPD
                    2. Viking
                        250 HP // 30 ATK // 30 DEF // 30 SPD
                    3. Minotaur
                        350 HP // 40 ATK // 40 DEF // 20 SPD
                    
                    Choice: """
            );
            int input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1:
                    opponent.setName("Thief");
                    opponent.setHitPoints(150);
                    opponent.setAttack(20);
                    opponent.setDefense(20);
                    opponent.setSpeed(40);
                    System.out.println("Thief Selected");
                    break;
                case 2:
                    opponent.setName("Viking");
                    opponent.setHitPoints(250);
                    opponent.setAttack(30);
                    opponent.setDefense(30);
                    opponent.setSpeed(30);
                    System.out.println("Viking Selected");
                    break;
                case 3:
                    opponent.setName("Minotaur");
                    opponent.setHitPoints(350);
                    opponent.setAttack(40);
                    opponent.setDefense(40);
                    opponent.setSpeed(20);
                    System.out.println("Minotaur Selected");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    continue;
            }
            System.out.println("Proceed with current opponent?");
            System.out.println("1. Continue");
            System.out.println("2. Reset");
            System.out.print("Choice: ");
            String confirm = sc.nextLine();
            if (confirm.equals("1")) {
                return opponent;
            }
        }
    }

    public static Environment chooseEnvironment() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            Environment environment = new Environment();
            System.out.println("""
                    Choose Your Environment
                    1. Arena
                        No Buffs or Penalties
                    2. Swamp
                        Player loses 1 HP every turn
                        Opponent gains 1 ATK every turn
                    3. Colosseum
                        Player gains 1 ATK every turn
                        Opponent loses 1 DEF every turn
                    
                    Choice: 
                    """);

            int input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1:
                    environment.setEnvironmentName("Arena");
                    break;
                case 2:
                    environment.setEnvironmentName("Swamp");
                    break;
                case 3:
                    environment.setEnvironmentName("Colosseum");
                    break;
                default:
                    System.out.println("Invalid Choice.");
                    continue;
            }
            System.out.println("Proceed with current Environment?");
            System.out.println("1. Continue");
            System.out.println("2. Reset");
            System.out.print("Choice: ");
            String confirm = sc.nextLine();
            if (confirm.equals("1")) {
                return environment;
            }
        }
    }

    public static int WarriorMove() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose your move");
            System.out.print("1. ATTACK ");
            System.out.print("2. DEFEND ");
            System.out.print("3. CHARGE");
            int choice = sc.nextInt();
            if (choice < 4 && choice > 0) {
                return choice;
            } else
                System.out.println("Invalid move!");

        }
    }

    public static boolean isDead(Warrior warrior) {
        return warrior.getHitPoints() < 0;

    }

    public static boolean isDead(Opponent opponent) {
        return opponent.getHitPoints() < 0;

    }


    public static void Phase2() {
        Warrior warrior = characterCreation();
        Opponent opponent = chooseOpponent();
        Environment environment = chooseEnvironment();
        int faux = 1;
        boolean warriorDead = false;
        boolean opponentDead = false;
        int temp = opponent.getAttack();



        while (!warriorDead && !opponentDead) {

            environment.environmentEffects(warrior, opponent);
            warrior.weaponAbility(opponent, temp);
            System.out.print("\nWARRIOR HP " + warrior.getHitPoints() + "\t\t" + "OPPONENT HP " + opponent.getHitPoints());
            System.out.print("\nWARRIOR ATK " + warrior.getAttack() + "\t\t" + "OPPONENT ATK " + opponent.getAttack());
            System.out.print("\nWARRIOR DEF " + warrior.getDefense() + "\t\t" + "OPPONENT DEF " + opponent.getDefense());
            if(faux == 3) {
                faux = 1;
            }
            int warriorChoice = WarriorMove();
            if (warrior.getSpeed() > opponent.getSpeed()) {
                switch (warriorChoice) {
                    case 1:
                        warrior.attack(opponent);
                        opponentDead = isDead(warrior);
                        opponent.think(warrior,faux);
                        warriorDead = isDead(warrior);
                        faux++;
                        break;
                    case 2:
                        warrior.defend();
                        opponent.think(warrior,faux);
                        warriorDead = isDead(warrior);
                        faux++;
                        break;
                    case 3:
                        warrior.charge();
                        opponent.think(warrior,faux);
                        warriorDead = isDead(warrior);
                        faux++;
                        break;

                }


            }
            else if (warrior.getSpeed() < opponent.getSpeed()) {
                switch (warriorChoice) {
                    case 1:
                        opponent.think(warrior,faux);
                        warriorDead = isDead(warrior);
                        warrior.attack(opponent);
                        opponentDead = isDead(opponent);
                        faux++;
                        break;

                    case 2:
                        opponent.think(warrior,faux);
                        warriorDead = isDead(warrior);
                        warrior.defend();
                        faux++;
                        break;
                    case 3:
                        opponent.think(warrior,faux);
                        warriorDead = isDead(warrior);
                        warrior.charge();
                        faux++;
                        break;

                }
            }

        }



        if(warriorDead) {
            System.out.println("YOU HAVE DIED!");
            System.out.println("Tip: Minecraft exists for players like you");
        }
        else {
            System.out.println("YOU WON!!!");
        }


    }


}
