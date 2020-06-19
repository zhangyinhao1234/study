package org.binpo.study.spi;

import java.util.List;

/**
 * 搜索服务接口
 */
public interface SearchService {

    public List<String> search(String keyWord);

}
