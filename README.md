# Hunt-the-Wumpus

About/Overview
The world for this game consists of a dungeon, a network of tunnels and caves that are interconnected.
Players can explore the entire world by traveling from cave to cave through the tunnels that connect them.
The dungeon contains treasure, arrows, and a monster called an Otyugh. The player must reach the end square to win, without dying to an Otyugh.
The dungeon also contains a thief, who will steal the player's treasure when first encountered.

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
1. COMMAND LINE: Run the JAR file: Project5_Dungeon.jar {rows} {columns} {wrapped} {interconnectivity}
              {treasure percent} {arrow percent} {number of monsters}
2. GUI: Run the JAR file: Project5_Dungeon.jar. MUST be in the same directory as res/img/. On time of submission, JAR is in res/, while images are in res/res/img/.

How to Use the Program.
1. COMMAND LINE: 
	a. Move: "m" + DIRECTION ("n","s","e","w")
	b. Shoot: "s" + DIRECTION ("n","s","e","w") + DISTANCE (0+)
	c. PickUp: "p"
	d. Information: "i"
	e. Quit: "q"
2. GUI: 
	a. Move: {Arrow Keys} OR {Click adjacent cells}
	b. Shoot: CNTRL + {Arrow Key}. Input distance in the pop window and press enter.
	c. PickUp: "p"

Description of Examples.
Run 1 -- Game_Screenshot.png
1. Create a 5 by 5, unwrapped, 0 interconnectivity dungeon with 1 monster.
2. Player moves throughout the dungeon, picking up some arrows along the way
3. Player moves away towards the smell, where the Otyugh is residing.

Assumptions.
1. None

Limitations.
1. None

Citations.
1. None
