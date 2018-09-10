package lafoet.ch5;

public class SortedList {
    private LinkX first;                 // ref to first item
    // -------------------------------------------------------------
    public SortedList()                 // constructor
    { first = null; }
    // -------------------------------------------------------------
    public SortedList(LinkX[] linkArr)  // constructor (array
    {                               // as argument)
        first = null;                        // initialize list
        for(int j=0; j<linkArr.length; j++)  // copy array
            insert( linkArr[j] );             // to list
    }
    public boolean isEmpty()            // true if no links
    { return (first==null); }
    // -------------------------------------------------------------
    public void insert(long key)        // insert, in order
    {
        LinkX newLink = new LinkX(key);    // make new link
        LinkX previous = null;            // start at first
        LinkX current = first;
        // until end of list,
        while(current != null && key > current.dData)
        {                             // or key > current,
            previous = current;
            current = current.next;       // go to next item
        }
        if(previous==null)               // at beginning of list
            first = newLink;              // first --> newLink
        else                             // not at beginning
            previous.next = newLink;      // old prev --> newLink
        newLink.next = current;          // newLink --> old currnt
    }  // end insert()
    public void insert(LinkX k)     // insert (in order)
    {
        LinkX previous = null;            // start at first
        LinkX current = first;
        // until end of list,
        while(current != null && k.dData > current.dData)
        {                             // or key > current,
            previous = current;
            current = current.next;       // go to next item
        }
        if(previous==null)               // at beginning of list
            first = k;                    // first --> k
        else                             // not at beginning
            previous.next = k;            // old prev --> k
        k.next = current;                // k --> old currnt
    }  // end insert()
    // -------------------------------------------------------------
    public LinkX remove()           // return & delete first link
    {                           // (assumes non-empty list)
        LinkX temp = first;               // save first
        first = first.next;              // delete first
        return temp;                     // return value
    }
    // -------------------------------------------------------------
    public void displayList()
    {
        System.out.print("List (first-->last): ");
        LinkX current = first;       // start at beginning of list
        while(current != null)      // until end of list,
        {
            current.displayLink();   // print data
            current = current.next;  // move to next link
        }
        System.out.println("");
    }
}
