package moneyhelper.service;

import moneyhelper.entity.TypesOfCosts;
import moneyhelper.entity.TypesOfIncome;
import moneyhelper.entity.UserFinance;
import moneyhelper.repository.FinanceRepository;
import moneyhelper.repository.TypesOfCostsRepository;
import moneyhelper.repository.TypesOfIncomesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService {

    private final TypesOfIncomesRepository typesOfIncome;
    private final TypesOfCostsRepository typesOfCosts;
    private final FinanceRepository financeRepository;


    public FinanceService(TypesOfIncomesRepository typesOfIncome, TypesOfCostsRepository typesOfCosts, FinanceRepository financeRepository) {
        this.typesOfIncome = typesOfIncome;
        this.typesOfCosts = typesOfCosts;
        this.financeRepository = financeRepository;
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
}
