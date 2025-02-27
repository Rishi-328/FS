// Imagine you are navigating a maze where each path you take has a section with a 
// code. The maze is structured as a series of interconnected rooms, 
// represented as a tree structure. Each room in the maze has a code (integral value)
// associated with it, and you are trying to check if a given sequence of code 
// matches a valid path from the entrance to an exit. 

// You are provide with the maze structure, where you have to build the maze and then,
// you are provided with a series of space seperated digits, representing a journey 
// starting from the entrance and passing through the rooms along the way. 
// The task is to verify whether the path corresponding to this array of codes 
// exists in the maze.

// Example 1:
// Input:
// 0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
// 0 1 0 1                         //path to verify

// Output: true

// Explanation:
//                0
//              /   \
//             1     0
//            / \    /
//           0   1  0
//            \  / \
//             1 0  0

// The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
// Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.


// Example 2:
// Input:
// 1 2 3
// 1 2 3

// output: false

// Explanation:
// The proposed path 1 → 2 → 3 does not exist in the maze, 
// so it cannot be a valid path.
import java.util.*;
class tree{
    int data;
    tree left;
    tree right;
    tree(int data){
        this.data=data;
        left = null;
        right = null;
    }
}    
public class ValidPath{
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
    public static boolean isPathPossible(tree root,int[] path,int idx){
        if(idx>=path.length){
            return true;
        }    
         
        if(root==null || path[idx]!=root.data){
            return false;
        }
        if(isPathPossible(root.left,path,idx+1) || isPathPossible(root.right,path,idx+1)){
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s1 = sc.nextLine().split(" ");
        String[] s2 = sc.nextLine().split(" ");
        int n = s1.length;
        int m = s2.length;
        int[] arr = new int[n];
        int[] path = new int[m];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(s1[i]);
        }
        for(int i=0;i<m;i++){
            path[i]=Integer.parseInt(s2[i]);
        }
        tree root = buildTree(arr,n);
        System.out.println(isPathPossible(root,path,0));
    }

}
        

