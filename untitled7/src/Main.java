import java.util.Scanner;

/**
 * The main class for handling the game loop, character creation, battle mechanics, and player choices.
 * This class contains the entry point of the game and provides various methods to interact with the game.
 */
public class Main {

    /**
     * Main entry point of the game. Starts the game loop and initializes the game phase.
     *
     * @param args Command-line arguments (not used in this game).
     */
    public static void main(String[] args) {
        Phase2(); // Start the main game loop
    }

    /**
     * Displays the current stats of the warrior and waits for user input to continue.
     *
     * @param warrior The warrior whose stats are to be displayed.
     */
    public static void showStats(Warrior warrior) {
        System.out.println("CURRENT STATS");
        System.out.printf("HP %d\n", warrior.getHitPoints());
        System.out.printf("ATK %d\n", warrior.getAttack());
        System.out.printf("DEF %d\n", warrior.getDefense());
        System.out.printf("SPD %d\n", warrior.getSpeed());
        Scanner sc = new Scanner(System.in);
        sc.nextLine(); // Wait for user to press Enter before continuing
    }

    /**
     * Allows the player to choose their armor with different defense/speed trade-offs.
     * Displays options for light, medium, and heavy armor.
     *
     * @param warrior The warrior to equip the chosen armor to.
     */
    public static void chooseArmor(Warrior warrior) {
        // Create three armor options with different stats
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

        // Equip the chosen armor based on user input
        switch (sc.nextInt()) {
            case 1 -> warrior.equip(lightArmor);
            case 2 -> warrior.equip(mediumArmor);
            case 3 -> warrior.equip(heavyArmor);
        }

        clearScreen();
        System.out.println("Equipped " + warrior.getArmor().getName());
        sc.nextLine(); // Wait for user acknowledgment
    }

    /**
     * Clears the console screen by printing empty lines.
     */
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    /**
     * Allows the player to choose their weapon with different attack/speed trade-offs and special abilities.
     * Displays options for dagger, sword, and battleaxe.
     *
     * @param warrior The warrior to equip the chosen weapon to.
     */
    public static void chooseWeapon(Warrior warrior) {
        // Create three weapon options with different stats and abilities
        Weapon dagger = new Weapon("Dagger", 20, 0);
        Weapon sword = new Weapon("Sword", 30, 10);
        Weapon battleaxe = new Weapon("Axe", 40, 20);

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your weapon");

        // Display weapon options with their special abilities
        System.out.println("1.Dagger (+20 ATK)");
        System.out.print("""
                Weapon Ability: When defending, every other defend will become a 100% evade.
                """);
        System.out.println("2. Sword (+30 ATK -10 SPD)");
        System.out.print("""
                Weapon Ability: When attacking, gain an additional +10 attack.
                """);
        System.out.println("3. BattleAxe (+40 ATK -20 SPD)");
        System.out.print("""
                Weapon Ability: When charging, gain 5 speed and 5 attack in the next turn.
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
        }
        System.out.println("Equipped " + warrior.getWeapon().getName());
    }

    /**
     * Handles the character creation process, including showing stats, choosing armor, and weapon.
     * Allows the player to restart the creation process or confirm the character.
     *
     * @return The completed warrior character.
     */
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

    /**
     * Allows the player to choose an opponent with different stat distributions.
     * Provides options for Thief, Viking, and Minotaur.
     *
     * @return The chosen opponent.
     */
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
                    
                    Choice:"""
            );
            int input = sc.nextInt();
            sc.nextLine();

            // Set opponent stats based on selection
            switch (input) {
                case 1: // Fast, low health glass cannon
                    opponent.setName("Thief");
                    opponent.setHitPoints(150);
                    opponent.setAttack(20);
                    opponent.setDefense(20);
                    opponent.setSpeed(40);
                    System.out.println("Thief Selected");
                    break;
                case 2: // Balanced stats
                    opponent.setName("Viking");
                    opponent.setHitPoints(250);
                    opponent.setAttack(30);
                    opponent.setDefense(30);
                    opponent.setSpeed(30);
                    System.out.println("Viking Selected");
                    break;
                case 3: // Tank with high health/defense, low speed
                    opponent.setName("Minotaur");
                    opponent.setHitPoints(350);
                    opponent.setAttack(40);
                    opponent.setDefense(40);
                    opponent.setSpeed(20);
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

    /**
     * Allows the player to choose a battle environment with different effects.
     * Provides options for Arena, Swamp, and Colosseum.
     *
     * @return The chosen environment.
     */
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
                   \s
                    Choice:\s""");

            int input = sc.nextInt();
            sc.nextLine();

            // Set environment based on selection
            switch (input) {
                case 1: // Neutral environment
                    environment.setEnvironmentName("Arena");
                    break;
                case 2: // Favors opponent
                    environment.setEnvironmentName("Swamp");
                    break;
                case 3: // Favors player
                    environment.setEnvironmentName("Colosseum");
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

    // Other methods like warriorMove(), isDead(), etc., can follow the same pattern of Javadoc commenting

    // Continue with remaining methods...
}
