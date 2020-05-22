package com.trainingmanagesys.conf.exceptionHandler;

import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.conf.resultCode.ResultCode;
import com.trainingmanagesys.conf.resultVO.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author RudeCrab
 * @description 全局异常处理
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        System.out.println("APIException : " + e.getMsg());
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

    // JSON返回参数错误异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        System.out.println("MethodArgument: " + objectError.getDefaultMessage());
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

    // 捕获前端传递参数过来时的错误
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO<String> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        // 整条错误信息实例："getUserById.uid: uid不能为空"
        String messageAll = e.getMessage();
        // 对错误信息进行剪切，取后半段
        String[] message = messageAll.split(" ");
        // 然后提取错误提示信息进行返回
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, message[1]);
    }

    // 捕获前端传递参数过来时的错误
    @ExceptionHandler(NullPointerException.class)
    public ResultVO<String> NullPointerExceptionHandler(NullPointerException e) {
        // 整条错误信息实例："getUserById.uid: uid不能为空"
        String messageAll = e.getMessage();
        System.out.println("显示所有的错误信息: " + messageAll);
        // 对错误信息进行剪切，取后半段
        String[] message = messageAll.split(" ");
        // 然后提取错误提示信息进行返回
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, message[1]);
    }

}
