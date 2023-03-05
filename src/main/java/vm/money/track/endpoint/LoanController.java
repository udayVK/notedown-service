package vm.money.track.endpoint;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import vm.money.track.pojo.Loan;
import vm.money.track.pojo.LoanHistory;
import vm.money.track.repos.LoanHistoryRepos;
import vm.money.track.repos.LoanRepos;

@RestController
@RequestMapping(path = "/loan")
@CrossOrigin(value = {"http://localhost:4200"})
public class LoanController {
	
	private LoanRepos lr;
	private LoanHistoryRepos lhr;
	
	//for Autowiring repository
	public LoanController(LoanRepos lr, LoanHistoryRepos lhr) { this.lr=lr; this.lhr = lhr;}
	
	@PostMapping(path="/new")
	public Loan addLoan(@RequestBody Loan loan) throws JsonProcessingException {
		Loan savedLoan = lr.save(loan);
		List<LoanHistory> pays = loan.getLoanHistory();
		for(int i = 0; i < pays.size(); i++){
			LoanHistory pay = pays.get(i);
			pay.setLoan(savedLoan);
			lhr.save(pay);
		}
		return savedLoan;
	}

	@GetMapping(path = "/all")
	public List<Loan> findAll() {
		return lr.findAll();
	}

	@GetMapping(path = "/taken")
	public List<Loan> takenLoans(){
		return lr.takenLoans();
	}
	
	@GetMapping(path = "/given")
	public List<Loan> givenLoans(){
		return lr.givenLoans();
	}
	
	@GetMapping(path = "/change")
	public void changeLoanStatus(@RequestParam(name = "lid") int lid) {
		lr.changeLoanStatus(lid);
	}
	
	@PutMapping(path = "/edit")
	public Loan editLoan(@RequestBody Loan loan) {
		System.out.println("loan edit");
		lr.edit(loan.getReason(),loan.getPendingAmount(),loan.getTotalAmount(),loan.getId());
		return loan;
	}

	@GetMapping(path = "/pending/all")
	public int getTotalPendingAmount() {
		return lr.getTotalPendingAmount();
	}
}
