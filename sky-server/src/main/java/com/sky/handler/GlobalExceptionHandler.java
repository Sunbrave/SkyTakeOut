package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    //处理SQL完整性约束违规异常
    /*
        1、主键冲突：插入或更新时主键已经存在
        2、唯一约束冲突：唯一索引字段值重复
        3、外键约束失败
        4、非空字段插入空值
        5、实体类名与数据库字段不一致
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException exception){
        String message = exception.getMessage();
        if (message.contains("Duplicate entry")) {
            String[] mgs = message.split(" ");
            String username = mgs[2];
            return Result.error(username + MessageConstant.ALREADY_EXISTS);
        }else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }

    }

}
