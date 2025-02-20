// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100

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
public class MaxProduct{
    static int maxi = Integer.MIN_VALUE;
    static Set<Integer> set = new HashSet<>();
    public static tree buildTree(int[] arr,int n){
        if(n==0)return null;
        tree root = new tree(arr[0]);
        int i = 1;
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        while(i<n){
            tree node = q.poll();
            if(arr[i]!=-1){
                node.left= new tree(arr[i]);
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
    public static int getSum(tree root){
        if(root== null){
            return 0;
        }
        int left = getSum(root.left);
        int right = getSum(root.right);
        int x = root.data + left+right;
        set.add(x);
        return x;
    }
    public static void  MaxPro(tree root,int sum){
        if(root==null) return;
        int x = getSum(root);
        maxi=Math.max(maxi,(sum-x)*x);
        MaxPro(root.left,sum);
        MaxPro(root.right,sum);
        
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
        // MaxPro(root,sum);
        for(Integer i : set){
            maxi = Math.max(maxi,(sum-i)*i);
        }
        System.out.println(maxi%1000000007);
    }
}