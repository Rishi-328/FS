// A group of researchers is analyzing satellite imagery of agricultural fields, 
// represented by a grid of land sections. Each section is marked either as 
// fertile (1) or infertile (0). To efficiently plan crop planting, the researchers 
// need to identify the largest rectangular area consisting exclusively of fertile 
// land sections.

// Given a binary grid representing the land (1 for fertile and 0 for infertile), 
// your task is to compute the area of the largest rectangle consisting entirely 
// of fertile land sections.

// Input Format:
// -------------
// Line-1: Two integers rows(r) and columns(c) of grid.
// Next r lines: c space separated integers, each row of the grid.

// Output Format:
// --------------
// Print an integer, area of the largest rectangle consisting entirely of fertile land sections.

// Example:
// --------
// input=
// 4 5
// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 1 1
// 1 0 0 1 0

// output=
// 6
import java.util.*;
public class MaximalRectangle{
    public static int helper(int[][] arr,int n,int m){
        int ans = 0;
        int[][] h = new int[n][m];
        for(int i=0;i<m;i++){
            h[0][i] = arr[0][i]==1?1:0;
            for(int j=1;j<n;j++){
                if(arr[j][i] == 1){
                    h[j][i] = h[j-1][i] + 1;
                }else{
                    h[j][i] = 0;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int min = h[i][j];
                for(int k=j;k>=0;k--){
                    if(h[i][k]==0){
                        break;
                    }
                    min = Math.min(min,h[i][k]);
                    ans = Math.max(ans,min*(j-k+1));
                }
            }
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                grid[i][j]=sc.nextInt();
            }
        }
        System.out.println(helper(grid,n,m));
        
        
    }
}