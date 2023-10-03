import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadders {

    public enum PlayerOutcomes {
        eatenBySnake,
        climbedLadder,
        nothingSpecial
    }

    public static void main(String[] args) {
        System.out.println("::::::::::::::::Snake and Ladder Game:::::::::::::");
        GameManager gm = new SnakeAndLadders().new GameManager();
        gm.playGame();
    }

    // Models
    public class Player {
        public String name;
        public int currentPosition = 1;

        public Player(String name) {
            this.name = name;
        }
    }

    public class Board {
        int size; // size is of a 1-indexed board from 1 to size (size included)
        HashMap<Integer, Integer> snakePositions; // Positions of the snake head as key and snake tail as value
        HashMap<Integer, Integer> ladderPositions; // Positions of ladder foot as key and ladder head as value

        public Board(int size) {
            this.size = size;
            this.snakePositions = new HashMap<>();
            this.ladderPositions = new HashMap<>();

            HashSet<Integer> occupiedPositions = new HashSet<>(); // Used to prevent overlap
            int maxNum = size / 10; // Assuming 2/10th of the board will be filled with snakes and ladders
            Random rand = new Random();

            // Initializing all snakes
            while (snakePositions.size() < maxNum) {
                int snakeHead = rand.nextInt(size - 1) + 2;
                while (occupiedPositions.contains(snakeHead)) {
                    snakeHead = rand.nextInt(size) + 2;
                }
                occupiedPositions.add(snakeHead);

                int snakeTail = rand.nextInt(snakeHead - 1) + 1;
                while (occupiedPositions.contains(snakeTail)) {
                    snakeTail = rand.nextInt(snakeHead - 1) + 1;
                }
                occupiedPositions.add(snakeTail);

                snakePositions.put(snakeHead, snakeTail);
            }

            // Initializing all ladders
            while (ladderPositions.size() < maxNum) {
                int ladderFoot = rand.nextInt(size - 1) + 2;
                while (occupiedPositions.contains(ladderFoot)) {
                    ladderFoot = rand.nextInt(size) + 2;
                }
                occupiedPositions.add(ladderFoot);

                int ladderHead = rand.nextInt(size - ladderFoot) + ladderFoot + 1;
                while (occupiedPositions.contains(ladderHead)) {
                    ladderHead = rand.nextInt(size - ladderFoot) + ladderFoot + 1;
                }
                occupiedPositions.add(ladderHead);

                ladderPositions.put(ladderFoot, ladderHead);
            }
        }

        public TurnOutcome getNextPosition(int initialPos, int diceValue) {
            int goingToPos = initialPos + diceValue;
            goingToPos = Math.min(goingToPos, this.size);

            TurnOutcome turnOutcome = new TurnOutcome(initialPos, diceValue);

            if (snakePositions.containsKey(goingToPos)) {
                turnOutcome.currPos = snakePositions.get(goingToPos);
                turnOutcome.outcome = PlayerOutcomes.eatenBySnake;
            } else if (ladderPositions.containsKey(goingToPos)) {
                turnOutcome.currPos = ladderPositions.get(goingToPos);
                turnOutcome.outcome = PlayerOutcomes.climbedLadder;
            } else {
                turnOutcome.currPos = goingToPos;
                turnOutcome.outcome = PlayerOutcomes.nothingSpecial;
            }

            return turnOutcome;
        }
    }

    // Manager classes
    public class GameManager {
        Queue<Player> playerQueue;
        Board board;

        public GameManager() {
            GameInitializationInput input = NotificationManager.gameInitialization();
            System.out.println("Board size set to: " + input.boardSize);
            board = new Board(input.boardSize);
            System.out.println("Player Count: " + input.playerCount);

            playerQueue = new LinkedList<>();
            for (int i = 1; i <= input.playerCount; i++) {
                Player player = new Player(NotificationManager.playerInitialization(i));
                playerQueue.add(player);
            }
        }

        public void playGame() {
            System.out.println("Get ready players, the game begins now");
            int endGamePlayerCount = this.playerQueue.size() - 1;

            while (this.playerQueue.size() > endGamePlayerCount) {
                Player currPlayer = this.playerQueue.peek();
                NotificationManager.playerTurnTriggerWaiting(currPlayer);
                currPlayer = this.playerQueue.poll();
                Random random = new Random();
                int diceValue = random.nextInt(6) + 1;
                TurnOutcome outcome = this.board.getNextPosition(currPlayer.currentPosition, diceValue);
                NotificationManager.playerOutcome(outcome);
                currPlayer.currentPosition = outcome.currPos;

                if (currPlayer.currentPosition == this.board.size) {
                    NotificationManager.playerWins(currPlayer);
                } else {
                    this.playerQueue.add(currPlayer);
                }
            }
        }
    }

    public static class NotificationManager {
        public static GameInitializationInput gameInitialization() {
            Scanner sc = new Scanner(System.in);
            GameInitializationInput input = new GameInitializationInput();
            System.out.println("Enter Board size: ");
            input.boardSize = sc.nextInt();
            System.out.println("Enter the number of players: ");
            input.playerCount = sc.nextInt();

            return input;
        }

        public static String playerInitialization(int playerNumber) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter player " + playerNumber + "'s name: ");
            return sc.nextLine();
        }

        public static void playerTurnTriggerWaiting(Player currPlayer) {
            System.out.println("---------------------------------------");
            System.out.println(currPlayer.name + " it is your turn.");
            System.out.println("Press ENTER to throw the dice.");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
        }

        public static void playerOutcome(TurnOutcome outcome) {
            System.out.println("Your current position is: " + outcome.prevPos);
            System.out.println("Value on dice: " + outcome.diceValue);

            if (outcome.outcome == PlayerOutcomes.eatenBySnake) {
                System.out.println("YOU HAVE BEEN EATEN BY A SNAKE!!!!!");
            } else if (outcome.outcome == PlayerOutcomes.climbedLadder) {
                System.out.println("YOU HAVE CLIMBED A LADDER!!!!!");
            }
            System.out.println("Your new position is: " + outcome.currPos);
            System.out.println("---------------------------------------");
        }

        public static void playerWins(Player currPlayer) {
            System.out.println(currPlayer.name + " has WON this Game");
        }
    }

    // Data Transfer Objects (DTOs)
    public static class GameInitializationInput {
        public int playerCount;
        public int boardSize;
    }

    public class TurnOutcome {
        public int prevPos;
        public int currPos;
        public int diceValue;
        public PlayerOutcomes outcome;

        public TurnOutcome(int prevPos, int diceValue) {
            this.prevPos = prevPos;
            this.diceValue = diceValue;
        }
    }
}
