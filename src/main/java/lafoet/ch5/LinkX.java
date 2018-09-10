package lafoet.ch5;

public class LinkX {
    public long dData;                 // data item
    public LinkX next;                  // next link in list
    // -------------------------------------------------------------
    public LinkX(long d)                // constructor
    { dData = d; }
    // -------------------------------------------------------------
    public void displayLink()          // display this link
    { System.out.print(dData + " "); }
}
