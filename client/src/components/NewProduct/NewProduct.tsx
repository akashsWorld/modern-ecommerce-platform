import React, { useState } from "react";
import { json, useLoaderData } from "react-router-dom";
import axios from "axios";
import ProductsFrom from "../ProdcutsFrom/ProductsFrom";
import { Button, Typography } from "@mui/material";

const NewProduct = () => {
  const data = useLoaderData();

  const [product, setProduct] = useState<NewProductObject[]>();

  const [productNumber, setProductNumber] = useState<number>(1);

  return (
    <div className="flex flex-col m-3 bg-slate-200">
      {Array.from(Array(productNumber)).map((product, index) => (
        <div key={index}  className="border-dashed border-gray-400 rounded-md border m-2">
          <div className="m-2">
            <Typography variant="h5" gutterBottom>
              {`Product ${index + 1}`}
            </Typography>
          </div>
          <ProductsFrom productNumber={index} />
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

export interface NewProductObject {}
