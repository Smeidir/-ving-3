import java.util.HashMap;

class Main{ 
    HashMap<Coordinate, Pixel> pixel_map = new HashMap<Coordinate, Pixel>();
    public static void main(String[] args){ 
        String img_name = "Project 3 training_images\\poland strong.jpg";
        Image image = new Image(img_name);
        image.init();  
    }


}
