import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import PatientInfo from './components/patients/PatientInfo.tsx';
import './index.css';
import './App.css';
import {
  createBrowserRouter,
  RouterProvider,
  Outlet
} from "react-router-dom";
import Header from './components/Header.tsx';
import Footer from './components/Footer.tsx';
import EncounterDetails from './components/encounters/EncounterDetails.tsx';

function Layout() {
  return (
    <>
    <header className='header'><Header /></header>    
      <Outlet />
      <footer className='footer'><Footer /></footer>
    </>
  )
}

const router = createBrowserRouter([
  {
    element: <Layout />,
    // errorElement: <errorPage />
    children: [
      {
        path: '/',
        element: <App />
      },
      {
        path: 'patients/:id',
        element: <PatientInfo />
      },
      {
        path: '/patients/:id/encounters/:encounterId',
        element: <EncounterDetails />
      }
    ]
  }

])

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
