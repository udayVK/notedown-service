package vm.money.track.pojo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	//true if given loan
    //false if taken loan
    //just to use less memory
	private boolean type;
	private String name;
	private int totalAmount;
	private int pendingAmount;
	private LocalDate date;
	private String reason;
	//true if already paid
	//false if yet to pay
	private boolean status;
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = LoanHistory.class, mappedBy = "loan")
	private List<LoanHistory> loanHistory; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int i) {
		this.totalAmount = i;
	}
	public int getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(int pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<LoanHistory> getLoanHistory() {
		return loanHistory;
	}
	public void setLoanHistory(List<LoanHistory> loanHistory) {
		this.loanHistory = loanHistory;
	}
	
	@Override
	public String toString() {
		return "Loan [id=" + id + ", type=" + type + ", name=" + name + ", totalAmount=" + totalAmount
				+ ", pendingAmount=" + pendingAmount + ", date=" + date + ", reason=" + reason + ", status=" + status
				+ ", loanHistory=" + loanHistory + "]";
	}
	
}
