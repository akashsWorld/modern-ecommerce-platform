import React, { ChangeEvent, FormEvent, useState } from "react";

export interface ProductFormProps {
  categorieList: string[];
  formSubmitHandler: (eve: FormEvent<HTMLFormElement>) => void;
  product: NewProductObject;
  setProduct: React.Dispatch<React.SetStateAction<NewProductObject>>;
}
export interface NewProductObject {
  productName: string;
  description: string;
  images: FileObject[];
  productPrice: number;
  discount: number;
  categories: string[];
}
export interface FileObject {
  data: File;
  name: string;
}
const ProductsFrom = ({
  categorieList,
  formSubmitHandler,
  product,
  setProduct,
}: ProductFormProps) => {
  // Data hooks
  const [imageNumber, setImageNumber] = useState(1);

  // Handler functions

  const productNameOnChangehadle = (eve: ChangeEvent<HTMLInputElement>) => {
    const val = eve.target.value;
    setProduct((pre) => {
      return { ...pre, productName: val };
    });
  };

  const descriptionOnChangehadle = (eve: ChangeEvent<HTMLTextAreaElement>) => {
    const val = eve.target.value;
    setProduct((pre) => {
      return { ...pre, description: val };
    });
  };

  const discountOnChangehadle = (eve: ChangeEvent<HTMLInputElement>) => {
    const val = eve.target.value;
    if (val.length > 0 && isParseAble(val) === true) {
      setProduct((pre) => {
        return { ...pre, discount: Number.parseFloat(val) };
      });
    } else {
      setProduct((pre) => {
        return { ...pre, discount: 0 };
      });
    }
  };

  const categoriesOnChangehadle = (eve: ChangeEvent<HTMLInputElement>) => {
    const value = eve.target.value;
    if (eve.target.checked) {
      setProduct((pre) => {
        return { ...pre, categories: [...pre.categories, value] };
      });
    } else {
      setProduct((pre) => {
        const cat = pre.categories;

        const newCat = cat.filter((ca) => {
          return ca !== value;
        });
        return { ...pre, categories: newCat };
      });
    }
  };
  const productPriceChangeHandle = (eve: ChangeEvent<HTMLInputElement>) => {
    const val = eve.target.value;
    if (val.length > 0 && isParseAble(val) === true) {
      setProduct((pre) => {
        return { ...pre, productPrice: Number.parseFloat(val) };
      });
    } else {
      setProduct((pre) => {
        return { ...pre, productPrice: 0 };
      });
    }
  };

  const productImageAddHandle = (eve: ChangeEvent<HTMLInputElement>) => {
    const name = eve.target.name;
    const files = eve.target.files;
    if (files !== null) {
      setProduct((pre) => {
        return {
          ...pre,
          images: [...pre.images, { data: files[0], name: name }],
        };
      });
    }
  };

  const productImageDeleteHandle = (eve: React.MouseEvent<HTMLDivElement>) => {
    const id = eve.currentTarget.id;
    if (product.images.length > 1) {
      setProduct((pre) => {
        return {
          ...pre,
          images: pre.images.filter((curr) => {
            return curr.name !== id;
          }),
        };
      });
    }
  };

  return (
    <form onSubmit={formSubmitHandler}>
      <div>
        <label htmlFor="product-name"></label>
        <input
          type="text"
          name="product-name"
          placeholder="Product Name"
          value={product.productName}
          onChange={productNameOnChangehadle}
        />
      </div>
      <div>
        <label htmlFor="description"></label>
        <textarea
          name="description"
          value={product.description}
          onChange={descriptionOnChangehadle}
        ></textarea>
      </div>

      <div>
        <label htmlFor="product-price"></label>
        <input
          type="text"
          name="product-price"
          placeholder="Product Price"
          value={product.productPrice}
          onChange={productPriceChangeHandle}
        />
      </div>
      <div>
        <label htmlFor="discount">Discount</label>
        <input
          type="text"
          value={product.discount}
          onChange={discountOnChangehadle}
          name="discount"
        />
      </div>

      {/* Map all categories */}
      {categorieList.map((category, index) => {
        return (
          <div key={index}>
            <label htmlFor={category}>
              <input
                type="checkbox"
                name={category}
                checked={product.categories.includes(category)}
                onChange={categoriesOnChangehadle}
                value={category}
              />
              {category}
            </label>
          </div>
        );
      })}

      {Array.from(Array(imageNumber).keys()).map((data, index) => {
        return (
          <div
            key={index}
            style={{
              backgroundColor: "lavender",
              height: "100px",
              width: "200px",
            }}
          >
            <label htmlFor={`image-${index}`}>
              <input
                type="file"
                name={`image-${index}`}
                onChange={productImageAddHandle}
              />
            </label>
            <div onClick={productImageDeleteHandle} id={`image-${index}`}></div>
          </div>
        );
      })}
      <div
        onClick={() => setImageNumber((pre) => pre + 1)}
        style={{
          backgroundColor: "red",
          height: "30px",
          width: "50px",
        }}
      >
        Add Image
      </div>

      <button type="submit">Upload</button>
    </form>
  );
};

export default ProductsFrom;

const isParseAble = (str: string): boolean => {
  try {
    Number.parseFloat(str);
    return true;
  } catch (err) {
    return false;
  }
};
