import java.util.ArrayList;
import java.util.HashMap;

public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean neighbours(Coordinate other){
        if(Math.abs((double) other.getX() - this.x) <=1 && Math.abs((double) other.getY() - this.y) <= 1){
            return true;
        }
        return false;
    }
    public double euclidist(Coordinate other){
        return Math.sqrt(Math.pow(other.getX() - this.x, 2) + Math.pow(other.getY() - this.y, 2));
    }
    @Override
    public boolean equals(Object v) {
          boolean retVal = false;
  
          if (v instanceof Coordinate){
              Coordinate ptr = (Coordinate) v;
              retVal = ptr.getX() == this.x && ptr.getY() == this.y;
          }
  
       return retVal;
    }
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    @Override
    public int hashCode() {
        int hash = 7;
        Integer hash_base = x*241 + y;
        hash = 17 * hash + (hash_base != null ? hash_base.hashCode() : 0);
        return hash;
    }

    public static void main(String[] args) {
        Coordinate c1 = new Coordinate(1, 1);
        Coordinate c2 = new Coordinate(1, 1);
        System.out.println(c1.equals(c2));
        ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
        coords.add(c1);
        System.out.println( "ArrayList:" + coords.contains(c2));
        HashMap<Coordinate, Integer> map = new HashMap<Coordinate, Integer>();
        map.put(c1, 1);
        System.out.println("Hashmap: " + map.containsKey(c2));
    }
  

}
