package vm.money.track.repos;

import vm.money.track.pojo.Data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

public interface DataRepos extends CrudRepository<Data, Byte>{
	
	@Query(value="select monthly_limit from data", nativeQuery = true)
	short getMonthlyLimit();
	
	@Transactional
	@Modifying
	@Query(value="update data set monthly_limit=?1",nativeQuery = true)
	void setMonthlyLimit(short limit);

}
