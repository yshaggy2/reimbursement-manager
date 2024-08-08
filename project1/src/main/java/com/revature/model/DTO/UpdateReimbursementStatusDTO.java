package com.revature.model.DTO;

public class UpdateReimbursementStatusDTO {
    private Integer reimbursementId;
    private String status;

    public UpdateReimbursementStatusDTO() {
    }

    public UpdateReimbursementStatusDTO(Integer reimbursementId, String status) {
        this.reimbursementId = reimbursementId;
        this.status = status;
    }

    public Integer getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(Integer reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
