package DFS;

import org.omg.CORBA.RepositoryIdHelper;

public class ShuDu {
	private int[][] matrix;  
    public ShuDu(int[][] matrix) {  
        this.matrix = matrix;  
    }  
  
    public static void main(String[] args) {  
        // 待解数独   
        int[][] sudoku = {  
                {8, 0, 0, 0, 0, 0, 0, 0, 0},  
                {0, 0, 3, 6, 0, 0, 0, 0, 0},  
                {0, 7, 0, 0, 9, 0, 2, 0, 0},  
                {0, 5, 0, 0, 0, 7, 0, 0, 0},  
                {0, 0, 0, 0, 4, 5, 7, 0, 0},  
                {0, 0, 0, 1, 0, 0, 0, 3, 0},  
                {0, 0, 1, 0, 0, 0, 0, 6, 8},  
                {0, 0, 8, 5, 0, 0, 0, 1, 0},  
                {0, 9, 0, 0, 0, 0, 4, 0, 0}};  
        ShuDu s = new ShuDu(sudoku);  
        s.backTrace(0, 0);  
    }  
  
    /** 
     * 数独算法 
     * @param i 
     * 行号 
     * @param j 
     * 列号 
     */  
    private void backTrace(int i, int j) {  
    	if(i==8&&j==9)
    	{
    		System.out.println("找到解法");
    		printArray();
    		return;
    	}
    	
    	//已经到了列末尾了，还没到行尾，换行
    	if(j==9)
    	{
    		i++;
    		j=0;
    	}
    	//如果i行j列是空格，那么才进入空格填值的逻辑
    	if(matrix[i][j]==0)
    	{
    		for(int k=1;k<=9;k++)
    		{
    			//判断给i行j列放1-9中的任意一个数是否能满足规则
    			if(check(i,j,k)) {
    				matrix[i][j]=k;
    				backTrace(i, j+1);
    				matrix[i][j]=0;
    			}
    		}
    	}else {
    		backTrace(i, j+1);
    	}
    }

	private boolean check(int row, int line, int num) {
		// 判断该行/列是否有重复数字
		for(int i=0;i<9;i++)
		{
			if(matrix[row][i]==num||matrix[i][line]==num)
			{
				return false;
			}
		}
		
		//判断小九宫格是否有重复
		int tempRow=row/3;
		int tempLine=line/3;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(matrix[tempRow*3+i][tempLine*3+j]==num)
				{
					return false;
				}
			}
		}
		return true;
	} 
	public void printArray()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
