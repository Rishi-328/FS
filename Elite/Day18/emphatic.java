// "Emphatic Pronunciation" of a given word is where we take the word and
// replicate some of the letter to emphasize their impact.

// Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
// We define emphatic pronunciation of a word, which is derived by replicating 
// a group (or single) of letters in the original word. 

// So that the replicated group is atleast 3 characters or more and 
// greater than or equal to size of original group. 
// For example Good -> Goood is an emphatic pronunciation,
// but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
// In the question you are given the "Emphatic pronunciation" word, 
// you have to findout how many words can legal result in the 
// "emphatic pronunciation" word.

// Input Format:
// -------------
// Line-1 -> A String contains a single word, Emphatic Pronunciation word
// Line-2 -> Space seperated word/s

// Output Format:
// --------------
// Print an integer as your result


// Sample Input-1:
// ---------------
// goood
// good goodd

// Sample Output-1:
// ----------------
// 1


// Sample Input-2:
// ---------------
// heeelllooo
// hello hi helo

// Sample Output-2:
// ----------------
// 2


import java.util.*;
public class emphatic{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String emp = sc.nextLine();
        String[] lis = sc.nextLine().split(" ");
        int n = lis.length;
        int count = 0;
        for(int i=0;i<n;i++){
            int a=0,b=0;
            int x=0,z=0;
            while(a<lis[i].length() && x<emp.length()){
                while(a<lis[i].length()-1 && lis[i].charAt(a)==lis[i].charAt(a+1)){
                    a++;
                }
                while(x<emp.length()-1 && emp.charAt(x)==emp.charAt(x+1)){
                    x++;
                }
                if(a-b+1 > x-z+1 || lis[i].charAt(a)!=emp.charAt(x) || (a-b+1 < x-z+1 && (x-z+1<=2))){
                    break;
                }
                a++;
                x++;
                b=a;
                z=x;
            }
            if(x==emp.length() && a==lis[i].length())count++;
        }
        System.out.println(count);
    }
}
// goood
// good goodd