package Problem;

public class ZuHe {
	 public void combine(int[] a) {  
         
	        if(null == a || a.length == 0)  
	            return;  
	        int[] b = new int[a.length];  
	        getCombination(a, 0, b, 0);  
	    }  
	    private void getCombination(int[] a, int begin, int b[], int index) {  
	          
	        if(index >= a.length)  
	            return;  
	        for(int i = begin; i < a.length; i++){  
	              
	            b[index] = a[i];  
	            printArray(b,index);  
	            getCombination(a, i+1, b, index+1);  
	        }  
	    }  
	        private void printArray(int[] b, int index) {  
	              
	            for(int i = 0; i < index+1; i++){  
	                System.out.print(b[i] + " ");  
	            }  
	            System.out.println();  
	        }  
	          
	    public static void main(String[] args){  
	          
	        ZuHe robot = new ZuHe();  
	        int[] a = {1,2,3};  
	        robot.combine(a);  
	  
	    }  
}
