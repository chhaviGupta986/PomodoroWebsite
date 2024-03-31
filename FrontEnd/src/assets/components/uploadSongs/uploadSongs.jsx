import React from 'react';
import { Button, Checkbox, Form, Input,Flex,Upload } from 'antd';
import {UploadOutlined } from '@ant-design/icons'
const onFinish = (values) => {

    console.log(values.file.originFileObj);
  
    const formData = new FormData()
    formData.append('title',values.title)
    formData.append('artist',values.artist)
    formData.append('credits',values.credits)
    formData.append('file',values.file.file.originFileObj)

    async function postSongs(){ 
            
        await fetch("http://localhost:8081/api/uploadSongs",{
            method:"POST",
            body: formData
        })
    }
    postSongs()
    
};
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};



const UploadSongs = () => (
    
    <Flex justify='center' align='center' style={{height:"70vh",left:50,position:'relative',overflow:'hidden'}}>
        <Form
            name="basic"
            labelCol={{
            span: 8,
            }}
            wrapperCol={{
            span: 16,
            }}
            style={{
            maxWidth: 600,
            }}
            initialValues={{
            remember: true,
            }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
        >
            <Form.Item
            label="Enter title"
            name="title"
            rules={[
                {
                required: true,
                message: 'Please input title!',
                },
            ]}
            >
            <Input />
            </Form.Item>

            <Form.Item
            label="Enter artist"
            name="artist"
            rules={[
                {
                required: true,
                message: 'Please input artist name!',
                },
            ]}
            >
            <Input/>
            </Form.Item>

            <Form.Item
            label="Enter Credits"
            name="credits"
            rules={[
                {
                required: true,
                message: 'Please input credits!',
                },
            ]}
            >
            <Input/>
            </Form.Item>

            <Form.Item
            label="Upload file"
            name="file"
            rules={[
                {
                required: true,
                message: 'Please input file!',
                },
            ]}
            >
            <Upload>
                <Button icon={<UploadOutlined />}>Click to Upload</Button>
            </Upload>
            </Form.Item>

            
            <Form.Item
            wrapperCol={{
                offset: 8,
                span: 16,
            }}
            >
            <Button type="primary" htmlType="submit">
                Submit
            </Button>
            </Form.Item>
        </Form>
    </Flex>
);
export default UploadSongs;