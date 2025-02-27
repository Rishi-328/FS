// Imagine a company where each employee has a performance score, and 
// the organizational chart is structured as a binary tree with the CEO at the top. 
// An employee is considered "outstanding" if, along the chain of command from the 
// CEO down to that employee, no one has a performance score higher than that 
// employee's score. Your task is to determine the total number of outstanding 
// employees in the company.

// Example 1:
// Input: 3 1 4 3 -1 1 5
// Output: 4

// Chart formed:
//          3
//         / \
//        1   4
//       /   / \
//      3   1   5

// Explanation:
// - The CEO (score 3) is automatically outstanding.
// - The employee with score 4, whose chain is [3,4], is outstanding because 4 
//   is higher than 3.
// - The employee with score 5, following the path [3,4,5], is outstanding as 5 
//   is the highest so far.
// - The subordinate with score 3, along the path [3,1,3], is outstanding because 
//   none of the managers in that chain have a score exceeding 3.

// Example 2:
// Input: 3 3 -1 4 2
// Output: 3

// Chart formed:
//        3
//       / 
//      3
//     / \
//    4   2

// Explanation:
// - The CEO (score 3) is outstanding.
// - The subordinate with score 3 (chain: [3,3]) is outstanding.
// - The employee with score 2 (chain: [3,3,2]) is not outstanding because the 
//   managers have higher scores.

import java.util.*;
class tree{
    int data;
    tree left;
    tree right;
    tree(int data){
        this.data=data;
        left=null;
        right=null;
    }
}
public class OutStanding{
    public static tree buildTree(int[] arr,int n){
        if(n==0)return null;
        tree root = new tree(arr[0]);
        int i=1;
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        while(i<n){
            tree node = q.poll();
            if(arr[i]!=-1){
                node.left = new tree(arr[i]);
                q.add(node.left);
            }
            i++;
            if(i<n && arr[i]!=-1){
                node.right = new tree(arr[i]);
                q.add(node.right);
                
            }
            i++;
        }
        return root;
    }
    public static void helper(tree root,int[] count,int[] maxi){
        if(root==null)return;
        if(root.data>=maxi[0])count[0]++;
        maxi[0] = Math.max(maxi[0],root.data);
        helper(root.left,count,maxi);
        helper(root.right,count,maxi);
    }
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
            String[] sr = sc.nextLine().split(" ");
            int n = sr.length;
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i]=Integer.parseInt(sr[i]);
            }
            tree root = buildTree(arr,n);
            int[] count = new int[1];
            helper(root,count,new int[]{root.data});
            System.out.println(count[0]);
            
        }
    
}