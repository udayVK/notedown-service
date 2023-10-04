package vm.money.track.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import vm.money.track.pojo.Loan;
import vm.money.track.pojo.LoanHistory;
import vm.money.track.repos.LoanHistoryRepos;
import vm.money.track.repos.LoanRepos;

@Component
public class LoanService {
    private LoanRepos lr;
	private LoanHistoryRepos lhr;
	
	//for Autowiring repositories
	public LoanService(LoanRepos lr, LoanHistoryRepos lhr) { this.lr=lr; this.lhr = lhr;}
	
	public Loan addLoan(Loan loan) throws JsonProcessingException {
		Loan savedLoan = lr.save(loan);
		List<LoanHistory> pays = loan.getLoanHistory();
		for(int i = 0; i < pays.size(); i++){
			LoanHistory pay = pays.get(i);
			pay.setLoan(savedLoan);
			lhr.save(pay);
		}
		return savedLoan;
	}

	public List<Loan> findAll() {
		return lr.findAll();
	}

	public List<Loan> takenLoans(){
		return lr.takenLoans();
	}
	
	public List<Loan> givenLoans(){
		return lr.givenLoans();
	}
	
	public void changeLoanStatus(int lid) {
		lr.changeLoanStatus(lid);
	}
	
	public Loan editLoan( Loan loan) {
		System.out.println("loan edit");
		lr.edit(loan.getReason(),loan.getPendingAmount(),loan.getTotalAmount(),loan.getId());
		return loan;
	}

	public int getTotalPendingAmount() {
		return lr.getTotalPendingAmount();
	}

	public Loan addATransaction( LoanHistory lH, Integer loanId) throws Exception {
		Optional<Loan> loan = this.lr.findById(loanId);
		if(loan.isPresent()){
			Loan currentLoan = loan.get();
			currentLoan.getLoanHistory().add(lH);
			lH.setLoan(currentLoan);
			this.lhr.save(lH);
			return currentLoan;
		}
		else{
			throw new Exception(String.format("Loan with id %d not found", loanId));
		}
	}
}
