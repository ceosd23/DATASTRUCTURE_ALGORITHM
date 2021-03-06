/*Implementing Lee Algorithm to find the path from a source to destination in Binary Graph*/

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LeeAlgorithm
{   int ROW,COLUMN;
    int matrix[][];
    boolean visited[][];
    int[] row_movement=new int[]{-1,0,0,1};
    int[] column_movement=new int[]{0,-1,1,0};
    Point source,destination;
    private Iterator<TravelPoint> pointIterator;

    public LeeAlgorithm(int matrix[][],int x1,int y1,int x2,int y2)
    {
        this.ROW=matrix.length;

        this.COLUMN=matrix[0].length;
        this.matrix=matrix;
        this.visited=new boolean[this.ROW][this.COLUMN];
        this.source=new Point(y1,x1);
        this.destination=new Point(y2,x2);

    }
    static class Point
    {
        int row_no;
        int column_no;

        public Point(int row_no,int column_no)
        {
            this.row_no=row_no;
            this.column_no=column_no;
        }
    }
    static class TravelPoint //implements Comparable<TravelPoint>
    {
        Point p;
        int weight;
       // int gold;
        public TravelPoint(Point p,int weight/*int gold*/)
        {
            this.p=p;
            this.weight=weight;
//            this.gold=gold;
        }

//        @Override
//        public int compareTo(TravelPoint o1){
//            if(this.gold>o1.gold)
//            {
//                    return -1;
//            }
//            else if(this.gold<o1.gold)
//            {   if(this.weight>o1.weight)
//                return -1;
//            }
//            return 0;
//        }
    }
    private boolean isvalid(int row,int column)
    {
        boolean b = (row >= 0) && (row < 8) && (column >=0) && (column <8);
        return b;
    }
    public void startVisiting()
    {
        if(this.matrix[this.source.row_no][this.source.column_no]!=1)
            {
                System.out.println("Path Cannot be found Source isn't reachable");
                return;
            }
        if(this.matrix[this.destination.row_no][this.destination.column_no]!=1)
        {
            System.out.println("Path Cannot be found Destination isn't reachable");
            return;
        }
        int count=0;
        Queue<TravelPoint> q=new LinkedList<TravelPoint>();
        this.visited[this.source.row_no][this.source.column_no]=true;
        q.add(new TravelPoint(source,0));
        while(!q.isEmpty())
        {
            TravelPoint current=q.poll();
            Point p=current.p;
            if((p.row_no==this.destination.row_no)&&(p.column_no==this.destination.column_no))
            {
                System.out.println("Path Found   "+current.weight);
//                System.out.println(count);
                return;
            }
                for(int i=0;i<8;i++)
                {
                    int row=p.row_no+this.row_movement[i];
                    int col=p.column_no+this.column_movement[i];
                    if((isvalid(row,col)&&(!this.visited[row][col]))&&((this.matrix[row][col]==1)||(this.matrix[row][col]==2)))
                    {
                        this.visited[row][col]=true;

                            q.add(new TravelPoint(new Point(row, col), current.weight + 1));
                        count++;

                    }


                }
            }
            System.out.println("Path cannot be devised");
            return;
        }
        public static void main(String ...x)
        {
            int mat[][]=new int[][]{
            {1,0,1,1,1,1,0,1,1,1},
            {1,0,1,0,1,1,1,0,1,1},
            {1,1,1,0,1,1,0,1,0,1},
            {0,0,0,0,1,0,0,0,0,1},
            {1,1,1,0,1,1,1,0,1,0},
            {1,0,1,1,1,1,0,1,0,0},
            {1,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,0,1,1,1},
            {1,1,0,0,0,0,1,0,0,1}};

            LeeAlgorithm l=new LeeAlgorithm(mat,0,1,mat[0].length-1,mat.length-1);
            l.startVisiting();
        }

    }


