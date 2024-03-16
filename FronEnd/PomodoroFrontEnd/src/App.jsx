import { BrowserRouter,Routes,Route } from 'react-router-dom';
import './App.css'
import Home from './assets/components/Home/Home';
import Timer from './assets/components/Timer/Timer';


function App() {

  return (
    <>

      <BrowserRouter>
          
          <Routes>

                  <Route path='/Home' element={<Home/>}/>
                  <Route path='/Timer' element={<Timer/>}/>
                  <Route path='*' element={}/>
          
          </Routes>
      
      </BrowserRouter>

    </>
  )
}

export default App
