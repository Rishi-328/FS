// Budget Padmanabham planned to visit the tourist places. There are N tourist 
// places in the city. The tourist places are numbered from 1 to N.

// You are given the routes[] to travel between the tourist places in the city.
// where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
// You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
// Your task is to help Budget Padmanabham to find the cheapest route plan, to 
// visit all the tourist places in the city. If you are not able to find such plan, 
// print -1.
 
// Input Format:
// -------------
// Line-1: Two space separated integers N and R,number of places and routes.
// Next R lines: Three space separated integers, start, end and price.
  
// Output Format:
// --------------
// Print an integer, minimum cost to visit all the tourist places.
 
 
// Sample Input-1:
// ---------------
// 4 5
// 1 2 3
// 1 3 5
// 2 3 4
// 3 4 1
// 2 4 5
 
// Sample Output-1:
// ----------------
// 8
 
// Explanation:
// ------------
// The cheapest route plan is as follows:
// 1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
// Sample Input-2:
// ---------------
// 4 3
// 1 2 3
// 1 3 5
// 2 3 4
 
// Sample Output-2:
// ----------------
// -1

import java.util.*;
public class MinimumCost{
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
        if(x<y){
            parent[y]=x;
        }else{
            parent[x]=y;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int[][] arr = new int[r][3];
        int[] parent = new int[n+1];
        for(int i=0;i<=n;i++)parent[i]=i;
        for(int i=0;i<r;i++){
            arr[i][0]=sc.nextInt();
            arr[i][1]=sc.nextInt();
            arr[i][2]=sc.nextInt();
        }
        Arrays.sort(arr,(a,b)->a[2]-b[2]);
        Set<Integer> s = new HashSet<>();
        int sum=0;
        for(int i=0;i<r;i++){
            if(findParent(arr[i][0],parent)!=findParent(arr[i][1],parent)){
                s.add(arr[i][0]);
                s.add(arr[i][1]);
                union(arr[i][0],arr[i][1],parent);
                sum += arr[i][2];
            }
        }
        System.out.println(s.size()==n?sum:-1);
        
    } 
}  
