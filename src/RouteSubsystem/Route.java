package RouteSubsystem;

import java.util.*;

import LocationSubsystem.Location;
import TravelSubsystem.Travel;

public class Route {

	private Travel route;
	private Collection<Location> locations;
	private int id;
	private String description;
	private String name;
	
	
	
	public Route(Travel route, Collection<Location> locations, int id, String description, String name) {
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
	public Collection<Location> getLocations() {
		return locations;
	}
	public void setLocations(Collection<Location> locations) {
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

	
	
}