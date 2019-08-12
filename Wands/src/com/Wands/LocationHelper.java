package com.Wands;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LocationHelper {

	public static Location offsetLocation(Location startLocation, Vector offset) {
		
		// Add the offset vector to the start location
		return new Location(
				startLocation.getWorld(),
				startLocation.getX() + offset.getX(),
				startLocation.getY() + offset.getY(),
				startLocation.getZ() + offset.getZ());
	}
	
	/*
	 * This function will move a location on the Y axis to find the nearest spot
	 * where a player can stand
	 */
	public static Location validateLocation(Location location) {
		
		// Create a new location to return later
		Location validatedLocation = location;
		
		// While block at location is solid
		while (validatedLocation.getBlock().getType().isSolid()) {
			
			// Move location up one
			validatedLocation.setY(validatedLocation.getY() + 1);
		}
		
		// While block under location is not solid
		while (!offsetLocation(validatedLocation, new Vector(0, -1, 0)).getBlock().getType().isSolid()) {
			
			// Move location down one
			validatedLocation.setY(validatedLocation.getY() - 1);
		}
		
		// Return validated location
		return validatedLocation;
	}
	
	public static Location getRandomNearbyPosition(Location startLocation, int range) {
		
		// Create a random number generator
		Random rdm = new Random();
		
		// Create a new location to return later
		Location randomLocation = startLocation;
		Location validatedRandomLocation = null;

		while (validatedRandomLocation == null
				|| validatedRandomLocation.distance(startLocation) > range * 2
				|| validatedRandomLocation.getBlock().getType().isSolid()) {

			// Safe the random offset to a vector
			Vector offset = new Vector(
					rdm.nextInt(range * 2) - range,
					rdm.nextInt(range * 2) - range,
					rdm.nextInt(range * 2) - range);

			// Add offset to random location
			randomLocation = offsetLocation(startLocation, offset);

			// Validate random location
			validatedRandomLocation = validateLocation(randomLocation);
		}
		
		// Return random validated location
		return validatedRandomLocation;
	}
	
}
