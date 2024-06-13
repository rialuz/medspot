import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import PatientInfo from './PatientInfo.tsx';
import './index.css'
import {
  createBrowserRouter,
  RouterProvider
} from "react-router-dom";

const router = createBrowserRouter([
  {
    path: "/", //table of all patients
    element: <App />
  },
  {
    path: "patients/:id", //data of patient + encounters
    element: <PatientInfo />
  },
  // {
  //   path: "/patients/:id/encounters/:id" // encounter data
  // }

])

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
