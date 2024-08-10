import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { store } from "../../globalData/store";
import { ReimbursementInterface } from "../../interfaces/ReimbursementInterface";
import { Reimbursement } from "./Reimbursement";

export const ReimbursementsContainer: React.FC = () => {
    const [reimbursements, setReimbursements] = useState<ReimbursementInterface[]>([]);
    const [filteredReimbursements, setFilteredReimbursements] = useState<ReimbursementInterface[]>([]);
    const [filter, setFilter] = useState<string | null>(null); // Holds the current filter criteria
    const navigate = useNavigate();

    useEffect(() => {
        if (store.loggedInUser && store.loggedInUser.userId) {
            getUserReimbursements();
        } else {
            console.error("User is not logged in or userId is missing.");
        }
    }, []);

    const getUserReimbursements = async () => {
        if (!store.loggedInUser) {
            console.error("User is not logged in.");
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8080/reimbursements/${store.loggedInUser.userId}`);
            setReimbursements(response.data);
            setFilteredReimbursements(response.data); // Initialize filtered data
            console.log(response.data);
        } catch (error) {
            console.error("Error fetching reimbursements:", error);
            alert("Error fetching reimbursements.")
        }
    };

    const getAllReimbursements = async () => {
        if (!store.loggedInUser) {
            console.error("User is not logged in.");
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8080/reimbursements`);
            setReimbursements(response.data);
            setFilteredReimbursements(response.data); // Initialize filtered data
            console.log(response.data);
        } catch (error) {
            console.error("Error fetching reimbursements:", error);
            alert("Error fetching reimbursements.")
        }
    };

    const filterPending = () => {
        setFilter("pending");
    };

    useEffect(() => {
        if (filter === "pending") {
            setFilteredReimbursements(reimbursements.filter(r => r.status === "pending"));
        } else {
            setFilteredReimbursements(reimbursements);
        }
    }, [filter, reimbursements]);

    return (
        <div className="collection-container">
            <div>
                <button onClick={() => navigate("/login")}>Logout</button>
                <button onClick={() => navigate("/create-reimbursement")}>Add New Reimbursement</button>
            </div>

            <Reimbursement reimbursements={filteredReimbursements} />
            <div>
                <button onClick={filterPending}>Filter Pending</button>
                <button onClick={() => setFilter(null)}>Clear Filter</button>
            </div>
            {store.loggedInUser.role === "manager" && (
                <button onClick={getAllReimbursements}>See All Reimbursements</button>
            )}
        </div>
    );
};