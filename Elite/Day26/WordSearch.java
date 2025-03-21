package Day26;

// Imagine you're an adventurer with a mystical treasure map. This map is a grid of 
// ancient runes, where each cell holds a single character. Legend has a 
// powerful incantation—represented as a string—is hidden within these runes. 
// To unlock the treasure, you must verify if the incantation exists on the map.

// The incantation is formed by linking runes that are directly next to each other 
// either horizontally or vertically. Each rune on the map can only be used once in
// the incantation.

// Your Task:  
// Given an m x n grid representing the treasure map and a string representing the 
// incantation, return true if the incantation can be traced on the map; 
// otherwise, return false.


// Example 1:
// ----------
// Input:  
// 3 4
// ABCD
// SFCS
// ADEE
// ABCCED

// Output:
// ABCCED can be traced

// Explanation (check hint)
// Treasure Map Grid:  
// [
//   ["A", "B", "C", "E"],
//   ["S", "F", "C", "S"],
//   ["A", "D", "E", "E"]
// ]

// Incantation: "ABCCED" exists in map


// Example 2:
// ----------
// Input:
// 3 4
// ABCE
// SFCS
// ADEE
// ABCB

// Output: 
// ABCB cannot be traced

// Explanation:
// Treasure Map Grid:  

// [
//   ["A", "B", "C", "E"],
//   ["S", "F", "C", "S"],
//   ["A", "D", "E", "E"]
// ]

// Incantation: "ABCB" does not exist in map


// Constraints:

// - m == the number of rows in the grid  
// - n == the number of columns in the grid  
// - 1 <= m, n <= 6  
// - 1 <= incantation length <= 15  
// - The grid and incantation consist only of uppercase and lowercase English letters.

import java.util.*;
public class WordSearch{
    static int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
    public static boolean isCheck(char[][] grid,String target,int n,int m){
        for(int i = 0;i < n; i++){
            for(int j = 0; j < m; j++){
                if(target.charAt(0) == grid[i][j]){
                    if(dfs(grid,target,i,j,0,n,m)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean dfs(char[][] grid,String target,int i,int j,int len,int n,int m){
        if(len==target.length()){
            return true;
        }
        if(i<0 || i>=n || j<0 || j>=m || grid[i][j]=='$' || target.charAt(len)!=grid[i][j]){
            return false;
        }
        char temp = grid[i][j];
        grid[i][j] = '$';
        for(int k=0;k<4;k++){
            int a = i + dir[k][0];
            int b = j + dir[k][1];
            if(dfs(grid,target,a,b,len+1,n,m)){
                return true;
            }
        }
        grid[i][j] = temp;
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        char[][] grid = new char[n][m];
        for(int i = 0; i < n; i++){
            String temp = sc.nextLine();
            int j = 0;
            for(char ch : temp.toCharArray()){
                grid[i][j++] = ch;
            }
        }
        String target = sc.nextLine();
        if(isCheck(grid,target,n,m)){
            System.out.println(target+" can be traced");
        }else{
            System.out.println(target+" cannot be traced");
        }
        
        sc.close();

    }
}