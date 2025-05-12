package CP_Notes;

import java.util.Arrays;
import java.util.Scanner;

public class Segment {
    int size;
    int[] tree;
    Segment(int n){
        size = n;
        tree = new int[4*n];
        build(0,0,n-1);
    }
    void build(int node,int start,int end){
        if(start==end){
            tree[node] = start;
            return;
        }
        int mid = (start + end)/2;
        build(2*node+1,start,mid);
        build(2*node+2,mid+1,end);
        tree[node] = Math.min(tree[2*node+1],tree[2*node+2]);

    }
    void update(int node,int start,int end,int idx){
        if(start == end){
            tree[node] = Integer.MAX_VALUE;
            return;
        }
        int mid = (start+end)/2;
        if(idx<=mid){
            update(2*node+1,start,mid,idx);
        }else{
            update(2*node+2,mid+1,end,idx);
        }
        tree[node] = Math.min(tree[2*node+1],tree[2*node+2]);
    }
    int query(int node,int start,int end,int l,int r){
        if(r<start || end < l){
            return Integer.MAX_VALUE;
        }
        if(l<= start && end <= r){
            return tree[node];
        }
        int mid = (start+end)/2;
        int left = query(2*node+1,start,mid,l,r);
        int right = query(2*node+2, mid+1, end, l, r);
        return Math.min(left,right);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // Consume the newline

        int[][] programs = new int[n][2];
        int maxDay = 0;

        for (int i = 0; i < n; i++) {
            programs[i][0] = sc.nextInt();
            programs[i][1] = sc.nextInt();
            maxDay = Math.max(maxDay, programs[i][1]);
        }
        Arrays.sort(programs,(a,b)->{
            if(a[1]==b[1]){
                return a[0]-b[0];
            }
            return a[1]-b[1];
        });
        int count = 0;
        Segment seg = new Segment(maxDay);
        for(int[] pro : programs){
            int early = seg.query(0,0,maxDay-1,pro[0]-1,pro[1]-1);
            if(early!=Integer.MAX_VALUE){
                count++;
                seg.update(0,0,maxDay-1,early);
            }
        }
        System.out.println(count);

    }
}
