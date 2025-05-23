// Venkatadri is a maths teacher.
// He is teaching matrices to his students.
// He is given a matrix of size m*n, and it contains only positive numbers.
// He has given a task to his students to find the special matrix, 
// in the iven matrix A[m][n].
// A special matrix has following property:
// 	- The sum of elements in each row, each column and the two diagonals are equal.
// 	- Every 1*1 matrix is called as a special matrix.
// 	- The size of the special matrix should be a square, i.e., P*P.

// Your task is to help the students to find the speical matrix  with max size P.


// Input Format:
// -------------
// Line-1: Two space separated integers M and N, size of the matrix.
// Next M lines: N space separated integers m and n.

// Output Format:
// --------------
// Print an integer, maximum size P of the special matrix.


// Sample Input-1:
// ---------------
// 5 5
// 7 8 3 5 6
// 3 5 1 6 7
// 3 5 4 3 1
// 6 2 7 3 2
// 5 4 7 6 2

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// The special square is:
// 5 1 6
// 5 4 3
// 2 7 3


// Sample Input-2:
// ---------------
// 4 4
// 7 8 3 5
// 3 2 1 6
// 3 2 3 3
// 6 2 3 3

// Sample Output-2:
// ----------------
// 2

// Explanation:
// ------------
// The special square is:
// 3 3
// 3 3

import java.util.*;
public class MatrixSplCheck
{
    static boolean check(int[][] arr, int i, int j, int k, int n, int m){
        int sum = 0;
        for(int t = k; t < k + i; t++){
            sum += arr[j][t];
        }
        for(int t = j+1; t < j+i; t++){
            int row = 0;
            for(int u = k; u < k+i; u++){
                row += arr[t][u];
            }
            if(sum != row) return false;
        }
        for(int t = k; t < k+i; t++){
            int col = 0;
            for(int u = j; u < j + i; u++){
                col += arr[u][t];
            }
            if(sum != col) return false;
        }
        int d1 = 0;
        for(int v = 0; v < i; v++){
            d1 += arr[j+v][k+v];
        }
        if(d1 != sum) return false;
        int d2 = 0;
        for(int v = 0; v < i; v++){
            d2 += arr[j+v][k-v+i-1];
        }
        if(sum != d2) return false;
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int ans = 1;
        int flag = 0;
        for(int i = n; i >= 1; i--){
            for(int j = 0; j <= n - i; j++){
                for(int k = 0; k <= m - i; k++){
                    if(check(arr, i, j, k, n, m)){
                        ans = i;
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1) break;
            }
            if(flag == 1) break;
        }
        System.out.println(ans);
    }
}