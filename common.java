public class turnOutcome {
    int _prevPos;
    int _currPos;
    playerOutcomes _outcome;

    public turnOutcome(int prevPos, int currPos, playerOutcomes outcome) {
        _prevPos = prevPos;
        _currPos = currPos;
        _outcome = outcome;
    }
}

public enum playerOutcomes {
    eatenBySnake,
    climbedLadder,
    nothingSpecial
}
