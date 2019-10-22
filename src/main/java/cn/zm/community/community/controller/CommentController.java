package cn.zm.community.community.controller;

import cn.zm.community.community.dto.CommentDTO;
import cn.zm.community.community.mapper.CommentMapper;
import cn.zm.community.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zfitness
 */
@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;


    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Comment post(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        System.out.println("commentDTO" + commentDTO);
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1);
        commentMapper.insert(comment);

        return comment;
    }
}
