package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto dto = postService.createPost(postDto);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //want to get post by id
    //http://localhost:8080/api/posts/particular?id=1

    @GetMapping("/particular")
    public ResponseEntity<PostDto> getPostById(@RequestParam long id){
       PostDto dto = postService.getPostById(id);
       return new ResponseEntity<>(dto,HttpStatus.OK);

    }

    //reading all data from database
//    @GetMapping
//    public List<PostDto> getAllPosts(){
//        List<PostDto> postDtos=postService.getAllPosts();
//        return postDtos;//not use responseEntity anyway bydefault response is 200;
//    }


//    http://localhost:8080/api/posts?pageNo=0&pageSize=5
//    @GetMapping
//    public List<PostDto> getAllPosts(
//            @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
//            @RequestParam(name="pageSize",required = false,defaultValue = "3") int pageSize
//    ){
//        List<PostDto> postDtos=postService.getAllPosts(pageNo,pageSize);
//        return postDtos;//not use responseEntity anyway bydefault response is 200;
//    }

    //sorting

//    //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title
//    @GetMapping
//    public List<PostDto> getAllPosts(
//            @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
//            @RequestParam(name="pageSize",required = false,defaultValue = "3") int pageSize,
//            @RequestParam(name="sortBy",required=false,defaultValue = "id") String sortBy
//    ){
//        List<PostDto> postDtos=postService.getAllPosts(pageNo,pageSize,sortBy);
//        return postDtos;//not use responseEntity anyway bydefault response is 200;
//    }


    //contrilling over dir of sorting
    //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=desc
    @GetMapping
    public List<PostDto> getAllPosts(
            @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
            @RequestParam(name="pageSize",required = false,defaultValue = "3") int pageSize,
            @RequestParam(name="sortBy",required=false,defaultValue = "id") String sortBy,
            @RequestParam(name="sortDir",required=false,defaultValue = "id") String sortDir
    ){
        List<PostDto> postDtos=postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return postDtos;//not use responseEntity anyway bydefault response is 200;
    }

}
