public class Distance {

    public static double Euclidean(float[] a, float[] b){
        double sum = 0;
        for (int i = 0; i < a.length; i++){
            sum += Math.pow(a[i]-b[i], 2);
        }
        return Math.sqrt(sum);
    }
    
}
