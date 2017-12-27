package BFS;

public class Maze {
	int dx[]={0,-1,1,0};//方向
	int dy[]={-1,0,0,1};
	boolean visit[][]=new boolean[6][6];
	int total=0;
	int startx=1;
	int starty=1;
	int num[][]=new int[10][10];
	int endx;
	int endy;
	private class Point{
		int x;
		int y;
	}
	
	private class PointTracker{
		int px;
		int py;
	}
	char map[][]={
		    {'#','#','#','#','#','#'},
		    {'#','.','.','.','#','#'},
		    {'#','.','#','.','.','#'},
		    {'#','.','.','.','#','#'},
		    {'#','#','.','.','.','#'},
		    {'#','#','#','#','#','#'}
		};
	boolean isAllow(int x,int y)
	{
		if(x<0||x>5||y<0||y>5)
		{
			return false;
		}
		if(map[x][y]=='#')
		{
			return false;
		}
		if(visit[x][y]==true)
		{
			return false;
		}
		return true;
	}
	public void dfs(int x,int y,int step)
	{
		 
		if(x==endx&&y==endy) {
			total++;
			
		}
	}
}
