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

}
