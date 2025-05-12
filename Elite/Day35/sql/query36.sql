/* Write a SQL query to find all employees who do not receive a commission.

Expected Output Columns:
------------------------
+-------+-------+------+
| ename | empno | comm |
+-------+-------+------+

*/
USE test;
select ename, empno, comm from emp
where comm is null or comm=0;
