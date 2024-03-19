import React, { useState } from "react";
import { json, useLoaderData } from "react-router-dom";
import axios from "axios";
import ProductsFrom from "../ProdcutsFrom/ProductsFrom";
import { Button, Typography } from "@mui/material";
import { Delete, Upload } from "@mui/icons-material";

const initialProduct = {
  productName: "",
  description: "",
  productPrice: 0,
  discount: 0,
  quantity: 0,
  heighlights: [],
  categories: [],
  specification: {
    specificationName: "",
    specificationKeyValues: [],
  },
  launchDate: "",
  accessories: [],
};

const NewProduct = () => {
  const data = useLoaderData();

  const [products, setProducts] = useState<NewProductObject[]>([
    initialProduct,
  ]);

  const onSubmitFormHandle = () => {};

  const deleteProductHandle = (productNumber: number) => {
    // TODO:Add the logic to handle the delete functionality.
    setProducts((pre) => {
      if (pre.length == 1) {
        return [...pre];
      }
      return pre.filter((prod, index) => {
        return index !== productNumber;
      });
    });
  };

  return (
    <div className="flex flex-col m-3 bg-slate-200 ">
      {products.map((product, index) => (
        <div
          key={index}
          className="border-dashed border-gray-400 rounded-md border m-2 shadow-lg "
        >
          <div className="m-2 relative">
            <div className="top-0 left-0">
              <Typography variant="h5" gutterBottom>
                {product.productName === ""
                  ? `Product ${index + 1}`
                  : product.productName}
              </Typography>
            </div>
            <div
              className=" absolute right-3 top-1"
              onClick={() => deleteProductHandle(index)}
            >
              <Button disabled= {products.length===1?true:false}>
                <Delete />
              </Button>
            </div>
          </div>
          <div>
            <ProductsFrom
              productNumber={index}
              product={product}
              setProducts={setProducts}
            />
          </div>
        </div>
      ))}
      <div className="m-3 justify-between">
        <div className="inline-block">
          <Button
            onClick={() => setProducts((pre) => [...pre, initialProduct])}
            variant="contained"
            size="medium"
          >
            Add Product
          </Button>
        </div>
        <div className="ml-2 inline-block">
          <Button
            onClick={() =>
              setProducts((pre) => {
                const newProd = [...pre];
                newProd.pop();
                return newProd;
              })
            }
            disabled={products.length === 1 ? true : false}
            variant="contained"
            size="medium"
            color="error"
          >
            Remove Last
          </Button>
        </div>
      </div>

      <div className="m-2">
        <Button variant="outlined" color="primary" endIcon={<Upload />}>
          Upload
        </Button>
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

  productName: string;
  description: string;
  productPrice: number;
  discount: number;
  quantity: number;
  heighlights: string[];
  categories: string[];
  specification: Specification;
  launchDate: string;
  accessories: NewProductObject[];
}

export interface Specification {
  specificationName: string;
  specificationKeyValues: SpecificationKeyValue[];
}
export interface SpecificationKeyValue {
  specificationKey: string;
  specificationValue: string;
}
