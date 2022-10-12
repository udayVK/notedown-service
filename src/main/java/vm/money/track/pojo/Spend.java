package vm.money.track.pojo;

import java.time.LocalDate;
import java.util.Map;

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
	private String heading;
    private LocalDate date;
    private Map<Short, SubSpend> spends;
    
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public Map<Short, SubSpend> getSpends() {
		return spends;
	}
	public void setSpends(Map<Short, SubSpend> spends) {
		this.spends = spends;
	}
	
}
