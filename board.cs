public class Board{
    #region  Referances
    int _size; //Size of the 1-indexed snake and ladder board.
    Dictionary<int,int> _snakePositions; Dictionary<int,int> _ladderPositions;
    #endregion
    
    #region  Constructor
    public Board(int size){
        //initialization
        _size = size; _snakePositions = new(); _ladderPositions = new();

        HashSet<int> occupiedPositions = new(); //To keep track of already occupied positions since we don't want overlapping
        int maxNum = size/10; //Assuming that 1/10th of the space is occupied by snakes/ladders
        Random rand = new();

        //Creating all snakes
        while(_snakePositions.Count < maxNum){

            //Generating random starting point for the snake
            int snakeHead = rand.Next(2, size - 1);
            while(occupiedPositions.Contains(snakeHead)){
                snakeHead = rand.Next(2, size); //Max is not included
            }
            occupiedPositions.Add(snakeHead);

            //Creating a snake tail which is below the head and also not occupied already
            int snakeTail = rand.Next(1, snakeHead);
            while(occupiedPositions.Contains(snakeTail)){
                snakeTail = rand.Next(1, snakeHead);
            }
            occupiedPositions.Add(snakeTail);

            _snakePositions.Add(snakeHead, snakeTail);            
        }

        //Creating all ladders
        while(_ladderPositions.Count < maxNum){

            //Generating random starting point for the snake
            int ladderFoot = rand.Next(2, size - 1);
            while(occupiedPositions.Contains(ladderFoot)){
                ladderFoot = rand.Next(2, size); //Max is not included
            }
            occupiedPositions.Add(ladderFoot);

            //Creating a snake tail which is below the head and also not occupied already
            int ladderHead = rand.Next(ladderFoot + 1, size + 1); //Ladder head starts from 1+ foot of ladder to end
            while(occupiedPositions.Contains(ladderHead)){
                ladderHead = rand.Next(ladderFoot + 1, size + 1);
            }
            occupiedPositions.Add(ladderHead);

            _ladderPositions.Add(ladderFoot, ladderHead);            
        }
    }
    #endregion

    #region  Public Methods
    public TurnOutCome GetNextPosition(int currPos, int diceValue){
        int goingToPos = currPos + diceValue;
        TurnOutcome turnOutcome = new(currPos);

        if(_snakePositions.ContainsKey(goingToPos)){
            turnOutcome.currPos = _snakePositions[goingToPos];
            turnOutcome.outcome = PlayerOutcomes.eatenBySnake;
        }else if(_ladderPositions.ContainsKey(goingToPos)){
            turnOutcome.currPos = _ladderPositions[goingToPos];
            turnOutcome.outcome = PlayerOutcomes.climbedLadder;
        }
    
        return turnOutcome;
    }
    #endregion

}