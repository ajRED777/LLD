package com.lld.game.snake_and_ladders;

import com.lld.game.snake_and_ladders.manager.GameManager;
import com.lld.game.snake_and_ladders.model.Board;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
        System.out.println( "Snake and Ladder test" );
        
        GameManager gm = new GameManager();
        gm.playGame();
        
    }
}
