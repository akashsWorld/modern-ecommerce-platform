import { Button } from "@mui/material";
import React, { useState } from "react";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import styled from "@emotion/styled";
import { Add, Delete } from "@mui/icons-material";

const FileUploadButton = () => {
  const onDeleteHandle = () => {};

  const VisuallyHiddenInput = styled("input")({
    clip: "rect(0 0 0 0)",
    clipPath: "inset(50%)",
    height: 1,
    overflow: "hidden",
    position: "absolute",
    bottom: 0,
    left: 0,
    whiteSpace: "nowrap",
    width: 1,
  });

  const [pictureNumber, setPictureNumber] = useState(1);
  return (
    <div className="m-3">
      {Array.from(Array(pictureNumber)).map((num, index) => (
        <div className="border-gray-200 rounded-sm bg-gray-300 p-3 inline-block m-2 ">
          <div className="inline-block">
            <Button
              key={num}
              component="label"
              role={undefined}
              variant="contained"
              tabIndex={-1}
              startIcon={<CloudUploadIcon />}
            >
              {`Add Photo ${index + 1}`}
              <VisuallyHiddenInput type="file" />
            </Button>
          </div>
          <section className="inline-block" onClick={onDeleteHandle}>
            <Delete />
          </section>
        </div>
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
            variant="contained"
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

export default FileUploadButton;
