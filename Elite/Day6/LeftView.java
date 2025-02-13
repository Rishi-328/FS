// A software development company is designing a smart home automation 
// system that uses sensor networks to monitor and control different devices 
// in a house. The sensors are organized in a hierarchical structure, where each 
// sensor node has a unique ID and can have up to two child nodes (left and right).

// The company wants to analyze the left-most sensors in the system to determine
// which ones are critical for detecting environmental changes. The hierarchy of 
// the sensors is provided as a level-order input, where missing sensors are 
// represented as -1.

// Your task is to build the sensor network as a binary tree and then determine 
// the left-most sensor IDs at each level.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the left-most sensor IDs at each level


// Sample Input-1:
// ---------------
// 1 2 3 4 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 2, 4]



// Sample Input-2:
// ---------------
// 3 2 4 1 5

// Sample Output-2:
// ----------------
// [3, 2, 1]

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
public class LeftView{
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
    public static void leftSideView(tree root,List<Integer> ans,int level){
        if(root!=null){
            if(level==ans.size()){
                ans.add(root.data);
            }
            leftSideView(root.left,ans,level+1);
            leftSideView(root.right,ans,level+1);
        }
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
        List<Integer> ans = new ArrayList<>();
        leftSideView(root,ans,0);
        System.out.println(ans);
    }
}