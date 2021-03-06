package RouteSubsystem;

import java.util.*;

import LocationSubsystem.Location;
import TravelSubsystem.Travel;

/**
*Classe che rappresenta un itinerario.
*@param locations = arraylist contenente l'insieme di luoghi contenuti nell'itinerario
*@param id = identificativo del l'itinerario
*@param description = descrizione dell'itinerario
*@param name = nome dell'itinerario
*@param route = viaggio relativo
*
*/
public class Route {

	
	private ArrayList<Location> locations;
	private int id;
	private String description;
	private String name;
	
	
	
	public Route(ArrayList<Location> locations, int id, String description, String name) {
		this.locations = locations;
		this.id = id;
		this.description = description;
		this.name = name;
	}
	
	public Route(ArrayList<Location> locations, String description, String name) {
		this.locations = locations;
		this.id = -1;
		this.description = description;
		this.name = name;
	}
	
	/**
	Metodo che restituisce i luoghi dell'itinerario.
	*@return locations
	*/
	public ArrayList<Location> getLocations() {
		return locations;
	}
	/**
	Metodo che setta l'arraylist di luoghi di un itinerario.
	*@param locations
	*/
	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}
	/**
	Metodo che restituisce l'identificativo dell'itinerario.
	*@return id
	*/
	public int getId() {
		return id;
	}
	/**
	Metodo che setta l'identificativo dell'itinerario.
	*@param id
	*/
	public void setId(int id) {
		this.id = id;
	}
	/**
	Metodo che restituisce la descrizione dell'itinerario.
	*@return description
	*/
	public String getDescription() {
		return description;
	}
	/**
	Metodo che setta la descrizione dell'itinerario.
	*@param description
	*/
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	Metodo che restituisce il nome dell'itinerario.
	*@return name
	*/
	public String getName() {
		return name;
	}
	/**
	Metodo che setta il nome dell'itinerario.
	*@param name
	*/
	public void setName(String name) {
		this.name = name;
	}
	/**
	Metodo che aggiunge un itinerario all'arraylist di itinerari.
	*@param location
	*/
	public void addLocation(Location location)
	{
		this.locations.add(location);
	}
	/**
	Metodo che rimuove un itinerario dall'arraylist di itinerari.
	*@param location
	*/
	public void removeLocation(Location location)
	{
		this.locations.remove(location);
	}
	
	
}