package com.mrcoder.sbredispubsub.utils;


import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: 文件工具类
 */
@Component
public class FileUtil {

    public String readFileByLine(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(ResourceUtils.getFile(filePath));
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String content = "";
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            content = content + line + "\n";
        }
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();
        return content;
    }

}
