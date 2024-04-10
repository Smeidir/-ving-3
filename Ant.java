import java.util.ArrayList;
import java.util.Random;

public class Ant{
    Pixel pixel;
    Coordinate current_coordinate;
    ArrayList<Coordinate> path;
    Image image;
    Random r = new Random();

    public Ant(Pixel pixel, Image image){
        this.pixel = pixel;
        this.image = image;
        this.pixel.set_ant(this);
        current_coordinate = pixel.get_coords();
        path = new ArrayList<Coordinate>();
        path.add(current_coordinate);
    }

    public void move(Coordinate new_coordinate){ 
        if (new_coordinate.neighbours(current_coordinate)){ //hvis koden kjører tregt kan man kanskje fjerne denne, hvis man sjekker andre steder
            current_coordinate = new_coordinate;
            path.add(current_coordinate);
        }
        else{
            throw new IllegalArgumentException("Invalid move, not a neighbouring coordinate. Current coordinate: " + current_coordinate 
            + " Attempted move-coordinate: " + new_coordinate);
            
        }
    }
    public Pixel get_pixel(){
        return pixel;
    }
    public void sniff(int epoch, Pheromone_map pheromone_map){
        ArrayList<Pixel> neighbours = image.get_neighbours(current_coordinate);
        Pixel best_neighbour = null;
        RouletteSelector roulette = new RouletteSelector(this.pixel, pheromone_map);
        for (Pixel neighbour : neighbours){ 
                roulette.add_pixel(neighbour);
        }
        best_neighbour = roulette.select();
        move(best_neighbour.get_coords());
    }

    
    public ArrayList<Coordinate> get_path(){
        return path;
    }   

    public void reset(){
        path = new ArrayList<Coordinate>();
        current_coordinate = pixel.get_coords();
        path.add(current_coordinate);
    }


}