// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and row 
// number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Sample Input:
// -------------
// 3 9 20 -1 -1 15 7

// Sample Output:
// --------------
// [[9],[3,15],[20],[7]]

// Explanation:
// ------------
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Sample Input-2:
// ---------------
// 3 9 8 4 0 1 7

// Sample Output-2:
// ----------------
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
// ------------

//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]

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
class pair{
    int a,b;
    tree node;
    pair(tree node,int a,int b){
        this.a=a;
        this.b=b;
        this.node=node;
    }
}
public class verticalTraversal{
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
    public static List<List<Integer>> verticalTra(tree root){
        Queue<pair> q = new LinkedList<>();
        TreeMap<Integer,List<Integer>> mp = new TreeMap<>();
        q.add(new pair(root,0,0));
        while(!q.isEmpty()){
            pair temp = q.poll();
            tree node = temp.node;
            int level = temp.a;
            int ver = temp.b;
            if(!mp.containsKey(ver)){
                mp.put(ver,new ArrayList<>());
            }
            mp.get(ver).add(node.data);
            if(node.left!=null){
                q.add(new pair(node.left,level+1,ver-1));
            }
            if(node.right!=null){
                q.add(new pair(node.right,level+1,ver+1));
            }
            
        }
        List<List<Integer>> result = new ArrayList<>();
        for(Integer i : mp.keySet()){
            List<Integer> ans = new ArrayList<>(mp.get(i));
            result.add(ans);
        }
        return result;
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
        System.out.println(verticalTra(root));
    }
}