import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class RouletteSelector{
    
    ArrayList<Pixel> pixels;
    ArrayList<Double> cumulative_probabilities;
    Pixel base_pixel;
    Pheromone_map pheromone_map;
    Random random = new Random();

    public RouletteSelector(Pixel base_pixel, Pheromone_map pheromone_map){
        this.base_pixel = base_pixel;
        this.pixels = new ArrayList<Pixel>();
        cumulative_probabilities = new ArrayList<>();
        this.pheromone_map = pheromone_map;
    }

    public void add_pixel(Pixel pixel){
        pixels.add(pixel);
    }
    public Pixel select(){
        double total = 0;
        for (Pixel pixel : pixels){

            Double fitness = 0.1*base_pixel.get_RGB_dissimilarity(pixel)+  pheromone_map.get(pixel.get_coords()) -base_pixel.get_coords().euclidist(pixel.get_coords());
            cumulative_probabilities.add(fitness);
            total += fitness;
        }
        Double total2 = total;
        cumulative_probabilities = new ArrayList<Double>(cumulative_probabilities.stream().map(x -> x/total2).collect(Collectors.toList()));
        double rand = random.nextDouble();
        for (int i=0; i < cumulative_probabilities.size(); i++){
            rand -= cumulative_probabilities.get(i);
            if(rand <= 0){
                return pixels.get(i);
            }
        }
        
        throw new IllegalArgumentException("No pixel selected");

        //return pixels.get(pixels.size()-1); //i tilfelle 1? kan det skje?
    }
}
