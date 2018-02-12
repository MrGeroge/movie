package com.strival.movie.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * 封装文件存储相关接口
 *
 * Author:zhangyu
 * create on 15/10/22.
 */
public interface FileClient {

    String save(String fileKey, InputStream inStream, long size) throws IOException;


    String asyncSave(String fileKey, InputStream inStream, long size, SaveCallBack callBack);

    interface SaveCallBack{
        void onSuccess(String uri);
        void onFailed(IOException e);
    }
}
