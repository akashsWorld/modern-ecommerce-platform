import React from "react";
import { NewProductObject } from "../components/NewProduct/NewProduct";

export interface ProductFormProps{
    productNumber:number;
    product:NewProductObject;
    setProducts:React.Dispatch<React.SetStateAction<NewProductObject[]>>;
}