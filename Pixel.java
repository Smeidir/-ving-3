public class Pixel {
    int R;
    int G;
    int B;
    Coordinate coords;
    int id;
    Ant ant;
    Segment segment;

    public Pixel(int R, int G, int B, int x, int y, int id){
        this.R = R;
        this.G = G;
        this.B = B;
        coords = new Coordinate(x, y);
        this.id = id;
    }
    public int getR(){
        return R;
    }
    public int getG(){
        return G;
    }   
    public int getB(){
        return B;
    }
    public int getX(){
        return coords.getX();
    }
    public int getY(){
        return coords.getY();
    }
    public Coordinate get_coords(){
        return coords;
    }
    public int[] get_feature_vector(){
        return new int[]{R, G, B};
    }
    public void set_ant(Ant ant){
        this.ant = ant;
        ant.pixel = this; //overflødig sannsynligvis
    }  
    public Ant get_ant(){
        return ant;
    } 
    public double get_RGB_dissimilarity(Pixel other){ //større verdi betyr mer ulikhet
        double similarity = Math.sqrt(Math.pow(R-other.getR(),2) + Math.pow(G-other.getG(),2) + Math.pow(B-other.getB(),2));
        return similarity;
    }

}
