package vm.money.track.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
   	private String heading;
	private boolean logicalSavedIndicator;

	public Category() {
	}

	public Category(String heading) {
		this.heading = heading;
	}
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public boolean isLogicalSavedIndicator() {
		return logicalSavedIndicator;
	}
	public void setLogicalSavedIndicator(boolean logicalSavedIndicator) {
		this.logicalSavedIndicator = logicalSavedIndicator;
	}

    @Override
    public String toString() {
        return this.heading;
    }
	
}
