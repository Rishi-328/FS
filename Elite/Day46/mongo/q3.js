/*
List users who spent more than ₹1000 on the platform.
Display the user's name along with the total amount they spent.
sort them based on amount spent


Sample output:
--------------
[
  { _id: 'U108', totalSpent: 5026, user_name: 'Tejas Sura' },
  { _id: 'U105', totalSpent: 1624, user_name: 'Jagat Dua' }
]



Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
printjson(
    db.Users.aggregate([{
        $lookup:{
            from:"Orders",
            localField:"user_id",
            foreignField:"user_id",
            as:"ee"
            
        }},
        {
            $unwind:"$ee"
        }
        ,
        {
            $group:{
                _id:"$user_id",
                totalSpent : {
                    $sum:"$ee.total_amount"
                },
                user_name:{
                    $first:"$name"
                }
                
            }
        },
        {
         $match:   {
             totalSpent:{
                 $gt:1000
             }
         }
        },
        {
            $sort:{
               totalSpent: 1 ,user_name:1
            }
        }
  
        ])
)
