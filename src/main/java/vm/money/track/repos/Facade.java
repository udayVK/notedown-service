package vm.money.track.repos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
    	LocalDate end = LocalDate.of(year, month+1, 1);
    	return repo.getBYMonth(start, end);
    }
	public List<Spend> getOfspeceficPurpose(String purpose) {
		return repo.findByPurpose(purpose);
	}

	public void deleteOld(LocalDate ld) {
		
	}
}
