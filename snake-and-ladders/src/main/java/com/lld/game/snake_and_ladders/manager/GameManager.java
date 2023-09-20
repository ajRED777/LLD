package com.lld.game.snake_and_ladders.manager;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

import com.lld.game.snake_and_ladders.dto.GameIntiliazationInput;
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
		GameIntiliazationInput input = NotificationManager.gameInitialization();
		System.out.println("Board size set to: "+input.boardSize);
		board = new Board(input.boardSize);
		System.out.println("Player Count :"+input.playerCount);
		
	}
	
}
