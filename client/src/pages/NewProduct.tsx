import React, { FormEvent, useState } from "react";
import ProductsFrom, {
  NewProductObject,
} from "../components/ProdcutsFrom/ProductsFrom";
import axios from "axios";
import { useLoaderData, useNavigate } from "react-router-dom";

export interface newProductResponse {
  result: number;
  categories: string[];
}

const NewProduct = () => {
  const categories = useLoaderData() as newProductResponse;
  

  const navigate = useNavigate();

  const onFromSubmit = async (eve: FormEvent<HTMLFormElement>) => {
    eve.preventDefault();

    const formData = new FormData();


    product.images.map((curr,index)=>{
      formData.append("productImages", curr.data,`image-${index}`);
      return curr;
    })

    const data = {
      productName: product.productName,
      description: product.description,
      productPrice:product.productPrice,
      discount:product.discount,
      categories:product.categories,
      specification:null
    }

    // TODO: Have to send the request and also create another api to only store the product related data.
    const respose = await axios.post('http://localhost:8081/products',formData,{
      headers:{
        'Content-Type':'multipart/form-data'
      }
    });

    console.log(respose.status);

    const response = await fetch('http://localhost:8081/products',{
      method:'POST',
      body:formData
    })

    console.log(response.status);
  };

  const [product, setProduct] = useState<NewProductObject>({
    productName: "",
    description: "",
    images: [],
    productPrice: 0,
    discount: 0,
    categories: [],
  });

  return (
    <div>
      <ProductsFrom
        categorieList={categories.categories}
        formSubmitHandler={onFromSubmit}
        product={product}
        setProduct={setProduct}
      />
    </div>
  );
};

export default NewProduct;

export const loader = async (): Promise<string[]> => {
  const url = "http://localhost:8081/products/categories";

  const response = await axios.get(url);
  return response.data;
};
