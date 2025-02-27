// Imagine you are designing a network of secret corridors in an ancient castle. 
// Each chamber in the castle leads to at most two other chambers through 
// hidden passageways, forming a branching layout. 
// The castle’s "longest secret route" is defined as the maximum number of corridors 
// you must traverse to get from one chamber to another (without repeating the corridor). 
// This route may or may not pass through the main entry chamber.

// Your task is to compute the length of longest secret route between 
// two chambers which is represented by the number of corridors between them.

// Example 1
// input=
// 1 2 3 4 5 
// output=
// 3

// Structure:
//        1
//       / \
//      2   3
//     / \
//    4   5

// Explanation:
// The longest secret route from chamber 4 to chamber 3 
// (alternatively, from chamber 5 to chamber 3) along the route:
// 4 → 2 → 1 → 3
// This path has 3 corridors (4–2, 2–1, 1–3), so the length is 3.

// Example 2:
// input=
// 1 -1 2 3 4
// output=
// 2

// Structure:
//    1
//     \
//      2
//     / \
//    3   4

// Explanation:
// The longest secret route from chamber 3 to chamber 4 
// (alternatively, from chamber 1 to chamber 4) along the route:
// 3 → 2 → 4
// This path has 2 corridors (3–2, 2–4), so the length is 2.


import java.util.*;
class tree{
    tree left,right;
    int data;
    tree(int data){
        this.data=data;
        left=null;
        right=null;
    }
}
public class SecretRoute{
    static int maxi = 0;
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
    public static int getMaximum(tree root){
        if(root==null)return 0;
        if(root.left==null && root.right==null)return 1;
        int left = getMaximum(root.left);
        int right = getMaximum(root.right);
        maxi = Math.max(maxi,left+right);
        return 1 + Math.max(left,right);
    }
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = str.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(str[i]);
        }
        tree root = buildTree(arr,n);
        getMaximum(root);
        System.out.println(maxi);
    }
}