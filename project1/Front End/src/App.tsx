import React, { useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, useNavigate } from 'react-router-dom';
import Login from './components/AuthComponents/Login';
import Register from './components/AuthComponents/Registration';
import { UsersContainer } from './components/Users/UsersContainer';
import { ReimbursementsContainer } from './components/Reimbursements/ReimbursementsContainer';
import ManagerView from './components/ManagerView/ManagerView';
import { CreateReimbursement } from './components/Reimbursements/createReimbursement';


const App: React.FC = () => {
    const RedirectToLogin: React.FC = () => {
        const navigate = useNavigate();
    
        useEffect(() => {
            navigate("/login");
        }, [navigate]);
    
        return null; // This component doesn’t render anything
    };

    return (
        <Router>
            <Routes>
                <Route path ="/" element={<RedirectToLogin/>}/>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path ="/users" element = {<UsersContainer/>} />
                <Route path ="/reimbursements" element = {<ReimbursementsContainer/>}></Route>
                <Route path ="/manager" element = {<ManagerView/>}></Route>
                <Route path="/create-reimbursement" element = {<CreateReimbursement/>}></Route>
                {/* Add other routes here */}
            </Routes>
        </Router>
    );
};

export default App;
