// There are N cities, and M routes[], each route is a path between two cities.
// routes[i] = [city1, city2], there is a travel route between city1 and city2.
// Each city is numbered from 0 to N-1.
 
// There are one or more Regions formed among N cities. 
// A Region is formed in such way that you can travel between any two cities 
// in the region that are connected directly and indirectly.
 
// Your task is to findout the number of regions formed between N cities. 
 
// Input Format:
// -------------
// Line-1: Two space separated integers N and M, number of cities and routes
// Next M lines: Two space separated integers city1, city2.
 
// Output Format:
// --------------
// Print an integer, number of regions formed.
 
 
// Sample Input-1:
// ---------------
// 5 4
// 0 1
// 0 2
// 1 2
// 3 4
 
// Sample Output-1:
// ----------------
// 2
 
 
// Sample Input-2:
// ---------------
// 5 6
// 0 1
// 0 2
// 2 3
// 1 2
// 1 4
// 2 4
 
// Sample Output-2:
// ----------------
// 1
 
// Note: Look HINT for explanation.

import java.util.*;
public class CityRegions{
    public static int findParent(int i,int[] parent){
        if(i==parent[i]){
            return i;
        }
        return parent[i]=findParent(parent[i],parent);
    }
    public static void union(int a,int b,int[] parent){
        int p1 = findParent(a,parent);
        int p2 = findParent(b,parent);
        if(p1<p2){
            parent[p2]=p1;
        }else{
            parent[p1]=p2;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int[] parent = new int[n];
        for(int i=0;i<n;i++)parent[i]=i;
        for(int i=0;i<r;i++){
            union(sc.nextInt(),sc.nextInt(),parent);
        }
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++){
            set.add(findParent(i,parent));
        }
        System.out.println(set.size());
        
        
    }
}