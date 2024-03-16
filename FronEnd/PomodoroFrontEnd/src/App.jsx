import { BrowserRouter,Routes,Route } from 'react-router-dom';
import './App.scss'
import Home from './assets/components/Home/Home';
import Timer from './assets/components/Timer/Timer';
import Error from './assets/components/Error/Error';
import SideBar from './assets/components/SideBar/SideBar';

function App() {

  return (
    <>

          <SideBar/>
          <BrowserRouter>
              <Routes>
                      <Route path='/' element={<Home/>} />
                      <Route path='/Timer' element={<Timer/>}/>
                      <Route path='*' element={<Error/>}/>
              </Routes>
          
          </BrowserRouter>

    </>
  )
}

export default App
