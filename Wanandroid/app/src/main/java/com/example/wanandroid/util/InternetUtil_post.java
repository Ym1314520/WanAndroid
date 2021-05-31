package com.example.wanandroid.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class InternetUtil_post {
    private String TAG="Ym";

    public void sendPostNetRequest(final String mUrl, final HashMap<String, String> params, BackInternet internet) {
//开启子线程执行网络请求
        Log.d(TAG, "sendPostNetRequest: "+"kaishila!");
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(mUrl);//这一行可能会导致MalformedURLException(url格式异常)，所以要try catch掉
                            HttpURLConnection connection = (HttpURLConnection)
                                    url.openConnection();
                            connection.setRequestMethod("POST");//设置请求方式为POST
                            connection.setConnectTimeout(8000);//设置最大连接时间，单位为毫秒，超出这个设定的时间将会导致连接超时
                            connection.setReadTimeout(8000);//设置最大的读取时间，单位为毫秒，超出这个设定的时间将会导致读取超时
                            connection.setDoOutput(true);//允许输入流
                            connection.setDoInput(true);//允许输出流
                            StringBuilder dataToWrite = new StringBuilder();//构建参数值
                            for (String key : params.keySet()) {
                                dataToWrite.append(key).append("=").append(params.get(key)).append("&");//拼接参数
                            }
                            connection.connect();//正式连接
                            OutputStream outputStream = connection.getOutputStream();//开启输入流
                            outputStream.write(dataToWrite.substring(0,dataToWrite.length() - 1).getBytes());//去除最后一个&
                            InputStream in = connection.getInputStream();//从接口处获取输入流
                            String responseData = InternetUtil_post.this.StreamToString(in);//这里就是服务器返回的数据!!!!
                            Log.d(TAG, "run: "+responseData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    private String StreamToString(InputStream in) {
        StringBuilder sb = new StringBuilder();//新建一个StringBuilder，用于一点一点构架字符串
        String oneLine;//流转换为字符串的一行
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));//由inputStream获取BufferReader，用于读取二进制流
        try {
            while ((oneLine = reader.readLine()) != null) {//readLine方法将读取一行数据，并转化为String类型，当读取的一行数据为null时，就代表当前数据已经读取完毕
                sb.append(oneLine).append('\n');//拼接字符串并且增加换行，提高可读性
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();//关闭InputStream
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();//将拼接好的字符串返回出去
    }

}
