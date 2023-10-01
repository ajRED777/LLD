package com.lld.game.snake_and_ladders.manager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.lld.game.snake_and_ladders.dto.GameIntiliazationInput;
import com.lld.game.snake_and_ladders.dto.TurnOutcome;
import com.lld.game.snake_and_ladders.model.Board;
import com.lld.game.snake_and_ladders.model.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameManager {
	private Queue<Player> playerQueue;
	private Board board; 
	
	//Initializing Game manager
	public GameManager(){
		
		//Initialize game board and player count
		GameIntiliazationInput input = NotificationManager.gameInitialization();
		System.out.println("Board size set to: "+input.boardSize);
		board = new Board(input.boardSize);
		System.out.println("Player Count :"+input.playerCount);
		
		//Create and add players to the queue
		playerQueue = new LinkedList<>();
		for(int i=1; i<=input.playerCount; i++) {
			Player player = NotificationManager.playerInitialization(i);
			playerQueue.add(player);
		}
	}
	
	public void playGame() {
		System.out.println("Get ready players the game begins now");
		
		//End game when 1 player reaches the end
		int endGamePlayerCount = this.playerQueue.size()-1;
		
		while(this.playerQueue.size()>endGamePlayerCount) {
			Player currPlayer = this.playerQueue.peek();
			NotificationManager.playerTurnTriggerWaiting(currPlayer);
			currPlayer = this.playerQueue.poll();
			Random random = new Random();
	        int diceValue = random.nextInt(6) + 1; // Generates a random int between 1 and 6 (inclusive)
	        TurnOutcome outcome = this.board.getNextPosition(currPlayer.currentPosition, diceValue);
	        NotificationManager.playerOutcome(outcome);
	        currPlayer.currentPosition=outcome.currPos;
	        
	        if(currPlayer.currentPosition == this.board.getSize()) {
	        	NotificationManager.playerWins(currPlayer);
	        }else {
	        	this.playerQueue.add(currPlayer);
	        }
		}
	}
	
}
