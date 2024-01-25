package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.payload.PostDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;

public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () ->new ResourceNotFoundException("Post Not Found With id: "+postId)
        );

        Comment comment = new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());

        //set comment to post,this will do 1->n
        comment.setPost(post);

        Comment Savedcomment = commentRepository.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(Savedcomment.getId());
        dto.setEmail(Savedcomment.getEmail());
        dto.setText(Savedcomment.getText());

        return dto;
    }
}
