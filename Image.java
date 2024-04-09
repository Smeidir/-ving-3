import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Image {
    HashMap<Coordinate, Pixel> pixel_map = new HashMap<Coordinate, Pixel>();
    String image_name;
    ArrayList<Segment> segments = new ArrayList<Segment>();

    public Image(String img_name){
        this.image_name = img_name;
    }
    
    public void init(){
        BufferedImage imgBuffer;
        try {
            imgBuffer = ImageIO.read(new File(image_name));
            byte[] pixels = (byte[])imgBuffer.getRaster().getDataElements(0, 0, imgBuffer.getWidth(), imgBuffer.getHeight(), null); // pixels[0], [1] og [2] er rød, grønn og blå for første pixel 
            //verdiene er mellom -128 og 127, men det går vel fint?
            int pixel_number = 0;
            for (int i = 0; i < pixels.length; i+=3) {//den leser av hele første linje, så neste osv osv
                pixel_number = i/3;
                Pixel pixel = new Pixel(pixels[i], pixels[i+1], pixels[i+2], pixel_number % imgBuffer.getWidth(), pixel_number / imgBuffer.getHeight() , pixel_number);//x er modulo, y er gå opp i 
                pixel_map.put(pixel.get_coords(), pixel);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (Pixel p: pixel_map.values()){
            Ant ant = new Ant(p, this);
            p.set_ant(ant); //overlødig men hvem bryr seg. er init. bra for lesbarhet            
        }
    }
    public ArrayList<Pixel> get_neighbours(Coordinate coords){
        ArrayList<Pixel> neighbours = new ArrayList<Pixel>();
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                Coordinate neighbour_coords = new Coordinate(coords.getX() + i, coords.getY() + j);          
                if (pixel_map.containsKey(neighbour_coords) && !neighbour_coords.equals(coords)){ //da går det fint med -1 osv, og utenfor rekkevidden
                    neighbours.add(pixel_map.get(neighbour_coords));
                }
            }
        }
        return neighbours;            
        }
    public ArrayList<Pixel> get_pixels_by_coords(ArrayList<Coordinate> coords){
        ArrayList<Pixel> neighbours = new ArrayList<Pixel>();
        for (Coordinate coord : coords){
            Pixel pixel = pixel_map.get(coord);
            neighbours.add(pixel);
        }
        return neighbours;
        }

    public void segment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'segment'");
    }
}


