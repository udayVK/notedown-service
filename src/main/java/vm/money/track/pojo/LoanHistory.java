package vm.money.track.pojo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "LOAN_HISTORY")
@JsonIgnoreProperties("loan")
public class LoanHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int amount;
    // false if taken
    // true if given
    boolean type;
    LocalDate date;
    @ManyToOne(cascade = CascadeType.ALL)
    Loan loan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "LoanHistory [id=" + id + ", amount=" + amount + ", type=" + type + ", date=" + date + "person" + this.getLoan().getName()+"]";
    }
}