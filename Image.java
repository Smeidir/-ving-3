import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

public class Image {
    HashMap<Coordinate, Pixel> pixel_map = new HashMap<Coordinate, Pixel>();
    String image_name;
    ArrayList<Segment> segments = new ArrayList<Segment>();
    int height;
    int width;
    int[][] visit_count;
    ArrayList<Ant> ants = new ArrayList<>();

    public Image(String img_name){

        this.image_name = img_name;
    }
    
    public void init(){
        BufferedImage imgBuffer;
        try {            
            imgBuffer = ImageIO.read(new File(image_name));
            this.height = imgBuffer.getHeight();
            this.width = imgBuffer.getWidth();

            int pixel_number = 0;
            for (int row = 0; row < imgBuffer.getHeight(); row++) {
                for (int col = 0; col < imgBuffer.getWidth(); col++) {
                    int rgb = imgBuffer.getRGB(col, row);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
    
                    Pixel pixel = new Pixel(255, green, blue, col, row, pixel_number);
                    pixel_number++;
                    pixel_map.put(pixel.get_coords(), pixel);
                }
            } 
            System.out.println(this.checkAllCoordinates());
                
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (Pixel p: pixel_map.values()){
            Ant ant = new Ant(p, this);
            p.set_ant(ant); //overlødig men hvem bryr seg. er init. bra for lesbarhet        
            ants.add(ant);   
        }
    }


    public boolean checkAllCoordinates() {
        for (int x = 0; x <= 240; x++) {
            for (int y = 0; y <= 160; y++) {
                Coordinate coords = new Coordinate(x, y);
                if (!pixel_map.containsKey(coords)) {
                    System.out.println("Missing coordinate: " + coords);
                    
                }
            }
        }
        return true;
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

    public boolean check_keys(Pheromone_map pmap){
        System.out.println("Size keyset pmap: " + pmap.pheromone_map.keySet().size());
        System.out.println("Size keyset pixel_map: " + this.pixel_map.keySet().size());
        for (Coordinate coord : pmap.pheromone_map.keySet()){
            if (!pixel_map.containsKey(coord)){
                return false;
            }
        }
        for (Coordinate coord : this.pixel_map.keySet()){
            if (!pmap.pheromone_map.containsKey(coord)){
                System.out.println("Missing key: " + coord);
                
            }
        }
        return true;
    } 
    public void create_visit_count(){
        visit_count = new int[width][height];
        for (Ant ant : pixel_map.values().stream().map(x -> x.ant).collect(Collectors.toList())){
            for (Coordinate coord : ant.get_path()){
                visit_count[coord.getX()][coord.getY()]++;
            }
        }
    }

    public boolean is_local_maxima(Pixel pixel){
        for (int i = 0; i<width; i++){
            for (int j = 0; j<height; j++){
                
            }
        }
        return true; //endre seff
    }

    public void print_image(){ //ren copilot
         // Create a BufferedImage object with the same dimensions as the int[][] array
         BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         // Iterate over the int[][] array and set the pixel color based on the value
         for (int x = 0; x <width; x++) {
             for (int y = 0; y < height; y++) {
                 int value = visit_count[x][y];
                 Color color = new Color(value, 0, 0);
                 image2.setRGB(x, y, color.getRGB()); // Set the pixel color in the BufferedImage
             }
         }
         // Save the BufferedImage as an image file
         try {
             File output = new File("output.png");
             ImageIO.write(image2, "png", output);
             System.out.println("Image saved successfully.");
         } catch (IOException e) {
             System.out.println("Failed to save the image.");
             e.printStackTrace();
         }

        }
 
}


