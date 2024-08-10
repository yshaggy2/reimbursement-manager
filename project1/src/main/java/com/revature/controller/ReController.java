package com.revature.controller;

import com.revature.model.DTO.ReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.model.DTO.UpdateReimbursementStatusDTO;
import com.revature.service.ReService;
import com.revature.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ReController {
    private ReService rs;
    private UserService us;
    @Autowired
    public ReController(ReService rs, UserService us) {
        this.rs = rs; this.us = us;
    }
        //User OK
    @GetMapping("/{userId}")
    public ResponseEntity<List<Reimbursement>> getReimbursementsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(rs.getReimbursementsByUserId(userId));
    }
        // User OK
    @GetMapping("/pending/{userId}")
    public ResponseEntity<List<Reimbursement>> getReimbursementsByUserIdAndStatus(@PathVariable int userId){
        return ResponseEntity.ok(rs.getReimbursementsByUserIdAndStatus(userId));
    }
        //Manager ONLY
    @GetMapping
    public ResponseEntity<List<Reimbursement>> getReimbursements(){
        return ResponseEntity.ok(rs.getReimbursements());
    }
        //Manager ONLY
    @GetMapping("/pending")
    public ResponseEntity<List<Reimbursement>> getReimbursementsByStatus(){
        return ResponseEntity.ok(rs.getReimbursementsByStatus());
    }
        //Manager ONLY
    @PatchMapping("/update-status")
    public ResponseEntity<?> updateReimbursementStatus(@RequestBody UpdateReimbursementStatusDTO updateReimbursementStatusDTO) {
        Reimbursement updatedReimbursement = rs.updateReimbursementStatus(updateReimbursementStatusDTO);
        if (updatedReimbursement == null) {
            return ResponseEntity.status(400).body("Either reimbursement does not exist or status was not set to 'Approved' or 'Denied')");
        }
        return new ResponseEntity<>(updatedReimbursement, HttpStatus.OK);
    }
    @PatchMapping("/update-description")
    public ResponseEntity<?> updateReimbursementDescription(@RequestBody UpdateReimbursementStatusDTO updateReimbursementStatusDTO) {
        Reimbursement updatedReimbursement = rs.updateReimbursementDescription(updateReimbursementStatusDTO);
        //This uses "Status" DTO but is actually going to update the Description
        if (updatedReimbursement == null) {
            return ResponseEntity.status(400).body("Either reimbursement does not exist or something went wrong");
        }
        return new ResponseEntity<>(updatedReimbursement, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Object> addReimbursement(@RequestBody ReimbursementDTO rDTO){

        //NOTE: moved the extra processing to the service layer

        //send the Car data to the service, saving the result to a variable
        Reimbursement r = rs.addReimbursement(rDTO);

        if(r == null){
            //if the User is not present, send back an error message (400)
            return ResponseEntity.status(400).body("Couldn't find User with ID: " + rDTO.getUserId());
        }

        //otherwise, send a 201 (Created) and the Car data!
        return ResponseEntity.status(201).body(r); //send the car back in the response

    }
}
