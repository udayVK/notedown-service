package vm.money.track.repos;

import org.springframework.data.repository.CrudRepository;

import vm.money.track.pojo.Goal;

public interface GoalRepos extends CrudRepository<Goal, Integer> {

}
