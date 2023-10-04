package vm.money.track.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import vm.money.track.service.LoanService;

@RestController
@RequestMapping(path = "/loan")
@CrossOrigin(value = {"http://localhost:4200"})
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@PostMapping(path="/new")
	public Loan addLoan(@RequestBody Loan loan) throws JsonProcessingException {
		return this.loanService.addLoan(loan);
	}

	@GetMapping(path = "/all")
	public List<Loan> findAll() {
		return loanService.findAll();
	}

	@GetMapping(path = "/taken")
	public List<Loan> takenLoans(){
		return loanService.takenLoans();
	}
	
	@GetMapping(path = "/given")
	public List<Loan> givenLoans(){
		return loanService.givenLoans();
	}
	
	@GetMapping(path = "/change")
	public void changeLoanStatus(@RequestParam(name = "lid") int lid) {
		loanService.changeLoanStatus(lid);
	}
	
	@PutMapping(path = "/edit")
	public Loan editLoan(@RequestBody Loan loan) {
		return loanService.editLoan(loan);
		
	}

	@GetMapping(path = "/pending/all")
	public int getTotalPendingAmount() {
		return loanService.getTotalPendingAmount();
	}

	@PostMapping(path = "pay/add")
	public Loan addATransaction(@RequestBody LoanHistory lH, @RequestParam(name = "loanid", required = true) Integer loanId) throws Exception {
		return this.loanService.addATransaction(lH, loanId);
	}
}
