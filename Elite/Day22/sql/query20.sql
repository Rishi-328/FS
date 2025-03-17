/* Get Max and min salaries in dept 20 or 40

Expected Output Columns:
+------------+-----------+
| HighestPay | LowestPay |
+------------+-----------+

*/
USE test;
select max(sal) as HighestPay, min(sal) as LowestPay
from emp
where deptno in (20,40);