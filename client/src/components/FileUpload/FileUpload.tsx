import styled from "@emotion/styled";
import { Button } from "@mui/material";
import React, { useState } from "react";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import { Delete } from "@mui/icons-material";

const FileUpload = ({ num, index, onDeleteHandle }: FileUploadProps) => {
  const [uploaded, setUploaded] = useState("");

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

  return (
    <div className="border-gray-200 rounded-sm bg-gray-300 p-3 inline-block m-2 ">
      <div className="inline-block">
        <Button
          key={num}
          component="label"
          color="secondary"
          role={undefined}
          variant="contained"
          tabIndex={-1}
          startIcon={<CloudUploadIcon />}
        >
          {`Add Photo ${index + 1}`}
          <VisuallyHiddenInput
            type="file"
            onChange={(eve) => {
              if (eve.target.files !== null)
                setUploaded(eve.target.files[0].name);
            }}
          />
        </Button>
      </div>
      <section className="inline-block" onClick={() => onDeleteHandle(index)}>
        <Delete />
      </section>
      <p className="block" hidden={uploaded === "" ? true : false}>
        {uploaded}
      </p>
    </div>
  );
};

export default FileUpload;

export interface FileUploadProps {
  num: number;
  index: number;
  onDeleteHandle: (index: number) => void;
}
