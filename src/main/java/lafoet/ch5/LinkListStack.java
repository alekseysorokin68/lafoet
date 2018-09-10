package lafoet.ch5;

public class LinkListStack {
    private LinkX first;            // ref to first item on list
    // -------------------------------------------------------------
    public LinkListStack()              // constructor
    { first = null; }           // no items on list yet
    // -------------------------------------------------------------
    public boolean isEmpty()       // true if list is empty
    { return (first==null); }
    // -------------------------------------------------------------
    public void insertFirst(long dd) // insert at start of list
    {                           // make new link
        LinkX newLink = new LinkX(dd);
        newLink.next = first;       // newLink --> old first
        first = newLink;            // first --> newLink
    }
    // -------------------------------------------------------------
    public long deleteFirst()      // delete first item
    {                           // (assumes list not empty)
        LinkX temp = first;          // save reference to link
        first = first.next;         // delete it: first-->old next
        return temp.dData;          // return deleted link
    }
    // -------------------------------------------------------------
    public void displayList()
    {
        LinkX current = first;       // start at beginning of list
        while(current != null)      // until end of list,
        {
            current.displayLink();   // print data
            current = current.next;  // move to next link
        }
        System.out.println("");
    }
}
