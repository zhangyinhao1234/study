package org.binpo.study.spi;

import java.util.List;

public class SearchFileFromDB implements SearchService {
    @Override
    public List<String> search(String keyWord) {
        System.out.println("从数据库中检索文件");
        return null;
    }
}
