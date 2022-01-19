# Hunt-the-Wumpus

About/Overview

The world for this game consists of a dungeon, a network of tunnels and caves that are interconnected.
Players can explore the entire world by traveling from cave to cave through the tunnels that connect them.
The dungeon contains treasure, arrows, and a monster called an Otyugh. The player must reach the end square to win, without dying to an Otyugh.

List of features

1. Dungeon
	a. A dungeon is created with a certain set of rows, columns, interconnectivity, the percent of treasure, the percent of arrows, the number of Otyughs, and an option for wrapping.
	b. Wrapping will allow dungeon locations to "wrap" to the other sides of the grid.
	c. Locations in the dungeon can be caves or tunnels.
		a. Caves can hold treasures
		b. Tunnels cannot hold treasures
		c. Both can hold arrows
	e. Each dungeon has a starting and ending location for the player.
	f. Players can pick up treasures in the location they are currently standing.
	g. Players move throughout the dungeon if there is a connecting path the adjacent location.
	h. Players can get a description of the player that includes a description of what treasure the player has collected.
	i. Players can get a list of viable directions that they can move to from their current location.
	j. Reaching the end location will end the game.
	k. Players can smell nearby Otyughs, ranging from no smell, faint smells, to very pungent smells.
	l. Players can shoot and kill Otyughs. A dead Otyugh will lose its distinct smell. Shooting an Otyugh once will injure it.
	m. Moving into a location with a healthy Otyugh will kill the player. An injured Otyugh has a 50% chance of killing the player.
	
How To Run. Instructions to run the program should include the following:
Run the JAR file: Project4_Dungeon.jar

How to Use the Program.
1. All dungeon creation and interaction is done through the Dungeon interface.

Description of Examples.
Run 1 -- ExampleRun1.txt: The brave adventurer (Game is won).
1. Create a 3 by 4, unwrapped, 0 interconnectivity dungeon.
2. Player moves throughout the dungeon, until the smell becomes VERY pungent
3. Player moves away from  and towards then smell to get an idea of where the Otyugh is.
4. Player picks up arrows.
5. Player shoots to the east twice to kill the Otyugh. The smell goes away.
6. Player moves to the end square where the dead Otyugh resides, game is won!

Run 2 -- ExampleRun2.txt: The greedy adventurer (Game is lost).
1. Create a 3 by 4, unwrapped, 0 interconnectivity dungeon.
2. Get player information before picking up any treasure or arrows.
3. Pick up arrows.
4. Get player information after picking up arrows.
5. Move to find treasure.
6. Pick up arrows and treasure.
7. Get player information after picking up arrows and treasure.
8. Move to the end square that contains a live Otyugh. Player dies and loses the game.

Design/Model Changes.
1. Created an edge class to keep track of the links between each node on creation.
2. Created Coordinate class and added a reference to Coordinate for viable classes. Allowed for better tracking when creating the dungeon and navigation.
3. Removed integer variables from Direction, was not needed.
4. Moved some of the kruskals algorithm to private methods for clean code.

Assumptions.
1. None

Limitations.
1. None

Citations.
1. None
