import java.util.ArrayList;
import java.util.HashMap;

class Main{ 
    HashMap<Coordinate, Pixel> pixel_map = new HashMap<Coordinate, Pixel>();
    public static void main(String[] args){ 
        String img_name = Parameters.img_name;
        Image image = new Image(img_name);
        image.init();  
        Pheromone_map pheromone_map = new Pheromone_map(240, 160);
        ArrayList<Ant> ants = new ArrayList<Ant>();
        
        for (int i = 0; i < Parameters.epochs; i++){

            for (Pixel pixel : image.pixel_map.values()){
                pixel.ant.sniff(i);
            }
            pheromone_map.update_pheromones(ants);
            pheromone_map.decay();
            System.out.println(pheromone_map);
        }
    }


}
