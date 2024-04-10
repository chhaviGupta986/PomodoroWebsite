import React from 'react'
import './Home.css'
const Home = (props) => {
  return (
    <div className="display">
      Welcome Back {props.user.userName}!
      <br/>
      Registered email:{props.user.email}
      </div>
  )
}

export default Home