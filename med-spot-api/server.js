// holds express server

const express = require("express");
const patientRoutes = require('./src/patients/routes');
const app = express();
const port = 8080;

app.use(express.json()) //allows us to GET/POST JSON

app.get("/", (req, res) => {
    res.send("Hello World")
})

app.use('/api/v1/patients', patientRoutes);

app.listen(port, ()=>{
    console.log(`Listening on port ${port}`)
});