// Mr NOkayya is AI developer, He is given a dictionary, he want to implement a 
// an Word-Corrector application that checks user-word and corrects it.

// For a given user-word, the Word-Corrector handles two types of validations:

// 1) If the user-word matches a word in the dictionary (case-insensitive), 
// then the user-word is returned with the matched word in the dictionary.
//     Ex-1: dict = ["kmit"], user-word = "KmIt": word-corrector = "kmit"
// 	Ex-2: dict = ["Kmit"], user-word = "kmit": word-corrector = "Kmit"
// 	Ex-3: dict = ["kmit"], user-word = "kmit": word-corrector = "kmit"
	
// 2) If after replacing the vowels of the user-word with any vowel individually,
// it matches a word in the dictionary (case-insensitive), 
// then the user-word is returned with the matched word in the dictionary.
// 	Ex-1: dict = ["KmIt"], user-word = "kmet": word-corrector = "KmIt"
// 	Ex-2: dict = ["KmIt"], user-word = "kmmit": word-corrector = "" (no match)
// 	Ex-3: dict = ["KmIt"], user-word = "kit": word-corrector = "" (no match)

// In addition to the above conditions, the word-corrector app works
// with the following precedence rules:
//  - When the user-word exactly matches a word in the dictionary (case-sensitive), 
//     you should return the same word back.
//  - When the user-word matches a word up to validation-1, 
//     you should return the first such match in the dictionary.
//  - When the user-word matches a word up to validation-2, 
//     you should return the first such match in the dictionary.
//  - If the user-word has no matches in the dictionary, 
//     you should return the empty string.

// You are given some user-words[], return a list of words result[], 
// where result[i] is the corrected words by the Word-Corrector app for 
// user-word = user-words[i].


// Input Format:
// -------------
// Line-1: comma separated strings, dictionary[].
// Line-2: comma separated strings, user-words[].

// Output Format:
// --------------
// List of corrected user-words[] by Word-Corrector app.


// Sample Input-1:
// ---------------
// LiTe,lite,bare,Bare
// lite,Lite,LiTe,Bare,BARE,Bear,bear,leti,leet,leto

// Sample Output-1:
// ----------------
// [lite, LiTe, LiTe, Bare, bare, , , LiTe, , LiTe]


// Sample Input-2:
// ---------------
// kmit,ngit,kmec
// KmOT,NHIT,KMIC

// Sample Output-2:
// ----------------
// [kmit, , kmec]


import java.util.*;
public class WordCorrector{
    public static String getVS(String s){
        String temp = "";
        s = s.toLowerCase();
        for(int j=0;j<s.length();j++){
                char ch = s.charAt(j);
                if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U'){
                    temp += '*';
                }
                else{
                    temp += ch;
                }
        }
        return temp;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] d = sc.nextLine().split(",");
        String[] c = sc.nextLine().split(",");
        Map<String,String> mp = new HashMap<>();
        Set<String> set = new HashSet<>();
        Map<String,String> v = new HashMap<>();
        for(int i=0;i<d.length;i++){
            if(!mp.containsKey(d[i].toLowerCase())){
                mp.put(d[i].toLowerCase(),d[i]);
            }
            set.add(d[i]);
            String temp = getVS(d[i]);
            
            if(!v.containsKey(temp)){
                v.put(temp,d[i]);
            }
            
        }
        List<String> res = new ArrayList<>();
        for(int i=0;i<c.length;i++){
            if(set.contains(c[i])){
                res.add(c[i]);
            }else if(mp.containsKey(c[i].toLowerCase())){
                res.add(mp.get(c[i].toLowerCase()));
            }else if(v.containsKey(getVS(c[i]))){
                res.add(v.get(getVS(c[i])));
            }else{
                res.add("");
            }
        }
        System.out.println(res);
    }
}