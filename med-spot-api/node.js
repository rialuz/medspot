// api calls
// localhost:8080
const express = require('express');
const { Pool } = require('pg');
const app = express();
const port = 8080;

const pool = new Pool({
    user: 'postgres',
    host: 'localhost',
    database: 'postgres',
    password: 'postgres',
    port: 5432,
})

app.use(express.json());

// async function createTable

app.get('', (req, res) => {
    res.send('Hello World')
})


app.listen(port, ()=> {
    console.log(`App listening on port ${port}`)
})