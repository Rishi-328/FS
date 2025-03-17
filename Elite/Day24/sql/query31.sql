/* Find employees who belong to a department with no employees.

Expected Output Columns:
------------------------
+---------+
| dname   |
+---------+

*/
USE test;
select dname from emp
right join dept on emp.deptno = dept.deptno
where empno is null;
