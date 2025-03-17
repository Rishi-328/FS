// You are a database integrity engineer working for a global cloud company. 
// Your team maintains a distributed database network, where each server either:
//     - Stores equivalent data to another server (serverX == serverY).
//     - Stores different data from another server (serverX != serverY).

// The transitive consistency rule must be followed:
//     - If A == B and B == C, then A == C must be true.
//     - If A == B and B != C, then A != C must be true.

// Your task is to analyze the given constraints and determine whether they 
// follow transitive consistency. If all relations are consistent, return true; 
// otherwise, return false

// Input Format:
// -------------
// Space separated strnigs, list of relations

// Output Format:
// --------------
// Print a boolean value, whether transitive law is obeyed or not.


// Sample Input-1:
// ---------------
// a==b c==d c!=e e==f

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// a==b b!=c c==a

// Sample Output-2:
// ----------------
// false

// Explanation:
// ------------
// {a, b} form one equivalence group.
// {c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
// However, b != c contradicts b == a and c == a.

// Sample Input-3:
// ---------------
// a==b b==c c!=d d!=e f==g g!=d

// Sample Output-3:
// ----------------
// true


import java.util.*;
public class Transitive{
    public static int findParent(int i,int[] parent){
        if(i==parent[i]){
            return i;
        }
        return parent[i]=findParent(parent[i],parent);
    }
    public static void union(char c1,char c2,int[] parent){
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
    public static boolean checkTrans(String[] s,int n,int[] parent){
        for(int i=0;i<n;i++){
            if(s[i].charAt(1)=='!'){
                char c1 = s[i].charAt(0);
                char c2 = s[i].charAt(3);
                if(findParent(c1-'a',parent)==findParent(c2-'a',parent)){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = str.length;
        int[] parent = new int[26];
        for(int i=0;i<26;i++)parent[i]=i;
        for(int i=0;i<n;i++){
            if(str[i].charAt(1)=='='){
                union(str[i].charAt(0),str[i].charAt(3),parent);
            }
        }
        System.out.println(checkTrans(str,n,parent));
        
    }
}