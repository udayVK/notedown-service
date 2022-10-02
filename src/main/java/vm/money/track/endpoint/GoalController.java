package vm.money.track.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vm.money.track.pojo.Goal;
import vm.money.track.pojo.Loan;
import vm.money.track.repos.GoalRepos;

@RestController
@RequestMapping(path = "/goal")
@CrossOrigin(value = {"http://localhost:4200"})
public class GoalController {
	
	@Autowired
	private GoalRepos gr;
	
	@PostMapping(path="/new")
	public Goal addGoal(@RequestBody Goal g) {
		System.out.println("adding goal");
		return this.gr.save(g);
	}
	
	@GetMapping(path = "/all")
	public List<Goal> findAll() {
		//find all method of crud repos returns iterable
		//find all method of jpa repos returns list
		return (List<Goal>)gr.findAll();
	}
	
	//get the goals that are done
	@GetMapping(path = "/done")
	public List<Goal> findDone() {
		List<Goal> allGoals = this.findAll();
		List<Goal> doneGoals = allGoals.stream().filter(g->g.isStatus()).collect(Collectors.toList());
		return doneGoals;
	}
	
	@GetMapping(path = "/done/{gid}")
	public void markDone(@PathVariable(name = "gid") int gid) {
		Goal g = this.gr.findById(gid).orElseThrow();
		g.setStatus(true);
		this.gr.save(g);
	}
	
	@PutMapping(path = "/change")
	public void chaneGoalStatus(@RequestBody int id) {
		System.out.println("goal"+id);
		Goal g = gr.findById(id).get();
		g.setStatus(!g.isStatus());
		this.gr.save(g);
	}
	
	

}
