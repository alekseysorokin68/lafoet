package lafoet.ch14;

public class DistPar {
    public int distance;   // distance from start to this vertex
    public int parentVert; // current parent of this vertex
    // -------------------------------------------------------------
    public DistPar(int pv, int d)  // constructor
    {
        distance = d;
        parentVert = pv;
    }
}
