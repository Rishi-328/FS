// In computer networking, subnetting is the process of dividing a larger IP network
// into multiple smaller subnetworks (subnets). Given a base network IP address, 
// a CIDR (Classless Inter-Domain Routing) prefix, and the number of subnets required, 
// write a Java program that calculates the starting addresses of all the subnets.

// Your program should take as input:
// 	- A base network address (e.g., 192.168.1.0).
// 	- A CIDR prefix (e.g., /26 means a subnet mask of 255.255.255.192).
// 	- The number of subnets to generate.

// The program should then compute and return the starting addresses of each subnet.

// Input Format:
// -------------
// A string NIP: The base network IP address (e.g., "192.168.1.0").
// An integer CIDR: The subnet mask prefix (e.g., 26 for /26).
// An integer numSubnets: The number of subnets to be generated.

// Output Format:
// --------------
// A list of subnet addresses, each representing the starting address of a subnet.


// Sample Input:
// -------------
// 192.168.1.0
// 26
// 4

// Sample Output:
// --------------
// [192.168.1.0/28, 192.168.1.16/28, 192.168.1.32/28, 192.168.1.48/28]

// Explanation:
// ------------
// A /26 subnet has 64 IP addresses. The starting addresses of 
// the first four subnets are:
// 192.168.1.0/28, 
// 192.168.1.16/28, 
// 192.168.1.32/28, 
// 192.168.1.48/28




// import java.util.*;
// public class Subnet4{
//     public static void main(String[] args){
//         Scanner sc = new Scanner(System.in);
//         String[] ip = sc.nextLine().split("\\.");
//         int mask = sc.nextInt();
//         int no = sc.nextInt();
//         int count = 1;
//         int n = no;
//         while(n!=2){
//             count++;
//             n /= 2;
//         }
//         int newMask = mask + count;
//         int[] ans = new int[no];
//         int i=0,p=0;
//         while(i<no){
//             ans[p]=(i<<((32-mask)-count));
//             p++;
//             i++;
//         }
//         List<String> res = new ArrayList<>();
//         for(int j=0;j<no;j++){
//             res.add(ip[0]+"."+ip[1]+"."+ip[2]+"."+Integer.toString(ans[j])+"/"+Integer.toString(newMask));
//         }
//         System.out.println(res);
        
//     }
// }

import java.util.*;

class Subnet4{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String ip=sc.next();
        int cidr=sc.nextInt();
        int n=sc.nextInt();
        ArrayList<String> res=new ArrayList<>();
        int newcd=cidr+(int) Math.ceil(Math.log(n)/Math.log(2));
        int size=1<<(32-newcd);
        String[] parts=ip.split("\\.");
        for(int i=0;i<n;i++){
            res.add(parts[0]+"."+parts[1]+"."+parts[2]+"."+(size*i+Integer.parseInt(parts[3]))+"/"+newcd);
        }
        System.out.println(res);
    }
}