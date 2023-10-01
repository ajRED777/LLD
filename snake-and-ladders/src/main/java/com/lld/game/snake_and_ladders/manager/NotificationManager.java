package com.lld.game.snake_and_ladders.manager;

import java.util.Scanner;

import com.lld.game.snake_and_ladders.dto.GameIntiliazationInput;
import com.lld.game.snake_and_ladders.dto.TurnOutcome;
import com.lld.game.snake_and_ladders.dto.TurnOutcome.PlayerOutcomes;
import com.lld.game.snake_and_ladders.model.Board;
import com.lld.game.snake_and_ladders.model.Player;

public class NotificationManager {
	public static GameIntiliazationInput gameInitialization() {
		Scanner sc = new Scanner(System.in);
		GameIntiliazationInput input = new GameIntiliazationInput();
		System.out.println("Enter Board size : ");
		input.boardSize = sc.nextInt();
		System.out.println("Enter the number of players : ");
		input.playerCount = sc.nextInt();
		
		return input;
		
	}
	
	public static Player playerInitialization(int playerNumber) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter player "+ playerNumber +"'s name :");
		String name = sc.nextLine();
			
		return new Player(name);
		
	}
	
	public static void playerTurnTriggerWaiting(Player currPlayer) {
		System.out.println("---------------------------------------");
		System.out.println(currPlayer.name+" it is your turn.");
		System.out.println("Press ENTER to throw the dice.");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	
	public static void playerOutcome(TurnOutcome outcome) {
		System.out.println("Your current position is: "+ outcome.prevPos);
		System.out.println("Value on dice: "+ outcome.diceValue);
		
		if(outcome.outcome == PlayerOutcomes.eatenBySnake) {
			System.out.println("YOU HAVE BEEN EATEN BY A SNAKE!!!!!");
		}else if(outcome.outcome == PlayerOutcomes.climbedLadder){
			System.out.println("YOU HAVE CLIMBED A LADDER!!!!!");
		}
		System.out.println("Your new position is: "+ outcome.currPos);
		
		System.out.println("---------------------------------------");
	}

	public static void playerWins(Player currPlayer) {
		System.out.println(currPlayer.name+" has WON this Game");
	}
	
	
}
