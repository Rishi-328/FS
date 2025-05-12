package Day34;

// Bablu is working in a construction field.
// He has N number of building blocks, where the height and width of all the blocks are same.
// And the length of each block is given in an array, blocks[].

// Bablu is planned to build a wall in the form of a square.
// The rules to cunstruct the wall are as follows:
// 	- He should use all the building blocks.
// 	- He should not break any building block, but you can attach them with other.
// 	- Each building-block must be used only once.
	
// Your task is to check whether Bablu can cunstruct the wall as a square
// with the given rules or not. If possible, print true. Otherwise, print false.

// Input Format:
// -------------
// Line-1: An integer N, number of BuildingBlocks.
// Line-2: N space separated integers, length of each block.

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 6
// 1 2 6 4 5 6

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 6
// 5 3 2 5 5 6

// Sample Output-2:
// ----------------
// false
import java.util.*;
public class Square{
    static int count = 0;
    public static boolean backtrack(int sum,int[] nums,int temp,int[] visi){
        if(temp>sum){
            return false;
        }
        if(sum==temp){
            count++;
            temp=0;
        }
        if(count==4){
            for(int x : visi){
                if(visi[x]==0)return false;
            }
            return true;
            
        }
        for(int i=0; i<nums.length; i++){
            if(visi[i]==0){
                visi[i]=1;
                if(backtrack(sum,nums,temp+nums[i],visi)){
                    return true;
                }
                visi[i]=0;
            }
        }
        return false;
    }
    public static boolean helper(int sum,int[] nums){
        if(sum%4!=0)return false;
        int temp = sum/4;
        int[] visi = new int[nums.length];
        return backtrack(temp,nums,0,visi);
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        int sum = 0;
        for(int i=0;i<n;i++){
            sum += nums[i];
        }
        System.out.println(helper(sum,nums));
    }
}
