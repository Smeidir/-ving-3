import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DumbAnt extends Ant{



    public DumbAnt(Pixel pixel, Image image){
        super(pixel, image);
    }


    public void sniff(){
        List<Pixel> remainingPixels = this.image.pixel_map.values().stream().filter(x -> x.ant.segment == null).collect(Collectors.toList());
        System.out.println(remainingPixels.size());
        for (Pixel pixel : remainingPixels){
            Pixel centroid = this.segment.get_centroid();
            double dissimilarity = Distance.Euclidean(centroid.get_feature_vector(), pixel.get_feature_vector());//centroid diff
            //dissimilarity += Distance.Euclidean(this.feature_vector, pixel.get_feature_vector());//ant diff, aka edge diff
            double neighbours = 0.0;
            double highest_edge = 0.0;
            for (Pixel neighbour : image.get_neighbours(pixel.get_coords())){

                if (neighbour.segment !=this.segment){
                    neighbours += 2; //16 ganger høyere enn 1/8
                }
                else{
                    if (Distance.Euclidean(neighbour.get_feature_vector(), pixel.get_feature_vector()) > highest_edge){
                        highest_edge = Distance.Euclidean(neighbour.get_feature_vector(), pixel.get_feature_vector());
                    }
                }
            }
            dissimilarity += highest_edge;
            dissimilarity += neighbours;
            
            if (dissimilarity< Parameters.similarity_index){//er flere parametere så justerer
                pixel.get_ant().add_to_colony(this.segment);
            }
            else{
                pixel.sniffed = true;
            
            }
        }
    }
}




/*
 * Spm: Du legger til de oppdaterte feature vektorene som clustering, med pointers til sin originale pixel?
 * Regner du ut avstand til alle andre pixler da veldig mange ganger per maur?
 * Når du assigner cluster centers assigner du selv de som er i andre clusters?
 * Feromoner oppdateres fortløpende?
 */