package vm.money.track.endpoint;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
import vm.money.track.pojo.SubSpend;
import vm.money.track.repos.CategoryRepo;
import vm.money.track.repos.Facade;
import vm.money.track.repos.Repos;
import vm.money.track.repos.SpendRepo;
import vm.money.track.repos.SubSpRepos;

@RestController
@RequestMapping("/spend")
@CrossOrigin("http://localhost:4200")
public class Controller {
	@Autowired
    private Facade facade;
    @Autowired
    private Repos repo;
    @Autowired
    private CategoryRepo ctRepo;
    
    // to use in the method getAllExistingCategoriesLike
    private List<String> categories;
    
    @PostMapping(path = "/add")
    public Spend add(@RequestBody Spend sp){
        if(sp.getId()==0) {
            ctRepo.save(sp.getCategory());
        }
        return repo.save(sp);
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
//    	    return 0;
    	} catch(Exception e ) {System.out.println(e.getStackTrace()); return 0;}
    }
    
    public void deleteOld(@RequestBody LocalDate ld) {
    	facade.deleteOld(ld);
    }
    
    //use to filter the catefories based on a keyword
    public List<String> getAllExistingCategoriesLike(String keyWord) {
        if(this.categories==null || this.categories.size()==0) {
            this.getAllExistingCategories();//populate the field, categories
        }
        categories.stream().filter(h->h.startsWith(keyWord) || h.contains(keyWord)).collect(Collectors.toList());
        return categories;
    }
    
    @GetMapping(path = "/categories")
    public List<String>  getAllExistingCategories(){
        this.categories = ctRepo.findAll().stream().map(c->c.getHeading()).collect(Collectors.toList());
        return this.categories;
    }
}
