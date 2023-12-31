package com.pwl.curhatin.Category;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pwl.curhatin.dto.GetCategory;
import com.pwl.curhatin.dto.ResponseData;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> postCategory(@Valid @RequestBody Category category, Errors errors) {
        
        ResponseData<Category> responseData = new ResponseData<>();
        
        if (errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            
            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        
        List<Category> storeData = (List<Category>) categoryService.findCategoryName(category.getCategoryName());
            
        if (storeData.isEmpty()){
            responseData.setResult(true);
            List<Category> value = new ArrayList<>();
            value.add(categoryService.save(category));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.setResult(false);
            responseData.getMessage().add("Kategori sudah ada");
             responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<Category>> fetchCategory() {
        ResponseData<Category> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Category> value = (List<Category>) categoryService.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Category>> fetchCategoryById(@PathVariable("id") int id) {
        ResponseData<Category> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Category> value = new ArrayList<>();
            value.add(categoryService.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> updateCategory(@Valid @RequestBody Category category, Errors errors) {

        ResponseData<Category> responseData = new ResponseData<>();
        if(category.getId() != 0){
            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }
                responseData.setResult(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
            responseData.setResult(true);
            List<Category> value = new ArrayList<>();
            value.add(categoryService.save(category));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.getMessage().add("ID is Required");
            responseData.setResult(false);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteStudentsById(@PathVariable("id") int id) {
        ResponseData<Void> responseData = new ResponseData<>();
        try {
            categoryService.removeOne(id);
            responseData.setResult(true);
            responseData.getMessage().add("Successfully Remove");

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PostMapping("/findCategory")
    public ResponseEntity<ResponseData<Category>> getCategory(@RequestBody GetCategory getCategory) {
        ResponseData<Category> responseData = new ResponseData<>();

        try {
            Iterable<Category> values = categoryService.findCategoryName(getCategory.getCategoryName());
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
