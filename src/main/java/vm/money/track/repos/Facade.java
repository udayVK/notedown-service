package vm.money.track.repos;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vm.money.track.pojo.Spend;

@Service
public class Facade {

    @Autowired
    private Repos repo;

    public List<Spend> spendOfMonthnYear(int year, int month) {
        // taking  all of them and filtering for given month and year
        return repo.findAll().stream().
                            filter(a->(a.getDate().getYear() == year && a.getDate().getMonthValue() == month)).
                            collect(Collectors.toList());
    }

    public List<Spend> getByMonth(int year, int month){
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
	public List<Spend> getOfspeceficPurpose(String purpose) {
		return repo.findByPurpose(purpose);
	}

	public void deleteOld(LocalDate ld) {
		
	}
}
