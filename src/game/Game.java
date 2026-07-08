package game;

import characters.Blossom;
import characters.Bubbles;
import characters.Buttercup;
import characters.PowerpuffGirl;
import exceptions.AttackDepletedException;
import exceptions.DefeatedCharacterException;
import exceptions.InvalidOptionException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== POWERPUFF GIRLS 1V1 BATTLE ===");
        System.out.println("Rules: 3 rounds | Defending cuts damage in half");
        System.out.println("Basic Attack: 3 uses | Special Attack: 2 uses | Super Power: 1 use");
        System.out.println();

        System.out.println("PLAYER 1, choose your character:");
        PowerpuffGirl player1 = chooseWithRetry(1);

        System.out.println("PLAYER 2, choose your character:");
        PowerpuffGirl player2 = chooseWithRetry(2);

        System.out.println();
        System.out.println("--- Player 1 Character ---");
        player1.introduce();
        System.out.println();
        System.out.println("--- Player 2 Character ---");
        player2.introduce();
        System.out.println();

        battle(player1, player2);
    }

    static PowerpuffGirl chooseWithRetry(int playerNumber) {
        while (true) {
            System.out.println("1 - Blossom  2 - Bubbles  3 - Buttercup");
            System.out.print("Player " + playerNumber + ": ");
            try {
                int option = readIntegerFromKeyboard();
                return createCharacter(option);
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }
    }

    static PowerpuffGirl createCharacter(int option) throws InvalidOptionException {
        if (option == 1) {
            return new Blossom();
        }
        if (option == 2) {
            return new Bubbles();
        }
        if (option == 3) {
            return new Buttercup();
        }
        throw new InvalidOptionException(option, 1, 3);
    }

    static void battle(PowerpuffGirl player1, PowerpuffGirl player2) {
        for (int round = 1; round <= 3; round++) {
            System.out.println("========== ROUND " + round + " ==========");
            System.out.println(player1.getName() + ": " + player1.getHealth() + "/100  x  "
                    + player2.getName() + ": " + player2.getHealth() + "/100");
            System.out.println();

            int action1 = readActionWithRetry(player1);
            int action2 = readActionWithRetry(player2);

            System.out.println();
            System.out.println("-- Round result --");

            if (action1 == 4) {
                player1.defend();
            }
            if (action2 == 4) {
                player2.defend();
            }

            if (action1 != 4) {
                execute(player1, player2, action1);
            }
            if (action2 != 4) {
                execute(player2, player1, action2);
            }

            player1.resetDefense();
            player2.resetDefense();

            System.out.println();

            if (!player1.isAlive() || !player2.isAlive()) {
                break;
            }
        }

        showResult(player1, player2);
    }

    static int readActionWithRetry(PowerpuffGirl character) {
        while (true) {
            System.out.println("1 - Basic Attack  2 - Special Attack  3 - Super Power  4 - Defend");
            System.out.print(character.getName() + ", choose: ");
            try {
                int action = readIntegerFromKeyboard();
                if (action < 1 || action > 4) {
                    throw new InvalidOptionException(action, 1, 4);
                }
                return action;
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }
    }

    static void execute(PowerpuffGirl attacker, PowerpuffGirl target, int option) {
        try {
            if (option == 1) {
                attacker.basicAttack(target);
            } else if (option == 2) {
                attacker.specialAttack(target);
            } else {
                attacker.superPower(target);
            }
        } catch (AttackDepletedException e) {
            System.out.println(e.getMessage() + " Turn skipped.");
        } catch (DefeatedCharacterException e) {
            System.out.println(e.getMessage());
        }
    }

    static void showResult(PowerpuffGirl player1, PowerpuffGirl player2) {
        System.out.println("========== RESULT ==========");
        if (!player1.isAlive() && !player2.isAlive()) {
            System.out.println("Draw. Both characters fell.");
        } else if (!player2.isAlive()) {
            System.out.println("PLAYER 1 WINS. " + player1.getName() + " is the strongest.");
        } else if (!player1.isAlive()) {
            System.out.println("PLAYER 2 WINS. " + player2.getName() + " is the strongest.");
        } else if (player1.getHealth() > player2.getHealth()) {
            System.out.println("PLAYER 1 WINS by points. " + player1.getName()
                    + " finished with " + player1.getHealth() + " health.");
        } else if (player2.getHealth() > player1.getHealth()) {
            System.out.println("PLAYER 2 WINS by points. " + player2.getName()
                    + " finished with " + player2.getHealth() + " health.");
        } else {
            System.out.println("Draw. Both characters finished with the same health.");
        }
    }

    static int readIntegerFromKeyboard() throws InvalidOptionException {
        try {
            return keyboard.nextInt();
        } catch (InputMismatchException e) {
            keyboard.nextLine();
            throw new InvalidOptionException(-1, 1, 4);
        }
    }
}
