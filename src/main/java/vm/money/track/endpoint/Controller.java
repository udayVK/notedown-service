package vm.money.track.endpoint;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vm.money.track.pojo.Spend;
import vm.money.track.repos.Facade;
import vm.money.track.repos.Repos;

@RestController
@RequestMapping("/spend")
@CrossOrigin("http://localhost:4200")
public class Controller {
	@Autowired
    private Facade facade;
    @Autowired
    private Repos repo;

    @PostMapping(path = "/add")
    public Spend add(@RequestBody Spend sp){
        System.out.println("adding spend");
        System.out.println(sp.getForOthers());
        repo.save(sp);
        return sp;
    }
    
    @GetMapping(path = "/{year}/{month}")
    public List<Spend> spendOfMonthnYear(@PathVariable int year, @PathVariable int month){
    	System.out.println("search for"+year+"-"+month);
//        return facade.spendOfMonthnYear(year, month);
    	List<Spend> spl = facade.getByMonth(year, month);
    	System.out.println(spl.size());
    	return spl;
    }
    
    @PutMapping(path = "/update/{id}")
    public Spend updateSpend(@PathVariable int id, @RequestBody Spend sp) {
    	return repo.save(sp);
    }
    
    @GetMapping(path = "/of/{prp}")
    public List<Spend> getOfspeceficPurpose(@PathVariable(name = "prp") String purpose){
    	return facade.getOfspeceficPurpose(purpose);
    }
    @GetMapping(path = "/monthlyspent/{year}/{month}")
    public int getMonthlySpent(@PathVariable int year, @PathVariable int month){
    	LocalDate start = LocalDate.of(year, month, 1);
    	LocalDate end = LocalDate.of(year, month+1, 1);
    	System.out.println(start.toString()+end.toString());
    	try {
    		return repo.monthlySpent(start, end);
    	} catch(org.springframework.aop.AopInvocationException e ) {return 0;}
    }
    
    public void deleteOld(@RequestBody LocalDate ld) {
    	facade.deleteOld(ld);
    }
}
