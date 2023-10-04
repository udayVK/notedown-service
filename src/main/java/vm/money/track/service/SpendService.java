package vm.money.track.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vm.money.track.pojo.Category;
import vm.money.track.pojo.Spend;
import vm.money.track.repos.CategoryRepo;
import vm.money.track.repos.Facade;
import vm.money.track.repos.Repos;

@Component
public class SpendService {
    	
    @Autowired
    private Facade facade;
    @Autowired
    private Repos repo;
    @Autowired
    private CategoryRepo ctRepo;
    
    // to use in the method getAllExistingCategoriesLike etc..
    private List<Category> categories;
    private List<String> categoryHeadings;
    
    public Spend addSpend(Spend sp, boolean saveCategory){
        this.getSavedCategories();
        if(saveCategory && !this.categoryHeadings.contains(sp.getCategory().getHeading())) {
            ctRepo.save(sp.getCategory());
        }
        else {
            sp.setCategory(this.categories.stream().filter(c->c.getHeading().equals(sp.getCategory().getHeading())).collect(Collectors.toList()).get(0));
        }
        System.out.println(sp);
        return repo.save(sp);
    }
    
    public List<Spend> getSpendsOfMonth(int year, int month){
    	System.out.println("search for"+year+"-"+month);
//        return facade.spendOfMonthnYear(year, month);
    	List<Spend> spl = this.filterSpendsOfMonth(year, month);
    	System.out.println(spl.size());
    	return spl;
    }
    
    public Spend updateSpend(int id, Spend sp) {
    	return repo.save(sp);
    }

    public int getMonthlySpent(int year, int month){
    	LocalDate start = LocalDate.of(year, month, 1);
    	LocalDate end = null;
    	if(month == 12) end = LocalDate.of(year+1, 1, 1);
    	else end = LocalDate.of(year, month+1, 1);
    	System.out.println(start.toString()+end.toString());
    	try {
    		return repo.monthlySpent(start, end);
//    	    return 0;
    	} catch(Exception e ) {System.out.println(e.getStackTrace()); return 0;}
    }
    
    public void deleteOld(LocalDate ld) {
    	facade.deleteOld(ld);
    }
    
    //use to filter the catefories based on a keyword
    public List<Category> getExistingCategoriesLike(String keyWord) {
        if(this.categories==null || this.categories.size()==0) {
            this.getSavedCategories();//populate the field, categories
        }
        categories.stream().filter(c->c.getHeading().startsWith(keyWord) || c.getHeading().contains(keyWord)).collect(Collectors.toList());
        return categories;
    }
    
    public List<Category> getSavedCategories(){
        this.categories = ctRepo.findAll();
        this.categoryHeadings = this.categories.stream().map(c->c.getHeading()).collect(Collectors.toList());
        return this.categories;
    }
    
    public List<Spend> filterSpendsOfMonth(int year, int month){
    	LocalDate start = LocalDate.of(year, month, 1);
    	LocalDate end = null;
    	if(month == 12) {
    	    end = LocalDate.of(year+1, Month.JANUARY, 1);
    	    System.out.println("dec month");
    	}
    	else{
    	    end = LocalDate.of(year, month+1, 1);
    	    System.out.println("otehr month");
    	}
    	return repo.getBYMonth(start, end);
    }
}
