package Day5;

// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)


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
public class Program2{
    public static tree buildTree(int[] inorder,int is,int ie,int[] preorder,int ps,int pe,Map<Integer,Integer> mp){
        if(ps>pe || is>ie) return null;
        tree root = new tree(preorder[ps]);
        int rootIndex = mp.get(preorder[ps]);
        int numLen = rootIndex - is;
        root.left = buildTree(inorder,is,rootIndex-1,preorder,ps+1,ps+numLen,mp);
        root.right = buildTree(inorder,rootIndex+1,ie,preorder,ps+numLen+1,pe,mp);
        return root;
    }
    public static List<List<Integer>> levelOrder(tree root){
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> ans = new ArrayList<>();
        int level=0;
        while(!q.isEmpty()){
            int size = q.size();
            level++;
            List<Integer> temp = new ArrayList<>();
            for(int i=0;i<size;i++){
                tree node = q.poll();
                temp.add(node.data);
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            if(level%2==0) Collections.reverse(temp);
            ans.add(temp);
        }
        return ans;
    }
    public static void printSolution(List<List<Integer>> lot,int lower,int higher){
        List<Integer> level = new ArrayList<>();
        for(int i=lower-1;i<higher;i++){
            // if((i+1)%2==0){
            //     Collections.reverse(lot.get(i));
            // }
            for(Integer k : lot.get(i)){
                level.add(k);
            }
        }
        System.out.println(level);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] preorder = new int[n];
        for(int i=0;i<n;i++){
            inorder[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            preorder[i]=sc.nextInt();
        }
        int lower = sc.nextInt();
        int higher = sc.nextInt();
        Map<Integer,Integer> mp = new HashMap<>();
        for(int i=0;i<n;i++) mp.put(inorder[i],i);
        tree root = buildTree(inorder,0,n-1,preorder,0,n-1,mp);
        List<List<Integer>> lot = levelOrder(root);
        printSolution(lot,lower,higher);
    }
}