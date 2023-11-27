package vm.money.track.endpoint;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vm.money.track.pojo.ToDo;
import vm.money.track.repos.ToDoRepos;

@RestController
@RequestMapping(path = "/todo")
@CrossOrigin("http://localhost:4200")
public class ToDoCotroller {
	
	@Autowired
	private ToDoRepos tr;
	
	@PostMapping(path="/add")
	public ToDo addToDo(@RequestBody  ToDo t) {
		if(t.getWhen().equals(LocalDate.now()) || t.getWhen().isAfter(LocalDate.now())) {			
			return tr.save(t);
		}
		return null;
	}

	@GetMapping(path = "done/{id}")
	private boolean markAToDoAsComplete(@PathVariable(name = "id") int id) {
		tr.markAToDoAsComplete(id);
		return true;
	}
	
	@GetMapping(path="/today")
	public List<ToDo> findTodaysToDos() {
		return tr.findTodaysToDos(LocalDate.now());
	}
	
	@GetMapping(path="/today/pending")
	public List<ToDo> findTodaysPendingToDos() {
		return tr.findTodaysPendingToDos(LocalDate.now());
	}
	
	//deletes all completed todos which are of 1 day old
	public void deleteOldDoneTodos(){
		tr.deleteOldDoneTodos(LocalDate.now().minusDays(1L));
	}

}
