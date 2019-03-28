package com.github.zhangyinhao1234.study;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author 【张殷豪】
 * Date 2019/3/28 20:02
 */
public class ValidUtil {


    public static Validator getValidator(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;

    }
}
