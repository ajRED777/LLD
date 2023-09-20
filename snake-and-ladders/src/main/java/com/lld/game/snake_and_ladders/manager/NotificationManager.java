package com.lld.game.snake_and_ladders.manager;

import java.util.Scanner;

import com.lld.game.snake_and_ladders.dto.GameIntiliazationInput;
import com.lld.game.snake_and_ladders.model.Board;

public class NotificationManager {
	public static GameIntiliazationInput gameInitialization() {
		Scanner sc = new Scanner(System.in);
		GameIntiliazationInput input = new GameIntiliazationInput();
		System.out.println("Enter Board size : ");
		input.boardSize = sc.nextInt();
		System.out.println("Enter the number of players : ");
		input.playerCount = sc.nextInt();
		
		sc.close();
		return input;
		
	}
}
