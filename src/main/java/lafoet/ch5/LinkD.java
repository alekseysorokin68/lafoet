package lafoet.ch5;

public class LinkD {
    public long dData;                 // data item
    public LinkD next;                  // next link in list
    public LinkD previous;              // previous link in list
    // -------------------------------------------------------------
    public LinkD(long d)                // constructor
    { dData = d; }
    // -------------------------------------------------------------
    public void displayLink()          // display this link
    { System.out.print(dData + " "); }
}
