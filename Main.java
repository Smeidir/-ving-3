import java.util.ArrayList;
import java.util.HashMap;


class Main{ 
    HashMap<Coordinate, Pixel> pixel_map = new HashMap<Coordinate, Pixel>();
    public static void main(String[] args){ 
        String img_name = Parameters.img_name;
        Image image = new Image(img_name);
        image.init();  
        Pheromone_map pheromone_map = new Pheromone_map(image.width, image.height);
        ArrayList<Ant> ants = new ArrayList<Ant>();
        ants = image.ants;

        for (Ant ant : ants){
            if (!ant.has_colony()){
                Segment segment = new Segment(image);
                ant.add_to_colony(segment);
                image.segments.add(segment);
                ant.sniff();
                System.out.println("Nå har vi " + image.segments.size() + " segmenter");
                System.out.println("Det er så mange pixler i segmentene til sammen: " + image.segments.stream().mapToInt(s -> s.pixels.size()).sum());
            }
        }
        for(Segment segment: segments){
            if (segment.pixels.size() < image.pixel_map.size()/Parameters.max_segments){
                
                image.segments.remove(segment);
            }
        }
        System.out.println(image.segments.size());
        image.colorSegments();

    }


}
