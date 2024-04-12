import java.util.ArrayList;

public class Segment {

    ArrayList<Pixel> pixels = new ArrayList<Pixel>();  
    Image image;
    Ant start_ant;

    public Segment(Image image){
        this.image = image;
    }

    public void add_pixel(Pixel pixel){
        pixels.add(pixel);
    }

    public ArrayList<Pixel> get_pixels(){
        return pixels;
    }
    public Pixel get_centroid(){
        int R = 0;
        int G = 0;
        int B = 0;
        for (Pixel pixel : pixels){
            R += pixel.getR();
            G += pixel.getG();
            B += pixel.getB();
        }
        R = R/pixels.size();
        G = G/pixels.size();
        B = B/pixels.size();
        return new Pixel(R, G, B, -1, -1, -1);
    }
    public double get_overall_deviation(){//finne fargelikhet i segmentet
        double deviation = 0;
        Pixel centroid = this.get_centroid();
        for (Pixel pixel : pixels){
            deviation += pixel.get_RGB_dissimilarity(centroid);
        }
        return deviation;
    }
    public double get_connectivity(){ // finne ut om naboene til pixlene er i segmentet. Må nok deles på 2 så man ikke teller verdien dobbelt?
        double connectivity = 0; 
        for (Pixel pixel : pixels){
            ArrayList<Pixel> neighbours = this.image.get_neighbours(pixel.get_coords());
            for (Pixel neighbour : neighbours){
                if (!pixels.contains(neighbour)){
                    connectivity += 1/8;
                }
            }
        }
        return connectivity/2;
    }
    public double get_edge_value(){
        double edge_value = 0;
        for (Pixel pixel : pixels){
            ArrayList<Pixel> neighbours = this.image.get_neighbours(pixel.get_coords());
            for (Pixel neighbour : neighbours){
                if (!pixels.contains(neighbour)){
                    edge_value += pixel.get_RGB_dissimilarity(neighbour);
                }
            }
        }
        return edge_value;
    }
    public void add(Ant ant){
        this.pixels.add(ant.get_pixel());
        ant.pixel.segment = this;
    }
    public ArrayList<Pixel> get_neighbouring_pixels(){
        ArrayList<Pixel> neighbouring_pixels = new ArrayList<Pixel>();
        for (Pixel pixel : pixels){
            ArrayList<Pixel> neighbours = this.image.get_neighbours(pixel.get_coords());
            for (Pixel neighbour : neighbours){
                if (!pixels.contains(neighbour)){
                    neighbouring_pixels.add(neighbour);
                }
            }
        }
        return  neighbouring_pixels;

    }

    public ArrayList<Segment> get_neighbouring_segments(){
        ArrayList<Segment> neighbouring_segments = new ArrayList<Segment>();
        for (Pixel pixel: this.get_neighbouring_pixels()){
            if (!neighbouring_segments.contains(pixel.segment)){
                neighbouring_segments.add(pixel.segment);
            }
        }
        return neighbouring_segments;
    }

    //TODO: fucntion get edge pixels?


}
