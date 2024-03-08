import React from 'react'
import { MainNavigation } from '../components';
import { Outlet } from 'react-router-dom';
const Home = () => {
  return (
    <div>
      <MainNavigation/>
      <Outlet/>
    </div>
  )
}

export default Home;
