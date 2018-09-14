package lafoet.ch11;

public class DataItem {
    private int iData;               // data item (key)
    //--------------------------------------------------------------
    public DataItem(int ii)          // constructor
    { iData = ii; }
    //--------------------------------------------------------------
    public int getKey()
    { return iData; }
}
