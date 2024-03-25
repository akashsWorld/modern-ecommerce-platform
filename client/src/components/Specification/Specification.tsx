import {
  Autocomplete,
  Box,
  Button,
  TextField,
  Typography,
} from "@mui/material";
import React, { ChangeEvent, SyntheticEvent, useEffect, useState } from "react";
import { ProductFormProps } from "../../types/product-form-props";
import { Add, Delete } from "@mui/icons-material";
import NewProduct, { SpecificationType } from "../NewProduct/NewProduct";

const Specification = ({
  specificationList,
  productForm,
}: SpecificationProps) => {
  const { product, setProducts, productNumber } = productForm;

  const [fieldValue, setFieldValue] = useState("");
  const [selectedSpecfication, setSelectedSpecfication] = useState<
    number | undefined
  >(undefined);

  const addSpecificationField = () => {
    if (
      fieldValue.length === 0 ||
      isSpecificationPresent(fieldValue, product.specification).isPresent
    ) {
      return;
    }
    setProducts((pre) => {
      const newPoduct = [...pre];
      newPoduct[productNumber] = {
        ...pre[productNumber],
        specification: [
          ...pre[productNumber].specification,
          {
            specificationName: fieldValue,
            specificationKeyValues: [
              { specificationKey: "", specificationValue: "" },
            ],
          },
        ],
      };
      return newPoduct;
    });
    setFieldValue("");
  };

  const specificationOnDelete = () => {
    if (selectedSpecfication === undefined) return;
    setProducts((pre) => {
      const newPoduct = [...pre];
      return newPoduct.filter((pre, index) => index !== selectedSpecfication);
    });
    setSelectedSpecfication(undefined);
  };

  const addNewSpecificationKeyValueField = () => {
    if (selectedSpecfication === undefined) {
      return;
    }
    setProducts((pre) => {
      const newPoduct = [...pre];
      newPoduct[productNumber] = {
        ...pre[productNumber],
        specification: pre[productNumber].specification.map((spec, index) => {
          if (index === selectedSpecfication) {
            return {
              specificationName: spec.specificationName,
              specificationKeyValues: [
                ...spec.specificationKeyValues,
                { specificationKey: "", specificationValue: "" },
              ],
            };
          }
          return spec;
        }),
      };
      return newPoduct;
    });
  };

  const specificationKeyValuePairOnDelete = (keyValueFieldNumber: number) => {
    if (selectedSpecfication === undefined) {
      return;
    }

    setProducts((pre) => {
      const newPoduct = [...pre];
      const previousSpec = pre[productNumber].specification;
      newPoduct[productNumber] = {
        ...pre[productNumber],
        specification: previousSpec.map((spec, index) => {
          if (index === selectedSpecfication) {
            return {
              ...spec,
              specificationKeyValues: spec.specificationKeyValues.filter(
                (keyVal, keyValIndex) => keyValIndex !== keyValueFieldNumber
              ),
            };
          }
          return spec;
        }),
      };

      console.log(newPoduct);
      return newPoduct;
    });

    // setProducts((pre) => {
    //   const newPoduct = [...pre];
    //   newPoduct[productNumber] = {
    //     ...pre[productNumber],
    //     specification: pre[productNumber].specification.map((spec, index) => {
    //       if (index === selectedSpecfication) {
    //         return {
    //           specificationName: spec.specificationName,
    //           specificationKeyValues: spec.specificationKeyValues.filter(
    //             (keyValue, index) => index !== keyValueFieldNumber
    //           ),
    //         };
    //       }
    //       return spec;
    //     }),
    //   };
    //   return newPoduct;
    // });
  };

  const onChangeSpecificationKey = (
    keyValueFieldNumber: number,
    newValue: string
  ) => {
    if (selectedSpecfication === undefined) {
      return;
    }
    setProducts((pre) => {
      const newPoduct = [...pre];
      const keyValueField =
        newPoduct[productNumber].specification[selectedSpecfication]
          .specificationKeyValues[keyValueFieldNumber];

      newPoduct[productNumber].specification[
        selectedSpecfication
      ].specificationKeyValues[keyValueFieldNumber] = {
        ...keyValueField,
        specificationKey: newValue,
      };
      return newPoduct;
    });
  };

  const onChnageSpecificationValue = (
    keyValueFieldNumber: number,
    newValue: string
  ) => {
    if (selectedSpecfication === undefined) {
      return;
    }
    setProducts((pre) => {
      const newPoduct = [...pre];
      const keyValueField =
        newPoduct[productNumber].specification[selectedSpecfication]
          .specificationKeyValues[keyValueFieldNumber];

      newPoduct[productNumber].specification[
        selectedSpecfication
      ].specificationKeyValues[keyValueFieldNumber] = {
        ...keyValueField,
        specificationValue: newValue,
      };
      return newPoduct;
    });
  };

  const updateSelectedSpecification = (
    eve: SyntheticEvent<Element, Event>,
    newInput: string | null
  ) => {
    const isPresent = isSpecificationPresent(newInput, product.specification);
    console.log(isPresent);
    if (isPresent.isPresent) setSelectedSpecfication(isPresent.index);
  };

  const [currentSpec, setCurrentSpec] = useState<SpecificationType | undefined>(
    undefined
  );

  useEffect(() => {
    setCurrentSpec(
      selectedSpecfication !== undefined
        ? product.specification[selectedSpecfication]
        : selectedSpecfication
    );
  }, [selectedSpecfication]);

  return (
    <Box component={"section"}>
      <div className="flex justify-between ">
        <div>
          <TextField
            value={fieldValue}
            onChange={(eve) => setFieldValue(eve.target.value)}
            label="Specification Field Name"
          ></TextField>
          <div className="p-4 inline-block">
            <Button
              variant="outlined"
              onClick={addSpecificationField}
              endIcon={<Add />}
            >
              Add Specification
            </Button>
          </div>
        </div>

        <div className="m-3 p-2 w-full h-full">
          <div className="specifications">
            <Autocomplete
              disablePortal
              id="combo-box-demo"
              options={product.specification.map(
                (spec) => spec.specificationName
              )}
              sx={{ width: 300 }}
              value={
                selectedSpecfication !== undefined
                  ? product.specification[selectedSpecfication]
                      .specificationName
                  : selectedSpecfication
              }
              onChange={(eve, newInput) =>
                updateSelectedSpecification(eve, newInput)
              }
              renderInput={(params) => (
                <TextField {...params} label="Selected Specification" />
              )}
            />
          </div>

          {currentSpec !== undefined ? (
            <div className="specification-list">
              <div className="each-spec-field">
                <div className="flex">
                  <Typography variant="h6">
                    {currentSpec.specificationName}
                  </Typography>
                  <div className="ml-2">
                    <Button variant="outlined" onClick={specificationOnDelete}>
                      <Delete />
                    </Button>
                  </div>{" "}
                </div>
                {currentSpec.specificationKeyValues.map(
                  (keyValue, keyValueNumber) => (
                    <div className="each-key-value-field" key={keyValueNumber}>
                      <TextField
                        size="small"
                        value={keyValue.specificationKey}
                        onChange={(eve) =>
                          onChangeSpecificationKey(
                            keyValueNumber,
                            eve.target.value
                          )
                        }
                      ></TextField>
                      <TextField
                        size="small"
                        value={keyValue.specificationValue}
                        onChange={(eve) =>
                          onChnageSpecificationValue(
                            keyValueNumber,
                            eve.target.value
                          )
                        }
                      ></TextField>
                      <div className="p-4">
                        <Button
                          size="small"
                          variant="outlined"
                          onClick={() =>
                            specificationKeyValuePairOnDelete(keyValueNumber)
                          }
                        >
                          <Delete fontSize="small" />
                        </Button>
                      </div>
                    </div>
                  )
                )}
                <Button
                  variant="outlined"
                  onClick={addNewSpecificationKeyValueField}
                  endIcon={<Add />}
                >
                  Add New
                </Button>
              </div>
            </div>
          ) : (
            <div className="ml-2">
              <Typography variant="h6">
                No Specification Field selected{" "}
              </Typography>
            </div>
          )}
        </div>
      </div>
    </Box>
  );
};

export default Specification;

export interface SpecificationProps {
  specificationList: string[];
  productForm: ProductFormProps;
}

function isSpecificationPresent(
  specName: string | null,
  specifications: SpecificationType[]
) {
  if (specName !== null && specName.length !== 0) {
    let len = specifications.length;
    while (len--) {
      if (specifications[len].specificationName === specName) {
        return { isPresent: true, index: len };
      }
    }
  }
  return { isPresent: false, index: -1 };
}
