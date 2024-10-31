package com.socialmedialapplication.service;

import com.socialmedialapplication.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDeoService {
    // JPA/Hibernate > Database
    // UserDeoService > Static List

    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;
    static {
        users.add(new User(++userCount, "Pradeep", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount, "Yash", LocalDate.now().minusYears(24)));
        users.add(new User(++userCount, "Ravi", LocalDate.now().minusYears(26)));
    }
    //public List<User> findAll()

    public List<User> findAll(){
        return users;
    }

    //public User saveUser(User user)
    public User saveUser(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    //public User findOne()

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
}
