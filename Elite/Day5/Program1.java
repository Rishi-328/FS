package Day5;

// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a low7evel-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7

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

public class Program1{
    public static tree buildTree(int[] inorder,int is,int ie,int[] postorder,int ps,int pe,HashMap<Integer,Integer> mp){
        if(ps>pe || is>ie) return null;
        tree root = new tree(postorder[pe]);
        int rootIndex = mp.get(postorder[pe]);
        int leftLen = rootIndex - is;
        root.left = buildTree(inorder,is,rootIndex-1,postorder,ps,ps+leftLen-1,mp);
        root.right = buildTree(inorder,rootIndex+1,ie,postorder,ps+leftLen,pe-1,mp);
        return root;
        
    }
    public static List<List<Integer>> levelOrderTraversal(tree root){
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> answer = new ArrayList<>();
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
            answer.add(temp);
        }
        return answer;
        
    }
    public static void printLevels(List<List<Integer>> lot,int q,int[][] queries){
        for(int i=0;i<q;i++){
            List<Integer> level = new ArrayList<>();
            for(int j=queries[i][0]-1;j<queries[i][1];j++){
                for(Integer k : lot.get(j)){
                    level.add(k);
                }
            }
            System.out.println(level);
            
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] postorder = new int[n];
        for(int i=0;i<n;i++){
            inorder[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            postorder[i]=sc.nextInt();
        }
        int query = sc.nextInt();
        int[][] queries = new int[query][2];
        for(int i=0;i<query;i++){
            queries[i][0]=sc.nextInt();
            queries[i][1]=sc.nextInt();
        }
        HashMap<Integer,Integer> mp = new HashMap<>();
        for(int i=0;i<n;i++){
            mp.put(inorder[i],i);
        }
        tree root = buildTree(inorder,0,n-1,postorder,0,n-1,mp);
        List<List<Integer>> lot = levelOrderTraversal(root);
        printLevels(lot,query,queries);
        
    }
}