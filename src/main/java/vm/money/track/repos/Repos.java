package vm.money.track.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import vm.money.track.pojo.Spend;

public interface Repos extends JpaRepository<Spend,Integer>{

	//uses Query dsl to generate query
	List<Spend> findByPurpose(String purpose);
	
	@Query(value = "delete from spend where data < ?1 ",nativeQuery=true)
	void deleteOld(LocalDate ld);
	
	//get by month
	@Query(value="select * from spend where date>=?1 and date <?2",nativeQuery=true)
	List<Spend> getBYMonth(LocalDate firstDayOfMonth, LocalDate FirstDayOfNextMonth);

	@Query(value="select sum(money) from spend where date>=?1 and date <?2",nativeQuery = true)
	int monthlySpent(LocalDate firstDayOfMonth, LocalDate FirstDayOfNextMonth);
    
    
    

}
