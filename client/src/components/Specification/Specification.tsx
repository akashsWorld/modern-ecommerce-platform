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
import { SpecificationType } from "../NewProduct/NewProduct";

const Specification = ({
  specificationList,
  productForm,
}: SpecificationProps) => {
  const { product, setProducts, productNumber } = productForm;
  
  const [fieldValue, setFieldValue] = useState("");
  const [selectedSpecfication, setSelectedSpecfication] =
    useState<SpecificationType>(defaultValue);

  const addSpecificationField = () => {
    if (
      fieldValue.length === 0 &&
      isSpecificationPresent(fieldValue, product.specification)
    ) {
      return;
    }
    setProducts((pre) => {
      return pre.map((pro, index) => {
        if (index === productNumber) {
          return {
            ...pro,
            specification: [
              ...pro.specification,
              {
                specificationName: fieldValue,
                specificationKeyValues: [
                  {
                    specificationKey: "",
                    specificationValue: "",
                  },
                ],
              },
            ],
          };
        }
        return { ...pro };
      });
    });
    setFieldValue("");
  };

  const specificationOnDelete = (specificationTitle: string) => {
    // FIXME:
    setProducts((pre) => {
      return pre.map((pro, index) => {
        if (index === productNumber) {
          return {
            ...pro,
            specification: pro.specification.filter(
              (spec) =>
                spec.specificationName.toLowerCase() !==
                specificationTitle.toLowerCase()
            ),
          };
        }
        return { ...pro };
      });
    });
  };

  const addNewSpecificationKeyValueField = (specFieldName: string) => {
    // FIXME:
    setProducts((pre) => {
      return pre.map((pro, index) => {
        if (index === productNumber) {
          return {
            ...pro,
            specification: pro.specification.map((spec) => {
              if (spec.specificationName === specFieldName) {
                return {
                  ...spec,
                  specificationKeyValues: [
                    ...spec.specificationKeyValues,
                    {
                      specificationKey: "",
                      specificationValue: "",
                    },
                  ],
                };
              }
              return { ...spec };
            }),
          };
        }
        return { ...pro };
      });
    });
  };

  const specificationKeyValuePairOnDelete = (
    specFieldName: string,
    keyValueFieldNumber: number
  ) => {
    // FIXME:
    setProducts((pre) => {
      return pre.map((pro, index) => {
        if (index === productNumber) {
          return {
            ...pro,
            specification: pro.specification.map((spec) => {
              if (spec.specificationName === specFieldName) {
                return {
                  ...spec,
                  specificationKeyValues: spec.specificationKeyValues.filter(
                    (specKeyValueObj, index) => index !== keyValueFieldNumber
                  ),
                };
              }
              return { ...spec };
            }),
          };
        }
        return { ...pro };
      });
    });
  };

  const onChangeSpecificationKey = (
    specFieldName: string,
    keyValueFieldNumber: number,
    newValue: string
  ) => {
    FIXME: setProducts((pre) => {
      return pre.map((pro, index) => {
        if (index === productNumber) {
          return {
            ...pro,
            specification: pro.specification.map((spec) => {
              if (spec.specificationName === specFieldName) {
                return {
                  ...spec,
                  specificationKeyValues: spec.specificationKeyValues.map(
                    (specKeyValueObj, keyValueIndex) => {
                      if (keyValueIndex === keyValueFieldNumber) {
                        return {
                          ...specKeyValueObj,
                          specificationKey: newValue,
                        };
                      }
                      return { ...specKeyValueObj };
                    }
                  ),
                };
              }
              return { ...spec };
            }),
          };
        }
        return { ...pro };
      });
    });
  };

  const onChnageSpecificationValue = (
    specFieldName: string,
    keyValueFieldNumber: number,
    newValue: string
  ) => {
    FIXME: setProducts((pre) => {
      return pre.map((pro, index) => {
        if (index === productNumber) {
          return {
            ...pro,
            specification: pro.specification.map((spec) => {
              if (spec.specificationName === specFieldName) {
                return {
                  ...spec,
                  specificationKeyValues: spec.specificationKeyValues.map(
                    (specKeyValueObj, keyValueIndex) => {
                      if (keyValueIndex === keyValueFieldNumber) {
                        return {
                          ...specKeyValueObj,
                          specificationValue: newValue,
                        };
                      }
                      return { ...specKeyValueObj };
                    }
                  ),
                };
              }
              return { ...spec };
            }),
          };
        }
        return { ...pro };
      });
    });
  };

  const updateSelectedSpecification = (
    eve: SyntheticEvent<Element, Event>,
    newInput: string | null
  ) => {
    if (newInput !== null && newInput.length !== 0) {
      const spec = getSpecificationByName(newInput, product.specification);
      setSelectedSpecfication(spec);
    }
  };

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
              value={selectedSpecfication.specificationName}
              onChange={updateSelectedSpecification}
              renderInput={(params) => (
                <TextField {...params} label="Selected Specification" />
              )}
            />
          </div>

          {selectedSpecfication !== undefined ? (
            <div className="specification-list">
              <div className="each-spec-field">
                <div className="flex">
                  <Typography variant="h6">
                    {selectedSpecfication.specificationName}
                  </Typography>
                  <div className="ml-2">
                    <Button
                      variant="outlined"
                      onClick={() =>
                        specificationOnDelete(
                          selectedSpecfication.specificationName
                        )
                      }
                    >
                      <Delete />
                    </Button>
                  </div>{" "}
                </div>
                {selectedSpecfication.specificationKeyValues.map(
                  (keyValue, keyValueNumber) => (
                    <div className="each-key-value-field" key={keyValueNumber}>
                      <TextField
                        size="small"
                        value={keyValue.specificationKey}
                        onChange={(eve) =>
                          onChangeSpecificationKey(
                            selectedSpecfication.specificationName,
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
                            selectedSpecfication.specificationName,
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
                            specificationKeyValuePairOnDelete(
                              selectedSpecfication.specificationName,
                              keyValueNumber
                            )
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
                  onClick={() =>
                    addNewSpecificationKeyValueField(
                      selectedSpecfication.specificationName
                    )
                  }
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
  specName: string,
  specifications: SpecificationType[]
) {
  if (specName !== null && specName.length === 0) {
    let len = specifications.length;
    while (len--) {
      if (specifications[len].specificationName == specName) {
        return true;
      }
    }
  }
  return false;
}

function getSpecificationByName(
  specName: string,
  specifications: SpecificationType[]
) {
  if (specName !== null && specName.length !== 0) {
    let len = specifications.length;
    while (len--) {
      if (specifications[len].specificationName == specName) {
        return { ...specifications[len] };
      }
    }
  }
  return defaultValue;
}

const defaultValue: SpecificationType = {
  specificationName: "All",
  specificationKeyValues: [{ specificationKey: "", specificationValue: "" }],
};