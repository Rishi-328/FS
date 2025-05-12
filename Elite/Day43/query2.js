/*
Write a MongoDB aggregation query to display each patient's total billed 
amount across all their visits. Sort the data by name.


Sample output:
--------------
[
  { totalBilledAmount: 200, patientId: 'PT005', name: 'Patient 5' },
  { totalBilledAmount: 200, patientId: 'PT006', name: 'Patient 6' },
  { totalBilledAmount: 200, patientId: 'PT001', name: 'Patient 1' }
]

Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
printjson(
    db.billing.aggregate([
        {
            $group: {
              _id: "$patientId",
              totalBilledAmount: {$sum : "$totalAmount"}
            }
            },
            {
            $lookup : {
                from: "patients",
                localField: "_id",
                foreignField: "patientId",
                as: "temp"
            }
        },
        {
            $unwind : "$temp"
        },
        {
            $project:{
                _id:0,
                totalBilledAmount: 1,
                patientId:"$_id",
                name:"$temp.name"
                
            }
        },
        {
            $sort:{name:1}
            
        }
        ])
    
    
)
