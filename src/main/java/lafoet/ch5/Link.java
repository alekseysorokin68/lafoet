package lafoet.ch5;

public class Link {
    public int iData;              // data item (key)
    public double dData;           // data item
    public Link next;              // next link in list
    // -------------------------------------------------------------
    public Link(int id, double dd) // constructor
    {
        iData = id;
        dData = dd;
    }
    // -------------------------------------------------------------
    public void displayLink()      // display ourself
    {
        System.out.print("{" + iData + ", " + dData + "} ");
    }
}
