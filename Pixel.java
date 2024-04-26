public class Pixel {
    float R;
    float G;
    float B;
    Coordinate coords;
    int id;
    Ant ant;
    Segment segment =null;
    boolean sniffed = false;

    public Pixel(float R, float G, float B, int x, int y, int id){
        this.R = R;
        this.G = G;
        this.B = B;
        coords = new Coordinate(x, y);
        this.id = id;
    }
    
    public float getR(){ //L
        return R;
    }
    
    public float getG(){//A
        return G;
    }   
    
    public float getB(){//B in LAB system
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
    public float[] get_feature_vector(){
        return new float[]{Parameters.L_weight*R, G, B, 1.0f*coords.getX(), 1.0f*coords.getY()};
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
    public boolean isAdj(Pixel other){
        if (this.equals(other)){
            return false;
        }
        return Math.abs(coords.getX()-other.getX()) <= 1 && Math.abs(coords.getY()-other.getY()) <= 1;
    }
    public void addToSegment(Segment segment){
        this.segment = segment;
        segment.add_pixel(this);
    }

}
