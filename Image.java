import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Image {
    HashMap<Coordinate, Pixel> pixel_map = new HashMap<Coordinate, Pixel>();
    String image_name;

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
            Ant ant = new Ant(p);
            p.set_ant(ant); //overlødig men hvem bryr seg. er init. bra for lesbarhet            
        }
        public ArrayList<Pixel> get_neighbours(Pixel pixel){
            ArrayList<Pixel> neighbours = new ArrayList<Pixel>();
            Coordinate coords = pixel.get_coords();
            
        }
}
}
