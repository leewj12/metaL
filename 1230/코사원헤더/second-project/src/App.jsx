import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from "./pages/HomePage";
import SignupPage from "./pages/SignupPage";
import LoginPage from "./pages/LoginPage";
import BoardWritePage from "./pages/BoardWritePage";
import Attendance from "./components/Attendance"
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/boardwrite" element={<BoardWritePage />} />
        <Route path="/attendance" element={<Attendance />} />

      </Routes>
    </Router>
  );
}


export default App
