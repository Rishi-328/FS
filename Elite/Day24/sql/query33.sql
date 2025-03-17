/* Find the department name where ‘JONES’ works.

Expected Output Columns:
------------------------
+----------+
| dname    |
+----------+

*/
USE test;
select dname from emp e
join dept d on e.deptno = d.deptno
where ename = 'JONES';