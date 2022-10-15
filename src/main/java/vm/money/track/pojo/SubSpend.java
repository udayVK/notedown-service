package vm.money.track.pojo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubSpend {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String purpose;
	private LocalDate date;
	private short money;
	private short forOthers;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public short getForOthers() {
		return forOthers;
	}
	public void setForOthers(short forOthers) {
		this.forOthers = forOthers;
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
	public short getMoney() {
		return money;
	}
	public void setMoney(short money) {
		this.money = money;
	}
	
    @Override
    public String toString() {
        return "SubSpend "+ id +  purpose +  date +  money +forOthers;
    }
	
}
