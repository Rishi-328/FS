package Day5;

// Construct Tree from the given Level-order and In-order.
// Compute the Depth and Sum of the Deepest nodes in the Binary tree

// Input Format:
// -------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the level-order traversal.

// Output Format:
// --------------
// Print two values:
// ->The maximum number of levels.
// ->The sum of all node values at the deepest level.

// Example:
// -------------
// Input:
// 11
// 7 8 4 2 5 9 11 10 1 6 3
// 1 2 3 4 5 6 7 9 8 10 11

// Output:
// 6 11

// Explanation:
// The binary tree structure:
//            1
//          /   \
//        2       3
//       / \     /
//      4   5   6
//     /     \   
//    7       9
//     \       \
//      8      10
//             /
//           11
// Maximum Depth: 6
// nodes at the Deepest Level (6): 11
// Sum of nodes at Level 6: 11

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
public class Program3{
    public static tree buildTree(int[] inorder,int is,int ie,Map<Integer,Integer> mp){
        if(is>ie)return null;
        int index = findMinimum(inorder,is,ie,mp);
        tree root = new tree(inorder[index]);
        if(is==ie) return root;
        root.left = buildTree(inorder,is,index-1,mp);
        root.right = buildTree(inorder,index+1,ie,mp);
        return root;
    }
    public static int findMinimum(int[] inorder,int low,int high,Map<Integer,Integer> mp){
        int mini = Integer.MAX_VALUE;
        int index=0;
        for(int i=low;i<=high;i++){
            if(mini > mp.get(inorder[i])){
                mini = mp.get(inorder[i]);
                index = i;
            }
        }
        return index;
        
    }
    public static void levelOrderTraversal(tree root){
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        List<Integer> level  = new ArrayList<>();
        while(!q.isEmpty()){
            int size = q.size();
            int sum=0;
            for(int i=0;i<size;i++){
                tree node = q.poll();
                sum += node.data;
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            level.add(sum);
        }
        System.out.println(level.size()+" "+level.get(level.size()-1));
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] levelOrder = new int[n];
        for(int i=0;i<n;i++){
            inorder[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            levelOrder[i]=sc.nextInt();
        }
        Map<Integer,Integer> mp = new HashMap<>();
        for(int i=0;i<n;i++){
            mp.put(levelOrder[i],i);
        }
        tree root = buildTree(inorder,0,n-1,mp);
        levelOrderTraversal(root);
        
    }
    
}