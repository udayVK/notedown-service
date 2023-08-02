package vm.money.track.pojo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Spend {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String purpose;
	private LocalDate date;
	private int money;
	private short forOthers;
	@ManyToOne
	@JoinColumn(name = "heading")
	@Cascade(CascadeType.ALL)
	private Category category; 
	
	public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
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
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
    @Override
    public String toString() {
        return "Spend "+ id +  purpose +  date +  money +forOthers;
    }
	
}
