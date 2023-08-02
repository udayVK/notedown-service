package vm.money.track;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vm.money.track.service.DefaultDataService;

@SpringBootApplication
@RestController
@Configuration
public class TrackApplication {

	@Autowired
	private DefaultDataService dataService;

	public static void main(String[] args) {
		SpringApplication.run(TrackApplication.class, args);
	}
	
	@GetMapping("/")
	public String hello(){
		return "hello vm";
	}

//    @Bean
//    DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("org.postgresql.Driver");
//        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/spends");
//        dataSourceBuilder.username("postgres");
//        dataSourceBuilder.password("nothing");
//        return dataSourceBuilder.build();
//    }

	@GetMapping(path = "/populate")
	public String populateTheDB() throws IOException {
		boolean spendsFlag = this.dataService.pupulateSpendData() ;
		boolean loansFlag = this.dataService.populateLoanData();
		if(spendsFlag && loansFlag)
		return "success";
		return "something went wrong";
	}
}

// File spendFile = new File("classpath:defaultdata/spend.txt");
// File spendFile = new File("../resources/defaultdata/spend.txt");
// File spendFile = new File("resources/DefaultData/spend.txt");