package vm.money.track.pojo;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	//desc is akeyword in postgreSQL so we cant have that as a variable name
	private String descp;
	private LocalDate upto;
	//true if goal met and completed
	//false if goal is not met yet
	private boolean status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return descp;
	}
	public void setDesc(String descp) {
		this.descp = descp;
	}
	public LocalDate getUpto() {
		return upto;
	}
	public void setUpto(LocalDate upto) {
		this.upto = upto;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
