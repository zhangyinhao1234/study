package org.binpo.study.discovery;/**
 * @author 【张殷豪】
 * Date 2019/6/10 16:07
 */

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @Author yinhao.zhang
 * @Date 2019/6/10 16:07
 **/
@Configurable
public class SimpleAConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.TRACE;
    }
}
