import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


class DumbMain{ 
    HashMap<Coordinate, Pixel> pixel_map = new HashMap<Coordinate, Pixel>();
    public static void main(String[] args) throws IOException{ 
        for (String s: Parameters.training_images){
            
        
            FileWriter python_param = new FileWriter("Project 3 evaluator\\params.py");
            python_param.write("IMAGE = " + Integer.parseInt(s.replaceAll("[^0-9]", "").substring(1)));
            python_param.close();
            String img_name = s;
            Image image = new Image(img_name);
            image.init();  
            ArrayList<Ant> dumbAnts = new ArrayList<Ant>();
            for(Ant a: image.ants){
                DumbAnt da = new DumbAnt(a.pixel, a.image);
                dumbAnts.add(da);
            }
            image.ants = dumbAnts;

            for (Ant dumbAnt : dumbAnts){
                if (!dumbAnt.has_colony()){
                    Segment segment = new Segment(image);
                    dumbAnt.add_to_colony(segment);
                    image.segments.add(segment);
                    dumbAnt.sniff();
                }
            }
            ArrayList<Segment> segments_to_remove = new ArrayList<Segment>();
            for(Segment segment: image.segments){
                
                if (segment.pixels.size() < image.pixel_map.size()/Parameters.max_segments){
                    Segment most_similar = null;
                    double best_similarity = 10000.0;
                    
                    Pixel centroid = segment.get_centroid();
                    for (Segment other_segment : segment.get_neighbouring_segments()){
                        double dissimilarity = centroid.get_RGB_dissimilarity(other_segment.get_centroid()); //høyere verdi er mer forskjellig

                            if (dissimilarity < best_similarity){//jo mindre dissimilarity jo bedre
                                most_similar = other_segment;
                                segments_to_remove.add(segment);

                            }
                        }
                    most_similar.pixels.addAll(segment.pixels);
                    }
                    
                }
            System.out.println(s + " Antall segmenter før: " + image.segments.size());
            image.segments.removeAll(segments_to_remove);
            System.out.println(s + " Antall segmenter etter: " +image.segments.size());

            image.colorSegments();

            image.saveSegmentation(s, true );
            image.saveSegmentationGreen(s, true);

        }
}


}