package Day19;

// AlphaCipher is a string formed from another string by rearranging its letters

// You are given a string S.
// Your task is to check, can any one of the AlphaCipher is a palindrome or not.

// Input Format:
// -------------
// A string S

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// carrace

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// code

// Sample Output-2:
// ----------------
// false


// import java.util.*;
// public class AlphaCiper{
//     public static boolean getP(Map<Character,Integer> mp){
//         int c = 0;
//         for(char i:mp.keySet()){
//             if(mp.get(i)%2!=0 && c==1){
//                 return false;
//             }
//             if(mp.get(i)%2!=0){
//                 c=1;
//             }
//         }
//         return true;
//     }
//     public static void main(String[] args){
//         Scanner sc = new Scanner(System.in);
//         String s = sc.nextLine();
//         Map<Character,Integer> mp = new HashMap<>();
//         int n = s.length();
//         for(int i=0;i<n;i++){
//             mp.put(s.charAt(i),mp.getOrDefault(s.charAt(i),0)+1);
//         }
//         System.out.println(getP(mp));
//     }
// }


import java.util.*;
public class AlphaCiper{
    public static boolean isPalindrome(String s){
        int c = 0;
        int n = s.length();
        for(int i=0;i<n;i++){
            c ^= s.charAt(i);
        }
        if(c==0)return true;
        for(int i=0;i<n;i++){
            if((char)s.charAt(i)==c)return true;
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(isPalindrome(s));
        
        
    }
}