package vm.money.track.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import vm.money.track.pojo.LoanHistory;


public interface LoanHistoryRepos extends JpaRepository<LoanHistory, Integer>{
    
}
