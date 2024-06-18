import './App.css';
import MainContent from './components/MainContent';
import { useEffect, useState } from 'react';
import {client } from './client'

function App() {
  const [patients, setPatients] = useState([]);

   const getPatients = () => {
    client.get('/patients')
    .then((res) => {
      setPatients(res.data);
    })
    .catch((err) => console.log(err))
  }

  useEffect(() => {
    // fetch data
    getPatients();
  }, []);

 

  

  return (
    <>
      <MainContent patients={patients} setPatients={setPatients} />
    </>
  )
}

export default App
