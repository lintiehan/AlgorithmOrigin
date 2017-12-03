public class Test{  
    public void findLCS(String s1,String s2){  
        int len1 = s1.length();  
        int len2 = s2.length();  
        char[] cs1 = s1.toCharArray();  
        char[] cs2 = s2.toCharArray();  
        int[] cnt = new int[100];  
        int maxlen = 0;  
        int pos = 0;  
        for(int i=0;i<len1;++i){  
            for(int j=len2;j>0;--j){//这里逆向遍历是为了防止覆盖cnt数组之前记录的结果，想不明白的话请在纸上模拟一下  
                if(cs1[i]==cs2[j-1]){  
                    cnt[j]=cnt[j-1]+1;  
                    if(cnt[j]>maxlen){  
                        maxlen=cnt[j];  
                        pos=j;  
                    }  
                }else{  
                    cnt[j]=0;  
                }  
            }  
        }  
        System.out.println(maxlen);  
        for(int i=0;i<maxlen;++i){  
            System.out.print(cs2[pos-maxlen+i]);//输出最长公共子串  
        }  
    }  
    public static void main(String[] args){  
        String s1="abcdefg";  
        String s2="aaabcfff";  
        Test test = new Test();  
        test.findLCS(s1, s2);  
    }  
}  