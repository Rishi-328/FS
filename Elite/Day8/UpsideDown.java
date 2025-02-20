// VishnuVardan is working with Decision Trees for AI-based predictions.
// To analyze alternative outcomes, Kishore has planned to flip the decision 
// tree horizontally to simulate a reverse processing approach.

// Rules for Flipping the Decision Tree:
// 	- The original root node becomes the new rightmost node.
// 	- The original left child becomes the new root node.
// 	- The original right child becomes the new left child.
// This transformation is applied level by level recursively.

// Note:
// ------
// - Each node in the given tree has either 0 or 2 children.
// - Every right node in the tree has a left sibling sharing the same parent.
// - Every right node has no further children (i.e., they are leaf nodes).

// Your task is to help VishnuVardan flip the Decision Tree while following 
// the given transformation rules.

// Input Format:
// -------------
// Space separated integers, nodes of the tree.

// Output Format:
// --------------
// Print the list of nodes of flipped tree as described below.


// Sample Input-1:
// ---------------
// 4 2 3 5 1

// Sample Output-1:
// ----------------
// 5 1 2 3 4


// Sample Input-2:
// ---------------
// 4 2 5

// Sample Output-2:
// ----------------
// 2 5 4


import java.util.*;
class tree{
    int data;
    tree left,right;
    tree(int data){
        this.data=data;
        left=null;
        right=null;
    }
}
public class UpsideDown{
    public static tree buildTree(int[] arr,int n){
        if(n==0)return null;
        tree root = new tree(arr[0]);
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(i<n){
            tree temp = q.poll();
            if(arr[i]!=-1){
                temp.left = new tree(arr[i]);
                q.add(temp.left);
            }
            i++;
            if(i<n && arr[i]!=-1){
                temp.right = new tree(arr[i]);
                q.add(temp.right);
            }
            i++;
        }
        return root;
    }
    public static void levelOrder(tree root){
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int s = q.size();
            for(int i=0;i<s;i++){
                tree temp = q.poll();
                System.out.print(temp.data+" ");
                if(temp.left!=null){
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    q.add(temp.right);
                }
            }
            
        }
    }
    public static tree upside(tree root){
        if(root==null || root.left==null){
            return root;
        }
        tree newroot = upside(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left=null;
        root.right=null;
        return newroot;
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
        tree newroot = upside(root);
        levelOrder(newroot);
        
    }
}