import java.io.*;
import java.util.*;

class City {
    private String name;
    private int population;
    private List<City> connectedCities;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
        this.connectedCities = new ArrayList<>();
    }

    public void addConnection(City city) {
        connectedCities.add(city);
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public List<City> getConnectedCities() {
        return connectedCities;
    }

    public void displayConnections(List<City> connectedCities){
        for(City connectedCity : connectedCities){
            System.out.print(connectedCity.getName() + " ");
        }
        System.out.println();
    }
}