package com.lld.game.snake_and_ladders;

import com.lld.game.snake_and_ladders.model.Board;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Board board = new Board(100);
        System.out.println( "Snake and Ladder test" );
        System.out.println( "Board size is :::: "+ board.getSize() );
        
    }
}
