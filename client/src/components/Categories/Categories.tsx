import { TextField } from '@mui/material'
import React from 'react'

const Categories = ({name}:CategoriesProps) => {
  return (
    <React.Fragment>
      <TextField label={name}/>
    </React.Fragment>
  )
}

export default Categories


export interface CategoriesProps{
    name:string
} 