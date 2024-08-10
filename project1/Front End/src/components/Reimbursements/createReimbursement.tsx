import React, { useState } from 'react';
import { FormControl, Button } from 'react-bootstrap';
import axios from 'axios';
import { store } from '../../globalData/store';
import { useNavigate } from 'react-router';

export const CreateReimbursement: React.FC = () => {
    const [amount, setAmount] = useState<number>(0);
    const [description, setDescription] = useState<string>('');

    const navigate = useNavigate();
    const handleAmountChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setAmount(parseFloat(e.target.value));
    };

    const handleDescriptionChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setDescription(e.target.value);
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        // Validate form fields
        if (amount <= 0 || !description) {
            alert('Please fill out all fields correctly.');
            return;
        }

        try {
            // Send POST request to the backend to create a new reimbursement
            await axios.post('http://localhost:8080/reimbursements', {
                amount,
                description,
                userId: store.loggedInUser.userId // Assuming you need to include the user ID
            });

            alert('Reimbursement created successfully.');
            // Optionally, navigate back or reset form here
        } catch (error) {
            console.error('Error creating reimbursement:', error);
            alert('Failed to create reimbursement.');
        }
    };

    return (
        <div className="container">
            <h3>Enter New Reimbursement:</h3>
            <form onSubmit={handleSubmit}>
                <FormControl
                    type="number"
                    placeholder="0.00"
                    name="amount"
                    value={amount}
                    onChange={handleAmountChange}
                />
                <FormControl
                    type="text"
                    placeholder="Enter Description"
                    name="description"
                    value={description}
                    onChange={handleDescriptionChange}
                />
                <Button type="submit" variant="primary">
                    Create Reimbursement
                </Button>
            </form>
        <div>
            <button onClick={()=>navigate("/reimbursements")}>Go Back</button>
        </div>
        </div>

    );
};