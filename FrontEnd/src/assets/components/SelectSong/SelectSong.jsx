import React, { useDeferredValue, useEffect } from 'react'
import { useState } from 'react';
import { DownOutlined, SmileOutlined } from '@ant-design/icons';
import { Button, Form, Input, Dropdown, Space } from 'antd';

const SelectSong = (props) => {
    const [form] = Form.useForm();
    const [items,setItems]  =useState([])
    const [song,setSong] = useState("")
    function handleClick(event){

            setSong(items[event.key].label)
    }

    const buttonItemLayout =
    
      {
          wrapperCol: {
            span: 14,
          },
    }
      
   const Songs = useState({})

   function submit(values){

    props.setTimer({timer:parseFloat(values.Timer),break_timer:parseFloat(values.break)})
    
   }

   useEffect(()=>{
        
        async function getSongs(){

            let result = await fetch("http://localhost:8080/api/getSongsList",
          {
              method:"GET",
              headers:{"Authorization":`Bearer ${props.user.token}`}
          }).then(async(res)=>{return await res.json()})

          console.log(result)

          let new_items = []

          for (let index = 0; index < result.length; index++) {
            const element = result[index];
            let obj = {
                key:index,
                label:element.title
                    
                
            }
            new_items.push(obj)
          }

          setItems(new_items)
        }

        getSongs()
   },[])
    return (
        <div style={{marginLeft:"4rem",height:"80vh",display:"flex",justifyContent:"center",alignItems:"center",margin:"auto"}}>
        <Form
            // {...formItemLayout}
            layout='vertical'
            form={form}
            onFinish={submit}
        >
           
            <Form.Item  name="Timer" label="Timer duration for Studying" style={{marginBottom:"20%"}}>
            <Input placeholder="Enter duration in minutes" style={{marginTop:"5%"}}/>
            </Form.Item>
            
            <Form.Item name={"break"} label="Timer duration for break">
            <Input placeholder="Enter timer durstion for break" style={{marginTop:"5%"}} />
            </Form.Item>

            <Form.Item name={"dropdown"} >
            {/* <Input type='dro' placeholder="Enter timer durstion for break" style={{marginTop:"5%"}} /> */}
            <Dropdown menu={{items,selectable:true,onClick:handleClick}}>
                
                <a onClick={(e) => e.preventDefault()}>
                        <Space>
                            Select Song
                            <DownOutlined />
                        </Space>
                </a>

                </Dropdown>
            </Form.Item>

            <Form.Item {...buttonItemLayout}>

                <Button type="primary" htmlType='submit'>Lets Start Studying</Button>

            </Form.Item>
            
        </Form>
    </div>
    );

}

export default SelectSong