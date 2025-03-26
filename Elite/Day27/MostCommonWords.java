package Elite.Day27;

// Imagine you’re managing a busy cafe where every order is logged. You have a 
// record—a list of dish names ordered throughout the day—and you want to determine
// which dishes are the most popular. Given an list of strings representing the dish
// names and an integer P, your task is to return the P most frequently ordered dishes.

// The results must be sorted by the number of orders, from the most frequent to 
// the least. If two dishes have been ordered the same number of times, they should
// be listed in alphabetical order.

// Input Format:
// -------------
// Line-1: comma separated line of words, list of words.
// Line-2: An integer P, number of words to display. 

// Output Format:
// --------------
// Print P number of most common used words.

// Example 1:
// ----------
// Input=
// coffee,sandwich,coffee,tea,sandwich,muffin
// 2
// Output=
// [coffee, sandwich]

// Explanation: "coffee" and "sandwich" are the two most frequently ordered items. 
// Although both appear frequently, "coffee" is placed before "sandwich" because 
// it comes earlier alphabetically.

// Example 2:
// ----------
// Input=
// bagel,muffin,scone,bagel,bagel,scone,scone,muffin,muffin
// 3
// Output=
// [bagel, muffin, scone] 

// Explanation: "bagel", "muffin", and "scone" are the three most popular dishes 
// with order frequencies of 3, 3, and 2 respectively. Since "bagel" and "muffin" 
// have the same frequency, they are ordered alphabetically.

// Constraints:

// - 1 ≤ orders.length ≤ 500  
// - 1 ≤ orders[i].length ≤ 10  
// - Each orders[i] consists of lowercase English letters.  
// - P is in the range [1, The number of unique dish names in orders].

import java.util.*;
class TrieNode{
    TrieNode[] children;
    boolean endOfWord = false;
    int count = 0;
    TrieNode(){
        children = new TrieNode[26];
    }
}
class Pair{
    String val;
    int freq;
    Pair(String val,int freq){
        this.val = val;
        this.freq = freq;
    }
}
public class MostCommonWords{
    public static void insert(String s,TrieNode root){
        TrieNode curr = root;
        for(int i=0; i<s.length();i++){
            char ch = s.charAt(i);
            if(curr.children[ch-'a']==null){
                curr.children[ch-'a'] = new TrieNode();
            }
            curr = curr.children[ch-'a'];
        }
        curr.endOfWord = true;
        curr.count++;
        
    }
    public static int search(String s,TrieNode root){
        TrieNode curr = root;
        for(char ch : s.toCharArray()){
            if(curr.children[ch-'a']==null){
                return curr.count;
            }
            curr = curr.children[ch-'a'];
        }
        return curr.count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        int n = sc.nextInt();
        TrieNode root = new TrieNode();
        for(String s : str){
            insert(s,root);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
            if(a.freq == b.freq) return a.val.compareTo(b.val);
            return Integer.compare(b.freq,a.freq);
        });
        for(String s : new HashSet<>(Arrays.asList(str))){
            Pair p = new Pair(s,search(s,root));
            pq.add(p);
        }
        List<String> ans = new ArrayList<>();
        while(n-->0){
            ans.add(pq.peek().val);
            pq.poll();
        }
        System.out.println(ans);
    }
}