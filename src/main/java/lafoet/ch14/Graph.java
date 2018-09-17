package lafoet.ch14;

public class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private int currentVert;
    private PriorityQ thePQ;
    private int nTree;
    private DistPar sPath[];     // array for shortest-path data
    private int startToCurrent;  // distance to currentVert
    // -------------------------------------------------------------
    public Graph()               // constructor
    {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int j=0; j<MAX_VERTS; j++)      // set adjacency
            for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
                adjMat[j][k] = INFINITY;
        thePQ = new PriorityQ();
        sPath = new DistPar[MAX_VERTS];    // shortest paths
    }  // end constructor
    // -------------------------------------------------------------
    public void addVertex(char lab)
    {
        vertexList[nVerts++] = new Vertex(lab);
    }
    // -------------------------------------------------------------
    public void addEdge(int start, int end, int weight)
    {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }
    // -------------------------------------------------------------
    public void displayVertex(int v)
    {
        System.out.print(vertexList[v].label);
    }
    // -------------------------------------------------------------
    public void mstw()           // minimum spanning tree
    {
        currentVert = 0;          // start at 0

        while(nTree < nVerts-1)   // while not all verts in tree
        {                      // put currentVert in tree
            vertexList[currentVert].isInTree = true;
            nTree++;

            // insert edges adjacent to currentVert into PQ
            for(int j=0; j<nVerts; j++)   // for each vertex,
            {
                if(j==currentVert)         // skip if it's us
                    continue;
                if(vertexList[j].isInTree) // skip if in the tree
                    continue;
                int distance = adjMat[currentVert][j];
                if( distance == INFINITY)  // skip if no edge
                    continue;
                putInPQ(j, distance);      // put it in PQ (maybe)
            }
            if(thePQ.size()==0)           // no vertices in PQ?
            {
                System.out.println(" GRAPH NOT CONNECTED");
                return;
            }
            // remove edge with minimum distance, from PQ
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;

            // display edge from source to current
            System.out.print( vertexList[sourceVert].label );
            System.out.print( vertexList[currentVert].label );
            System.out.print(" ");
        }  // end while(not all verts in tree)

        // mst is complete
        for(int j=0; j<nVerts; j++)     // unmark vertices
            vertexList[j].isInTree = false;
    }  // end mstw
    // -------------------------------------------------------------
    public void putInPQ(int newVert, int newDist)
    {
        // is there another edge with the same destination vertex?
        int queueIndex = thePQ.find(newVert);
        if(queueIndex != -1)              // got edge's index
        {
            Edge tempEdge = thePQ.peekN(queueIndex);  // get edge
            int oldDist = tempEdge.distance;
            if(oldDist > newDist)          // if new edge shorter,
            {
                thePQ.removeN(queueIndex);  // remove old edge
                Edge theEdge =
                        new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge);      // insert new edge
            }
            // else no action; just leave the old vertex there
        }  // end if
        else  // no edge with same destination vertex
        {                              // so insert new one
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }  // end putInPQ()

    public void path()                // find all shortest paths
    {
        int startTree = 0;             // start at vertex 0
        vertexList[startTree].isInTree = true;
        nTree = 1;                     // put it in tree

        // transfer row of distances from adjMat to sPath
        for(int j=0; j<nVerts; j++)
        {
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }

        // until all vertices are in the tree
        while(nTree < nVerts)
        {
            int indexMin = getMin();    // get minimum from sPath
            int minDist = sPath[indexMin].distance;

            if(minDist == INFINITY)     // if all infinite
            {                        // or in tree,
                System.out.println("There are unreachable vertices");
                break;                   // sPath is complete
            }
            else
            {                        // reset currentVert
                currentVert = indexMin;  // to closest vert
                startToCurrent = sPath[indexMin].distance;
                // minimum distance from startTree is
                // to currentVert, and is startToCurrent
            }
            // put current vertex in tree
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();             // update sPath[] array
        }  // end while(nTree<nVerts)

        displayPaths();                // display sPath[] contents

        nTree = 0;                     // clear tree
        for(int j=0; j<nVerts; j++)
            vertexList[j].isInTree = false;
    }  // end path()
    // -------------------------------------------------------------
    public int getMin()               // get entry from sPath
    {                              //    with minimum distance
        int minDist = INFINITY;        // assume minimum
        int indexMin = 0;
        for(int j=1; j<nVerts; j++)    // for each vertex,
        {                           // if it's in tree and
            if( !vertexList[j].isInTree &&  // smaller than old one
                    sPath[j].distance < minDist )
            {
                minDist = sPath[j].distance;
                indexMin = j;            // update minimum
            }
        }  // end for
        return indexMin;               // return index of minimum
    }  // end getMin()
    // -------------------------------------------------------------
    public void adjust_sPath()
    {
        // adjust values in shortest-path array sPath
        int column = 1;                // skip starting vertex
        while(column < nVerts)         // go across columns
        {
            // if this column's vertex already in tree, skip it
            if( vertexList[column].isInTree )
            {
                column++;
                continue;
            }
            // calculate distance for one sPath entry
            // get edge from currentVert to column
            int currentToFringe = adjMat[currentVert][column];
            // add distance from start
            int startToFringe = startToCurrent + currentToFringe;
            // get distance of current sPath entry
            int sPathDist = sPath[column].distance;

            // compare distance from start with sPath entry
            if(startToFringe < sPathDist)   // if shorter,
            {                            // update sPath
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }  // end while(column < nVerts)
    }  // end adjust_sPath()
    // -------------------------------------------------------------
    public void displayPaths()
    {
        for(int j=0; j<nVerts; j++) // display contents of sPath[]
        {
            System.out.print(vertexList[j].label + "="); // B=
            if(sPath[j].distance == INFINITY)
                System.out.print("inf");                  // inf
            else
                System.out.print(sPath[j].distance);      // 50
            char parent = vertexList[ sPath[j].parentVert ].label;
            System.out.print("(" + parent + ") ");       // (A)
        }
        System.out.println("");
    }
}
