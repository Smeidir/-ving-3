import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    public void sniff(ArrayList<Pixel> remaining){
        List<Pixel> pixels = new ArrayList<Pixel> (remaining);

        pixels = pixels.stream().filter(x -> Distance.Euclidean(this.feature_vector, x.get_feature_vector()) < Parameters.similarity_index).collect(Collectors.toList());
        this.segment.pixels.addAll(pixels);
        pixels.stream().forEach(x -> x.segment = this.segment);
            
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