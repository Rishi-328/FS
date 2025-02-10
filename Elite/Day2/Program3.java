package Day2;

// You are developing an application for a garden management system where each tree 
// in the garden is represented as a binary tree structure. The system needs to 
// allow users to plant new trees in a systematic way, ensuring that each tree is 
// filled level by level.

// A gardener wants to:
//  - Plant trees based on user input.
//  - Ensure trees grow in a balanced way by filling nodes level by level.
//  - Inspect the garden layout by performing an in-order traversal, which helps 
//    analyze the natural arrangement of trees.

// Your task is to implement a program that:
//     - Accepts a list of N tree species (as integers).
//     - Builds a binary tree using level-order insertion.
//     - Displays the in-order traversal of the tree.

// Input Format:
// -------------
// - An integer N representing the number of tree plants.
// - A space-separated list of N integers representing tree species.

// Output Format:
// --------------
// A list of integers, in-order traversal of tree.


// Sample Input:
// -------------
// 7
// 1 2 3 4 5 6 7

// Sample Output:
// --------------
// 4 2 5 1 6 3 7


// Explanation:
// ------------
// The tree looks like this:

//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// The in order is : 4 2 5 1 6 3 7


import java.util.*;
class tree{
    tree left;
    int data;
    tree right;
    tree(int data){
        this.data = data;
        left=null;
        right=null;
    }
}
public class Program3
{
    static tree root;
    public static void insert(int data,tree temp){
        if(temp==null){
            root = new tree(data);
            return;
        }
        Queue<tree> q = new LinkedList<>();
        q.add(temp);
        while(!q.isEmpty()){
            tree p = q.poll();
            if(p.left==null){
                p.left = new tree(data);
                break;
            }else{
                q.add(p.left);
            }
            if(p.right==null){
                p.right = new tree(data);
                break;
            }else{
                q.add(p.right);
            }
        }
        
        
    }
    public static void inorder(tree root){
        if(root!=null){
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        for(int i=0;i<nums.length;i++){
            insert(nums[i],root);
        }
        inorder(root);
        
    }
}