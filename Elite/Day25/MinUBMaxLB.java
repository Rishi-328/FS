package Elite.Day25;

// Imagine you are the curator of a historic library, where books are arranged in a 
// unique catalog system based on their publication years. The library’s archive is 
// structured like a hierarchical tree, with each book’s publication year stored at 
// a node. You are given the nodes of this catalog tree starting with main node
// and a list of query years.

// For each query year, you need to find two publication years:
// - The first is the latest year in the archive that is less than or equal to the 
//   query year. If no such book exists, use -1.
// - The second is the earliest year in the archive that is greater than or equal 
//   to the query year. If no such book exists, use -1.

// Display the results as an list of pairs, where each pair corresponds to a query.

// Example 1:
// ----------
// Input: 
// 2006 2002 2013 2001 2004 2009 2015 2014
// 2002 2005 2016

// Output:
// [[2002, 2002], [2004, 2006], [2015, -1]] 


// Archive Structure:
//           2006
//          /    \
//      2002     2013
//      /   \     /   \
//   2001  2004  2009  2015
//                      /
//                   2014
                  
// Explanation:  
// - For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
//   the earliest publication year that is ≥ 2002 is also 2002.  
// - For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
//   the earliest publication year that is ≥ 2005 is 2006.  
// - For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
//   there is no publication year ≥ 2016, so we output -1 for the second value.

// Example 2:
// ----------
// Input:  
// 2004 2009
// 2003

// Output:
// [[-1, 2004]]

// Explanation:  
// - For the query 2003, there is no publication year ≤ 2003, while the earliest 
//   publication year that is ≥ 2003 is 2004.

// Constraints:
// - The total number of books in the archive is in the range [2, 10^5].
// - Each publication year is between 1 and 10^6.
// - The number of queries n is in the range [1, 10^5].
// - Each query year is between 1 and 10^6.

import java.util.*;
class tree{
    int data;
    tree left;
    tree right;
    tree(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
public class MinUBMaxLB{
    static tree root = null;
    public static void helper(tree root,int target,int[] a,int[] b){
        if(root == null)return;
        if(root.data == target){
            a[0] = root.data;
            b[0] = root.data;
            return;
        }
        if(root.data > target){
            b[0] = Math.min(b[0],root.data);
            helper(root.left,target,a,b);
        }else{
            a[0] = Math.max(a[0],root.data);
            helper(root.right,target,a,b);
        }
    }
    public static tree buildTree(int ele, tree root){
        if(root == null){
            return new tree(ele);
        }
        if(ele < root.data){
            root.left = buildTree(ele,root.left);
        }else{
            root.right = buildTree(ele,root.right);
        }
        return root;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        String[] query = sc.nextLine().split(" ");
        int n = str.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(str[i]);
        }
        for(int i : arr){
            root = buildTree(i,root);
        }
        int[] q = new int[query.length];
        for(int i=0;i<query.length;i++){
            q[i] = Integer.parseInt(query[i]);
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i : q){
            int[] a = {Integer.MIN_VALUE};
            int[] b = {Integer.MAX_VALUE};
            helper(root,i,a,b);
            
            result.add(new ArrayList<>(Arrays.asList(a[0]==Integer.MIN_VALUE?-1:a[0],b[0]==Integer.MAX_VALUE?-1:b[0])));
        }
        System.out.println(result);
        
        
    }
}