package com.github.zhangyinhao1234.study.agent;

public class LinuxService implements Service {
    @Override
    public void searchByGoogle() {
        System.out.println("Searching with Google by LinuxService");
    }
}
