package Day19;

// Birbal is creating a new binary code system BBC (Birbal Binary Code) as follows:

// I	f(I)
// -------
// 0	""
// 1	"0"
// 2	"1"
// 3	"00"
// 4	"01"
// 5	"10"
// 6	"11"
// 7	"000"

// You are 45
// an integer value I, where I is positive number.
// Your task is to find BBC representation of  the given number I.

// Input Format:
// -------------
// An integer I

// Output Format:
// --------------
// Print the BBC representation of I.


// Sample Input-1:
// ---------------
// 23

// Sample Output-1:
// ----------------
// 1000


// Sample Input-2:
// ---------------
// 45

// Sample Output-2:
// ----------------
// 01110

import java.util.*;
public class BinaryCode{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 1,BinCount=0;
        while(count*2-1<=n){
            count *= 2;
            BinCount++;
        }
        String temp = Integer.toBinaryString(n-count+1);
        StringBuilder sb = new StringBuilder();
        if(temp.equals("0")){
            for(int i=0;i<BinCount;i++){
                sb.append("0");
            }
            System.out.println(sb);
        }else{
            int rem = BinCount - temp.length();
            for(int i=0;i<rem;i++){
                sb.append("0");
            }
            sb.append(temp);
            System.out.println(sb);
        }
        
    }
}