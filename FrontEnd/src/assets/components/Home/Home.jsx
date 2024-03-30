import React, { useEffect } from 'react'
import { Link } from 'react-router-dom';

function Home(props) {
  useEffect(()=>{
    async function getSongsList(){
        
      let res = await fetch("http://localhost:8080/api/getSongsList",
      {
        // credentials:'include',
        headers:{"Authorization":`Bearer ${props.user.token}`}
      }).then(async(res)=>{return await res.json()})
      console.log(res);
    }

    getSongsList()
  },[])
  return (
    <div>
      Home
      <Link to={"/Profile"}>Link</Link>
    </div>

  )
}

export default Home