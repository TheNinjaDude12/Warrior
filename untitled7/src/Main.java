import java.util.Scanner;

/**
 * Main class for a text-based battle game. Handles the console display, user input,
 * game setup, and main game loop where the player creates a warrior, chooses an
 * opponent, environment, and then battles the opponent in turn-based combat.
 */
public class Main {
    private static final int CONSOLE_WIDTH = 80;

    /**
     * Main method to start the game. Initializes the game by calling the Phase2 method,
     * which handles the main game loop.
     *
     * @param args command-line arguments (not used in this game)
     */
    public static void main(String[] args) {
        Phase2();
    }

    /**
     * Centers the given text on the console.
     *
     * @param text the text to be centered
     */
    public static void centerText(String text) {
        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

    /**
     * Prints a horizontal separator line across the console.
     */
    public static void printSeparator() {
        System.out.println("=".repeat(CONSOLE_WIDTH));
    }

    /**
     * Prints the given text inside a box format on the console.
     *
     * @param lines an array of strings representing the content to be displayed in the box
     */
    public static void printBox(String[] lines) {
        int maxLength = 0;
        for (String line : lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        maxLength += 4;
        System.out.println("+" + "-".repeat(maxLength) + "+");
        for (String line : lines) {
            int padding = maxLength - line.length() - 2;
            System.out.println("| " + line + " ".repeat(padding) + " |");
        }
        System.out.println("+" + "-".repeat(maxLength) + "+");
    }

    /**
     * Prints some blank lines to provide spacing between sections.
     */
    public static void addSpacing() {
        System.out.println("\n\n\n");
    }

    /**
     * Displays the game title with ASCII art and waits for user input to proceed.
     */
    public static void displayTitle() {
        Scanner sc = new Scanner(System.in);
        addSpacing();
        centerText("+--------------------------------------------------------------------------+");
        centerText("|                                                                          |");
        centerText("|    ##      #####  #####  #####     #####  #####  ##   ## ##            |");
        centerText("|    ##     ##   ## ##     ##   ##  ##     ##   ## ##   ## ##            |");
        centerText("|    ##     ####### #####  ##   ##   ####  ##   ## ##   ## ##            |");
        centerText("|    ##     ##   ##     ## ##   ##      ## ##   ## ##   ## ##            |");
        centerText("|    #####  ##   ## #####  ##   ##  #####   #####   #####  #####         |");
        centerText("|                                                                          |");
        centerText("+--------------------------------------------------------------------------+");
        System.out.println();
        System.out.print("Press any key to continue");
        sc.nextLine();
    }

    /**
     * Displays the warrior's statistics in a styled box format.
     *
     * @param warrior the warrior whose stats are displayed
     */
    public static void showStats(Warrior warrior) {
        addSpacing();
        System.out.println();
        centerText("WARRIOR STATISTICS");
        System.out.println();
        String[] statsLines = {
                "+----------------------------+",
                "|      CURRENT STATS         |",
                "+----------------------------+",
                String.format("|  HP:  %-3d                  |", warrior.getHitPoints()),
                String.format("|  ATK: %-3d                  |", warrior.getAttack()),
                String.format("|  DEF: %-3d                  |", warrior.getDefense()),
                String.format("|  SPD: %-3d                  |", warrior.getSpeed()),
                "+----------------------------+"
        };
        for (String line : statsLines) {
            centerText(line);
        }
        System.out.println();
        centerText("Press Enter to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * Prompts the player to choose an armor type, displaying the available options and their effects.
     *
     * @param warrior the warrior who will equip the chosen armor
     */
    public static void chooseArmor(Warrior warrior) {
        addSpacing();
        System.out.println();
        centerText("ARMOR SELECTION");
        System.out.println();
        String[] armorOptions = {
                "CHOOSE YOUR ARMOR",
                "",
                "[1] Light Armor      [2] Medium Armor     [3] Heavy Armor",
                "    +20 DEF              +30 DEF             +40 DEF",
                "    -5 SPD              -15 SPD             -25 SPD",
                "",
                "Quick & Agile       Balanced Protection   Maximum Defense"
        };
        printBox(armorOptions);
        System.out.println();
        centerText("Enter your choice (1-3): ");
        Armor lightArmor = new Armor("Light Armor", 20, 5);
        Armor mediumArmor = new Armor("Medium Armor", 30, 15);
        Armor heavyArmor = new Armor("Heavy Armor", 40, 25);
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> warrior.equip(lightArmor);
            case 2 -> warrior.equip(mediumArmor);
            case 3 -> warrior.equip(heavyArmor);
        }
        System.out.println();
        centerText("Equipped: " + warrior.getArmor().getName());
        centerText("Press Enter to continue...");
        sc.nextLine(); // Buffer
        sc.nextLine();
    }

    /**
     * Prompts the player to choose a weapon type, displaying the available options and their effects.
     *
     * @param warrior the warrior who will equip the chosen weapon
     */
    public static void chooseWeapon(Warrior warrior) {
        addSpacing();
        System.out.println();
        centerText("WEAPON SELECTION");
        System.out.println();
        centerText("CHOOSE YOUR WEAPON");
        System.out.println();
        String[] daggerInfo = {
                "[1] DAGGER (20 ATK)",
                "Special: Every other defend becomes 100% evade"
        };
        printBox(daggerInfo);
        System.out.println();
        String[] swordInfo = {
                "[2] SWORD (30 ATK, -10 SPD)",
                "Special: +10 bonus attack damage when attacking"
        };
        printBox(swordInfo);
        System.out.println();
        String[] axeInfo = {
                "[3] BATTLE AXE (40 ATK, -20 SPD)",
                "Special: Charging grants +5 SPD and +5 ATK next turn"
        };
        printBox(axeInfo);
        System.out.println();
        String[] staffInfo = {
                "[4] STAFF (30 ATK, -20 SPD)",
                "Special: Charging conjures random element boost",
                "Fire: +5 ATK | Wind: +5 SPD | Water: +10 HP | Earth: +3 DEF"
        };
        printBox(staffInfo);
        System.out.println();
        centerText("Enter your choice (1-4): ");
        Weapon dagger = new Weapon("Dagger", 20, 0);
        Weapon sword = new Weapon("Sword", 30, 10);
        Weapon battleaxe = new Weapon("Axe", 40, 20);
        Weapon staff = new Weapon("Staff", 30, 20);
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> warrior.equip(dagger);
            case 2 -> warrior.equip(sword);
            case 3 -> warrior.equip(battleaxe);
            case 4 -> warrior.equip(staff);
        }
        System.out.println();
        centerText("Equipped: " + warrior.getWeapon().getName());
        centerText("Press Enter to continue...");
        sc.nextLine();
    }

    /**
     * Handles the complete character creation process, displaying the warrior's stats,
     * allowing the player to choose armor and weapons, and confirming the character setup.
     *
     * @return the fully created warrior character
     */
    public static Warrior characterCreation() {
        displayTitle();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            centerText("WARRIOR SETUP");
            System.out.println();
            Warrior warrior = new Warrior();
            showStats(warrior);
            chooseArmor(warrior);
            showStats(warrior);
            chooseWeapon(warrior);
            showStats(warrior);
            addSpacing();
            System.out.println();
            centerText("CHARACTER CONFIRMATION");
            System.out.println();
            String[] confirmOptions = {
                    "Proceed with this warrior?",
                    "",
                    "[1] Continue",
                    "[2] Reset Character"
            };
            printBox(confirmOptions);
            System.out.println();
            centerText("Choice: ");
            String confirm = sc.nextLine();
            if (confirm.equals("1")) {
                return warrior;
            }
        }
    }

    /**
     * Prompts the player to choose an opponent, displaying available options.
     *
     * @return the selected opponent
     */
    public static Opponent chooseOpponent() {
        Scanner sc = new Scanner(System.in);
        Opponent thief = new Opponent("Thief", 150, 20, 20, 40);
        Opponent viking = new Opponent("Viking", 250, 30, 30, 30);
        Opponent minotaur = new Opponent("Minotaur", 350, 40, 40, 20);
        Opponent magician = new Opponent("Magician", 300, 30, 30, 30);
        while (true) {
            addSpacing();
            System.out.println();
            centerText("OPPONENT SELECTION");
            System.out.println();
            String[] opponentOptions = {
                    "CHOOSE YOUR OPPONENT",
                    "",
                    "[1] THIEF           [2] VIKING          [3] MINOTAUR",
                    "    150 HP              250 HP             350 HP",
                    "    20 ATK              30 ATK             40 ATK",
                    "    20 DEF              30 DEF             40 DEF",
                    "    40 SPD              30 SPD             20 SPD",
                    "",
                    "Fast Glass Cannon   Balanced Warrior    Tanky Powerhouse",
                    "",
                    "[4] MAGICIAN (300 HP / 30 ATK / 30 DEF / 30 SPD)",
                    "    Mysterious Spell Caster"
            };
            printBox(opponentOptions);
            System.out.println();
            centerText("Choice: ");
            int input = sc.nextInt();
            sc.nextLine();
            Opponent opponent;
            String selectedName = "";
            switch (input) {
                case 1:
                    opponent = thief;
                    selectedName = "THIEF";
                    break;
                case 2:
                    opponent = viking;
                    selectedName = "VIKING";
                    break;
                case 3:
                    opponent = minotaur;
                    selectedName = "MINOTAUR";
                    break;
                case 4:
                    opponent = magician;
                    selectedName = "MAGICIAN";
                    break;
                default:
                    centerText("Invalid Choice! Please select 1-4.");
                    centerText("Press Enter to try again...");
                    sc.nextLine();
                    continue;
            }
            System.out.println();
            centerText(selectedName + " Selected!");
            System.out.println();
            String[] confirmOptions = {
                    "Proceed with this opponent?",
                    "",
                    "[1] Continue",
                    "[2] Reset Selection"
            };
            printBox(confirmOptions);
            System.out.println();
            centerText("Choice: ");
            String confirm = sc.nextLine();
            if (confirm.equals("1")) {
                return opponent;
            }
        }
    }

    /**
     * Prompts the player to choose an environment, displaying available options and effects.
     *
     * @return the selected environment
     */
    public static Environment chooseEnvironment() {
        Scanner sc = new Scanner(System.in);
        Environment arena = new Environment("Arena");
        Environment swamp = new Environment("Swamp");
        Environment colosseum = new Environment("Colosseum");
        while (true) {
            addSpacing();
            System.out.println();
            centerText("ENVIRONMENT SELECTION");
            System.out.println();
            centerText("CHOOSE YOUR BATTLEFIELD");
            System.out.println();
            String[] arenaInfo = {
                    "[1] ARENA",
                    "Effect: No buffs or penalties - Fair fight!"
            };
            printBox(arenaInfo);
            System.out.println();
            String[] swampInfo = {
                    "[2] SWAMP",
                    "Effect: Player loses 1 HP per turn",
                    "        Opponent gains 1 ATK per turn"
            };
            printBox(swampInfo);
            System.out.println();
            String[] colosseumInfo = {
                    "[3] COLOSSEUM",
                    "Effect: Player gains 1 ATK per turn",
                    "        Opponent loses 1 DEF per turn"
            };
            printBox(colosseumInfo);
            System.out.println();
            centerText("Choice: ");
            int input = sc.nextInt();
            sc.nextLine();
            Environment environment;
            String selectedName = "";
            switch (input) {
                case 1:
                    environment = arena;
                    selectedName = "ARENA";
                    break;
                case 2:
                    environment = swamp;
                    selectedName = "SWAMP";
                    break;
                case 3:
                    environment = colosseum;
                    selectedName = "COLOSSEUM";
                    break;
                default:
                    centerText("Invalid Choice! Please select 1-3.");
                    centerText("Press Enter to try again...");
                    sc.nextLine();
                    continue;
            }
            System.out.println();
            centerText(selectedName + " Selected!");
            System.out.println();
            String[] confirmOptions = {
                    "Proceed with this environment?",
                    "",
                    "[1] Continue",
                    "[2] Reset Selection"
            };
            printBox(confirmOptions);
            System.out.println();
            centerText("Choice: ");
            String confirm = sc.nextLine();
            if (confirm.equals("1")) {
                return environment;
            }
        }
    }

    /**
     * Prompts the player to choose a move during combat (Attack, Defend, Charge).
     *
     * @return the selected move choice (1 for attack, 2 for defend, 3 for charge)
     */
    public static int warriorMove() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            String[] moveOptions = {
                    "CHOOSE YOUR MOVE",
                    "",
                    "[1] ATTACK",
                    "[2] DEFEND",
                    "[3] CHARGE"
            };
            printBox(moveOptions);
            System.out.println();
            centerText("Choice: ");
            int choice = sc.nextInt();
            if (choice >= 1 && choice <= 3) {
                return choice;
            } else {
                centerText("Invalid move! Please select 1-3.");
                System.out.println();
            }
        }
    }

    /**
     * Checks if the warrior is dead.
     *
     * @param warrior the warrior to be checked
     * @return true if the warrior's HP is 0 or below, false otherwise
     */
    public static boolean isDead(Warrior warrior) {
        return warrior.getHitPoints() <= 0;
    }

    /**
     * Checks if the opponent is dead.
     *
     * @param opponent the opponent to be checked
     * @return true if the opponent's HP is 0 or below, false otherwise
     */
    public static boolean isDead(Opponent opponent) {
        return opponent.getHitPoints() <= 0;
    }

    /**
     * Predicts the next move of the opponent based on their current state and logic.
     *
     * @param opponent the opponent whose move is being predicted
     * @param faux an integer representing the turn or sequence for prediction
     */
    public static void predictMove(Opponent opponent, int faux) {
        System.out.println();
        centerText("OPPONENT PREDICTION");
        System.out.println();
        String prediction = "";
        switch (opponent.getName()) {
            case "Thief":
                prediction = "THIEF WILL ATTACK!!";
                break;
            case "Viking":
                switch (faux) {
                    case 1, 3:
                        prediction = "VIKING WILL ATTACK!!";
                        break;
                    case 2:
                        prediction = "VIKING WILL DEFEND!!";
                        break;
                }
                break;
            case "Minotaur":
                switch (faux) {
                    case 1:
                        prediction = "MINOTAUR WILL ATTACK!!";
                        break;
                    case 2:
                        prediction = "MINOTAUR WILL CHARGE!!";
                        break;
                    case 3:
                        prediction = "MINOTAUR WILL DO A CHARGED ATTACK!!";
                }
                break;
            case "Magician":
                switch (faux) {
                    case 1:
                        prediction = "MAGICIAN WILL ATTACK!!";
                        break;
                    case 2:
                        prediction = "MAGICIAN WILL CHARGE THEIR STAFF!!";
                        break;
                    case 3:
                        prediction = "MAGICIAN WILL DO A CHARGED ATTACK!!";
                }
                break;
        }
        String[] predictionBox = {
                "WARNING: " + prediction
        };
        printBox(predictionBox);
    }

    /**
     * Displays the battle stats for both the warrior and the opponent during the combat.
     *
     * @param warrior the player's warrior
     * @param opponent the selected opponent
     */
    public static void displayBattleStats(Warrior warrior, Opponent opponent) {
        System.out.println();
        printSeparator();
        centerText("BATTLE STATUS");
        printSeparator();
        String[] battleStats = {
                "BATTLE STATS",
                "",
                String.format("WARRIOR     |  HP: %-3d  |  ATK: %-3d  |  DEF: %-3d  |  SPD: %-3d",
                        warrior.getHitPoints(), warrior.getAttack(), warrior.getDefense(), warrior.getSpeed()),
                "",
                String.format("OPPONENT    |  HP: %-3d  |  ATK: %-3d  |  DEF: %-3d  |  SPD: %-3d",
                        opponent.getHitPoints(), opponent.getAttack(), opponent.getDefense(), opponent.getSpeed())
        };
        printBox(battleStats);
    }

    /**
     * Main game loop where the player fights against the opponent.
     * Handles combat, player moves, and opponent responses.
     */
    public static void Phase2() {
        Warrior warrior = characterCreation();
        Opponent opponent = chooseOpponent();
        Environment environment = chooseEnvironment();
        int faux = 1;
        int turn = 1;
        int opponentAttack = opponent.getAttack();
        int warriorAttack = warrior.getAttack();
        addSpacing();
        System.out.println();
        centerText("BATTLE BEGINS!");
        System.out.println();
        String[] battleIntro = {
                "WARRIOR vs " + opponent.getName().toUpperCase(),
                "Location: " + environment.getEnvironmentName(),
                "",
                "PREPARE FOR COMBAT!"
        };
        printBox(battleIntro);
        Scanner sc = new Scanner(System.in);
        System.out.println();
        centerText("Press Enter to start battle...");
        sc.nextLine();
        while (true) {
            addSpacing();
            opponent.setAttack(opponentAttack);
            warrior.setDefense(warrior.getArmor().getDefense());
            warrior.setSpeed(50 - warrior.getArmor().getSpeedPenalty() - warrior.getWeapon().getSpeedPenalty());
            warrior.setAttack(warriorAttack);
            warrior.weaponAbility(opponent);
            environment.environmentEffects(warrior, opponent, turn);
            displayBattleStats(warrior, opponent);
            if (faux == 4) {
                faux = 1;
            }
            predictMove(opponent, faux);
            if(warrior.isCharging()) {
                System.out.println();
                String[] chargeReady = {
                        "WARRIOR CHARGED ATTACK READY!"
                };
                printBox(chargeReady);
            }
            int warriorChoice = warriorMove();
            if (warrior.isCharging() && warriorChoice == 3) {
                do {
                    centerText("Warrior is already charging!!!!");
                    warriorChoice = warriorMove();
                } while (warriorChoice == 3);
            }
            if (warrior.getSpeed() > opponent.getSpeed()) {
                switch (warriorChoice) {
                    case 1:
                        if(opponent.getName().equals("Viking") && faux == 2) {
                            opponent.think(warrior, faux);
                            warrior.attack(opponent);
                        } else {
                            warrior.attack(opponent);
                            opponent.think(warrior, faux);
                        }
                        break;
                    case 2:
                        warrior.defend();
                        opponent.think(warrior, faux);
                        break;
                    case 3:
                        if (!warrior.charge()) {
                            continue;
                        }
                        warrior.charge();
                        opponent.think(warrior, faux);
                        break;
                }
                if(isDead(opponent) || isDead(warrior)) break;
            } else if (warrior.getSpeed() < opponent.getSpeed()) {
                switch (warriorChoice) {
                    case 1:
                        opponent.think(warrior, faux);
                        warrior.attack(opponent);
                        break;
                    case 2:
                        warrior.defend();
                        opponent.think(warrior, faux);
                        break;
                    case 3:
                        opponent.think(warrior, faux);
                        warrior.charge();
                        break;
                }
                if(isDead(opponent) || isDead(warrior)) break;
            } else {
                switch (warriorChoice) {
                    case 1:
                        opponent.think(warrior, faux);
                        warrior.attack(opponent);
                        break;
                    case 2:
                        opponent.think(warrior, faux);
                        warrior.defend();
                        break;
                    case 3:
                        opponent.think(warrior, faux);
                        warrior.charge();
                        break;
                }
                if(isDead(warrior) && isDead(opponent)) {
                    break;
                }
            }
            faux++;
            turn++;
        }
        addSpacing();
        System.out.println();
        if(warrior.getHitPoints() == 0 && opponent.getHitPoints() == 0) {
            centerText("BATTLE RESULT: TIE!");
            System.out.println();
            String[] tieMessage = {
                    "Both warriors fell in glorious combat!",
                    "A draw worthy of legends!"
            };
            printBox(tieMessage);
        } else if (warrior.getHitPoints() <= 0) {
            centerText("YOU HAVE BEEN DEFEATED!");
            System.out.println();
            String[] defeatMessage = {
                    "The warrior has fallen...",
                    "Tip: Minecraft exists for players like you"
            };
            printBox(defeatMessage);
        } else {
            centerText("VICTORY IS YOURS!");
            System.out.println();
            String[] victoryMessage = {
                    "CONGRATULATIONS!",
                    "You have proven yourself in combat!",
                    "The realm celebrates your triumph!"
            };
            printBox(victoryMessage);
        }
        System.out.println();
        centerText("Thanks for playing Last Souls!");
    }
}
