// A shipping company is managing two cargo ships: a large cargo ship and 
// a smaller cargo ship. The company has divided the large cargo ship into 
// X compartments and the smaller cargo ship into Y compartments, where X > Y.

// Each compartment in both ships is loaded with a specific amount of cargo. 
// The company needs to relocate cargo from the large cargo ship to the smaller 
// cargo ship by selecting Y compartments from the large ship and transferring 
// their cargo to Y compartments in the smaller ship, maintaining the respective order.

// However, due to weight balance regulations, the amount in compartment n+1 
// should always be greater than or equal to that in the compartment n of the smaller 
// cargo ship, after the transferred from the large cargo ship.

// Your task is to help the company determine the number of ways they can transfer 
// the cargo while satisfying these regulations.

// Input Format:
// -------------
// Number of compartments in the large cargo ship (X).
// Number of compartments in the smaller cargo ship (Y).
// Cargo weights in compartments of the large cargo ship.
// Cargo weights in compartments of the smaller cargo ship.

// Output Format:
// ----------------
// Return the number of valid ways to transfer the cargo.


// Sample Input:
// --------------
// 5
// 3
// 1 5 2 4 7
// 7 8 6

// Sample Output:
// ----------------
// 4

// Explanation:
// -----------
// The compartments from the large cargo ship can be selected as:
// - (1, 2, 7)
// - (1, 4, 7)
// - (5, 4, 7)
// - (2, 4, 7)  
// Thus, there are 4 valid ways to transfer the cargo.

// Sample Input:
// --------------
// 4
// 2
// 7 7 7 7
// 3 4

// Sample Output:
// ----------------
// 6

// Explanation:
// -----------
// The compartments from the large cargo ship can be selected as:
// - (1st, 2nd) (7,7)
// - (1st, 3rd) (7,7)
// - (1st, 4th) (7,7)
// - (2nd, 3rd) (7,7)
// - (2nd, 4th) (7,7)
// - (3rd, 4th) (7,7)  

// Thus, there are 6 valid ways to transfer the cargo.


import java.util.*;
public class NumberOfWays
{
    static int count = 0;
    public static void backtrack(int[] large,int[] small,int x,int y,int idx,List<Integer> temp){
        if(temp.size()== y){
            count++;
            return;
        }
        for(int i = idx ; i < x; i++){
            if(temp.size()==0 || temp.get(temp.size()-1)<=(large[i]+small[temp.size()])){
                temp.add(large[i]+small[temp.size()]);
                backtrack(large,small,x,y,i+1,temp);
                temp.remove(temp.size()-1);
            }
        }
    }
    public static void chBacktrack(int[] large,int[] small,int i,int j, int sum,int x,int y,int[] ans){
        if(j>=y){
            ans[0]++;
            return;
        }
        if(i>=x)return;
        if(large[i]+small[j]>=sum){
            chBacktrack(large,small,i+1,j+1,large[i]+small[j],x,y,ans);
        }
        chBacktrack(large,small,i+1,j,sum,x,y,ans);
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[] large = new int[x];
        int[] small = new int[y];
        for(int i=0;i<x;i++){
            large[i]=sc.nextInt();
        }
        for(int i=0;i<y;i++){
            small[i]=sc.nextInt();
        }
        List<Integer> temp = new ArrayList<>();
        backtrack(large,small,x,y,0,temp);
        // System.out.println(count);
        int[] ans = new int[1];
        chBacktrack(large,small,0,0,0,x,y,ans);
        System.out.println(ans[0]);
    }
}

