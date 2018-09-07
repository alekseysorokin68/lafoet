package lafoet.ch4;

public class StackChar {
    private int maxSize;
    private char[] stackArray;
    private int top;
    //--------------------------------------------------------------
    public StackChar(int max)    // constructor
    {
        maxSize = max;
        stackArray = new char[maxSize];
        top = -1;
    }

    //--------------------------------------------------------------
    public void push(char j)  // put item on top of stack
    {
        stackArray[++top] = j;
    }
    //--------------------------------------------------------------
    public char pop()         // take item from top of stack
    {
        return stackArray[top--];
    }
    //--------------------------------------------------------------
    public char peek()        // peek at top of stack
    {
        return stackArray[top];
    }
    //--------------------------------------------------------------
    public boolean isEmpty()  // true if stack is empty
    {
        return (top == -1);
    }

    public int size()         // return size
    { return top+1; }
    //--------------------------------------------------------------
    public char peekN(int n)  // return item at index n
    { return stackArray[n]; }

    public void displayStack(String s)
    {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        for(int j=0; j<size(); j++)
        {
            System.out.print( peekN(j) );
            System.out.print(' ');
        }
        System.out.println("");
    }
}
