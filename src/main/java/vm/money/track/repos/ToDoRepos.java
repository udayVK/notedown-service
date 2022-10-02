package vm.money.track.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vm.money.track.pojo.ToDo;

public interface ToDoRepos extends JpaRepository<ToDo, Integer>{
	
	@Query(value = "select * from to_do where when = ?1", nativeQuery = true)
	List<ToDo> findTodaysToDos(LocalDate day);

	@Query(value = "select * from to_do where when = ?1 and status = false", nativeQuery = true)
	List<ToDo> findTodaysPendingToDos(LocalDate now);

	@Query(value = "update to_do set status = true where id = ?1", nativeQuery = true)
	void markAToDoAsComplete(int id);

	@Query(value = "delete from to_do where status = true and when < ?1", nativeQuery = true)
	void deleteOldDoneTodos(LocalDate minusDays);
}
