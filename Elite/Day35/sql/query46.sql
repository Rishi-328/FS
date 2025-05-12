/* Write a SQL query to determine the minimum salary for employees hired in the 1990s.

Expected Output Columns:
------------------------
+----------------+
| min_salary_90s |
+----------------+

*/
USE test;
select min(sal) as "min_salary_90s"
from emp
where year(hiredate) >= 1990 and year(hiredate) <= 1999;