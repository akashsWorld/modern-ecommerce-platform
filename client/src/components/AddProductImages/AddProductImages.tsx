import { Button } from "@mui/material";
import React, { useState } from "react";
import styled from "@emotion/styled";
import { Add, Delete } from "@mui/icons-material";
import FileUpload from "../FileUpload/FileUpload";
import { NewProductObject } from "../NewProduct/NewProduct";

const AddProductImages = ({ product, setProducts }: AddProductImagesProps) => {
  const onDeleteHandle = (index: number) => {};

  const [pictureNumber, setPictureNumber] = useState(1);
  return (
    <div className="m-3">
      {Array.from(Array(pictureNumber)).map((num, index) => (
        <FileUpload
          key={index}
          num={num}
          index={index}
          onDeleteHandle={onDeleteHandle}
        />
      ))}

      <div className="photo-buttons mt-3">
        <div className="inline-block">
          <Button
            variant="outlined"
            startIcon={<Add />}
            onClick={() => setPictureNumber((pre) => pre + 1)}
          >
            Add Image
          </Button>
        </div>
        <div className="ml-2 inline-block">
          <Button
            variant="outlined"
            color="error"
            startIcon={<Delete />}
            disabled={pictureNumber === 1 ? true : false}
            onClick={() => setPictureNumber((pre) => pre - 1)}
          >
            Delete Image
          </Button>
        </div>
      </div>
    </div>
  );
};

export default AddProductImages;

export interface AddProductImagesProps {
  product: NewProductObject;
  setProducts: React.Dispatch<React.SetStateAction<NewProductObject[]>>;
}
