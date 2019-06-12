package com.github.zhangyinhao1234.study;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.constraints.Email;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

/**
 * @author 【张殷豪】
 * <p>
 * Date 2019/3/28 20:01
 */
public class SimpleDemo {

    public static void main(String[] args) {
        validObj();

//        validproPertyName("age");

//        validproValue("userName","123");
    }

    /**
     * 校验全部字段
     */
    public static void validObj() {
        Validator validator = ValidUtil.getValidator();
        Set<ConstraintViolation<User>> validate = validator.validate(getUser());
        validate.forEach(c ->{
            System.out.println("error:"+c.getMessage()+" propertyName:"+c.getPropertyPath().toString()+"value:"+c.getInvalidValue());


        });
    }

    /**
     * 验证实体中某个属性
     * @param propertyName
     */
    public static void validproPertyName(String propertyName) {
        Validator validator = ValidUtil.getValidator();
        Set<ConstraintViolation<User>> validate = validator.validateProperty(getUser(),propertyName);
        validate.forEach(c ->{
            System.out.println("error:"+c.getMessage()+" message:"+c.getPropertyPath().toString());
        });
    }

    /**
     * 使用指定的class 的规则验证指定属性的值
     * @param propertyName
     * @param val
     */
    public static void validproValue(String propertyName,Object val) {
        Validator validator = ValidUtil.getValidator();
        Set<ConstraintViolation<User>> validate = validator.validateValue(User.class,propertyName,val);
        validate.forEach(c ->{
            System.out.println("error:"+c.getMessage()+" message:"+c.getPropertyPath().toString());
        });
    }



    public  void hibernate1(){

    }



    public static User getUser() {
        return new User("zhangsan", 222, null);
    }


}


