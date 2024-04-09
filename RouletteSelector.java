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

            double fitness = 10/base_pixel.get_RGB_similarity(pixel)+ pheromone_map.get(pixel.get_coords()) +0.5*base_pixel.get_coords().euclidist(pixel.get_coords());
            //System.out.println("Bae pixel rgb: " + base_pixel.get_RGB_similarity(pixel) + " Pheromone: " + pheromone_map.get(pixel.get_coords()) + " Distance: " + base_pixel.get_coords().euclidist(pixel.get_coords()));
            cumulative_probabilities.add(fitness);
            total += fitness;
        }
        double total2 = total;//tihi 
        cumulative_probabilities = new ArrayList<Double>(cumulative_probabilities.stream().map(x -> x/total2).collect(Collectors.toList()));
        double rand = random.nextDouble();
        for (int i=0; i < cumulative_probabilities.size(); i++){
            rand -= cumulative_probabilities.get(i);
            if(rand <= 0){
                return pixels.get(i);
            }
        }
        return pixels.get(pixels.size()-1); //i tilfelle 1? kan det skje?
    }
}
