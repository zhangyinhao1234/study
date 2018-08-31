package com.github.zhangyinhao1234.study.hello;

public class ExampleService {
    private String hi = "";
    
    public ExampleService() {}

    public ExampleService(String name) {
        hi = "hi!" + name;
    }

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

}
