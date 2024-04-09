import java.util.ArrayList;
import java.util.HashMap;

class Main{ 
    HashMap<Coordinate, Pixel> pixel_map = new HashMap<Coordinate, Pixel>();
    public static void main(String[] args){ 
        String img_name = Parameters.img_name;
        Image image = new Image(img_name);
        image.init();  
        Pheromone_map pheromone_map = new Pheromone_map(image.width, image.height);
        System.out.println(pheromone_map.pheromone_map.size());
        ArrayList<Ant> ants = new ArrayList<Ant>();
        System.out.println(image.check_keys(pheromone_map));
        
        for (int i = 0; i < Parameters.epochs; i++){
            image.pixel_map.values().stream().forEach(pixel -> pixel.ant.reset());
            for (Pixel pixel : image.pixel_map.values()){
                for (int j  = 0; j < Parameters.path_length; j++){
                    pixel.ant.sniff(i, pheromone_map);
            }}
            System.out.println(image.pixel_map.get(new Coordinate(15,15)).ant.get_path());
            pheromone_map.update_pheromones(ants);
            pheromone_map.decay();
            System.out.println(i);
            
        }
        image.create_visit_count();
        image.segment();
    }


}
