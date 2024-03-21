import React, { ChangeEvent } from "react";
import { Box, TextField } from "@mui/material";
import { Specification, AddProductImages, Highlights, Categories } from "./../";
import { DateTimePicker } from "@mui/x-date-pickers";
import dayjs, { Dayjs } from "dayjs";
import { ProductFormProps } from "../../types/product-form-props";

const ProductsFrom = ({
  categoryList,
  specificationList,
  productProp,
}: ProductPropsWithNewObjectResponse) => {
  const { productNumber, product, setProducts } = productProp;

  const onChangeProductName = (eve: ChangeEvent<HTMLInputElement>) => {
    if (eve !== null) {
      setProducts((pre) => {
        return pre.map((pro, index) => {
          if (index === productNumber) {
            return { ...pro, productName: eve.target.value };
          }
          return pro;
        });
      });
    }
  };
  const onChnageDescription = (eve: ChangeEvent<HTMLInputElement>) => {
    if (eve !== null) {
      setProducts((pre) => {
        return pre.map((pro, index) => {
          if (index === productNumber) {
            return { ...pro, description: eve.target.value };
          }
          return pro;
        });
      });
    }
  };
  const onChangePrice = (eve: ChangeEvent<HTMLInputElement>) => {
    if (eve !== null) {
      setProducts((pre) => {
        return pre.map((pro, index) => {
          if (index === productNumber) {
            return { ...pro, productPrice: +eve.target.value };
          }
          return pro;
        });
      });
    }
  };
  const onChnageDiscount = (eve: ChangeEvent<HTMLInputElement>) => {
    if (eve !== null) {
      setProducts((pre) => {
        return pre.map((pro, index) => {
          if (index === productNumber) {
            return { ...pro, discount: +eve.target.value };
          }
          return pro;
        });
      });
    }
  };
  const onChangeQuantity = (eve: ChangeEvent<HTMLInputElement>) => {
    if (eve !== null) {
      setProducts((pre) => {
        return pre.map((pro, index) => {
          if (index === productNumber) {
            return { ...pro, quantity: +eve.target.value };
          }
          return pro;
        });
      });
    }
  };

  const onChangeLaunchDate = (dateTime: Dayjs | null) => {
    if (dateTime !== null) {
      const dateValue = dateTime.format();
      setProducts((pre) => {
        return pre.map((pro, index) => {
          if (index === productNumber) {
            return { ...pro, launchDate: dateValue };
          }
          return pro;
        });
      });
    }
  };

  return (
    <div className="product-from m2">
      <Box
        component={"form"}
        sx={{
          "& .MuiTextField-root": { m: 1, width: "30ch" },
        }}
      >
        <TextField
          label={"Product Name"}
          onChange={onChangeProductName}
          value={product.productName}
        />
        <TextField
          label={"Description"}
          onChange={onChnageDescription}
          value={product.description}
        />
        <TextField
          label={"Product Price"}
          value={product.productPrice === 0 ? undefined : product.productPrice}
          type="number"
        />
        <TextField
          label={"Discount"}
          onChange={onChnageDiscount}
          value={product.discount === 0 ? undefined : product.discount}
          type="number"
        />
        <TextField
          label={"Quantity"}
          onChange={onChangeQuantity}
          value={product.quantity === 0 ? undefined : product.quantity}
          type="number"
        />

        <Highlights
          productNumber={productNumber}
          product={product}
          setProducts={setProducts}
        />
        <Categories categoryList={categoryList} productProp={productProp} />
        <Specification
          specificationList={specificationList}
          productForm={productProp}
        />
        <DateTimePicker
          onChange={onChangeLaunchDate}
          label="Date"
          defaultValue={dayjs(Date.now())}
        />
        <AddProductImages
          productNumber={productNumber}
          product={product}
          setProducts={setProducts}
        />
      </Box>
    </div>
  );
};

export default ProductsFrom;

export interface ProductPropsWithNewObjectResponse {
  categoryList: string[];
  specificationList: string[];
  productProp: ProductFormProps;
}
