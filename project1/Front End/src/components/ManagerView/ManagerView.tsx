import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";

export const ManagerView: React.FC = () => {
    const navigate = useNavigate();

    return (
        <div className="container text-center">
            <h1>Manager Dashboard</h1>
            <Button 
                variant="primary" 
                onClick={() => navigate("/users")} 
                style={{ margin: '10px' }}
            >
                View Users
            </Button>
            <Button 
                variant="secondary" 
                onClick={() => navigate("/reimbursements")} 
                style={{ margin: '10px' }}
            >
                View Reimbursements
            </Button>
        </div>
    );
};
export default ManagerView;