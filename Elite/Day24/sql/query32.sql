/* Find the department that has the most employees.

Expected Output Columns:
------------------------
+--------+----------------+
| deptno | employee_count |
+--------+----------------+

*/
USE test;
select deptno,count(*) as employee_count
from emp
group by deptno
order by count(*) desc
limit 1;