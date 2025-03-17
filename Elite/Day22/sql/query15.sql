/* Retrieve employees with 'ar' anywhere in their name

Expected Output Columns:
+-------+--------+----------+------+------------+---------+---------+--------+
| empno | ename  | job      | mgr  | hiredate   | sal     | comm    | deptno |
+-------+--------+----------+------+------------+---------+---------+--------+

*/
USE test;
select empno,ename,job,mgr,hiredate,sal,comm,deptno
from emp
where ename like "%ar%";