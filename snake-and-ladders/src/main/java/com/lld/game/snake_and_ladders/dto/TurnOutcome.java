package com.lld.game.snake_and_ladders.dto;

public class TurnOutcome {
	public int prevPos;
    public int currPos;
    public int diceValue;
    public PlayerOutcomes outcome;
    
    public enum PlayerOutcomes {
        eatenBySnake,
        climbedLadder,
        nothingSpecial
    }


	public TurnOutcome(int prevPos, int diceValue) {
		this.prevPos = prevPos;
		this.diceValue = diceValue;
	}
}
