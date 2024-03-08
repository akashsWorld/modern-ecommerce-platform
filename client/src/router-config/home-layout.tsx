import { LandingContent } from "../components";
import { Home, ProductsPage } from "../pages";
import { loader as productListLoader } from "../components/ProductsList/ProductList";

const homeLayout = {
  path: "/",
  element: <Home />,
  children: [
    {
      index: true,
      element: <LandingContent />,
    },
    {
      path: "/products/:id?/:query?/:value?",
      element: <ProductsPage/>,
      loader: productListLoader,
    },
  ],
};

export default homeLayout;
