package vm.money.track.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Repeatable;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import vm.money.track.pojo.Spend;

public interface Repos extends JpaRepository<Spend,Integer>{
	
	@Query(value = "delete from spend where data < ?1 ",nativeQuery = true)
	void deleteOld(LocalDate ld);
	
	//get by month
	@Query(value = "select * from spend where date>=?1 and date <?2", nativeQuery = true)
	List<Spend> getBYMonth(LocalDate firstDayOfMonth, LocalDate FirstDayOfNextMonth);

	@Query(value="select sum(money) from spend where date>=?1 and date < ?2",nativeQuery = true)
	int monthlySpent(LocalDate firstDayOfMonth, LocalDate FirstDayOfNextMonth);
    
//	@Transactional
//	@Query(value = "insert into spend values (?1, ?2)", nativeQuery = true)
//    void save(String heading, LocalDate date);
	
//	@Query(value = "insert into sub_spend (purpose, date, money, for_others) values (?1, ?2, ?3, ?4)", nativeQuery = true)
//	void saveSubSpend(String purpose, LocalDate date, short money, short forOthers);


	
	
}
