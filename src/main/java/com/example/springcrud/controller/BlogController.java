package com.example.springcrud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcrud.helper.ResponseHelper;
import com.example.springcrud.model.Blog;
import com.example.springcrud.repo.BlogRepo;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogRepo blogRepo;
    
    @GetMapping("/getAllBlogs")
    public ResponseEntity<Object> getAllBlogs(@RequestParam(defaultValue = "asc") String sort, @RequestParam(defaultValue = "id") String field) {
        try {
            List<Blog> blogList = new ArrayList<>();
            if (sort.equalsIgnoreCase("desc")) blogRepo.findAll(Sort.by(field).descending()).forEach(blogList::add);
            else if(sort.equalsIgnoreCase("asc")) blogRepo.findAll(Sort.by(field).ascending()).forEach(blogList::add);
            else blogRepo.findAll().forEach(blogList::add);
            
            if (blogList.isEmpty()) {
                return ResponseHelper.generateSuccessResponse("Data Not Found", HttpStatus.NO_CONTENT, null);
            }
            return ResponseHelper.generateSuccessResponse("Data Found", HttpStatus.OK, blogList);
        } catch (Exception e) {
            return ResponseHelper.generateErrorResponse("Failed to get Data", HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/getBlogById/{id}")
    public ResponseEntity<Object> getBlogByID(@PathVariable Long id) {
        try {
            Optional<Blog> blogObj = blogRepo.findById(id);
            if (blogObj.isPresent()) {   
                return ResponseHelper.generateSuccessResponse("Data Found", HttpStatus.OK, blogObj.get());
            }
        return ResponseHelper.generateSuccessResponse("Data Not Found", HttpStatus.NO_CONTENT, null);
        } catch (Exception e) {
            System.out.println("===========> " + e.getMessage());   
            return ResponseHelper.generateErrorResponse("Failed to get Data", HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Object> pagination(@PathVariable int offset, @PathVariable int pageSize) {
        try {
            List<Blog> blogList = new ArrayList<>();
            blogRepo.findAll(PageRequest.of(offset, pageSize)).forEach(blogList::add);
            if (blogList.isEmpty()) {
                return ResponseHelper.generateSuccessResponse("Data Not Found", HttpStatus.NO_CONTENT, null);
            }
            return ResponseHelper.generateSuccessResponse("Data Found", HttpStatus.OK, blogList);
        } catch (Exception e) {
            return ResponseHelper.generateErrorResponse("Failed to get Data", HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/postBlog")
    public ResponseEntity<Object> postBlog(@RequestBody Blog blog) {
        try {
            Blog blogObj =  blogRepo.save(blog);
    
            return ResponseHelper.generateSuccessResponse("Successfully Create Data", HttpStatus.OK, blogObj);
        } catch (Exception e) {
            return ResponseHelper.generateErrorResponse("Failed to Create Data", HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/updateBlogById/{id}")
    public ResponseEntity<Object> updateBlogById(@PathVariable Long id, @RequestBody Blog blog) {
        try {
            Optional<Blog> oldBlog = blogRepo.findById(id);
    
            if (oldBlog.isPresent()) {      
                Blog updatedBlog = oldBlog.get();
                updatedBlog.setTitle(blog.getTitle());
                updatedBlog.setBody(blog.getBody());
                updatedBlog.setAuthor(blog.getAuthor());
    
                Blog blogObj = blogRepo.save(updatedBlog);
                // return new ResponseEntity<Blog>(blogObj, HttpStatusCode.valueOf(200));
                return ResponseHelper.generateSuccessResponse("Successfully Update Data", HttpStatus.OK, blogObj);
            }
            return ResponseHelper.generateSuccessResponse("Data Not Found", HttpStatus.NO_CONTENT, null);
        } catch (Exception e) {
            return ResponseHelper.generateErrorResponse("Failed to Update Data", HttpStatusCode.valueOf(500));
        }
    }

    @DeleteMapping("/deleteBlogById/{id}")
    public ResponseEntity<Object> deleteBlogById(@PathVariable Long id) {
        try {
            Optional<Blog> blogObj = blogRepo.findById(id);
            if (blogObj.isPresent()) {
                Blog blogData = blogObj.get();
                blogRepo.deleteById(id);
                // return new ResponseEntity<>(HttpStatusCode.valueOf(200));
                return ResponseHelper.generateSuccessResponse("Data Deleted", HttpStatus.OK, blogData);
            }
            return ResponseHelper.generateSuccessResponse("Data Not Found", HttpStatus.NO_CONTENT, null);
        } catch (Exception e) {
            return ResponseHelper.generateErrorResponse("Failed to Delete Data", HttpStatusCode.valueOf(500));
        }
    }
    
}
