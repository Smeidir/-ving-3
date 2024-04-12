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
                //System.out.println("Nå har vi " + image.segments.size() + " segmenter");
                //System.out.println("Det er så mange pixler i segmentene til sammen: " + image.segments.stream().mapToInt(s -> s.pixels.size()).sum());
            }
        }
        ArrayList<Segment> segments_to_remove = new ArrayList<Segment>();
        for(Segment segment: image.segments){
            
            if (segment.pixels.size() < image.pixel_map.size()/Parameters.max_segments){
                Segment most_similar = null;
                double similarity = 10000.0;
                
                Pixel centroid = segment.get_centroid();
                for (Segment other_segment : segment.get_neighbouring_segments()){
                     double dissimilarity = centroid.get_RGB_dissimilarity(other_segment.get_centroid()); //høyere verdi er mer forskjellig
                        if (dissimilarity < similarity){
                            other_segment.pixels.addAll(segment.pixels);
                            most_similar = other_segment;
                            segments_to_remove.add(segment);
                        }
                    }
                }
                   
            }
        System.out.println("Antall segmenter før: " + image.segments.size());
        image.segments.removeAll(segments_to_remove);
        System.out.println("Antall segmenter etter: " +image.segments.size());
        image.colorSegments();
        image.saveSegmentation();

    }



}
