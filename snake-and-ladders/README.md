# Snake and Ladder LLD

A basic project with the LLD of the commonly known Snakes and Ladders game. Code is explained as follows. 

- Main project files are located in this directory: [here]([http://localhost/](https://github.com/ajRED777/LLD/tree/main/snake-and-ladders/src/main/java/com/lld/game/snake_and_ladders)https://github.com/ajRED777/LLD/tree/main/snake-and-ladders/src/main/java/com/lld/game/snake_and_ladders).
- `App.java` is equivalent to the main class and is what is to be run to start the application.
- `/models` folder contains classes which mainly contain data
- `/managers` contains classes which mainly execute logic
- `/dto` is a folder with common scripts

Basic requirements, pre-conditions and application flow are as follows:
- Board can be of any size Each player puts their counter on the board starting position.
- Press entre to roll the dice.
- When dice lands on some place, you can either climb a ladder, get eaten by a snake or just move there.
- Game is continued till one player reaches the finish.
- Ladder can be of any size, even one. Snake can be of any size, even one.
- Board is not represented as a 2D grid but as linear set of positions.
- Snakes and ladders apprear at most 1/10th of the size of the board.