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
	@GetMapping(path = "/monthlylimit/")
	public short getMonthlyLimit() {
		System.out.println("get monthly limit");
		return dRepo.getMonthlyLimit();
	}
	@PostMapping(path = "/monthlylimit/{ml}")
	public void setMonthlyLimit(@PathVariable(name = "ml")short limit) {
		System.out.println("set monthly limit of "+ limit);
		dRepo.setMonthlyLimit(limit);
	}
}
