import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.math.*;
import java.util.Queue;

public class graph {
    private static int sizex,sizey;
    ArrayList<pair> island1,island2;

    public graph(int sizex,int sizey){
        island1 = new ArrayList<>();
        island2 = new ArrayList<>();
        this.sizex = sizex;
        this.sizey = sizey;
    }
    static class pair
    {
        public int x, y;
        public pair(){};
        public pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public void setxy(int x , int y){
            this.x = x;
            this.y = y;
        }
    }
    private static boolean isSafe(int mat[][], int i, int j,boolean vis[][])
    {
        return (i >= 0) && (i < sizex) &&
                (j >= 0) && (j < sizey) &&
                (mat[i][j]==1 && !vis[i][j]);
    }
    private static void BFS(int mat[][], boolean vis[][],int si, int sj)
    {
        int row[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int col[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
        // Simple BFS first step, we enqueue
        // source and mark it as visited
        Queue<pair> q = new LinkedList<pair>();
        q.add(new pair(si, sj));
        vis[si][sj] = true;

        // Next step of BFS. We take out
        // items one by one from queue and
        // enqueue their univisited adjacent
        while (!q.isEmpty())
        {

            int i = q.peek().x;
            int j = q.peek().y;
            q.remove();

            // Go through all 8 adjacent
            for (int k = 0; k < 8; k++)
            {
                if (isSafe(mat, i + row[k],
                        j + col[k], vis))
                {
                    vis[i + row[k]][j + col[k]] = true;
                    q.add(new pair(i + row[k], j + col[k]));
                }
            }
        }
    }
    private static void BFS(int mat[][], boolean vis[][],int si, int sj,ArrayList<pair> islnd)
    {
        int row[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int col[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
        // Simple BFS first step, we enqueue
        // source and mark it as visited
        Queue<pair> q = new LinkedList<pair>();
        q.add(new pair(si, sj));

        vis[si][sj] = true;

        // Next step of BFS. We take out
        // items one by one from queue and
        // enqueue their univisited adjacent
        while (!q.isEmpty())
        {

            int i = q.peek().x;
            int j = q.peek().y;
            islnd.add(new pair(i,j));
            q.remove();

            // Go through all 8 adjacent
            for (int k = 0; k < 8; k++)
            {
                if (isSafe(mat, i + row[k],
                        j + col[k], vis))
                {
                    vis[i + row[k]][j + col[k]] = true;
                    q.add(new pair(i + row[k], j + col[k]));
                }
            }
        }
    }
    /*public void ShowMatrix(){
        for(int i = 0 ; i < sizey ; i++)
        {
            for(int j = 0 ; j < sizey ; j++)
                System.out.print(mapJ[i][j]);
            System.out.println();
        }
    }*/
    public int[] shortway(int mat[][])
    {
        if(countIslands(mat)!=2)
            return null;
        // Mark all cells as not visited
        boolean [][]vis = new boolean[sizex][sizey];
        // Call BFS for every unvisited vertex
        // Whenever we see an univisted vertex,
        // we increment res (number of islands)
        // also.
        int res = 0;
        for (int i = 0; i < sizex; i++)
        {
            for (int j = 0; j < sizey; j++)
            {
                if (mat[i][j]==1 && !vis[i][j])
                {
                    if(res==0){
                        BFS(mat, vis, i, j,island1);
                    }
                    else{
                        BFS(mat, vis, i, j,island2);
                    }
                    //numbers island
                    res++;
                }
            }
        }
        return findShortWay();
    }
    static int countIslands(int mat[][])
    {
        // Mark all cells as not visited
        boolean [][]vis = new boolean[sizex][sizey];

        // Call BFS for every unvisited vertex
        // Whenever we see an univisted vertex,
        // we increment res (number of islands)
        // also.
        int res = 0;
        for (int i = 0; i < sizex; i++)
        {
            for (int j = 0; j < sizey; j++)
            {
                if (mat[i][j]==1 && !vis[i][j])
                {
                    BFS(mat, vis, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    private int[] findShortWay(){

        pair[] p = new pair[2];

        p[0] = new pair(island1.get(0).x,island1.get(0).y);
        //System.out.println("x: "+(p[0].x+1)+"\ty:"+(p[0].y+1));
        p[1] = new pair(island2.get(0).x,island2.get(0).y);
        //System.out.println("x1: "+(p[1].x+1)+"\ty1:"+(p[1].y+1));
        for(int i = 1 ; i < island1.size() ; i++)
            for(int j = 0 ; j < island2.size() ; j++){
                if(calclength(p[0],p[1])>calclength(island1.get(i),island2.get(j)))
                {
                    p[0] = island1.get(i);
                    //System.out.println("x: "+(p[0].x+1)+"\ty:"+(p[0].y+1));
                    p[1] = island2.get(j);
                    //System.out.println("x1: "+(p[1].x+1)+"\ty1:"+(p[1].y+1));
                }

            }
        return new int[]{p[0].x,p[0].y,p[1].x,p[1].y};
    }
    private double calclength(pair p1,pair p2){
        int x = p2.x-p1.x;
        int y = p2.y-p1.y;
        //if(x==)
        // System.out.println("res: "+res);
        int res = (int)Math.sqrt((x*x)+(y*y));
        //System.out.println("res: "+res);
        return res;
    }
}

