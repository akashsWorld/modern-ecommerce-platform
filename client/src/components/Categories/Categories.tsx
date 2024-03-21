import { Autocomplete, Chip, TextField, Typography } from "@mui/material";
import React from "react";
import { ProductFormProps } from "../../types/product-form-props";
import { json } from "react-router-dom";

const Categories = ({ categoryList , productProp }:CategoryProps ) => {
  
  const {product,productNumber,setProducts}=productProp


  const onChangeCategory=(eve:React.SyntheticEvent<Element, Event>,newValue:string[])=>{
    setProducts(pre=>{
      return pre.map((pro,index)=>{
        if(index===productNumber){
          return {...pro,categories:newValue}
        }
        return pro;
      })
    })
  }


  return (
    <React.Fragment>
      <Autocomplete
        multiple
        id="fixed-tags-demo"
        value={product.categories}
        onChange={onChangeCategory}
        options={categoryList}
        getOptionLabel={(option) => option}
        renderTags={(tagValue, getTagProps) =>
          tagValue.map((option, index) => (
            <Chip
              label={option}
              {...getTagProps({ index })}
            />
          ))
        }
        style={{ width: 500 }}
        renderInput={(params) => (
          <TextField {...params} label="Categories"/>
        )}
      />
    </React.Fragment>
  );
};

export default Categories;

export interface CategoryProps{
  categoryList:string[];
  productProp:ProductFormProps;

}
