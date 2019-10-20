package cn.zm.community.community.advice;

import cn.zm.community.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器， 出现异常跳转到指定页面
 * @author zfitness
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    /**
     * ModelAndView 代表返回页面
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model) {
        HttpStatus status = getStatus(request);
        //返回error页面
        if (ex instanceof CustomizeException) {
            model.addAttribute("errMsg", ex.getMessage());
        } else {
            model.addAttribute("errMsg", "服务器冒烟了");
        }

        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
