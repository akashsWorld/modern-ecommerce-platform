import React, { useState } from "react";
import { NewProductObject } from "../NewProduct/NewProduct";
import { IoIosRemoveCircleOutline } from "react-icons/io";
import './highlights.css'
import {
  Box,
  Button,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  TextField,
  Typography,
} from "@mui/material";
import { Delete, FiberManualRecord, Remove, RemoveCircle } from "@mui/icons-material";

const Highlights = ({
  productNumber,
  product,
  setProducts,
}: HighlightsProps) => {
  const [highlight, setHighlight] = useState<string>('');

  const onAddHighlight = () => {
    setProducts((pre) => {
      return pre.map((pro, index) => {
        if (index === productNumber && highlight !== undefined) {
          return { ...pro, heighlights: [...pro.heighlights, highlight] };
        }
        return pro;
      });
    });
    setHighlight('');
  };

  const onDeletHighLight = (highLightIndex: number) => {
    setProducts((pre) => {
      return pre.map((pro, index) => {
        if (index === productNumber) {
          return {
            ...pro,
            heighlights: pro.heighlights.filter(
              (name, index) => index !== highLightIndex
            ),
          };
        }
        return pro;
      });
    });
  };

  return (
    <div className="highlights">
      <Box component={'section'}>
        <div className="highlights-add">
          <TextField
            label="Highlight"
            size="small"
            value={highlight.length===0?undefined:highlight}
            onChange={(eve) => setHighlight(eve.target.value)}
          />
          <div className="mt-3 inline-block">
            <Button
              disabled={highlight === '' ? true : false}
              onClick={onAddHighlight}
              variant="outlined"
              color="secondary"
              className=""
              size="small"
            >
              Add Highlights
            </Button>
          </div>
        </div>
        <div className="m-2">
          <ul>
            {product.heighlights.length !== 0 ? (
              product.heighlights.map((highLight, index) => (
                <li key={index} className="list-none">
                  <div className="m-2">
                    <p className=" mt-1 p-1 box-border text-black font-medium inline-block text-2xl mr-2">
                      {highLight}
                    </p>
                    <div className="inline-block transition-all ease-out duration-150 rounded-full hover:highlights-icon__hover" onClick={()=>onDeletHighLight(index)}>
                      <IoIosRemoveCircleOutline/>
                    </div>
                  </div>
                </li>
              ))
            ) : (
              <Typography className="text-gray-600" variant="h6">
                No items are present in the Highlights
              </Typography>
            )}
          </ul>
        </div>
      </Box>
    </div>
  );
};

export default Highlights;

export interface HighlightsProps {
  productNumber: number;
  product: NewProductObject;
  setProducts: React.Dispatch<React.SetStateAction<NewProductObject[]>>;
}
