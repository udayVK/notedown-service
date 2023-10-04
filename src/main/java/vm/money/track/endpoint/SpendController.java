package vm.money.track.endpoint;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vm.money.track.pojo.Category;
import vm.money.track.pojo.Spend;
import vm.money.track.service.SpendService;

@RestController
@RequestMapping("/spend")
@CrossOrigin("http://localhost:4200")
public class SpendController {
	
    @Autowired
    private SpendService spendsService;
    
    @PostMapping(path = "/add")
    public Spend add(@RequestBody Spend sp, @RequestParam boolean saveCategory){
        System.out.println("Save Category "+sp.getCategory().getHeading()+" : "+saveCategory);
        return spendsService.addSpend(sp,saveCategory);
    }
    
    @GetMapping(path = "/{year}/{month}")
    public List<Spend> spendsOfMonth(@PathVariable int year, @PathVariable int month){
        List<Spend> spends = spendsService.getSpendsOfMonth(year, month);
    	return spends;
    }
    
    @PutMapping(path = "/update/{id}")
    public Spend updateSpend(@PathVariable int id, @RequestBody Spend sp) {
    	return spendsService.updateSpend(id, sp);
    }

    @GetMapping(path = "/monthlyspent/{year}/{month}")
    public int getMonthlySpent(@PathVariable int year, @PathVariable int month) {
        return spendsService.getMonthlySpent(year, month);
    }
    
    @GetMapping(path = "/categories")
    public List<Category> getSavedCategories(){
        return spendsService.getSavedCategories();
    }
    
    public void deleteOld(@RequestBody LocalDate ld) {
        spendsService.deleteOld(ld);
    }
    
}
