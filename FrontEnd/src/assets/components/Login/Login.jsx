import { useState } from 'react';
import { useEffect } from 'react'
import './Login.css'
function Login() {

  async function handleSubmit(event){

    event.preventDefault()
    const email = event.target.user.value;
    const password = event.target.pass.value;
    
    const res = await fetch("http://localhost:8080/login",
    {
      method:"POST",
      headers:{
        'Content-Type': 'application/json'
      },
      body:JSON.stringify({
        "email":email,
        "password":password
      })
    })
    console.log(res);


  }

  return (
    <>
            <form className="login-wrap" onSubmit={handleSubmit}>
                <div className="login-html">
                  <input id="tab-1" type="radio" name="tab" className="sign-in" /><label htmlFor="tab-1" className="tab">Sign In</label>
                  <input id="tab-2" type="radio" name="tab" className="sign-up"/><label htmlFor="tab-2" className="tab"></label>
                  <div className="login-form">
                    <div className="sign-in-htm">
                      <div className="group">
                        <label htmlFor="user" className="label">Username</label>
                        <input id="user" type="text" className="input"/>
                      </div>
                      <div className="group">
                        <label htmlFor="pass" className="label">Password</label>
                        <input id="pass" type="password" className="input" data-type="password"/>
                      </div>
                      <div className="group">
                        <input id="check" type="checkbox" className="check" />
                        <label htmlFor="check"><span className="icon"></span> Keep me Signed in</label>
                      </div>
                      <div className="group">
                        <input type="submit" className="button" value="Sign In"/>
                      </div>
                      <div className="hr"></div>
                      <div className="foot-lnk">
                        <a href="#forgot">Forgot Password?</a>
                      </div>
                    </div>
                
                </div>
              </div>
          </form>

  </>
  )
}

export default Login