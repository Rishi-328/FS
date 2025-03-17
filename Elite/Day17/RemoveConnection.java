// There are N computers in a network, all the computers are connected as tree 
// structure. And one new connection is added in the Network. The computers in 
// the network are identified with their IDs, the IDs are numbered between 1 to N.

// The connections in the network is given as coonection[i] = [comp-A, comp-B], 
// there is a connection between comp-A and comp-B.

// Your task is to remove a connection in the network and print it, so that 
// all the computers are connected as tree structure. If there are multiple 
// options to remove, remove the connection that occurs last in the input.


// Input Format:
// -------------
// Line-1: Two space separated integers N, number of computers.
// Next N lines: Two space separated integers, comp-A & comp-B.

// Output Format:
// --------------
// Print the connection which is removed.


// Sample Input-1:
// ---------------
// 6
// 1 2
// 3 4
// 3 6
// 4 5
// 5 6
// 2 3

// Sample Output-1:
// ---------------
// 5 6


// Sample Input-2:
// ---------------
// 4
// 1 2
// 2 3
// 3 4
// 2 4

// Sample Output-2:
// ---------------
// 2 4

import java.util.*;
public class RemoveConnection{
    public static int findParent(int i,int[] parent){
        if(i==parent[i]){
            return i;
        }
        return parent[i]=findParent(parent[i],parent);
    }
    public static void union(int a,int b,int[] parent){
        int x = findParent(a,parent);
        int y = findParent(b,parent);
        if(x==y){
            return;
        }
        parent[y]=x;
    }
    public static String getRC(int n,int[][] path,int[] parent){
        String s = "";
        for(int i=0;i<n;i++){
            if(findParent(path[i][0],parent)==findParent(path[i][1],parent)){
                s = path[i][0]+" "+path[i][1];
            }
            union(path[i][0],path[i][1],parent);
        }
        return s;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] parent = new int[n+1];
        for(int i=0;i<n+1;i++)parent[i]=i;
        int[][] path = new int[n][2];
        for(int i=0;i<n;i++){
            path[i][0]=sc.nextInt();
            path[i][1]=sc.nextInt();
        }
        String ans = getRC(n,path,parent);
        System.out.println(ans);
        
    }
}