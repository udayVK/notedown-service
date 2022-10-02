package vm.money.track.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import vm.money.track.pojo.Loan;

public interface LoanRepos extends JpaRepository<Loan, Integer>{
	
	@Query(value = "select * from loan where status = false",nativeQuery = true)
	List<Loan> pendingLoans();

	@Query(value = "select * from loan where type = false",nativeQuery = true)
	List<Loan> givenLoans();
	
	@Query(value = "select * from loan where type = true",nativeQuery = true)
	List<Loan> takenLoans();
	
	
	@Modifying
	@Transactional
	@Query(value = "update loan set status = not(status) where id = ?1",nativeQuery = true)
	void changeLoanStatus(int lid);

	@Modifying
	@Transactional
	@Query(value = "update loan set reason=?1, pending_amount=?2, total_amount=?3 where id=?4",nativeQuery = true)
	void edit(String reason,short pendAmnt, short totAmnt, int id);

	@Query(value  = "select sum(pending_amount) from loan where status = false",nativeQuery = true)
	int getTotalPendingAmount();

}
