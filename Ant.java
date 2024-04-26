import java.util.ArrayList;
import java.util.Random;

public class Ant{
    Pixel pixel;
    Coordinate current_coordinate;
    ArrayList<Coordinate> path;
    Image image;
    float[] feature_vector;
    boolean has_colony = false;
    Segment segment;

    public Ant(Pixel pixel, Image image){
        this.pixel = pixel;
        this.image = image;
        this.feature_vector = pixel.get_feature_vector();
        this.pixel.set_ant(this);
        current_coordinate = pixel.get_coords();
        path = new ArrayList<Coordinate>();
        path.add(current_coordinate);
    }

    public Pixel get_pixel(){
        return pixel;
    }
    public void sniff(){
        ArrayList<Pixel> neighbours = image.get_neighbours(current_coordinate);
        for (int i = 0; i < neighbours.size(); i++){

            if (Distance.Euclidean(this.feature_vector, neighbours.get(i).get_feature_vector()) < Parameters.similarity_index && !neighbours.get(i).get_ant().has_colony()){
                neighbours.get(i).get_ant().add_to_colony(this.segment);
                ArrayList<Pixel> potential_neighbours = image.get_neighbours(neighbours.get(i).get_coords());
                potential_neighbours.removeAll(neighbours); //if already in the list, we dont need to add them twice
                potential_neighbours.removeAll(segment.get_pixels()); //if already in segment, we dont need to check
                neighbours.addAll(potential_neighbours);
            }
            else if(Distance.Euclidean(this.pixel.get_feature_vector(), neighbours.get(i).get_feature_vector()) > Parameters.similarity_index && !neighbours.get(i).get_ant().has_colony()){
                neighbours.get(i).sniffed = true;
            }
        }
    }


    public ArrayList<Coordinate> get_path(){
        return path;
    }   

    public void reset(){
        path = new ArrayList<Coordinate>();
        current_coordinate = pixel.get_coords();
        path.add(current_coordinate);
    }

    public boolean has_colony(){
        return !(this.segment == null);
    }

    public void add_to_colony(Segment segment){
        segment.add(this);
        this.segment = segment;
    }
    public void move(Coordinate new_coordinate){
        current_coordinate = new_coordinate;
        path.add(new_coordinate);
    }
}
/*
 * Spm: Du legger til de oppdaterte feature vektorene som clustering, med pointers til sin originale pixel?
 * Regner du ut avstand til alle andre pixler da veldig mange ganger per maur?
 * Når du assigner cluster centers assigner du selv de som er i andre clusters?
 * Feromoner oppdateres fortløpende?
 */