import axios from 'axios'
import React from 'react'
import {json} from 'react-router-dom'

const ProductList = () => {
  return (
    <h1>
      Product List...
    </h1>
  )
}

export default ProductList


export const loader = async ({params}:any)=>{
  const id = params.id;
  const searchParam = params.searchParam;
  const searchParamValue = params.searchParamValue;
  const url = `http://localhost:8081/products?${searchParam}=${searchParamValue}`;

  // TODO:Have to implement the url builder method.

  // const response = await axios.get(url);

  // if(response.status!==200){
  //   // throw json({
  //   //   status: response.status,
  //   //   message:/*TODO: Update the message*/'Some message'
  //   // })
  // }
  // return response.data;
  return json({
    
  })
}
