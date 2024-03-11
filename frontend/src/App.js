import './App.css';
import {BrowserRouter as Router, Route, Routes, Navigate} from "react-router-dom";
import Navbar from './Components/Navbar/Navbar';
import Home from './Components/Home/Home';
import User from './Components/User/User';
import Auth from './Components/Auth/Auth';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar></Navbar>
        <Routes>
          <Route exact path="/" component={Home}></Route>
          <Route exact path="/users/:userId" component={User}></Route>
          <Route exact path="/auth" element= {localStorage.getItem("currentUser") != null ? <Navigate to="/"/>: <Auth/>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
