import java.util.Collections;
import java.util.ArrayList;

public class Pheromone_map {
 
    ArrayList<ArrayList<Double>> pheromone_map; //kanskje hashmap? avhengig av kjøretid
    //gjør den så generell at jeg bare må endre på denne klassen om kjøretiden blir treig.

    public Pheromone_map(int width, int height){
        pheromone_map = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < height; i++){
            ArrayList<Double> row = new ArrayList<Double>(Collections.nCopies(width, 0.0));
            pheromone_map.add(row);
        }
        
    }


    @Override //ren copilot. Ikke testet
    public String toString(){
        String s = "";
        for (int i = 0; i < pheromone_map.size(); i++){
            for (int j = 0; j < pheromone_map.get(i).size(); j++){
                s += pheromone_map.get(i).get(j) + " ";
            }
            s += "\n";
        }
        return s;
    }    

}
