import { Root } from "../pages";
import {Error} from '../pages'
import homeLayout from "./home-layout";
import adminLayout from "./admin-layout";

const routerConfig = {
    path:'/',
    element:<Root/>,
    errorElement:<Error/>,
    children: [
      homeLayout,
      adminLayout
    ]
  }
  
export {routerConfig}