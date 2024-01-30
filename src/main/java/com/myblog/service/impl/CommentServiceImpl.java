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




//update without postId
//    @Override
//    public CommentDto updateComment(long id, CommentDto commentDto) {
//        Comment comment = commentRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("Comment Not Found for Id: " + id)
//        );
//
////        by me
////        comment.setText(commentDto.getText());
////        comment.setEmail(commentDto.getEmail());
////
////        Comment savedComment=commentRepository.save(comment);
////
////        CommentDto dto = new CommentDto();
////        dto.setText(savedComment.getText());
////        dto.setEmail(savedComment.getEmail());
////        dto.setId(savedComment.getId());
////        return dto;
//
//        Comment c = mapToEntity(commentDto);
//        c.setId(comment.getId());
//        Comment savedComment=commentRepository.save(c);
//
//        return mapToDto(savedComment);
//
//    }


    //update with postId
    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
            Post post = postRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("Post Not Found for Id: " + id)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment Not Found for Id: " + id)
        );

        Comment c = mapToEntity(commentDto);
       c.setId(comment.getId());
       c.setPost(post);
       Comment savedComment=commentRepository.save(c);

       return mapToDto(savedComment);

    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto=modelMapper.map(comment,CommentDto.class);
        return dto;
    }

    Comment mapToEntity(CommentDto commentDto){
        Comment comment=modelMapper.map(commentDto,Comment.class);
        return comment;
    }

}
