import React from 'react'
import {Navigate} from 'react-router-dom'
const AuthenticatedRoute = (props,{children}) => {

  if( !(props.loggedIn.token) ){
    return <Navigate to="/Login" replace/>
  }
  return (
    children
  )
}

export default AuthenticatedRoute