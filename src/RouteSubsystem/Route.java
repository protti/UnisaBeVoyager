package RouteSubsystem;

import java.util.*;

import LocationSubsystem.Location;
import TravelSubsystem.Travel;

/*
 * 
 * Classe dedicata agli itinerari
 * 
 * 
 * 
 * 
 * */



public class Route {

	
	private ArrayList<Location> locations;
	private int id;
	private String description;
	private String name;
	private Travel route;
	
	
	public Route(Travel route, ArrayList<Location> locations, int id, String description, String name) {
		super();
		this.route = route;
		this.locations = locations;
		this.id = id;
		this.description = description;
		this.name = name;
	}
	public Travel getRoute() {
		return route;
	}
	public void setRoute(Travel route) {
		this.route = route;
	}
	public ArrayList<Location> getLocations() {
		return locations;
	}
	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addLocation(Location location)
	{
		this.locations.add(location);
	}
	public void removeLocation(Location location)
	{
		this.locations.remove(location);
	}
	
	
}