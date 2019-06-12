package org.binpo.study.discovery;/**
 * @author 【张殷豪】
 * Date 2019/6/12 14:52
 */

import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.nepxion.discovery.plugin.framework.adapter.PluginAdapter;
import com.nepxion.discovery.plugin.strategy.adapter.DiscoveryEnabledStrategy;
import com.nepxion.discovery.plugin.strategy.service.context.ServiceStrategyContextHolder;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author yinhao.zhang  适合在网关使用，和配置文件方式存在冲突，使用会抛出异常
 * @Date 2019/6/12 14:52
 **/
public class MyDiscoveryEnabledStrategy implements DiscoveryEnabledStrategy {

    Logger LOG = LoggerFactory.getLogger(MyDiscoveryEnabledStrategy.class);
    @Autowired
    private ServiceStrategyContextHolder serviceStrategyContextHolder;

    @Autowired
    private PluginAdapter pluginAdapter;

    @Override
    public boolean apply(Server server) {

        return applyFromHeader(server);
    }

    private boolean applyFromHeader(Server server) {
        String token = serviceStrategyContextHolder.getHeader("token");
        String serviceId = pluginAdapter.getServerServiceId(server);
        //需要路由的服务的version
        String version = pluginAdapter.getServerMetadata(server).get(DiscoveryConstant.VERSION);
        String region = pluginAdapter.getServerMetadata(server).get(DiscoveryConstant.REGION);

        LOG.info("负载均衡用户定制触发：token={}, serviceId={}, version={}, region={}", token, serviceId, version, region);

        String filterServiceId = "discovery-springcloud-example-c";
        String filterToken = "1.1";
//        if (StringUtils.equals(serviceId, filterServiceId) && StringUtils.isNotEmpty(token) && token.contains(filterToken)) {
//            LOG.info("过滤条件：当serviceId={} && Token含有'{}'的时候，不能被Ribbon负载均衡到", filterServiceId, filterToken);
//            return false;
//        }

        //对 提供的服务版本为 1.1 直接放行
        if(filterToken.equals(version)){
            LOG.info("过滤条件：当filterToken={} 被负载均衡器负载", version);
            return true;
        }
        return false;
    }
}
