package moneyhelper.service;

import moneyhelper.entity.*;
import moneyhelper.repository.DetailsRepository;
import moneyhelper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final DetailsRepository detailsRepository;

    private final MailSender mailSender;

    @Value("${upload.path}")
    private String uploadPath;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, DetailsRepository detailsRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.detailsRepository = detailsRepository;
        this.mailSender = mailSender;
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

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user;
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

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
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

        user.setActivationCode(UUID.randomUUID().toString());
        String message = String.format(
                "Привет, %s \n" +
                        "Для подтверждения пользователя перейдите по ссылке: http://localhost:8080/activate/%s",
                user.getEmail(),
                user.getActivationCode()
        );
        mailSender.send(user.getEmail(), "Activation code", message);

        userRepository.save(user);
    }

    public void updatePassword(User user, String password){
        User userFromDb = userRepository.findByEmail(user.getEmail());
        userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userFromDb);
    }


    public void updatePhoto(MultipartFile file, User user) {
        moneyhelper.entity.UserDetails detailsFromDb = detailsRepository.findById(user.getId()).get();
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "-" + file.getOriginalFilename();

        try {
            file.transferTo(new File(uploadPath + "/" + resultFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        detailsFromDb.setFileName(resultFileName);
        detailsRepository.save(detailsFromDb);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }
}
