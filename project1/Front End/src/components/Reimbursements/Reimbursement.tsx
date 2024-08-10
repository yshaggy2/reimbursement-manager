import { Button, Table } from "react-bootstrap"
import { ReimbursementInterface } from "../../interfaces/ReimbursementInterface"
import axios from "axios";
import { useState } from "react";
import { store } from "../../globalData/store";

export const Reimbursement: React.FC<{ reimbursements: ReimbursementInterface[] }> = ({ reimbursements }) => {
    const [data, setData] = useState<ReimbursementInterface[]>(reimbursements);

    const updateDescription = async (reimbursementId: number) => {
        const newDescription = prompt("Enter new description:");

        if (!newDescription) {
            alert("Description cannot be empty.");
            return;
        }

        try {
            //Note: This should say description instead of status, but the backend is reusing another method, hence
            //the DTO having incorrect syntax
            await axios.patch(`http://localhost:8080/reimbursements/update-description`, {
                reimbursementId: reimbursementId,
                status: newDescription
            });
            alert("Description updated successfully.");

            // Refresh the data to reflect the changes
            const updatedReimbursements = data.map(r =>
                r.reimbursementId === reimbursementId
                    ? { ...r, description: newDescription }
                    : r
            );
            setData(updatedReimbursements);

        } catch (error) {
            console.error("Error updating description:", error);
            alert("Failed to update description.");
        }
    };
    const updateStatus = async (reimbursementId: number, status: string) => {

        try {
            //Note: This should say description instead of status, but the backend is reusing another method, hence
            //the DTO having incorrect syntax
            await axios.patch(`http://localhost:8080/reimbursements/update-status`, {
                reimbursementId: reimbursementId,
                status: status
            });
            alert("Status updated successfully.");

            // Refresh the data to reflect the changes
            const updatedReimbursements = data.map(r =>
                r.reimbursementId === reimbursementId
                    ? { ...r, status: status}
                    : r
            );
            setData(updatedReimbursements);

        } catch (error) {
            console.error("Error updating status:", error);
            alert("Failed to update status.");
        }
    };
    return(
        <div className="reimbursement-container">
                <Table striped bordered hover variant = "dark">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Amount</th>
                            <th>Description</th>
                            <th>Status</th>   
                            <th>Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        {reimbursements.map((r, index) => (
                            <tr key = {r.reimbursementId} >
                                <td>{r.reimbursementId}</td>
                                <td>{r.amount}</td>
                                <td>{r.description}</td>
                                <td>{r.status}</td>
                                <td>
                                    <button onClick={() => {
                                        if (r.reimbursementId !== undefined) {
                                            updateDescription(r.reimbursementId);
                                        } else {
                                            alert("Reimbursement ID is not defined.");
                                        }
                                    }}>Update Description</button>
                                </td>

                                <td>
                                    {store.loggedInUser.role === "manager" && 
                                    (<button onClick={() => {updateStatus(r.reimbursementId!, "Approved" )}}>Approve</button>
                                    )}
                                </td>
                                <td> 
                                    {store.loggedInUser.role === "manager" && 
                                    (<button onClick={()=> {updateStatus(r.reimbursementId!, "Denied" )}}>Deny</button>
                                    )}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>

        </div>
    )

}