package vm.money.track.pojo;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
    @OneToMany
    @JoinColumn(name = "sub_spend")
    private List<SubSpend> spends;
    
    
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
	public List<SubSpend> getSpends() {
		return spends;
	}
	public void setSpends(List<SubSpend> spends) {
		this.spends = spends;
	}
    @Override
    public String toString() {
        return id +  heading + date +  spends ;
    }
	
	
	
}
