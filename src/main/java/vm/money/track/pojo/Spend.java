package vm.money.track.pojo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class Spend {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String purpose;
	private int money;
    private LocalDate date;
    //allocate money for others in the same spend for me
    private short forOthers;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public short getForOthers() {
		return forOthers;
	}
	public void setForOthers(short forOthers) {
		this.forOthers = forOthers;
	}
	

}
