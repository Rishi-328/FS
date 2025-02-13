// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3


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

public class Program1{
    static int n;
    public static tree buildTree(int[] preorder,int preStart,int preEnd,int[] postorder,int postStart,int postEnd,Map<Integer,Integer> mp){
        if(preStart>preEnd || postStart>postEnd) return null;
        tree root = new tree(preorder[preStart]);
        if(preStart==preEnd && postStart==postEnd) return root;
        int leftIndex = mp.get(preorder[preStart+1]);
        int numLength = leftIndex - postStart + 1;
        root.left = buildTree(preorder,preStart+1,preStart+numLength,postorder,postStart,leftIndex,mp);
        root.right = buildTree(preorder,preStart+numLength+1,preEnd,postorder,leftIndex+1,postEnd-1,mp);
        return root;
    }
    public static void printSolution(tree root){
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> ans = new ArrayList<>();
        while(!q.isEmpty()){
            int size = q.size();
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
            ans.add(temp);
            
            
        }
        System.out.println(ans);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] pre = sc.nextLine().split(" ");
        String[] post = sc.nextLine().split(" ");
        n = pre.length;
        int[] preorder = new int[n];
        int[] postorder = new int[n];
        for(int i=0;i<n;i++){
            preorder[i]=Integer.parseInt(pre[i]);
            postorder[i]=Integer.parseInt(post[i]);
        }
        Map<Integer,Integer> mp = new HashMap<>();
        for(int i=0;i<n;i++){
            mp.put(postorder[i],i);
        }
        tree root = buildTree(preorder,0,n-1,postorder,0,n-1,mp);
        printSolution(root);
        
    }
}