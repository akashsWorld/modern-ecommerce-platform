import React from 'react'
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import {Admin, Home,NewProduct,ErrorMain,Root} from './pages'
import AdminDashBoard from './components/AdminDashBoard/AdminDashBoard'
import rootLayout from './router-config/root-layout'

const router = createBrowserRouter([
  rootLayout
])

const App = () => {
  return (
    <RouterProvider router={router}/>
  )
}

export default App
