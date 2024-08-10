import { useEffect, useState } from "react";
import { UserInterface } from "../../interfaces/UserInterface";
import { Button, Table } from "react-bootstrap";
import axios from "axios";
import { store } from "../../globalData/store";

export const User: React.FC<{ users: UserInterface[] }> = ({ users }) => {
    // useEffect just to print our users for debugging purposes
    useEffect(() => {
        console.log(users);
    }, [users]);

    // useState hooks for selectedUser<UserInterface> and userOptions<boolean>
    const [selectedUser, setSelectedUser] = useState<UserInterface>({
        userId: 0,
        username: "",
        firstName: "",
        lastName: "",
        role: ""
    });

    const [userOptions, setUserOptions] = useState<boolean>(false);

    const promoteUser = async (userId?: number) => {
        try {
            await axios.patch(`http://localhost:8080/users/promote/${userId}`);
            alert('User Promoted.');
        } catch (error) {
            console.error("Error promoting user:", error);
            alert('Failed to promote user.');
        }
        // TODO: re-render on promotion
    };

    const fireUser = async (userId?: number) => {
        try {
            await axios.delete(`${store.baseURL}users/${userId}`);
            alert('User fired.');
            // TODO: re-render on delete
        } catch (error) {
            console.error("Error firing user:", error);
            alert("Task failed.");
        }
    };

    return (
        <div className="d-flex flex-box">
            <div className="container">
                <h3>Welcome Manager! All employees:</h3>
                <Table striped bordered hover variant="success">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Role</th>
                            <th>Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user) => (
                            <tr key={user.userId}>
                                <td>{user.userId}</td>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.role}</td>
                                <td>
                                    <Button variant="outline-primary" onClick={() => promoteUser(user.userId)}>Promote</Button>
                                </td>
                                <td>
                                    <Button variant="outline-danger" onClick={() => fireUser(user.userId)}>Fire User</Button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </div>
        </div>
    );
};
