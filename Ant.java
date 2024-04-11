import java.util.ArrayList;
import java.util.Random;

public class Ant{
    Pixel pixel;
    Coordinate current_coordinate;
    ArrayList<Coordinate> path;
    Image image;
    Random r = new Random();
    int[] feature_vector;

    public Ant(Pixel pixel, Image image){
        this.pixel = pixel;
        this.image = image;
        this.feature_vector = pixel.get_feature_vector();
        this.pixel.set_ant(this);
        current_coordinate = pixel.get_coords();
        path = new ArrayList<Coordinate>();
        path.add(current_coordinate);
    }

    public void move(Coordinate new_coordinate){ 
        feature_vector += this.next();
        return;
            
        
    }
    public void next(){
        return;
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
/*
 * Spm: Du legger til de oppdaterte feature vektorene som clustering, med pointers til sin originale pixel?
 * Regner du ut avstand til alle andre pixler da veldig mange ganger per maur?
 * Når du assigner cluster centers assigner du selv de som er i andre clusters?
 * Feromoner oppdateres fortløpende?
 */