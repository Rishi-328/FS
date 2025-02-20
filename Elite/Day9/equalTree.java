// Balbir Singh is working with networked systems, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
// a certain processing load (integer value).

// Balbir has been given a critical task: split the network into two balanced 
// sub-networks by removing only one connection (edge). The total processing 
// load in both resulting sub-networks must be 
// equal after the split.

// Network Structure:
// - The network of servers follows a binary tree structure.
// - Each server is represented by an integer value, indicating its processing load.
// - If a server does not exist at a particular position, it is represented as '-1' (NULL).
	
// Help Balbir Singh determine if it is possible to split the network into two equal 
// processing load sub-networks by removing exactly one connection.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 7

// Sample Output-2:
// ----------------
// false
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
public class equalTree{
    public static tree buildTree(int[] arr,int n){
        if(n==0)return null;
        tree root = new tree(arr[0]);
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        int i = 1;
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
    public static boolean isEqual(tree root,int target){
        if(root==null) return false;
        if(getSum(root)==target) return true;
        return isEqual(root.left,target)||isEqual(root.right,target);
    }
    public static int getSum(tree root){
        if(root==null)return 0;
        if(root.left==null && root.right==null)return root.data;
        int left = getSum(root.left);
        int right = getSum(root.right);
        return root.data+left+right;
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
        int sum = getSum(root);
        // System.out.println(sum);
        if(sum%2!=0){
            System.out.println("false");
        }    
        else{
            System.out.println(isEqual(root,sum/2));
        }    
        
    }
}
