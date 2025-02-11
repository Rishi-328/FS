const express = require('express');
const app = express();
app.use(express.json());
const data = [{ 
	id: 1,
	customerName: "Azar",
	totalPrice: 150.0
}];
app.get('/orders',(req,res)=>{
    res.status(200).send(data);
})
app.post('/orders',(req,res)=>{
    const {customerName,totalPrice}=req.body;
    const temp = {id:data.length+1,customerName,totalPrice};
    if(temp){
        data.push(temp);
        res.status(201).send(temp);
    }
    
});

app.put('/orders/:id',(req,res)=>{
    const id = req.params.id;
    const {customerName,totalPrice}=req.body;
    const temp = data.find((t)=>{ 
        return t.id===parseInt(id)
    }
    );
    
    if(temp){
        temp.customerName=customerName;
        temp.totalPrice=totalPrice;
        res.status(200).send(temp);
    }else{
        res.status(404).send();
    }
})
app.delete('/orders/:id',(req,res)=>{
    const id = req.params.id;
    const index = data.findIndex((t)=>t.id===parseInt(id));
    data.splice(index,1);
    if(index!=-1){
        res.status(200).send();
    }

    
})
app.get('/orders/:id',(req,res)=>{
    const id = req.params.id;
    const temp = data.find((t)=>t.id===parseInt(id));
    if(temp){
        res.status(200).send(temp);
    }else{
        res.status(404).send();
    }
})
app.listen(3000,()=>{
    console.log('server running on port 3000')
})
