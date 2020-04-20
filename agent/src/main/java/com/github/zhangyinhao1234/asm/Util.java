package com.github.zhangyinhao1234.asm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Util {
    public static void saveclass(byte[] outputByteCode) {
        String dst = "/Users/yinhao.zhang/soft/" + UUID.randomUUID() + ".class";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(dst);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(outputByteCode);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
