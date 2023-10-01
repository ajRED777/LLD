package com.lld.game.snake_and_ladders.model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import com.lld.game.snake_and_ladders.dto.TurnOutcome;
import com.lld.game.snake_and_ladders.dto.TurnOutcome.PlayerOutcomes;

import lombok.Getter;

@Getter
public class Board {
	private int size; //size is of a 1-indexed board from 1 to size (size included)
    private HashMap<Integer, Integer> snakePositions; //Positions of the snake head as key and snake tail as value
    private HashMap<Integer, Integer> ladderPositions; //Positions of ladder foot as key and ladder head as value

    public Board(int size) {
        this.size = size;
        this.snakePositions = new HashMap<>();
        this.ladderPositions = new HashMap<>();

        HashSet<Integer> occupiedPositions = new HashSet<>(); //Used to prevent overlap
        int maxNum = size / 10; //Assuming 2/10th of the board will be filled with snakes and ladders
        Random rand = new Random();

        //Initializing all snakes
        while (snakePositions.size() < maxNum) {
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

            //Adding unique pair to snakePositions
            snakePositions.put(snakeHead, snakeTail);
        }

        //Initializing all ladders
        while (ladderPositions.size() < maxNum) {

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
            ladderPositions.put(ladderFoot, ladderHead);
        }
    }
    
    //Function to get next position after dice roll
    public TurnOutcome getNextPosition(int initialPos, int diceValue){
        int goingToPos = initialPos + diceValue;
        goingToPos = goingToPos>this.size?size:goingToPos;
        
        TurnOutcome turnOutcome = new TurnOutcome(initialPos, diceValue);

        //Writing outcomes based on if eaten by snake or climbed ladder or did nothing
        if(snakePositions.containsKey(goingToPos)){
            turnOutcome.currPos = snakePositions.get(goingToPos);
            turnOutcome.outcome = PlayerOutcomes.eatenBySnake;
        }else if(ladderPositions.containsKey(goingToPos)){
            turnOutcome.currPos = ladderPositions.get(goingToPos);
            turnOutcome.outcome = PlayerOutcomes.climbedLadder;
        }else {
        	turnOutcome.currPos = goingToPos;
            turnOutcome.outcome = PlayerOutcomes.nothingSpecial;
        }
        
        

        return turnOutcome;
    }
}
