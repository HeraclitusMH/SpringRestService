package com.heraclitus.springstudyproject.restfulwebservice.dao;

import com.heraclitus.springstudyproject.restfulwebservice.beans.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static Integer userCounts = 3;

    static{
        users.add(new User(1,"Luca", new Date()));
        users.add(new User(2,"Tiziano", new Date()));
        users.add(new User(3,"Paco", new Date()));
    }

    public List<User> findAllUsers(){
        return users;
    }

    public User saveUser(User user){
        if(user.getId() == null){
            user.setId(++userCounts);
        }
        users.add(user);
        return user;
    }

    public User findUserById(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User deleteUserById(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
