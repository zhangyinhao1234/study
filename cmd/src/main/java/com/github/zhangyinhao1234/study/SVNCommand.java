package com.github.zhangyinhao1234.study;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SVNCommand {

    String host;
    String userName;
    String pwd;

    public SVNCommand(String host, String userName, String pwd) {
        this.host = host;
        this.userName = userName;
        this.pwd = pwd;
    }


    /**
     * //当前目录以及子目录下的所有文件都更新到最新版本
     * svn update
     * <p>
     * //更新文件到最新版
     * svn update 123.api.doc
     * <p>
     * //更新文件到指定版本
     * svn update -r 1 123.api.doc
     *
     * @param dir 文档所在目录
     * @param fileName 文件名 不传文件名更新此目录文件到最新
     * @param version 版本号 更新到最新不传
     * @throws IOException
     */
    public void update(String dir, String fileName, String version) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        StringBuilder buf = new StringBuilder();
        buf.append("svn update ");
        if (StringUtils.isNotBlank(version)) {

            buf.append("-r " + version + " ");
        }
        if (StringUtils.isNotBlank(dir) && StringUtils.isNotBlank(fileName)) {
            buf.append(dir);
        }

        if (StringUtils.isNotBlank(fileName)) {
            buf.append(fileName);
        }

        Process process = runtime.exec(buf.toString());
        process.waitFor();

        process.getInputStream();

        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = stdoutReader.readLine()) != null) {
            System.out.println(line);
        }

    }

}
