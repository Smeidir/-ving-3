import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;

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
            value *= (1-Parameters.epsilon);
        }
    }



    //write a tostring function transforming the pheromonem map to a string based on the map values
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Coordinate coord = new Coordinate(j, i);
                sb.append(pheromone_map.get(coord)).append(" ");
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

}
