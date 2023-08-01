package vm.money.track.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

import vm.money.track.endpoint.Controller;
import vm.money.track.pojo.Category;
import vm.money.track.pojo.Loan;
import vm.money.track.pojo.Spend;
import vm.money.track.repos.CategoryRepo;
import vm.money.track.repos.LoanRepos;

@Service
public class DefaultDataService {
    
    private CategoryRepo catRepos;
	private Controller spendController;
    private LoanRepos loanRepo;
    
    private static final String DELIMITER = "-#-"; 
    
    public DefaultDataService(CategoryRepo catRepos, Controller spendController, LoanRepos loanRepo) {
        this.catRepos = catRepos;
        this.spendController = spendController;
        this.loanRepo = loanRepo;
    }

    public boolean pupulateSpendData() {
        System.out.println("populating spends");
        File spendFile = new File("src/main/resources/DefaultData/spend.txt");
		try (   FileReader filereader = new FileReader(spendFile);
                BufferedReader reader = new BufferedReader(filereader)
            ) {
                Random rand = new Random();
                String line;
                while((line=reader.readLine())!=null) {
                    // Arrays.stream(line.split("-#-")).map(item->item+=" ").forEach(System.out::print);
                    List<String> spendItems = Arrays.asList(line.split(DELIMITER));
                    Spend sp = new Spend();
                    sp.setPurpose(spendItems.get(0));
                    sp.setMoney(Integer.parseInt(spendItems.get(1)));
                    sp.setForOthers(Short.parseShort(spendItems.get(2)));
                    sp.setDate(LocalDate.now().withMonth(rand.nextInt(1,12)).withDayOfMonth(new Random().nextInt(1,28)));
                    sp.setCategory(new Category(spendItems.get(3)));
                    System.out.println(sp);
                    this.spendController.add(sp);
                }
		} catch (Exception e) {
            System.out.println(e);
            return false;
            
        }
		return true;
    }

    public boolean populateLoanData() {
        System.out.println("populating Loans");
        File loanFile = new File("src/main/resources/DefaultData/loan.txt");
		try (   FileReader filereader = new FileReader(loanFile);
                BufferedReader reader = new BufferedReader(filereader)
            ) {
                Random rand = new Random();
                String line;
                while((line=reader.readLine())!=null) {
                    List<String> loanItems = Arrays.asList(line.split(DELIMITER));
                    Loan loan = new Loan();
                    loan.setType(Boolean.parseBoolean(loanItems.get(0)));
                    loan.setName(loanItems.get(1));
                    loan.setTotalAmount(Integer.parseInt(loanItems.get(2)));
                    loan.setPendingAmount(Integer.parseInt(loanItems.get(3)));
                    loan.setDate(LocalDate.now().withDayOfMonth(rand.nextInt(1,28)));
                    loan.setReason(loanItems.get(4));
                    loan.setStatus(Boolean.parseBoolean(loanItems.get(5)));
                    this.loanRepo.save(loan);
                    System.out.println(loan);
                }
		} catch (Exception e) {
            System.out.println(e);
            return false;
            
        }
		return true;
    }
}
