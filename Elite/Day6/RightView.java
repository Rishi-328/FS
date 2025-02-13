// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]
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
public class RightView{
    static tree root;
    public static void rightSideView(tree root,int level,List<Integer> arr){
        if(root!=null){
            if(level==arr.size() && root.data!=-1){
                arr.add(root.data);
            }
            rightSideView(root.right,level+1,arr);
            rightSideView(root.left,level+1,arr);
        }
    }
    public static void insert(tree temp,int data){
        if(temp==null){
            root = new tree(data);
            return;
        }
        Queue<tree> q = new LinkedList<>();
        q.add(temp);
        while(!q.isEmpty()){
            tree node = q.poll();
            if(node.data!=-1){
                if(node.left==null){
                    node.left = new tree(data);
                    break;
                }else{
                    q.add(node.left);
                }
                if(node.right==null){
                    node.right = new tree(data);
                    break;
                }else{
                    q.add(node.right);
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = str.length;
        for(int i=0;i<n;i++){
            insert(root,Integer.parseInt(str[i]));
        }
        List<Integer> arr = new ArrayList<>();
        rightSideView(root,0,arr);
        System.out.println(arr);
        
    }
        
}
