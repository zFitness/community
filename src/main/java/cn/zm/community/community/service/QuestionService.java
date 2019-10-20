package cn.zm.community.community.service;

import cn.zm.community.community.dto.PaginationDTO;
import cn.zm.community.community.dto.QuestionDTO;
import cn.zm.community.community.exception.CustomizeErrorCode;
import cn.zm.community.community.exception.CustomizeException;
import cn.zm.community.community.mapper.QuestionMapper;
import cn.zm.community.community.mapper.UserMapper;
import cn.zm.community.community.model.Question;
import cn.zm.community.community.model.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zfitness
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> findAll() {
        List<Question> questions = questionMapper.selectList(null);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    /**
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        int totalPage;
        Integer totalCount = questionMapper.selectCount(null);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;

        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        //size*(page-1)
        Integer offset = size * (page - 1);
        // 分页
        Page<Question> questionPage = new Page<>(page, size);
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");
        IPage<Question> questionIPage = questionMapper.selectPage(questionPage, queryWrapper);
        List<Question> questions = questionIPage.getRecords();
        List<QuestionDTO> questionDTOS = new ArrayList<>();


        for (Question question : questions) {
            User user = userMapper.selectById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);


        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        int totalPage;
        LambdaQueryWrapper<Question> lambda = new QueryWrapper<Question>().lambda();
        lambda.eq(Question::getCreator, userId);
        Integer totalCount = questionMapper.selectCount(lambda);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;

        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        LambdaQueryWrapper<Question> lambdaQuery = Wrappers.<Question>lambdaQuery();
        lambdaQuery.eq(Question::getCreator, userId);
        Page<Question> questionPage = new Page<>(page, size);
        IPage<Question> questionIPage = questionMapper.selectPage(questionPage, lambdaQuery);
        List<Question> questions = questionIPage.getRecords();
        List<QuestionDTO> questionDTOS = new ArrayList<>();


        for (Question question : questions) {
            User user = userMapper.selectById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);


        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 根据id创建问题或者更新问题
     *
     * @param question
     */
    public void createOrUpdate(Question question) {
        //如果没有id
        if (question.getId() == null) {
            //创建问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            //更新问题
            question.setGmtModified(System.currentTimeMillis());
            int rows = questionMapper.updateById(question);
            if (rows != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    /**
     * 增加阅读数
     *
     * @param id
     */
    public void incView(Integer id) {
        questionMapper.incView(id);
    }
}
