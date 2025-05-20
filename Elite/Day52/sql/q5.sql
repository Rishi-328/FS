/*

Find the names of products that are only ordered by customers who live in a
specific city (e.g., 'New York')

Sample Output:
==============

Name                                                                                                                    
Keyboard                                                                                                          
                                                                                            


Customers:
==========
Field   Type    Null    Key     Default Extra                                                                           
CustomerID      int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Email   varchar(255)    YES             NULL                                                                            
Address varchar(255)    YES             NULL                                                                            
PhoneNumber     varchar(20)     YES             NULL                                                                    

Orders:
=======
Field   Type    Null    Key     Default Extra                                                                           
OrderID int     NO      PRI     NULL                                                                                    
CustomerID      int     YES     MUL     NULL                                                                            
OrderDate       date    YES             NULL                                                                            
TotalCost       decimal(10,2)   YES             NULL                                                                    
Status  varchar(20)     YES             NULL                                                                            

OrderItems:
============
Field   Type    Null    Key     Default Extra                                                                           
OrderItemID     int     NO      PRI     NULL                                                                            
OrderID int     YES     MUL     NULL                                                                                    
ProductID       int     YES     MUL     NULL                                                                            
Quantity        int     YES             NULL                                                                            
UnitPrice       decimal(10,2)   YES             NULL                                                                    

Products:
=========
Field   Type    Null    Key     Default Extra                                                                           
ProductID       int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Description     varchar(255)    YES             NULL                                                                    
Price   decimal(10,2)   YES             NULL  


*/

use fs;

-- Write your query below.
select Distinct p.Name
from Customers c
join Orders o on o.CustomerID = c.CustomerID
join OrderItems i on i.OrderID = o.OrderID
join Products p on p.ProductID = i.ProductID
where Address regexp 'New York' and not exists(
select p1.Name
from Customers c1
join Orders o1 on o1.CustomerID = c1.CustomerID
join OrderItems i1 on i1.OrderID = o1.OrderID
join Products p1 on p1.ProductID = i1.ProductID
where c1.Address not like '%New York%' and p1.ProductID = p.ProductID
)
