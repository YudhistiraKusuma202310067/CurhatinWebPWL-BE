package com.pwl.curhatin.User;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pwl.curhatin.dto.AuthKey;
import com.pwl.curhatin.dto.GetEmail;
import com.pwl.curhatin.dto.GetUsername;
import com.pwl.curhatin.dto.ResponseData;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseData<User>> postUser(@Valid @RequestBody User user, Errors errors) {
        ResponseData<User> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        List<User> storeData = (List<User>) userService.findEmail(user.getEmail());
        List<User> storeData2 = (List<User>) userService.findUsername(user.getUsername());

        if (storeData.isEmpty()) {
            if (storeData2.isEmpty()){
                responseData.setResult(true);
                List<User> value = new ArrayList<>();
                value.add(userService.save(user));
                responseData.setData(value);

                return ResponseEntity.ok(responseData);
            } else {
                responseData.setResult(false);
                responseData.getMessage().add("Username Sudah Digunakan");
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
        } else {
            responseData.setResult(false);
            responseData.getMessage().add("Email Sudah Terdaftar");
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<User>> fetchUser() {
        ResponseData<User> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<User> value = (List<User>) userService.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<User>> fetchUserById(@PathVariable("id") int id) {
        ResponseData<User> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<User> value = new ArrayList<>();
            value.add(userService.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<ResponseData<User>> getUserAuth(@RequestBody AuthKey authKey) {
        ResponseData<User> responseData = new ResponseData<>();

        System.out.print(authKey.getEmail());
        System.out.print(authKey.getPassword());

        try{
        Iterable<User> values = userService.findAuth(authKey.getEmail(), authKey.getPassword());
        responseData.setResult(true);
        responseData.getMessage();
        responseData.setData(values);
        return ResponseEntity.ok(responseData);

        } catch (Exception e ) {
        List<String> message = new ArrayList<>();
        message.add(e.getMessage());
        responseData.setMessage(message);
        responseData.setData(null);
        responseData.setResult(false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PostMapping("/findEmail")
    public ResponseEntity<ResponseData<User>> getEmail(@RequestBody GetEmail getEmail) {
        ResponseData<User> responseData = new ResponseData<>();

        try {
            Iterable<User> values = userService.findEmail(getEmail.getEmail());
            responseData.setResult(true);
            responseData.getMessage();
            responseData.setData(values);
            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            List<String> message = new ArrayList<>();
            message.add(e.getMessage());
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PostMapping("/findUsername")
    public ResponseEntity<ResponseData<User>> getUsername(@RequestBody GetUsername getUsername) {
        ResponseData<User> responseData = new ResponseData<>();

        try {
            Iterable<User> values = userService.findUsername(getUsername.getUsername());
            responseData.setResult(true);
            responseData.getMessage();
            responseData.setData(values);
            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            List<String> message = new ArrayList<>();
            message.add(e.getMessage());
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
}
