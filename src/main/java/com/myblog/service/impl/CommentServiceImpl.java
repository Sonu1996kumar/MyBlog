package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;

import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper=modelMapper;
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

    @Override
    public void deleteComment(long id) {

        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment Not Found for Id: " + id)
        );

        comment.setText(commentDto.getText());
        comment.setEmail(commentDto.getEmail());



        Comment savedComment=commentRepository.save(comment);

        CommentDto dto = new CommentDto();
        dto.setText(savedComment.getText());
        dto.setEmail(savedComment.getEmail());
        dto.setId(savedComment.getId());


        return dto;



    }
}
