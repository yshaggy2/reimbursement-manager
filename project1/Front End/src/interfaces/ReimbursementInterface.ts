export interface ReimbursementInterface {
    reimbursementId?:number,
    description: string,
    amount: number,
    status: string,
    userId?: number
}