// Coach Arjun is training a cricket team and is experimenting with a new fitness 
// evaluation drill. He provided the fitness scores of N players in the team. As 
// part of the drill, he asked the team manager to keep track and perform these 
// two specific operations on the players' fitness scores:

// 1. bestFitness(start, end) - Report the maximum fitness score among the players 
//    whose jersey numbers lie between the positions start and end inclusive.
// 2. improveFitness(index, newScore) - Update the fitness score of the player at 
//    the specific index position with a new fitness score newScore.

// Your task is to efficiently handle these requests by using a Segment Tree data structure.

// Input Format:  
// -------------
// Line-1: Two integers N and Q, representing the number of players and the total 
//         number of queries respectively.
// Line-2: N space-separated integers representing the initial fitness scores of 
//         the players.
// The next Q lines: Each line contains three integers: 
//     - First integer (option) specifies the type of query:
//       - If option = 1, the next two integers (start, end) represent the range 
//         of jersey numbers (inclusive) for which to report the maximum fitness.
//       - If option = 2, the next two integers (index, newScore) represent the 
//         player's index to update and their new fitness score.

// Output Format:  
// --------------
// - Output an integer value for every bestFitness query, representing the maximum 
//   fitness score within the specified range.

// Sample Input:  
// -------------

// 8 5
// 1 2 13 4 25 16 17 28
// 1 2 6        //bestFitness
// 1 0 7        //bestFitness
// 2 2 18       //improveFitness
// 2 4 36       //improveFitness
// 1 2 7        //bestFitness


// Sample Output:  
// --------------
// 25
// 28
// 36

import java.util.*;
public class SegmentTree{
    public static void build(int[] segment,int ind,int low, int high,int[] arr){
        if(low == high){
            segment[ind] = arr[low];
            return;
        }
        int mid = (low+high)/2;
        build(segment,2*ind+1,low,mid,arr);
        build(segment,2*ind+2,mid+1,high,arr);
        segment[ind] = Math.max(segment[2*ind+1],segment[2*ind+2]);
    }
    public static int query(int[] segment,int ind,int low,int high, int l, int r){
        if(l<=low && high<=r){
            return segment[ind];
        }
        if(r<low || high<l){
            return Integer.MIN_VALUE;
        }
        int mid = (low + high)/2;
        int left = query(segment,2*ind+1,low,mid,l,r);
        int right = query(segment,2*ind+2,mid+1,high,l,r);
        return Math.max(left,right);
    }
    public static void update(int[] segment,int i,int val,int ind,int low,int high){
        if(low==high){
            segment[ind] = val;
            return;
        }
        int mid = (low + high)/2;
        if(i<=mid){
            update(segment,i,val,2*ind+1,low,mid);
        }else{
            update(segment,i,val,2*ind+2,mid+1,high);
        }
        segment[ind] = Math.max(segment[2*ind+1],segment[2*ind+2]);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nq = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int[][] query = new int[nq][3];
        for(int i=0; i<nq; i++){
            query[i][0] = sc.nextInt();
            query[i][1] = sc.nextInt();
            query[i][2] = sc.nextInt();
        }
        int[] segment = new int[4*n];
        build(segment,0,0,n-1,arr);
        for(int[] q : query){
            if(q[0]==1){
                System.out.println(query(segment,0,0,n-1,q[1],q[2]));
            }else{
                update(segment,q[1],q[2],0,0,n-1);
            }
        }
        
    }
}