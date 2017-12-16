package models;

public enum Command {
	NORTH, SOUTH, EAST, WEST, LOOK, QUIT, HELP,
	// reveal the location of the treasure, for debug purposes
	DEBUG,
	// use the map item, if the player is carrying it
	MAP
}
