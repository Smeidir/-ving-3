import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Pheromone_map {
 
    HashMap<Coordinate, Double> pheromone_map; //kanskje hashmap? avhengig av kjøretid
    int height;
    int width;
    //gjør den så generell at jeg bare må endre på denne klassen om kjøretiden blir treig.

  /*   public Pheromone_map(int width, int height){
        pheromone_map = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < height; i++){
            ArrayList<Double> row = new ArrayList<Double>(Collections.nCopies(width, 0.0));
            pheromone_map.add(row);
        }
        
    }
 */

    public Pheromone_map(int width, int height){
        this.height = height;  
        this.width = width;
        pheromone_map = new HashMap<Coordinate, Double>();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                Coordinate coord = new Coordinate(j, i);
                pheromone_map.put(coord, 0.0);
            }
        }
    }

    public void decay(){
        for (Double value : pheromone_map.values()){
            value *= (1-Parameters.decay_rate);
        }
    }

    //write a tostring function transforming the pheromonem map to a string based on the map values
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Coordinate coord = new Coordinate(j, i);
                sb.append(coord);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void update_pheromones(ArrayList<Ant> ants){
        for (Ant ant : ants){
            ArrayList<Coordinate> path = ant.get_path();
            for (Coordinate coord : path){
                double current_pheromone = pheromone_map.get(coord);
                pheromone_map.put(coord, current_pheromone + Parameters.pheromone_rate);
            }
        }
    }
    public double get(Coordinate coord){
        return pheromone_map.get(coord);
        
    }
    //make a function which prints out the coordinate keys of the pheromone map, with the highest tuple values first
    public void printCoordinatesByValue() {
        List<Map.Entry<Coordinate, Double>> sortedEntries = new ArrayList<>(pheromone_map.entrySet());
        Collections.sort(sortedEntries, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<Coordinate, Double> entry : sortedEntries) {
            System.out.println(entry.getKey());
        }
    }
    //make a function which checks whether some of the values are not present in the pheromone map key set. It should contain all tuples of x values from 0 to 240 and y from 0 to 160.
    public boolean checkMissingCoordinates() {
        for (int i = 0; i <= 240; i++) {
            for (int j = 0; j <= 160; j++) {
                Coordinate coord = new Coordinate(i, j);
                if (!pheromone_map.containsKey(coord)) {
                    return true;
                }
            }
        }
        return false;
    }
}
