package org.binpo.study.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SearchServiceMain {

    public static void main(String[] args) {
        ServiceLoader<SearchService> load = ServiceLoader.load(SearchService.class);
        Iterator<SearchService> iterator = load.iterator();
        while (iterator.hasNext()){
            SearchService next = iterator.next();
            next.search("hello");
        }
    }
}
