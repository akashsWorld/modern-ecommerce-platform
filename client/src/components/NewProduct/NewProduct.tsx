import React, { useState } from "react";
import { json, useLoaderData } from "react-router-dom";
import axios from "axios";
import ProductsFrom from "../ProdcutsFrom/ProductsFrom";
import { Button, Typography } from "@mui/material";
import { Delete } from "@mui/icons-material";

const NewProduct = () => {
  const data = useLoaderData();

  const [product, setProduct] = useState<NewProductObject[]>();


  const onSubmitFormHandle =()=>{
    
  }

  const deleteProductHandle =(productNumber: number)=>{
    // TODO:Add the logic to handle the delete functionality.

  }

  const [productNumber, setProductNumber] = useState<number>(1);

  return (
    <div className="flex flex-col m-3 bg-slate-200 ">
      {Array.from(Array(productNumber)).map((product, index) => (
        <div
          key={index}
          className="border-dashed border-gray-400 rounded-md border m-2 shadow-lg "
        >
          <div className="m-2 relative">
            <div className="top-0 left-0">
              <Typography variant="h5" gutterBottom>
                {`Product ${index + 1}`}
              </Typography>
            </div>
            <div className=" absolute right-3 top-1" onClick={()=> deleteProductHandle(index)}>
              <Delete />
            </div>
          </div>
          <div>
            <ProductsFrom productNumber={index} />
          </div>
        </div>
      ))}
      <div className="m-3 justify-between">
        <div className="inline-block">
          <Button
            onClick={() => setProductNumber((pre) => pre + 1)}
            variant="contained"
          >
            Add Product
          </Button>
        </div>
        <div className="ml-2 inline-block">
          <Button
            onClick={() => setProductNumber((pre) => pre - 1)}
            disabled={productNumber === 1 ? true : false}
            variant="contained"
            color="secondary"
          >
            Remove Last
          </Button>
        </div>
      </div>
    </div>
  );
};

export default NewProduct;

export const loader = async (): Promise<NewProductResponse> => {
  const url = "http://localhost:8081/products/categories";

  const response = await axios.get(url);

  if (response.status === 200) {
    return response.data;
  }
  throw json({
    status: response.status,
    message: "Some error occured",
  });
};

export interface NewProductResponse {
  categoriesList: string[];
  specificationList: string[];
}

export interface NewProductObject {
  // TODO:Created the type of new Product.

}
