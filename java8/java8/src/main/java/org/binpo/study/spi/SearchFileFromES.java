package org.binpo.study.spi;

import java.util.List;

public class SearchFileFromES implements SearchService{
    @Override
    public List<String> search(String keyWord) {
        System.out.println("从 ES 中检索文件");
        return null;
    }
}
