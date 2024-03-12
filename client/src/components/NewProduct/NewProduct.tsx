import React, { FormEvent, useState } from "react";
import './new-product.scss';
import ProductsFrom from "../ProdcutsFrom/ProductsFrom";
import axios from "axios";
import { json, useLoaderData, useNavigate } from "react-router-dom";
import { FileObject } from "../ProdcutsFrom/ProductsFrom";

const NewProduct = () => {
  const categories = useLoaderData() as NewProductResponse;


  const navigate = useNavigate();

  const [product, setProduct] = useState<NewProductObject>({
    productName: "",
    description: "",
    images: [],
    productPrice: 0,
    discount: 0,
    categories:[],
  });

  const onFromSubmit = async (eve: FormEvent<HTMLFormElement>) => {
    eve.preventDefault();

    const formData = new FormData();

    product.images.map((curr, index) => {
      formData.append("productImages", curr.data, `image-${index}`);
      return curr;
    });

    const data = {
      productName: product.productName,
      description: product.description,
      productPrice: product.productPrice,
      discount: product.discount,
      categories: product.categories.map(cat=>{
        return {
          category: cat
        }
      }),
      specification: null,
    };

    const savProductResponse = await axios.post(
      "http://localhost:8081/products",
      data,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (savProductResponse.status === 201) {
      // const id = savProductResponse.data;
      const id = savProductResponse.data;

      const saveImageResponse = await axios.post(`http://localhost:8081/images/${id}`,formData);

      if (saveImageResponse.status===204) {
        
        navigate("/admin");
        return;
      } else {
        const data = await saveImageResponse.data;
        throw json({
          status: saveImageResponse.status,
          message: data.message,
        });
      }
    } else {
      throw json({
        status: savProductResponse.status,
        message: savProductResponse.data.message,
      });
    }
  };

  

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

  if(response.status===200){
    return response.data;
  }
  throw json({
    status:response.status,
    message:'Some error occured'
  })
};


export interface CategoryResponse{
  category:string
}
export interface NewProductResponse {
  result: number;
  categories: CategoryResponse[];
}

export interface NewProductObject {
  productName: string;
  description: string;
  images: FileObject[];
  productPrice: number;
  discount: number;
  categories: CategoryResponse [];
}

