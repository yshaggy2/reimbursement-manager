package com.revature.DAO;

import com.revature.model.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement,Integer> {
    public List<Reimbursement> findByUserUserId(int userId);
    public List<Reimbursement> findByUserUserIdAndStatus(int userId, String status);
    public List<Reimbursement> findByStatus(String status);

}
