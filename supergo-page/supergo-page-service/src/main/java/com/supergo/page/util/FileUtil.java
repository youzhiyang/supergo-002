package com.supergo.page.util;

import java.io.File;

/**
 * Created by Administrator on 2020/8/8 0008.
 */
public class FileUtil {

    /**
     * 删除单个文件
     * @param path 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        //路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }
}
