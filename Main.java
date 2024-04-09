import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

class Main{ 
    HashMap<Coordinate, Pixel> pixel_map = new HashMap<Coordinate, Pixel>();
    public static void main(String[] args){ 
        String img_name = Parameters.img_name;
        Image image = new Image(img_name);
        image.init();  
        Pheromone_map pheromone_map = new Pheromone_map(image.width, image.height);
        ArrayList<Ant> ants = new ArrayList<Ant>();
        ants = image.ants;
        
        for (int i = 0; i < Parameters.epochs; i++){
            ants.stream().forEach(ant -> ant.reset());
            int k = i;
            for (int j  = 0; j < Parameters.path_length; j++){
                ants.stream().forEach(ant -> ant.sniff(k, pheromone_map));
            }
            System.out.println(image.pixel_map.get(new Coordinate(15,15)).ant.get_path());
            pheromone_map.update_pheromones(ants);
            pheromone_map.decay();
            System.out.println(i);
            
        }
        image.create_visit_count();
        image.print_image();
        image.segment();
    }


}
