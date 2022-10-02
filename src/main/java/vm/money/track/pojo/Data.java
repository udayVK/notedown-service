package vm.money.track.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Data {
	@Id
	private byte id;
	private short monthlyLimit;
	
	

	public Data(short monthlyLimit) {
		this.monthlyLimit = monthlyLimit;
	}

	public short getMonthlyLimit() {
		return monthlyLimit;
	}

	public void setMonthlyLimit(short monthlyLimit) {
		this.monthlyLimit = monthlyLimit;
	}
	
}
