// You are managing a fleet of exploratory spacecraft, each carrying containers 
// composed of two types of critical resources: 
// fuel units (represented by '0') and oxygen tanks (represented by '1'). 
// You're given a list 'containers', where each container is represented by a 
// binary string indicating its resource composition, 
// along with two integers: 'fuelLimit' (maximum allowed fuel units) and 
// 'oxygenLimit' (maximum allowed oxygen tanks).

// Your goal is to select the largest possible subset of containers such that the 
// total number of fuel units does not exceed 'fuelLimit' and the total number of 
// oxygen tanks does not exceed 'oxygenLimit'.

// Input format:
// -------------
// Line 1: Space seperated strings, represents the container strings
// Line 2: Two space separated integers, represents fuelLimit & oxygenLimit

// Output format:
// --------------
// An integer, largest possible subset of containers.


// Example 1:
// ----------
// Input=
// 10 0001 111001 1 0
// 5 3

// Output=
// 4

// Explanation: The largest subset meeting the constraints is {"10", "0001", "1", "0"}.
// - Total fuel units = 5 (within limit)
// - Total oxygen tanks = 3 (within limit)
// Container "111001" cannot be included as it exceeds the oxygen tank limit.


// Example 2:
// ----------
// Input=
// 10 0 1
// 1 1

// Output=
// 2

// Explanation: The largest subset meeting the constraints is {"0", "1"}.
// - Total fuel units = 1
// - Total oxygen tanks = 1


// Constraints:
// - 1 <= containers.length <= 600
// - 1 <= containers[i].length <= 100
// - 'containers[i]' consists only of digits '0' and '1'.
// - 1 <= fuelLimit, oxygenLimit <= 100

import java.util.*;
public class Subset{
    public static int helper(String[] str,int f,int o,int i,int a,int b){
        if(i==str.length){
            return 0;
        }
        int c = 0;
        for(int k=0;k<str[i].length();k++){
            c += (str[i].charAt(k)-'0');
        }
        if(a+c <= o && b+(str[i].length()-c) <= f){
            return Math.max(1+helper(str,f,o,i+1,a+c,b+(str[i].length()-c)),helper(str,f,o,i+1,a,b));
        }
        return helper(str,f,o,i+1,a,b);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int f = sc.nextInt();
        int o = sc.nextInt();
        System.out.println(helper(str,f,o,0,0,0));
        
    }
}