package com.revature.service;

import com.revature.DAO.ReimbursementDAO;
import com.revature.DAO.UserDAO;
import com.revature.model.DTO.ReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.model.DTO.UpdateReimbursementStatusDTO;
import com.revature.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReService {
    private ReimbursementDAO rDAO;
    private UserDAO uDAO;

    @Autowired
    public ReService(ReimbursementDAO rDAO, UserDAO uDAO) {
        this.rDAO = rDAO;
        this.uDAO = uDAO;
    }
    //User OK
    public List<Reimbursement> getReimbursementsByUserId(int userId){
        return rDAO.findByUserUserId(userId);
    }
    //User OK
    public List<Reimbursement> getReimbursementsByUserIdAndStatus(int userId){
        return rDAO.findByUserUserIdAndStatus(userId, "Pending");
    }
    //Manager ONLY
    public List<Reimbursement> getReimbursementsByStatus(){
        return rDAO.findByStatus("Pending");
    }
    //Manager ONLY
    public List<Reimbursement> getReimbursements(){
        return rDAO.findAll();
    }
    //Manager ONLY
    public Reimbursement updateReimbursementStatus(UpdateReimbursementStatusDTO ursDTO) {
        if (!ursDTO.getStatus().equals("Approved") && !ursDTO.getStatus().equals("Denied")) {
           return null;
        }
        Optional<Reimbursement> o = rDAO.findById(ursDTO.getReimbursementId());
        Reimbursement r;
        if (o.isPresent()) {
            r = o.get();
        } else {
            return null;
        }
        r.setStatus(ursDTO.getStatus());
        return rDAO.save(r);

    }
    public Reimbursement updateReimbursementDescription(UpdateReimbursementStatusDTO ursDTO) {
        if (ursDTO.getStatus() == null) {
            return null;
        }
        Optional<Reimbursement> o = rDAO.findById(ursDTO.getReimbursementId());
        Reimbursement r;
        if (o.isPresent()) {
            r = o.get();
        } else {
            return null;
        }
        //Too lazy to rename Status variable or make new DTO
        r.setDescription(ursDTO.getStatus());
        return rDAO.save(r);

    }
    public Reimbursement addReimbursement(ReimbursementDTO rDTO) {
        Reimbursement r = new Reimbursement(0, rDTO.getDescription(),rDTO.getAmount(),"pending",null);
        Optional<User> u = uDAO.findById(rDTO.getUserId());
        if(u.isPresent()){
            r.setUser(u.get()); //assign the User to the Car
            return rDAO.save(r);
        } else {
            return null;
        }
    }
}
