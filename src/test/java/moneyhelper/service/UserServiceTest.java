package moneyhelper.service;

import moneyhelper.entity.User;
import moneyhelper.entity.UserDetails;
import moneyhelper.repository.UserRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    public UserServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserById_IsNotNull() {
        User user = new User();
        user.setId(5L);
        given(userRepository.findById(5L)).willReturn(Optional.of(user));
        assertThat(userRepository.findById(5L)).isNotNull();
        assertThat(userRepository.findById(5L).get().getId()).isEqualTo(5L);
    }

    @Test
    public void findUserById_IsNull() {
        given(userRepository.findById(5L)).willReturn(null);
        Optional<User> user = userRepository.findById(5L);
        assertThat(user).isNull();
    }

    @Test
    public void allUsers_IsNotNull() {
        List<User> users = userRepository.findAll();
        assertThat(users).isNotNull();
    }

    @Test
    public void allUsers_Should_Return_List() {
        List<User> users = Arrays.asList(
                new User("any@gmail.com"),
                new User("alexandr@gamil.com"));

        given(userRepository.findAll()).willReturn(users);

        List<User> list = Arrays.asList(
                new User("any@gmail.com"),
                new User("alexandr@gamil.com"));

        assertEquals(list,users);
    }

    @Test
    public void allUsers_Should_Return_Null(){
        given(userRepository.findAll()).willReturn(null);
        List<User> users = userRepository.findAll();
        assertThat(users).isNull();
    }

    @Test
    public void allUsers_Should_Return_EmptyList(){
        List<User> emptyList = new ArrayList<>();
        given(userRepository.findAll()).willReturn(emptyList);
        List<User> users = userRepository.findAll();
        assertThat(users).isEmpty();
    }

    @Test
    public void deleteUser() {
        User user = new User("any@gmail.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(new User());
        boolean removed = false;
        if (user != null){
            users.remove(user);
            if (!users.contains(user)){
                removed = true;
            }
        }
        assertThat(removed).isTrue();
    }

    @Test
    public void update() {
        User user = new User("any@gmail.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        if (users.contains(user)){
            users = users.stream().filter(u -> u.getEmail().equals(user.getEmail())).collect(Collectors.toList());
            User userFromList = users.get(0);
            if (!user.equals(userFromList)){

                if (!user.getDetails().equals(userFromList.getDetails())){
                    userFromList.setDetails(user.getDetails());
                }

                else if (!user.getPassword().equals(userFromList.getPassword())){
                    userFromList.setPassword(user.getPassword());
                }

                else if (!user.getFinance().equals(userFromList.getFinance())){
                    userFromList.setFinance(user.getFinance());
                }

            }
            assertEquals(userFromList, user);
        }

    }

    @Test
    public void updateDetails() {
        List<User> users = new ArrayList<>();
        User user = new User("any@gmail.com");
        user.setDetails(new UserDetails());
        users.add(user);
        if (users.contains(user)){
            users = users.stream().filter(u -> u.getEmail().equals(user.getEmail())).collect(Collectors.toList());
            User userFromList = users.get(0);
            // пройтись по полям объекта сравнить их, и если есть отличие то присвоить это отличие объекту из списка
            if  (!user.getDetails().equals(userFromList.getDetails())){
                userFromList.setDetails(user.getDetails());
            }
            assertEquals(users.get(0), user);
        }

    }

    @Test
    public void saveNewUser() {
        List<User> users = new ArrayList<>();
        User user = new User();
        if (user !=null){
            users.add(user);
        }
        assertThat(users).isNotEmpty();
    }

    @Test
    public void findUserByEmail_IsExist() {
        given(userRepository.findByEmail("any@gmail.com")).willReturn(new User("any@gmail.com"));
        User user = userRepository.findByEmail("any@gmail.com");
        assertThat(user).isNotNull();
    }

    @Test
    public void findUserByEmail_IsNotExist(){
        given(userRepository.findByEmail("any@gmail.com")).willReturn(null);
        User user = userRepository.findByEmail("any@gmail.com");
        assertThat(user).isNull();
    }
}