package com.github.zhangyinhao1234.study;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SVNCommandTest {


    ObjectMapper mapper = new ObjectMapper();

    String file = "/Users/yinhao.zhang/localSVNTest/test/test.json";
    @Test
    public void wirteFile() throws JsonProcessingException {

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(loadJson());
        //Object obj = mapper.readValue(json, Object.class);
        clearInfoForFile(file);
        appendInfoToFile(file, json);

    }

    private void clearInfoForFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendInfoToFile(String fileName, String info) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
//            info = info + System.getProperty("line.separator");
            fileWriter.write(info);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Map loadJson() {
        HashMap<Object, Object> obj = new HashMap<>();
        obj.put("title", "test");
        obj.put("code", "00001");
        List list = new ArrayList();
        list.add("hello");
        list.add("boo");
        list.add("foo");
        list.add("gg");
        list.add("hahah");
        obj.put("data", list);
        obj.put("o", "12345");
        obj.put("m", "1234");
        obj.put("n", "123455");
        obj.put("p", "123466");
        for (int i = 0; i < 20; i++) {
            obj.put(i + "", i + "123466k");
        }
        return obj;

    }


    public void updateTest() {


    }

    private String username = "zhangyinhao";
    private String password = "123456";


    @Test
    public void svnDiff() throws SVNException, IOException {
        DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
        SVNClientManager svnClientManager = SVNClientManager.newInstance(options, username, password);
        SVNDiffClient svnDiffClient = svnClientManager.getDiffClient();
        svnDiffClient.setIgnoreExternals(false);
        OutputStream os = System.out;
        //  signature :  void doDiff(File[] paths,SVNRevision rN,SVNRevision rM,SVNRevision pegRevision,SVNDepth depth,boolean useAncestry,OutputStream result,Collection changeLists)
        svnDiffClient.doDiff(new File(file), SVNRevision.WORKING,SVNRevision.create(13),SVNRevision.create(14), SVNDepth.INFINITY,true,os,null);
        os.flush();

    }
}
