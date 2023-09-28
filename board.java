import java.util.*;

public class board {
    private int _size; //size is of a 1-indexed board from 1 to size (size included)
    private HashMap<Integer, Integer> _snakePositions; //Positions of the snake head as key and snake tail as value
    private HashMap<Integer, Integer> _ladderPositions; //Positions of ladder foot as key and ladder head as value

    public board(int size) {
        _size = size;
        _snakePositions = new HashMap<>();
        _ladderPositions = new HashMap<>();

        HashSet<Integer> occupiedPositions = new HashSet<>(); //Used to prevent overlapp
        int maxNum = size / 10; //Assuming 2/10th of the board will be filled with snakes and ladders
        Random rand = new Random();

        //Initializing all snakes
        while (_snakePositions.size() < maxNum) {
            //Making sure we're not duplicating the head
            int snakeHead = rand.nextInt(size - 1) + 2;
            while (occupiedPositions.contains(snakeHead)) {
                snakeHead = rand.nextInt(size) + 2;
            }
            occupiedPositions.add(snakeHead);

            //Making sure we're not duplicating the tail
            int snakeTail = rand.nextInt(snakeHead - 1) + 1;
            while (occupiedPositions.contains(snakeTail)) {
                snakeTail = rand.nextInt(snakeHead - 1) + 1;
            }
            occupiedPositions.add(snakeTail);

            //Adding unique pair to _snakePositions
            _snakePositions.put(snakeHead, snakeTail);
        }

        //Initializing all ladders
        while (_ladderPositions.size() < maxNum) {

            //Making sure we're not duplicating the foot
            int ladderFoot = rand.nextInt(size - 1) + 2;
            while (occupiedPositions.contains(ladderFoot)) {
                ladderFoot = rand.nextInt(size) + 2;
            }
            occupiedPositions.add(ladderFoot);

            //Making sure we're not duplicating the head
            int ladderHead = rand.nextInt(size - ladderFoot) + ladderFoot + 1;
            while (occupiedPositions.contains(ladderHead)) {
                ladderHead = rand.nextInt(size - ladderFoot) + ladderFoot + 1;
            }
            occupiedPositions.add(ladderHead);

            //Adding unique position to ladder map
            _ladderPositions.put(ladderFoot, ladderHead);
        }
    }
    
    //Function to get next position after dice roll
    public turnOutcome getNextPosition(int currPos, int diceValue){
        int goingToPos = currPos + diceValue;
        turnOutcome turnOutcome = new turnOutcome(currPos);

        //Writing outcomes based on if eaten by snake or climbed ladder or did nothing
        if(_snakePositions.containsKey(goingToPos)){
            turnOutcome.currPos = _snakePositions.get(goingToPos);
            turnOutcome.outcome = PlayerOutcomes.eatenBySnake;
        }else if(_ladderPositions.containsKey(goingToPos)){
            turnOutcome.currPos = _ladderPositions.get(goingToPos);
            turnOutcome.outcome = PlayerOutcomes.climbedLadder;
        }

        return turnOutcome;
    }
}
