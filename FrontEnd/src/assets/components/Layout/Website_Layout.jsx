import { useState } from 'react';
import {
  SmileOutlined,
  UploadOutlined,
  UserOutlined,
  VideoCameraOutlined,
  BarChartOutlined,
  FieldTimeOutlined 
} from '@ant-design/icons';
import { Layout, Menu, theme, Flex } from 'antd';
import { Link } from 'react-router-dom';
const { Header, Sider, Content } = Layout;
const Website_Layout = (props) => {
  const [collapsed] = useState(false);
  const [header, setHeader] = useState("Home")
  const [timer,setTimer] = useState({
    timer:null,
    break_timer:null
  })
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  function changeHeader(event) {
    if (event.key == 1) {

      setHeader("Home")
    } else if (event.key == 2) {

      setHeader("List Songs")
    } else if (event.key == 3) {

       setHeader("Upload Songs")
      }else if(event.key == 4){

        setHeader("Edit Songs")
      }
      else if(event.key == 5){

        setHeader("User Stats")
      }
      else if(event.key == 6){

        setHeader("Pomodoro Study Room")
      }
      else if(event.key == 7){
        setHeader("Set Timer")
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
        key: '5',
        icon: <BarChartOutlined />,
        label: <Link to={'/UserStats'}>User Stats</Link>,
        onClick: changeHeader
      },
      {
        key: '7',
        icon: <FieldTimeOutlined />,
        label: <Link to={'/SetTimer'}>SetTimer</Link>,
        onClick: changeHeader
      },
      {
        key: '6',
        icon: <SmileOutlined />,
        label:(timer.timer!=null?<Link to={'/StudyRoom'}>Study Room</Link>:("Study Room")),
        onClick: changeHeader,
        disabled: timer.timer==null?true:false
      },
     
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
                      {<props.element user={props.user} header={header} timer={timer} setTimer={setTimer} setHeader={setHeader}/>}
                    </Content>
      </Layout>
    </Layout>
  );
};
export default Website_Layout;