// const WebSocket = require('ws');
// const mongoose = require('mongoose')
// // const autoInc = require('mongoose-auto-increment');
// const server = new WebSocket.Server({port:8081});
// mongoose.connect('mongodb://localhost:27017/elite')
//        .then(()=>{
//         console.log('connected to mongodb');
//        })
//        .catch((err)=>{
//         console.log("error connecting to mongodb")
//        })
// const employeeSchema = new mongoose.Schema({
//         _id: Number,
//         name: String,
//         salary: Number,
//         role: String,
//         department: String,
//         experience: Number
//     });
// const Employee = mongoose.model('Employees',employeeSchema);
// const id = 1;
// server.on('connection',(ws)=>{
//     console.log("connected to client");
//     server.on('message',async(message)=>{
//         const msg = message.toString().split(' ');
//         if(msg[0]==='INSERT'){
//             const data = {_id:id++,name:msg[1],salary:parseInt(msg[2]),role:msg[3],department:msg[4],experience:parseInt(msg[5])};
//             const temp = new Employee(data);
//             const saveD = await temp.save();
//             if(saveD){
//                 ws.send(`Employee inserted successfully. ID:${data._id}`); 
//             }else{
//                 ws.send("Error adding data");
//             }
            
//         }
//         else if(msg[0]==='RETRIEVE'){
//             const empData = await Employee.find({});
//             empData.forEach(emp=>ws.send(`ID: ${emp._id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`));

//         }else if(msg[0]==='RETRIEVE_BY_DEPT'){
//             const dpt = msg[1];
//             const empData = await Employee.find({department:dpt});
//             empData.forEach(emp=>ws.send(`ID: ${emp._id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`));




//         }else{
//             ws.send("Invalid command.");
//         }

//     })
//     server.on('close',()=>{
//         console.log('clinet disconnected')
//     })
// })


const mongoose = require('mongoose');
const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8081 });
const AutoIncrement = require("mongoose-sequence")(mongoose);

mongoose.connect("mongodb://127.0.0.1:27017/webSocket").then(() => {
    console.log("Connected to DB");
}).catch((err) => {
    console.log(err);
});


const userSchema = new mongoose.Schema({
    userId: Number,
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number,
});


userSchema.plugin(AutoIncrement, { inc_field: "userId" });

const User = mongoose.model("User", userSchema);

wss.on('connection', (ws) => {
    console.log('Client Connected');

    ws.on('message', async (message) => {
        console.log(`Received: ${message}`);

        const parts = message.toString().split(' ');
        const command = parts[0].toUpperCase();

        if (command === 'INSERT' && parts.length === 6) {

            const name = parts[1];
            const salary = parseInt(parts[2], 10);
            const role = parts[3];
            const department = parts[4];
            const experience = parseInt(parts[5], 10);

            if (!isNaN(salary) && !isNaN(experience)) {
                const newUser = new User({ name, salary, role, department, experience });
                const emp=await newUser.save();
                ws.send(`Employee inserted successfully. ID: ${emp.userId}`);
            } else {
                ws.send('Invalid salary or experience.');
            }

        } else if (command === 'RETRIEVE') {
            try {
                const emps = await User.find({});
                const emplist = emps.map(emp =>
                    `ID: ${emp.userId}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`
                ).join('\n');

                ws.send(emplist || 'No employees found.');
            } catch (err) {
                ws.send('Error retrieving employees.');
            }

        } else if (command === 'RETRIEVE_BY_DEPT' && parts.length === 2) {
            try {
                const emps = await User.find({ department: parts[1] });
                const emplist = emps.map(emp =>
                    `ID: ${emp.userId}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`
                ).join('\n');

                ws.send(emplist || 'No employees found.');
            } catch (err) {
                ws.send('Error retrieving employees by department.');
            }

        } else {
            ws.send('Invalid command.');
        }
    });

    ws.on('close', () => {
        console.log('Client disconnected.');
    });
});

console.log('WebSocket server running on port 8081');