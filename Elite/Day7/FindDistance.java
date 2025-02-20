// Bubloo is working with computer networks, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) 
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication 
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1' (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2), 
// determine the minimum number of network hops (edges) required to 
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.
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
public class FindDistance{
    public static int findDepth(tree root,int val,int depth){
        if(root==null){
            return 0;
        }
        if(root.data==val){
            return depth;
        }
        int left = findDepth(root.left,val,depth+1);
        int right = findDepth(root.right,val,depth+1);
        return left!=0?left:right;
        
    }
    public static tree getlca(tree root,int p,int q){
        if(root==null || root.data==p || root.data==q){
            return root;
        }
        tree left = getlca(root.left,p,q);
        tree right = getlca(root.right,p,q);
        if(left!=null && right!=null){
            return root;
        }
        return left!=null?left:right;
    }
    public static tree buildTree(int[] arr,int n){
        if(n==0) return null;
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
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int source = sc.nextInt();
        int destination = sc.nextInt();
        int n = str.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(str[i]);
        }
        tree root = buildTree(arr,n);
        tree lca = getlca(root,source,destination);
        System.out.println(findDepth(lca,source,0)+findDepth(lca,destination,0));
    }
}