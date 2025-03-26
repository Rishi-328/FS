package Day3;

// You are given an integer array nums and two integers l and r. Your task is to 
// analyze the volatility of a sequence of values. The volatility of a sequence is 
// defined as the difference between the maximum and minimum values in that sequence.

// You need to determine the sequence with the highest volatility among all 
// sequences of length between l and r (inclusive).

// Return the highest volatility. If no such sequence exists, return -1.

// Input Format:
// -------------
// Line-1: 3 space separated integers, n, l, r
// Line-2: n space separated integers, nums[].

// Output Format:
// -------------
// An integer, the highest volatility.


// Sample Input-1:
// ---------------
// 5 2 3
// 8 5 1 6 2

// Sample Output-1:
// ----------------
// 7

// Explanation:
// ------------
// The possible sequences of length between l = 2 and r = 3 are:

// [8, 3] with a volatility of 8−3=5
// [3, 1] with a volatility of 3−1=2
// [1, 6] with a volatility of 6−1=5
// [8, 3, 1] with a volatility of 8−1=7
// The sequence [8, 3, 1] has the highest volatility of 7.

// Sample Input-2:
// ---------------
// 4 2 4
// 5 5 5 5

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// All possible sequences have no volatility as the maximum and minimum values 
// are the same, resulting in a difference of 0.
 

import java.util.*;
public class Program3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        Deque<Integer> mini = new ArrayDeque<>();
        Deque<Integer> maxi = new ArrayDeque<>();
        int i = 0, j = 0;
        int result = -1;
        while(i < n){
            while(!mini.isEmpty() && mini.peekLast()>nums[i]) mini.pollLast();
            mini.add(nums[i]);
            while(!maxi.isEmpty() && maxi.peekLast() < nums[i]) maxi.pollLast();
            maxi.add(nums[i]);
            if(i-j+1 >= l && i-j+1 <= r){
                result = Math.max(result,maxi.peekFirst()-mini.peekFirst());
                if(i-j+1==r){
                    if(mini.peekFirst()==nums[j]) mini.pollFirst();
                    if(maxi.peekFirst()==nums[j]) maxi.pollFirst();
                    j++;
                }
            }
            i++;
        }
        System.out.println(result);
    }
}