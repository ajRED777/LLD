//Can put all this in a namespace
public class TurnOutCome{
    int _prevPos; int _currPos; Board.PlayerOutcome _outcome;

    public TurnOutCome(int prevPos = 0, int currPos = 0, PlayerOutcomes outcome = PlayerOutcomes.nothingSpecial){
        _prevPos = prevPos; _currPos = currPos; _outcome = outcome;
    }
}

public enum PlayerOutcomes{
    eatenBySnake, climbedLadder, nothingSpecial
}