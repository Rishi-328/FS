/* Find the second highest salary from employees.

Expected Output Columns:
------------------------
+-----------------------+
| second_highest_salary |
+-----------------------+

*/
USE test;
SELECT sal as second_highest_salary
FROM emp
WHERE sal < (select MAX(sal) FROM emp)
ORDER BY sal DESC
LIMIT 1;