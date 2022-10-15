package vm.money.track.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vm.money.track.pojo.SubSpend;

@Repository
public interface SubSpRepos extends JpaRepository<SubSpend, Integer>{

}
