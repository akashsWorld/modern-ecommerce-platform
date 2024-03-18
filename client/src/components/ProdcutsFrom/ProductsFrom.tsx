import React from "react";
import { Box, TextField } from "@mui/material";
import Categories from "../Categories/Categories";
import Specification from "../Specification/Specification";
import { DateTimePicker } from "@mui/x-date-pickers";
import dayjs from "dayjs";

const ProductsFrom = ({ productNumber }: ProductsFromProps) => {
  

  return (
    <div className="product-from m2">
    <Box component={"form"} sx={{
      '& .MuiTextField-root': { m: 1, width: '30ch' },
    }}>
      <TextField label={"Product Name"} />
      <TextField label={"Description"} />
      <TextField label={"Product Price"} />
      <TextField label={"Discount"} />
      <TextField label={"Highlights"} />
      <Categories name="Categories" />
      <Specification />
      <DateTimePicker label="Date" defaultValue={dayjs(Date.now())} />
    </Box>
    </div>
  );
};

export default ProductsFrom;

export interface ProductsFromProps {
  productNumber: number;
}
