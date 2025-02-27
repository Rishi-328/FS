// Imagine you’re decoding a secret message that outlines the hierarchical structure 
// of a covert spy network. The message is a string composed of numbers and parentheses. 
// Here’s how the code works:

// - The string always starts with an agent’s identification number, this is the 
//   leader of the network.
// - After the leader’s ID, there can be zero, one, or two segments enclosed in 
//   parentheses. Each segment represents the complete structure of one subordinate 
//   network.
// - If two subordinate networks are present, the one enclosed in the first (leftmost) 
//   pair of parentheses represents the left branch, and the second (rightmost) 
//   represents the right branch.

// Your mission is to reconstruct the entire spy network hierarchy based on this 
// coded message.

// Example 1:
// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//        / \
//       2   6
//      / \  /
//     3   1 5

// Explanation:
// Agent 4 is the leader.
// Agent 2 (with its own subordinates 3 and 1) is the left branch.
// Agent 6 (with subordinate 5) is the right branch.

// Example 2:
// Input: 4(2(3)(1))(6(5)(7))
// Output: [4, 2, 6, 3, 1, 5, 7]

// Spy network:
//          4
//        /   \
//       2     6
//      / \   / \
//     3   1 5   7

// Explanation:
// Agent 4 leads the network.
// Agent 2 with subordinates 3 and 1 forms the left branch.
// Agent 6 with subordinates 5 and 7 forms the right branch.
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
public class SpyNetwork{
    static int i = 0;
    public static tree helper(String s){
        if(i>=s.length()){
            return null;
        }
        if(s.charAt(i)==')'){
            i++;
            return null;
        }
        int temp=i;
        while(temp<s.length() && s.charAt(temp)!='(' && s.charAt(temp)!=')')temp++;
        int num = Integer.parseInt(s.substring(i,temp));
        tree root = new tree(num);
        i=temp;
        if(i<s.length() && s.charAt(i)=='('){
            i++;
            root.left = helper(s);
        }
        if(i<s.length() && s.charAt(i)=='('){
            i++;
            root.right = helper(s);
        }
        if(i<s.length() && s.charAt(i)==')'){
            i++;
        }
        return root;
    }
    public static void preorder(tree root){
        if(root == null)return ;
        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }
    public static void printSolution(tree root){
        Queue<tree> q = new LinkedList<>();
        q.add(root);
        List<Integer> l = new ArrayList<>();
        while(!q.isEmpty()){
            tree node = q.poll();
            l.add(node.data);
            if(node.left!=null){
                q.add(node.left);
            }
            if(node.right!=null){
                q.add(node.right);
            }
        }
        System.out.print(l);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String  s = sc.nextLine();
        tree root = helper(s);
        printSolution(root);
        // preorder(root);
    }
}