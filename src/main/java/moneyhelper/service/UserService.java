package moneyhelper.service;

import moneyhelper.entity.*;
import moneyhelper.repository.DetailsRepository;
import moneyhelper.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final DetailsRepository detailsRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, DetailsRepository detailsRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.detailsRepository = detailsRepository;
    }

    public Optional<User> findUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public boolean deleteUser(Long id){
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void update(User user){
        userRepository.save(user);
    }
    public void updateDetails(User user){
        User userFromDb = userRepository.findById(user.getId()).get();
        moneyhelper.entity.UserDetails newDetails = user.getDetails();
        moneyhelper.entity.UserDetails detailsFromDb = detailsRepository.findById(userFromDb.getId()).get();

        detailsFromDb.setFullName(newDetails.getFullName());
        detailsFromDb.setAge(newDetails.getAge());
        detailsFromDb.setCountry(newDetails.getCountry());
        detailsFromDb.setRegion(newDetails.getRegion());
        detailsFromDb.setCity(newDetails.getCity());
        detailsFromDb.setPosition(newDetails.getPosition());
        detailsFromDb.setWebsite(newDetails.getWebsite());
        detailsFromDb.setPhoneNumber(newDetails.getPhoneNumber());

        userFromDb.setDetails(detailsFromDb);
        userRepository.save(userFromDb);

    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        moneyhelper.entity.UserDetails details = new moneyhelper.entity.UserDetails();
        UserFinance finance = new UserFinance();
        UserIncome income = new UserIncome();
        UserCosts costs = new UserCosts();
        List<UserCosts> costsList = new ArrayList<>();
        List<UserIncome> incomes = new ArrayList<>();
        income.setFinance(finance);
        costs.setFinance(finance);
        finance.setCosts(costsList);
        finance.setIncomes(incomes);

        user.setFinance(finance);
        finance.setUser(user);
        details.setUser(user);
        user.setDetails(details);
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.USER);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        UserDetails userFrom = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
        return userFrom;
    }
}
