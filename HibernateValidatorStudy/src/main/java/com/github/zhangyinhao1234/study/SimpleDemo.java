package com.github.zhangyinhao1234.study;

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
//        validObj();

//        validproPertyName("age");

        validproValue("userName","123");
    }

    /**
     * 校验全部字段
     */
    public static void validObj() {
        Validator validator = ValidUtil.getValidator();
        Set<ConstraintViolation<User>> validate = validator.validate(getUser());
        validate.forEach(c ->{
            System.out.println("error:"+c.getMessage()+" propertyName:"+c.getPropertyPath().toString());
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





    public static User getUser() {
        return new User("zhangsan", 222, null);
    }


}

class User {
    @NotNull(message = "姓名不能为空")
    String userName;

    @Max(value = 100,message = "年龄不能大于100")
    @Min(value = 0,message = "年龄不能小于0")
    Integer age;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 12, message = "密码长度必须在{min}和{max}之间")
    String passWord;

    @NotNull(message = "日期不能为空!")
    @Past(message = "你只能输入过去的日期")
    private Date birthday;

    @NotNull(message = "邮件不能为空!")
    @Email(message = "邮件格式不正确")
    private String email;


    public User() {
    }

    public User(String userName, Integer age, String passWord) {
        this.userName = userName;
        this.age = age;
        this.passWord = passWord;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
