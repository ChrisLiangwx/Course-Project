import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Archipelago archipelago = new Archipelago();

        // Read city_population.txt and road_network.txt and create City objects
        try {
            BufferedReader cityPopulationFile = new BufferedReader(new FileReader("../city_population_no_dup.txt"));
            BufferedReader roadNetworkFile = new BufferedReader(new FileReader("../road_network.txt"));

            String line;
            while ((line = cityPopulationFile.readLine()) != null) {
                String[] parts = line.split(" : ");
                String cityName = parts[0].trim();
                int population = Integer.parseInt(parts[1].trim());
                City city = new City(cityName, population);
                archipelago.addCity(city);
            }

            while ((line = roadNetworkFile.readLine()) != null) {
                String[] parts = line.split(" : ");
                String city1Name = parts[0].trim();
                String city2Name = parts[1].trim();
                //use name to search for city objects in List<City> cities
                //then add these cities(neighbors) to City Object's List<City> connectedCities
                City city1 = archipelago.findCityByName(city1Name);
                City city2 = archipelago.findCityByName(city2Name);
                if (city1 != null && city2 != null) {
                    city1.addConnection(city2);
                    city2.addConnection(city1);
                }
            }

            cityPopulationFile.close();
            roadNetworkFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        archipelago.displayArchipelago();


        int numberOfIslands = archipelago.getNumberOfIslands();
        System.out.println("number Of Islands:" + numberOfIslands);

        LinkedList<Integer> populationOfIslands = archipelago.getPopulationOfIslands();
        System.out.println("population Of Islands:" + populationOfIslands);

        City city1 = archipelago.findCityByName("New York");
        City city2 = archipelago.findCityByName("Hoover");
        int distance = archipelago.getMinHighways(city1, city2);
        System.out.println("distance:" + distance);


        City city3 = archipelago.findCityByName("Montclair");
        City city4 = archipelago.findCityByName("Monrovia");
        distance = archipelago.getMinHighways(city3, city4);
        System.out.println("distance:" + distance);





    }
}