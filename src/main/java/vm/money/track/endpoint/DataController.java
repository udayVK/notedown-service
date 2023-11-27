package vm.money.track.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vm.money.track.repos.DataRepos;

@RestController
@RequestMapping("/data")
@CrossOrigin
public class DataController {

	@Autowired
	private DataRepos dRepo;
	@GetMapping(path = "/monthlylimit")
	public short getMonthlyLimit() {
		if(dRepo.tableHasData()>0)
		return dRepo.getMonthlyLimit();
		return 12000;
	}
	
	@PostMapping(path = "/monthlylimit/{ml}")
	public void setMonthlyLimit(@PathVariable(name = "ml")short limit) {
		System.out.println("setting monthly limit of "+ limit);
		if(dRepo.tableHasData()>0) {
		    dRepo.updateMonthlyLimit(limit);
		}
		dRepo.setMonthlyLimit(limit);
	}
}
