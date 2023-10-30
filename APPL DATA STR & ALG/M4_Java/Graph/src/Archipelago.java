import java.util.*;

class Archipelago {
    private List<City> cities;

    public Archipelago() {
        cities = new ArrayList<>();
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void displayArchipelago(){
        for(City city : cities){
            System.out.println(city.getName()+ " " + city.getPopulation());
            city.displayConnections(city.getConnectedCities());
        }
    }

    public int getNumberOfIslands() {
        //use dfs to traverse all cities
        //at the same time use visited[] to mark visited cities
        //a new city haven't been visited is in a new island
        Set<City> visited = new HashSet<>();
        int count = 0;
        for(City city : cities){
            if(!visited.contains(city)){
//                System.out.println(city.getName()); //display the representative city of each island
                dfs(visited, city);
                count ++;
            }

        }

        return count;
    }


    private void dfs(Set<City> citySet, City city){
        if(!citySet.contains(city)){
            citySet.add(city);
        }

        for(City neighbor : city.getConnectedCities()){
            if (! citySet.contains(neighbor)){
                dfs(citySet, neighbor);
            }
        }
    }

    public LinkedList<Integer> getPopulationOfIslands() {
        LinkedList<Integer> populationInIsland = new LinkedList<>();
        Set<City> visited = new HashSet<>();
        for(City city : cities){
            //if new city hasn't been visited, it belongs to a new island
            if(!visited.contains(city)){
                populationInIsland.add(0);
                dfsAndAddPopulation(visited, city, populationInIsland);
            }
        }
        return populationInIsland;
    }

    private void dfsAndAddPopulation(Set<City> citySet, City city, LinkedList<Integer> populationArray){
        citySet.add(city);
        int population = populationArray.removeLast();
        population += city.getPopulation();
        populationArray.add(population);

        for(City neighbor : city.getConnectedCities()){
            if (! citySet.contains(neighbor)){

                dfsAndAddPopulation(citySet, neighbor, populationArray);
            }
        }
    }

    public int getMinHighways(City source, City destination) {
        //use bfs to calculate number of paths
        //each visited is stored along with a length of path
        Queue<City> queue = new LinkedList<>();
        Set<City> visited = new HashSet<>();
        Map<City, Integer> routeMap = new HashMap<>();

        queue.add(source);
        visited.add(source);
        routeMap.put(source, 0);

        while (!queue.isEmpty()) {
            City cur = queue.poll();
            if(cur.equals(destination)){
                return routeMap.get(cur);
            }

            for(City neighbor : cur.getConnectedCities()){
                if(!visited.contains(neighbor)){
                    queue.add(neighbor);
                    visited.add(neighbor);
                    routeMap.put(neighbor, routeMap.get(cur) + 1);
                }

            }
        }

        return -1; // Return -1 if no path exists
    }

    public City findCityByName(String cityName) {
        for (City city : cities) {
            if (city.getName().equals(cityName)) {
                return city;
            }
        }
        return null;
    }


/*    public int getMinHighways(City source, City destination) {
        Queue<City> queue = new LinkedList<>();
        Set<City> visited = new HashSet<>();
        Map<City, Integer> routeMap = new HashMap<>();
        Map<City, City> parentMap = new HashMap<>();

        queue.add(source);
        visited.add(source);
        routeMap.put(source, 0);
        parentMap.put(source, null);

        while (!queue.isEmpty()) {
            City cur = queue.poll();
            if (cur.equals(destination)) {
                printPath(parentMap, destination);
                return routeMap.get(cur);
            }

            for (City neighbor : cur.getConnectedCities()) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    routeMap.put(neighbor, routeMap.get(cur) + 1);
                    parentMap.put(neighbor, cur);
                }
            }
        }

        return -1;
    }


    private void printPath(Map<City, City> parentMap, City destination) {
        List<City> path = new ArrayList<>();
        City cur = destination;

        while (cur != null) {
            path.add(cur);
            cur = parentMap.get(cur);
        }

        Collections.reverse(path);

        // print path
        System.out.print("Path: ");
        for (City city : path) {
            System.out.print(city.getName() + " -> ");
        }
        System.out.println("[end]");
    }*/



}