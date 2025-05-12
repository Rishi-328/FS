package Day35.java;

// Captain Raynor is on a mission to decode an alien transmission.
// The transmission contains a single long string S, and it is believed to be 
// made up of multiple unique signal chunks, sent one after another with no spaces.

// Your task is to help Captain Raynor split the transmission into the maximum 
// number of non-empty, unique signal chunks such that when all chunks are 
// concatenated in order, they exactly recreate the original transmission S.

// Your goal is to maximize the number of unique chunks the message can be split into.

// Input Format:
// Line-1: A string S representing the alien transmission.

// Output Format:
// Print a single integer – the maximum number of distinct chunks the string can be split into.

// Sample Input-1:
// ---------------
// banana

// Sample Output-1:
// ----------------
// 4

// Explanation: 
// ------------
// One valid way to split the string is: "b", "a", "n", "ana".
// This keeps all chunks unique.
// Another way like "b", "a", "n", "an", "a" is invalid because "a" appears twice.


// Sample Input-2:
// ---------------
// mississippi

// Sample Output-2:
// ----------------
// 7

// Explanation: 
// ------------
// One valid way to split it is: "m", "i", "s", "si", "ssi", "p", "pi".
// All chunks are distinct and together recreate the original transmission.

// NOTE: Only contiguous chunks (i.e., substrings) are allowed. Subsequence-based 
//       splitting is not permitted.

import java.util.*;
public class MaxSplit{
    public static void backtrack(String s, Set<String> set, int[] ans,int idx,int n){
        if(idx >= n){
            ans[0] = Math.max(ans[0],set.size());
            return;
        }
        for(int i = idx; i<n ; i++){
            String str = s.substring(idx, i+1);
            if(!set.contains(str)){
                set.add(str);
                backtrack(s,set,ans,i+1,n);
                set.remove(str);
            }
            
            
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] ans = new int[1];
        Set<String> set = new HashSet<>();
        int n = s.length();
        backtrack(s,set,ans,0,n);
        System.out.println(ans[0]);
    }
}