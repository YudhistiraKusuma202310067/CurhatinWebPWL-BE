package com.pwl.curhatin.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public User save(User user) {
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(bcryptHashString);

        return userRepo.save(user);
    }

    public User findOne(int id) {
        return userRepo.findById(id).get();
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public Iterable<User> findAuth(String email, String password){
        List<User> storeData = (List<User>) userRepo.findEmail(email);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), storeData.get(0).getPassword());
        password = storeData.get(0).getPassword();

        if (result.verified == true) {
            return userRepo.findUserAuth(email, password);
        } else {
            return null;
        }
    }

    public Iterable<User> findEmail(String email) {
        return userRepo.findEmail(email);
    }

    public Iterable<User> findUsername(String username) {
        return userRepo.findUsername(username);
    }
}
