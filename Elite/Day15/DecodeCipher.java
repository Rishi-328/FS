// In the world of secret codes and cryptography, you are entrusted with deciphering a hidden message. 
// You have two encoded keys, key1 and key2, both of equal length. Each character 
// in key1 is paired with the corresponding character in key2. 

// This relationship follows the standard rules of an equivalence cipher:
// • Self-Mapping: Every character inherently maps to itself.  
// • Mutual Mapping: If a character from key1 maps to one in key2, then that 
//   character in key2 maps back to the one in key1.  
// • Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
//   are all interchangeable in this cipher.

// Using this mapping, you must decode a cipherText, by replacing every character 
// with the smallest equivalent character from its equivalence group. 
// The result should be the relatively smallest possible decoded message.


// Input Format:
// -------------
// Three space separated strings, key1 , key2 and cipherText

// Output Format:
// --------------k
// Print a string, decoded message which is relatively smallest string of cipherText.

// Example 1: 
// input=
// attitude progress apriori
// output=
// aaogoog


// Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
// [d, e, s]. By substituting each character in cipherText with the smallest from 
// its group, you decode the message to "aaogoog".


// Constraints:  
// • 1 <= key1.length, key2.length, cipherText.length <= 1000  
// • key1.length == key2.length  
// • key1, key2, and cipherText consist solely of lowercase English letters.

import java.util.*;
public class DecodeCipher{
    public static int findParent(int i,int[] parent){
        if(i==parent[i]){
            return i;
        }
        return parent[i] = findParent(parent[i],parent);
    }
    public static void union(char c1, char c2,int[] parent){
        int a = findParent(c1-'a',parent);
        int b = findParent(c2-'a',parent);
        if(a==b){
            return;
        }
        if(a<b){
            parent[b]=a;
        }else{
            parent[a]=b;
        }
        
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = str[0].length();
        int[] parent = new int[26];
        for(int i=0;i<26;i++)parent[i]=i;
        for(int i=0;i<n;i++){
            union(str[0].charAt(i),str[1].charAt(i),parent);
        }
        String cipher = str[2];
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<cipher.length();i++){
            int c = findParent(cipher.charAt(i)-'a',parent);
            sb.append((char)(c+97));
            
        }
        System.out.println(sb);
    }
}