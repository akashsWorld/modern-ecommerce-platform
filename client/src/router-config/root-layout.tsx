import { Admin, ErrorMain, Home, NewProduct, Root } from "../pages"
import AdminDashBoard from "../components/AdminDashBoard/AdminDashBoard"
import { loader } from "../pages/NewProduct"

export default {
    path:'/',
    element:<Root/>,
    errorElement:<ErrorMain/>,
    children:[
      {
        index:true,
        element: <Home/>
      },
      {
        path:'admin',
        element: <Admin/>,
        children:[
          {
            index:true,
            element: <AdminDashBoard/>
          },
          {
            path:'register_product',
            element: <NewProduct/>,
            loader:loader
          }
        ]
      }
    ]
  }