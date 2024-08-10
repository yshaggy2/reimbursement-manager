import React, { useState } from 'react';
import axios from 'axios'
import { store } from '../../globalData/store';
import { useNavigate } from 'react-router-dom';

export const Login: React.FC = () => {
    const [user, setUser] = useState({
        username:"",
        password:""
    }) 
    const navigate = useNavigate()
    const storeValues = (input:any) => {
 
        //if the "username" input changed, set the value of username in the user state object
 
        if(input.target.name === "username"){
            setUser((user) => ({...user, username:input.target.value}))
        } else {
            setUser((user) => ({...user, password:input.target.value}))
        }

        //rememeber the "..." is known as the "spread operator"
        //it SPREADS across an entire object so we can do things like send a whole object to a child component
        //or in this case, we're spreading for only the useState hook so we change individual values
 
    }
    const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault(); // Prevent default form submission behavior
    
        try {
            // Make POST request to the login endpoint
            const response = await axios.post(`${store.baseURL}login`, {
                username: user.username,
                password: user.password
            }, {
                withCredentials: true // Include cookies in the request
            });
    
            // Successfully logged in
            console.log(response.data);
            store.loggedInUser = response.data; // Update global store
            store.loggedInUser.userId = response.data.id
            alert(`Welcome, ${store.loggedInUser.username}`);
    
            // Navigate based on user role
            if (response.data.role === "employee") {
                navigate("/reimbursements");
            } else if (response.data.role === "manager") {
                navigate("/manager");
            }
        } catch (error) {
            if (axios.isAxiosError(error)) {
                // Handle known Axios errors
                console.error("Login failed:", error.response?.data || error.message);
                alert(`Login failed! ${error.response?.data?.message || error.message}`);
            } else {
                // Handle unexpected errors
                console.error("Unexpected error:", error);
                alert("An unexpected error occurred.");
            }
        }
    };
    


    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <div>
                    <label>Username:</label>
                    <input
                        type="text"
                        name="username"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input
                        type="password"
                        name="password"
                        onChange={storeValues}
                    />
                </div>
                <button type="submit">Login</button>
                <button type="button" onClick={() => navigate('/register')}>Register</button>
            </form>
        </div>
    );
};

export default Login;
