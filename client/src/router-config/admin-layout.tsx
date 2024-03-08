import { Admin } from "../pages";
import { AdminDashBoard, NewProduct } from "../components";
import {loader as newProductLoader} from '../components/NewProduct/NewProduct'

const adminLayout = {
  path: "admin",
  element: <Admin />,
  children: [
    {
      index: true,
      element: <AdminDashBoard />,
    },
    {
      path: "register_product",
      element: <NewProduct />,
      loader: newProductLoader,
    },
  ],
};

export default adminLayout;
