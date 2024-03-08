import React from 'react'
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import {routerConfig} from './router-config'

const router = createBrowserRouter([
  routerConfig
])

const App = () => {
  return (
    <RouterProvider router={router}/>
  )
}

export default App
