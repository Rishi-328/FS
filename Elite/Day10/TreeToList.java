// You are a gardener designing a beautiful floral pathway in a vast botanical 
// garden. The garden is currently overgrown with plants, trees, and bushes 
// arranged in a complex branching structure, much like a binary tree. Your task 
// is to carefully prune and rearrange the plants to form a single-file walking 
// path that visitors can follow effortlessly.

// To accomplish this, you must flatten the garden’s layout into a linear sequence 
// while following these rules:
//     1. The garden path should maintain the same PlantNode structure, 
//        where the right branch connects to the next plant in the sequence, 
//        and the left branch is always trimmed (set to null).
//     2. The plants in the final garden path should follow the same arrangement 
//        as a pre-order traversal of the original garden layout. 

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print the list.


// Sample Input:
// -------------
// 1 2 5 3 4 -1 6

// Sample Output:
// --------------
// 1 2 3 4 5 6


// Explanation:
// ------------
// input structure:
//        1
//       / \
//      2   5
//     / \    \
//    3   4    6
   
// output structure:
// 	1
// 	 \
// 	  2
// 	   \
// 		3
// 		 \
// 		  4
// 		   \
// 			5
// 			 \
// 			  6
import java.util.*;
class tree{
    tree left;
    tree right;
    int data;
    tree(int data){
        this.data=data;
        left=null;
        right=null;
    }
}
public class TreeToList{
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
    public static void print(tree root){
        if(root==null)return;
        System.out.print(root.data+" ");
        print(root.right);
    }
    public static tree getright(tree root){
        while(root!=null && root.right!=null){
            root = root.right;
        }
        return root;
    }
    public static void treeList(tree root){
        tree node = root;
        while(node!=null){
            tree temp = node.left;
            if(temp!=null){
                while(temp.right!=null){
                    temp = temp.right;
                }
                temp.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
            
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = str.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(str[i]);
        }
        tree root = buildTree(arr,n);
        treeList(root);
        print(root);
        
    }
}