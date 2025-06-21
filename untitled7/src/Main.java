import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Phase2(); // Start the main game loop
    }

    // Display current warrior statistics and wait for user input
    public static void showStats(Warrior warrior) {
        System.out.println("CURRENT STATS");
        System.out.printf("HP %d\n", warrior.getHitPoints());
        System.out.printf("ATK %d\n", warrior.getAttack());
        System.out.printf("DEF %d\n", warrior.getDefense());
        System.out.printf("SPD %d\n", warrior.getSpeed());
        Scanner sc = new Scanner(System.in);
        System.out.println("Press any key to continue...");
        sc.nextLine();


    }

    // Allow player to choose armor with different defense/speed tradeoffs
    public static void chooseArmor(Warrior warrior) {
        // Create three armor options with different stats
        Armor lightArmor = new Armor("Light Armor", 20, 5);   // Low defense, low speed penalty
        Armor mediumArmor = new Armor("Medium Armor", 30, 15); // Medium defense, medium speed penalty
        Armor heavyArmor = new Armor("Heavy Armor", 40, 25);   // High defense, high speed penalty

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

        // Equip the chosen armor based on user input
        switch (sc.nextInt()) {
            case 1 -> warrior.equip(lightArmor);
            case 2 -> warrior.equip(mediumArmor);
            case 3 -> warrior.equip(heavyArmor);
        }

        System.out.println("Equipped " + warrior.getArmor().getName());
        System.out.println("Press any key to continue...");
        sc.nextLine(); // Buffer
        sc.nextLine(); // Wait for user acknowledgment
    }



    // Allow player to choose weapon with different attack/speed tradeoffs and special abilities
    public static void chooseWeapon(Warrior warrior) {
        // Create three weapon options with different stats and abilities
        Weapon dagger = new Weapon("Dagger", 20, 0);     // Low attack, no speed penalty
        Weapon sword = new Weapon("Sword", 30, 10);      // Medium attack, medium speed penalty
        Weapon battleaxe = new Weapon("Axe", 40, 20);    // High attack, high speed penalty
        Weapon staff = new Weapon("Staff", 30, 20);      // Medium attack, high speed penalty

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your weapon");

        // Display weapon options with their special abilities
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
        System.out.println("4. Staff (+30 ATK -20 SPD)");
        System.out.print("""
                Weapon Ability: When charging, conjure a random element to boost your stats.
                                Fire: +5 ATK  // Wind: +5 SPD // Water: +10 HP // Earth: +3 DEF
                """);

        // Equip the chosen weapon based on user input
        switch (sc.nextInt()) {
            case 1:
                warrior.equip(dagger);
                break;
            case 2:
                warrior.equip(sword);
                break;
            case 3:
                warrior.equip(battleaxe);
                break;
            case 4:
                warrior.equip(staff);
        }
        System.out.println("Equipped " + warrior.getWeapon().getName());
        System.out.println("Press any key to continue...");
        sc.nextLine(); // Buffer
        sc.nextLine(); // Wait for user acknowledgment
    }

    // Handle complete character creation process with option to restart
    public static Warrior characterCreation() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Warrior warrior = new Warrior(); // Create new warrior with base stats
            System.out.println("Welcome to Last Souls");
            System.out.println("Warrior Setup");

            // Character creation flow: show stats, choose armor, show updated stats, choose weapon, show final stats
            showStats(warrior);
            chooseArmor(warrior);
            showStats(warrior);
            chooseWeapon(warrior);
            showStats(warrior);

            // Confirm character or allow restart
            System.out.println("Proceed with current character?");
            System.out.println("1. Continue");
            System.out.println("2. Reset");
            System.out.print("Choice: ");
            String confirm = sc.nextLine();
            if (confirm.equals("1")) {
                return warrior; // Return completed warrior
            }
            // If not confirmed, loop restarts character creation
        }
    }

    // Allow player to choose opponent with different stat distributions
    public static Opponent chooseOpponent() {
        Scanner sc = new Scanner(System.in);
        Opponent thief = new Opponent("Thief", 150, 20, 20, 40);
        Opponent viking = new Opponent("Viking", 250, 30, 30, 30);
        Opponent minotaur = new Opponent("Minotaur", 350, 40, 40, 20);

        while (true) {
            Opponent opponent;
            System.out.println("""
                    Choose your Opponent
                    1. Thief
                        150 HP // 20 ATK // 20 DEF // 40 SPD
                    2. Viking
                        250 HP // 30 ATK // 30 DEF // 30 SPD
                    3. Minotaur
                        350 HP // 40 ATK // 40 DEF // 20 SPD
                    
                    Choice:"""
            );
            int input = sc.nextInt();
            sc.nextLine();

            // Set opponent stats based on selection
            switch (input) {
                case 1: // Fast, low health glass cannon
                    opponent = thief;
                    System.out.println("Thief Selected");
                    break;
                case 2: // Balanced stats
                    opponent = viking;
                    System.out.println("Viking Selected");
                    break;
                case 3: // Tank with high health/defense, low speed
                    opponent = minotaur;
                    System.out.println("Minotaur Selected");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    continue; // Restart selection if invalid input
            }

            // Confirm opponent selection or allow restart
            System.out.println("Proceed with current opponent?");
            System.out.println("1. Continue");
            System.out.println("2. Reset");
            System.out.print("Choice: ");
            String confirm = sc.nextLine();
            if (confirm.equals("1")) {
                return opponent; // Return chosen opponent
            }
        }
    }

    // Allow player to choose battle environment with different effects
    public static Environment chooseEnvironment() {
        Scanner sc = new Scanner(System.in);
        Environment arena = new Environment("Arena");
        Environment swamp = new Environment("Swamp");
        Environment colosseum = new Environment("Colosseum");

        while (true) {
            Environment environment;
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
                   \s
                    Choice:\s""");

            int input = sc.nextInt();
            sc.nextLine();

            // Set environment based on selection
            switch (input) {
                case 1: // Neutral environment
                    environment = arena;
                    break;
                case 2: // Favors opponent
                    environment = swamp;
                    break;
                case 3: // Favors player
                    environment = colosseum;
                    break;
                default:
                    System.out.println("Invalid Choice.");
                    continue; // Restart selection if invalid input
            }

            // Confirm environment selection or allow restart
            System.out.println("Proceed with current Environment?");
            System.out.println("1. Continue");
            System.out.println("2. Reset");
            System.out.print("Choice: ");
            String confirm = sc.nextLine();
            if (confirm.equals("1")) {
                return environment; // Return chosen environment
            }
        }
    }

    // Get player's move choice for the current turn
    public static int warriorMove() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose your move");
            System.out.print("1. ATTACK ");
            System.out.print("2. DEFEND ");
            System.out.print("3. CHARGE \n");
            int choice = sc.nextInt();

            // Validate input is within acceptable range
            if (choice < 4 && choice > 0) {
                return choice;
            } else
                System.out.println("Invalid move!");
        }
    }

    // Check if warrior is dead (HP <= 0)
    public static boolean isDead(Warrior warrior) {
        return warrior.getHitPoints() <= 0;
    }

    // Check if opponent is dead (HP <= 0)
    public static boolean isDead(Opponent opponent) {
        return opponent.getHitPoints() <= 0;
    }

    // Display prediction of opponent's next move based on their AI pattern
    public static void predictMove(Opponent opponent, int faux) {
        switch (opponent.getName()) {
            case "Thief": // Always attacks
                System.out.println("Thief will attack!!");
                break;
            case "Viking": // Alternates between attack and defend
                switch (faux) {
                    case 1, 3:
                        System.out.println("Viking will attack!!");
                        break;
                    case 2:
                        System.out.println("Viking will defend!!");
                        break;
                }
                break;
            case "Minotaur": // Attack, charge, attack pattern
                switch (faux) {
                    case 1:
                        System.out.println("Minotaur will attack!!");
                        break;
                    case 2:
                        System.out.println("Minotaur will charge!!");
                        break;
                    case 3:
                        System.out.println("Minotaur will do a charged attack!!");
                }
                break;
        }
    }

    // Display current battle statistics for both combatants
    public static void displayBattleStats(Warrior warrior, Opponent opponent) {
        System.out.print("\nWARRIOR HP " + warrior.getHitPoints() + "\t\t" + "OPPONENT HP " + opponent.getHitPoints());
        System.out.print("\nWARRIOR ATK " + warrior.getAttack() + "\t\t" + "OPPONENT ATK " + opponent.getAttack());
        System.out.print("\nWARRIOR DEF " + warrior.getDefense() + "\t\t" + "OPPONENT DEF " + opponent.getDefense());
        System.out.println("\nWARRIOR SPD " + warrior.getSpeed() + "\t\t" + "OPPONENT SPD " + opponent.getSpeed());
    }

    // Main game loop handling combat mechanics
    public static void Phase2() {
        // Setup phase: create warrior, opponent, and environment
        Warrior warrior = characterCreation();
        Opponent opponent = chooseOpponent();
        Environment environment = chooseEnvironment();

        // Battle variables
        int faux = 1;                           // Counter for opponent AI pattern cycling
        int turn = 1;                           // Current turn number
        int opponentAttack = opponent.getAttack(); // Store original attack for resetting

        // Main battle loop
        while (true) {
            // Apply weapon abilities and environment effects at start of turn
            warrior.weaponAbility(opponent, turn);
            opponent.setAttack(opponentAttack); // Reset opponent attack
            environment.environmentEffects(warrior, opponent, turn);
            displayBattleStats(warrior, opponent);

            // Reset AI pattern counter after 3 moves
            if (faux == 4) {
                faux = 1;
            }


            // Show opponent's predicted move and get player's choice
            predictMove(opponent, faux);
            if(warrior.isCharging()) {
                System.out.println("WARRIOR CHARGED ATTACK READY!");
            }
            int warriorChoice = warriorMove();


            // Prevent charging while already charging
            if (warrior.isCharging() && warriorChoice == 3) {
                do {
                    System.out.println("Warrior is already charging!!!!");
                    warriorChoice = warriorMove();
                } while (warriorChoice == 3);
            }


            // Determine turn order based on speed and execute actions
            if (warrior.getSpeed() > opponent.getSpeed()) {
                // Warrior goes first
                switch (warriorChoice) {
                    case 1: // Attack
                        if(opponent.getName().equals("Viking") && faux == 2) {
                            opponent.think(warrior, faux);
                            warrior.attack(opponent);
                        }
                        else{
                            warrior.attack(opponent);
                            opponent.think(warrior, faux);
                        }

                        break;
                    case 2: // Defend
                        warrior.defend();
                        opponent.think(warrior, faux);
                        break;
                    case 3: // Charge
                        if (!warrior.charge()) { // If already charging, skip turn
                            continue;
                        }
                        warrior.charge();
                        opponent.think(warrior, faux);
                        break;
                }
                // Check for deaths after both actions
                if(isDead(opponent))
                    break;
                if(isDead(warrior))
                    break;
            } else if (warrior.getSpeed() < opponent.getSpeed()) {
                // Opponent goes first
                switch (warriorChoice) {
                    case 1: // Attack
                        opponent.think(warrior, faux);
                        warrior.attack(opponent);
                        break;
                    case 2: // Defend
                        warrior.defend();
                        opponent.think(warrior, faux);
                        break;
                    case 3: // Charge
                        opponent.think(warrior, faux);
                        warrior.charge();
                        break;
                }
                // Check for deaths after both actions
                if(isDead(opponent))
                    break;
                if(isDead(warrior))
                    break;
            } else {
                // Same speed - opponent goes first (tie-breaker)
                switch (warriorChoice) {
                    case 1: // Attack
                        opponent.think(warrior, faux);
                        warrior.attack(opponent);
                        break;
                    case 2: // Defend
                        opponent.think(warrior, faux);
                        warrior.defend();
                        break;
                    case 3: // Charge
                        opponent.think(warrior, faux);
                        warrior.charge();
                        break;
                }
                // Check for simultaneous death
                if(isDead(warrior) && isDead(opponent)) {
                    break;
                }
            }

            // Increment counters for next turn
            faux++;
            turn++;
        }

        // Determine and display battle outcome
        if(warrior.getHitPoints() == 0 && opponent.getHitPoints() == 0) {
            System.out.println("TIE!!!");
        }
        else if (warrior.getHitPoints() <= 0) {
            System.out.println("YOU HAVE DIED!");
            System.out.println("Tip: Minecraft exists for players like you"); // Humorous death message
        }

        else {
            System.out.println("YOU WON!!!");
        }
    }
}
