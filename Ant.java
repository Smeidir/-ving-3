import java.util.ArrayList;

public class Ant{
    Pixel pixel;
    Coordinate current_coordinate;
    ArrayList<Coordinate> path;


    public Ant(Pixel pixel){
        this.pixel = pixel;
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

}