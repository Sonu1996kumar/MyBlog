package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    //http://localhost:8080/api/posts?id=1

    @GetMapping
    public ResponseEntity<PostDto> getPostById(@RequestParam long id){
       PostDto dto = postService.getPostById(id);
       return new ResponseEntity<>(dto,HttpStatus.OK);

    }

}
