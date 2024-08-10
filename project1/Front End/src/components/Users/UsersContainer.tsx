import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { store } from "../../globalData/store"
import { User } from "./User"
import { UserInterface } from "../../interfaces/UserInterface"

export const UsersContainer: React.FC = () => {

    //useState hook, which will store an array of users (to send to the user component)
    const [users, setUsers] = useState<UserInterface[]>([])
    //need this to navigate between URLS
    const navigate = useNavigate()

    //useEffect tp GET the list of User upon component render
    useEffect(()=> {
        getAllUsers();        
    }, [])

    //function to get all users from the DB
    const getAllUsers = async () => {
        
        const response = await axios.get("http://localhost:8080/users")
        .then((response) => {console.log(response.data)
            setUsers(response.data)            
        })
    }
    return(
        <div>
            <button onClick={()=>navigate("/reimbursements")}>See Reimbursements</button>
            <User users={users}></User>
        </div>
    )
}