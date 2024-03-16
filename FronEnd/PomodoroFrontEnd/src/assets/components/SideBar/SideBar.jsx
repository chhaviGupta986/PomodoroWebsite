import React from 'react'
import './SideBar.scss'
import { FcHome } from "react-icons/fc";
import { FcAlarmClock } from "react-icons/fc";
import { FcHeadset } from "react-icons/fc";

function SideBar() {
  return (
    <div className='SideBar'>
           
           <div className="Icon home">
                <div className="icon">
                    <FcHome />
                </div>
                
                <div className="name">
                        Home
                </div>
           </div>

           <div className="Icon timer">
                <div className="icon">
                    <FcAlarmClock />
                </div>
                
                <div className="name">
                    Pomodoro
                </div>
           </div>

           <div className="Icon songs">
                <div className="icon">
                    <FcHeadset/>
                </div>
                
                <div className="name">
                    Songs
                </div>
           </div>
           
    </div>
  )
}

export default SideBar