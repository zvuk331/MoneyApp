package moneyhelper.service;

import moneyhelper.entity.*;
import moneyhelper.repository.FinanceRepository;
import moneyhelper.repository.TypesOfCostsRepository;
import moneyhelper.repository.TypesOfIncomesRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FinanceService {

    private final TypesOfIncomesRepository typesOfIncome;
    private final TypesOfCostsRepository typesOfCosts;
    private final FinanceRepository financeRepository;
    private final UserService userService;


    public FinanceService(TypesOfIncomesRepository typesOfIncome, TypesOfCostsRepository typesOfCosts, FinanceRepository financeRepository, UserService userService) {
        this.typesOfIncome = typesOfIncome;
        this.typesOfCosts = typesOfCosts;
        this.financeRepository = financeRepository;
        this.userService = userService;
    }

    public List<TypesOfIncome> findAllIncomes(){
        return typesOfIncome.findAll();
    }

    public List<TypesOfCosts> findAllCosts(){
        return typesOfCosts.findAll();
    }

    public void save(UserFinance finance){
        financeRepository.save(finance);
    }

    public void addCosts(User user, double value, String type) {
        UserFinance finance = user.getFinance();

        UserCosts costs = new UserCosts();
        Date date = new Date();

        costs.setFinance(finance);
        costs.setValue(value);
        costs.setType(type);
        costs.setDate(date);

        finance.getCosts().add(costs);

        finance.setBalance(finance.getBalance() - value);
        financeRepository.save(finance);
        userService.update(user);
    }

    public void addIncome(User user, double value, String type) {
        UserFinance finance = user.getFinance();

        UserIncome income = new UserIncome();
        Date date = new Date();

        income.setFinance(finance);
        income.setValue(value);
        income.setType(type);
        income.setDate(date);
        finance.getIncomes().add(income);

        finance.setBalance(finance.getBalance() + value);
        financeRepository.save(finance);
        userService.update(user);
    }
}
