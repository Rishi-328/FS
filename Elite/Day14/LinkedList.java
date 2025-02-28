// Cliff Shaw is working on the singly linked list.
// He is given a list of boxes arranged as singly linked list,
// where each box is printed a positive number on it.

// Your task is to help Mr Cliff to find the given list is equivalent to 
// the reverse of it or not. If yes, print "true", otherwise print "false"

// Input Format:
// -------------
// Line-1: space separated integers, boxes as list.

// Output Format:
// --------------
// Print a boolean a value.


// Sample Input-1:
// ---------------
// 3 6 2 6 3

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 6 2 3 6

// Sample Output-2:
// ----------------
// false

import java.util.*;
class node{
    int data;
    node next;
    node(int data){
        this.data=data;
        next=null;
    }
}
public class LinkedList{
    public static node buildLinkedList(int[] arr,int n){
        if(n==0)return null;
        node root = new node(arr[0]);
        node temp = root;
        int i=1;
        while(i<n){
            temp.next=new node(arr[i]);
            i++;
            temp=temp.next;
        }
        return root;
    }
    public static void print(node root){
        node temp = root;
        while(temp!=null){
            System.out.println(temp.data);
            temp=temp.next;
        }
    }
    public static boolean isPalendrome(node root,int n){
        node temp = root;
        node p = null, q = temp, r = temp;
        int i =0;
        while(i<n/2 && r!=null){
            r = r.next;
            q.next = p;
            p = q;
            q = r;
            i++;
        }
        if(n%2!=0)r=r.next;
        while(p!=null && r!=null){
            if(p.data!=r.data){
                return false;
            }
            p=p.next;
            r=r.next;
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = str.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(str[i]);
        }
        node root = buildLinkedList(arr,n);
        // print(root);
        System.out.println(isPalendrome(root,n));
    }
}