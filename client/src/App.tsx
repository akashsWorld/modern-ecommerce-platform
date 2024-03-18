import React from 'react'
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import {routerConfig} from './router-config'
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import 'dayjs/locale/en-gb';

const router = createBrowserRouter([
  routerConfig
])

const App = () => {
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs} adapterLocale={'en-gb'}>
      <RouterProvider router={router}/>
    </LocalizationProvider>
  )
}

export default App
