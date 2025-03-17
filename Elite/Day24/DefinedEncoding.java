package Elite.Day24;

import java.util.*;
// public class DefinedEncoding{
//     public static boolean isCheck(int[] arr,int count,int i){
//         for(int k=0;k<count;k++){
//             int c = 0;
//             int n = arr[i++];
//             while((n & (1<<7))!=0){
//                 c++;
//                 n <<= 1;
//             }
//             if(c!=1)return false;
            
//         }
//         // System.out.println(i);
//         if(i<arr.length){
//             return helper(arr,i);
//         }
//         return true;
//     }
//     public static boolean helper(int[] arr,int i){
//         int count = 0;
//         int n = arr[i];
//         while((n & (1<<7))!=0){
//             count++;
//             n <<= 1;
//         }
//         if(count==0) return helper(arr,i+1);
//         return isCheck(arr,count-1,i+1);
//     }
//     public static void main(String[] args){
//         Scanner sc = new Scanner(System.in);
//         String[] str = sc.nextLine().split(" ");
//         int len = str.length;
//         int[] arr = new int[len];
//         for(int i=0;i<len;i++){
//             arr[i]=Integer.parseInt(str[i]);
//         }
//         System.out.println(helper(arr,0));
//     }
// }
