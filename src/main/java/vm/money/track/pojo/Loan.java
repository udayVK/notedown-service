package vm.money.track.pojo;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private short totalAmount;
	private short pendingAmount;
	private LocalDate date;
	private String reason;
	//true if already paid
	//false if yet to pay
	private boolean status;
	
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
	public short getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(short amount) {
		this.totalAmount = amount;
	}
	public short getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(short pendingAmount) {
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
	
	
}
