import React, { useState, useMeasure } from 'react';
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  SmileOutlined,
  UploadOutlined,
  UserOutlined,
  VideoCameraOutlined,
} from '@ant-design/icons';
import { Layout, Menu, Button, theme, Flex } from 'antd';
import { useRef } from 'react';
import { Link } from 'react-router-dom';
const { Header, Sider, Content } = Layout;
const Website_Layout = (props) => {
  const [collapsed, setCollapsed] = useState(false);
  const [header, setHeader] = useState("Home")
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  function changeHeader(event) {
    if (event.key == 1) {

      setHeader("User Status")
    } else if (event.key == 2) {

      setHeader("List Songs")
    } else if (event.key == 3) {

       setHeader("Upload Songs")
      }else if(event.key == 4){

        setHeader("Edit Songs")
      }

  }

  let items_admin =
    [
      {
        key: '1',
        icon: <UserOutlined />,
        label: <Link to={'/'}>Home</Link>,
        onClick: changeHeader
      },
      {
        key: '2',
        icon: <VideoCameraOutlined />,
        label: <Link to={'/ListSongs'}>List Songs</Link>,
        onClick: changeHeader
      },
      {
        key: '3',
        icon: <UploadOutlined />,
        label: <Link to={'/UploadSong'}>Upload Songs</Link>,
        onClick: changeHeader
      }
    ]

  let items_user =
    [
      {
        key: '1',
        icon: <UserOutlined />,
        label: 'User Stats',
        onClick: changeHeader
      },
      {
        key: '2',
        icon: <VideoCameraOutlined />,
        label: 'nav 2',
        onClick: changeHeader
      },
      {
        key: '3',
        icon: <SmileOutlined />,
        label: <Link to={'/StudyRoom'}>Study Room</Link>,
        onClick: changeHeader
      }
    ]


  return (
    <Layout>
      <Sider trigger={null} collapsible collapsed={collapsed} style={{ height: '100vh', position: 'fixed' }}>
        <div className="demo-logo-vertical" />
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={['1']}
          items={props.user.role == "ADMIN;" ? items_admin : items_user}
        />
      </Sider>

                  <Layout>
                  
                    <Header
                      style={{
                        padding: 0,
                        background: colorBgContainer,
                        width:"100%",
                        color:'black'
                      }}
                    >
                    <Flex justify='center' align='center'>
                          {header}
                    </Flex>
                         
                    </Header>
                    
                    <Content
                      style={{
                        margin: '24px 16px',
                        padding: 24,
                        height:'100vh',
                        background: colorBgContainer,
                        borderRadius: borderRadiusLG,
                      }}
                    >
                      {<props.element user={props.user} header={header} setHeader={setHeader}/>}
                    </Content>
      </Layout>
    </Layout>
  );
};
export default Website_Layout;