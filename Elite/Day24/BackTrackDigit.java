package Elite.Day24;
// Imagine you're a top-secret agent receiving an encrypted directive from headquarters. The message comes as a string of digits, and each digit (from 2 to 9) is a cipher for a set of potential code letters. To uncover the true instruction, you must translate the string into every possible combination of letters by substituting each digit with its corresponding set of letters. The final decoded messages listed in lexicographycal order.

// Below is the mapping of digits to letters (as found on a traditional telephone keypad):

// | Digit | Letters       |
// |-------|---------------|
// | 2     | a, b, c       |
// | 3     | d, e, f       |
// | 4     | g, h, i       |
// | 5     | j, k, l       |
// | 6     | m, n, o       |
// | 7     | p, q, r, s    |
// | 8     | t, u, v       |
// | 9     | w, x, y, z    |

// Note: The digit 1 does not correspond to any letters.

// Example 1:
// Input:233  
// Output: [ad, ae, af, bd, be, bf, cd, ce, cf]

// Example 2:
// Input: 2 
// Output: [a, b, c]


// Constraints:

// - 0 <= digits.length <= 4  
// - Each digit in the input is between '2' and '9'.

import java.util.*;
public class BackTrackDigit{
    public static void helper(StringBuilder temp,List<String> ans,Map<Integer,List<Character>> mp,int i,int len,String n){
        if(temp.length()==len){
            ans.add(temp.toString());
            return;
        }
        for(Character ch : mp.get(n.charAt(i)-'0')){
            temp.append(ch);
            helper(temp,ans,mp,i+1,len,n);
            temp.deleteCharAt(temp.length()-1);
            
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        Map<Integer,List<Character>> mp = new HashMap<>();
        mp.put(2,new ArrayList<>(Arrays.asList('a','b','c')));
        mp.put(3,new ArrayList<>(Arrays.asList('d','e','f')));
        mp.put(4,new ArrayList<>(Arrays.asList('g','h','i')));
        mp.put(5,new ArrayList<>(Arrays.asList('j','k','l')));
        mp.put(6,new ArrayList<>(Arrays.asList('m','n','o')));
        mp.put(7,new ArrayList<>(Arrays.asList('p','q','r','s')));
        mp.put(8,new ArrayList<>(Arrays.asList('t','u','v')));
        mp.put(9,new ArrayList<>(Arrays.asList('w','x','y','z')));
        List<String> ans = new ArrayList<>();
        int len = n.length();
        StringBuilder temp = new StringBuilder();
        helper(temp,ans,mp,0,len,n);
        System.out.println(ans);
    }
}
