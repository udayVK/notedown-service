package vm.money.track.repos;

import vm.money.track.pojo.Data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DataRepos extends CrudRepository<Data, Byte>{
	
	@Query(value="select monthly_limit from data", nativeQuery = true)
	short getMonthlyLimit();
	

   @Transactional
    @Modifying
    @Query(value="insert into data values (1, ?1)",nativeQuery = true)
    void setMonthlyLimit(short limit);
   
	@Transactional
	@Modifying
	@Query(value="update data set monthly_limit = ?1 where id = 1",nativeQuery = true)
	void updateMonthlyLimit(short limit);
	
	//check weather data repos has atleast 1 record or not
	@Query(value = "select count(*) from data",nativeQuery=true)
	int tableHasData();

}
