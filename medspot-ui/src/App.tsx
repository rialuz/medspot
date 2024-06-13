import './App.css';
import Header from './components/Header';
import MainContent from './components/MainContent';
import Footer from './components/Footer';
import { useEffect, useState } from 'react';
import {client } from './client'

function App() {
  const [patients, setPatients] = useState([]);

   const getPatients = () => {
    client.get('/patients')
    .then((res) => {
      setPatients(res.data);
      console.log('res.data',res.data);
    })
    .catch((err) => console.log(err))
  }

  useEffect(() => {
    // fetch data
    getPatients();
  }, []);

 

  

  return (
    <>
      <Header />
      <MainContent patients={patients} setPatients={setPatients} />
      <Footer />
    </>
  )
}

export default App
