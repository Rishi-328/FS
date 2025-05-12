// In the present situation, most of the movies releasing in OTTs.
// The Showtime OTT in US, introduced a new offer for the customers, 
// they can purchase either 1-day, 7-day, or 30-day subscription,
// and the cost is as follows price[0], price[1], price[2].

// The Subscription allows you to watch as many movies as you want with in subscribed days. 
// For example:
// If you purchased, a 7-day subscription on day 5, then you can watch
// the movies for 7 days: day 5, 6, 7, 8, 9, 10 and 11.

// Your task is to find out the minimum cost, you spend to watch the movies
// in the given list of days .

// NOTE: Days are numbered from 1, 2, 3, ...365, in sorted order.

// Input Format:
// -------------
// Line 1: Space separated integer days[], list of days.
// Line 2: 3 space separated integer price[], cost of subscription.

// Output Format:
// --------------
// Print an integer, minimum cost. 


// Sample Input-1:
// ---------------
// 1 4 6 7 8 20
// 2 7 15

// Sample Output-1:
// ----------------
// 11

// Explanation:
// ------------
// For example, here is a way to buy subscription, to watch the movies in given days:
// On day 1, buy a 1-day subscription for price[0] = $2, which cover day 1.
// On day 4, buy a 7-day subscription for price[1] = $7, which cover days 4, 5, ..., 10.
// On day 20, buy a 1-day subscription for price[0] = $2, which cover day 20.
// In total you spent $11.


// Sample Input-2:
// ---------------
// 1 2 3 4 5 6 7 8 9 10 30 31
// 2 7 15

// Sample Output-2:
// ----------------
// 17

// Explanation:
// ------------
// For example, here is a way to buy subscription, to watch the movies in given days:
// On day 1, buy a 30-day subscription for price[2] = $15, which cover days 1, 2, 3,....,30.
// On day 31, buy a 1-day subscription for price[0] = $2, which cover day 31.
// In total you spent $17.



import java.util.*;
public class Min_cost_tickets{
    public static int helper(int[] arr,int p1,int p2,int p3,int idx,int check,int[][] dp){
        if(idx>=arr.length){
            return 0;
        }
        if(dp[idx][check]!=-1){
            return dp[idx][check];
        }
        if(arr[idx] > check){
            int a = p1 + helper(arr,p1,p2,p3,idx+1,arr[idx],dp);
            int b = p2 + helper(arr,p1,p2,p3,idx+1,arr[idx]+6,dp);
            
            int c = p3 + helper(arr,p1,p2,p3,idx+1,arr[idx]+29,dp);
            return dp[idx][check] = Math.min(a,Math.min(b,c));
        }
        return dp[idx][check] = helper(arr,p1,p2,p3,idx+1,check,dp);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = str.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(str[i]);
        }
        int p1 = sc.nextInt();
        int p2 = sc.nextInt();
        int p3 = sc.nextInt();
        int[][] dp = new int[n+1][365];
        for(int i=0;i<n+1;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(helper(arr,p1,p2,p3,0,0,dp));
    }
}