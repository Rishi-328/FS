/* Find departments that have at least one employee earning more than 3000.

Expected Output Columns:
------------------------
+--------+------------+
| deptno | dname      |
+--------+------------+

*/
USE test;
select d.deptno,d.dname
from dept d join emp e on e.deptno = d.deptno
group by e.deptno
having max(e.sal)>3000
