package DFS;

import java.util.LinkedList; 

public class Maze {
	int min=Integer.MAX_VALUE;
	int endx=3;
	int endy=3;
	int width=5;
	int height=4;
	int [][] maze=new int[5][4];
	int [][] mark=new int[5][4];
	LinkedList<Integer>map=new LinkedList<>();
	
	public void dfs(int startx,int starty,int step)
	{
		int [][] next=new int[][] {{1,0},{0,1},{-1,0},{0,-1}}; //按右下左上的顺序
		int nextX,nextY;
		int posible;
		if(startx==endx&&starty==endy)
		{
			if(step<min)
			{
				min=step;
			}
			for(int i=map.size()-1;i>=0;i=i-2)
			{
				nextX=map.get(i);
				nextY=map.get(i-1);
				System.out.print("("+nextX+","+nextY+")");
				if(i!=1)
				{
					System.out.print("->");
				}
			}
			System.out.println();
			return;
		}
		for(posible=0;posible<next.length;posible++)
		{
			nextX=startx+next[posible][0];
			nextY=starty+next[posible][1];
			if(nextX<0||nextX>=width||nextY<0||nextY>=height)
			{
				continue;
			}
			
			if(maze[nextX][nextY]==0&&mark[nextX][nextY]==0)
			{
				map.push(nextX);
				map.push(nextY);
				mark[nextX][nextY]=1;
				dfs(nextX, nextY, step+1);
				mark[nextX][nextY]=0;
				map.pop();
				map.pop();
			}
		}
	}
	  /* 
     * 初始化迷宫 
     */  
    public void initMaze() {  
        this.maze = new int[width][height];  
        this.mark = new int[width][height];  
  
        this.maze[2][0] = 1;  
        this.maze[1][2] = 1;  
        this.maze[2][2] = 1;  
        this.maze[3][2] = 1;  
        this.mark[0][0] = 1;  
  
        //打印迷宫 _表示可通行 *表示障碍 !表示目标  
        for(int y = 0; y < height; y++) {  
            for(int x = 0; x < width; x++) {  
                if(x == endx && y == endy) {  
                    System.out.print("!  ");  
                }  else if(this.maze[x][y] == 1) {  
                    System.out.print("*  ");  
                } else {  
                    System.out.print("_  ");  
                }  
            }  
            System.out.println();  
        }  
        System.out.println();  
    }  
  
    public static void main(String[] args) {  
        int startX = 0;  
        int startY = 0;  
        Maze d = new Maze();  
        d.initMaze();  
        d.dfs(startX, startY, 0);  
        if(d.min < Integer.MAX_VALUE)  
            System.out.println("最少需要" + d.min + "步");  
        else  
            System.out.println("目标地点无法到达");  
    }  
}
