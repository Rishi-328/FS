/* Write a SQL query to find the difference between the highest and second highest salary.

Expected Output Columns:
------------------------
+-------------------+
| salary_difference |
+-------------------+

*/
USE test;
select max(sal)-(select max(sal) from emp where sal < max(e.sal)) as salary_difference
from emp e;