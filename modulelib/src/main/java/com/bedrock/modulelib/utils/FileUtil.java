package com.bedrock.modulelib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    private static FileUtil singleton;
    public static FileUtil getSingleton(){
        if (singleton == null){
            synchronized (FileUtil.class){
                if (singleton == null){
                    singleton = new FileUtil();
                }
            }
        }
        return singleton;
    }

    private String getUUID(){
        return UUID.randomUUID().toString();
    }

    public String saveBitmap2AppFile(Context context , Bitmap bitmap){
        File file = new File(context.getFilesDir(),getUUID() + ".jpg");

        try {
            if (! file.exists()){
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            if (bitmap == null){
                return "";
            }

            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

    public String saveString2AppFile(Context context,String name,String content){
        if (content == null){
            return "";
        }
        File file = new File(context.getFilesDir(),name + ".txt");
        FileOutputStream fos;
        BufferedWriter writer;
        try {
            if (!file.exists()){
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(content);
            writer.close();
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }


    public long getFilesTotalSize(List<String> strings){
        long totalSize = 0;
        for (String path : strings){
            File file = new File(path);
            if (file.exists()){
                if (file.isFile()){
                    totalSize += file.length();
                }else {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(file.getAbsolutePath());
                    totalSize += getFilesTotalSize(list);
                }
            }
        }
        return totalSize;
    }

    public void deleteFile(String path){
        File file = new File(path);
        if (!file.exists()){
            return;
        }
        if (file.isDirectory()){
            for (File f : file.listFiles()){
                deleteFile(f.getAbsolutePath());
            }
            file.delete();
        }else if (file.exists()){
            file.delete();
        }

    }

    public String getStringContent(String path){
        File file;
        String content = "";
        FileInputStream fis = null;
        try {
            file = new File(path);
            fis = new FileInputStream(file);
            int length = fis.available();
            byte[] buff = new byte[length];
            fis.read(buff);

            content = new String(buff,"utf-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null){
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content;
    }


    public String getTxtMimeType(String path){
        File file = new File(path);
        //BufferedReader reader;

        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream in = new BufferedInputStream(fis);
            in.mark(4);
            byte[] first3bytes = new byte[3];
            in.read(first3bytes);//找到文档的前三个字节并自动判断文档类型。
            in.reset();

            if (first3bytes[0] == (byte) 0xEF && first3bytes[1] == (byte) 0xBB
                    && first3bytes[2] == (byte) 0xBF) {// utf-8
                return "utf-8";

                //reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

            } else if (first3bytes[0] == (byte) 0xFF
                    && first3bytes[1] == (byte) 0xFE) {
                return "unicode";
//                reader = new BufferedReader(
//                        new InputStreamReader(in, "unicode"));
            } else if (first3bytes[0] == (byte) 0xFE
                    && first3bytes[1] == (byte) 0xFF) {
                return "utf-16be";
//                reader = new BufferedReader(new InputStreamReader(in,
//                        "utf-16be"));
            } else if (first3bytes[0] == (byte) 0xFF
                    && first3bytes[1] == (byte) 0xFF) {
                return "utf-16le";

//                reader = new BufferedReader(new InputStreamReader(in,
//                        "utf-16le"));
            } else {
                return "GBK";
                //reader = new BufferedReader(new InputStreamReader(in, "GBK"));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String convertCodeAndGetText(String filePath){
        String code = "";
        FileInputStream fis = null;
        BufferedInputStream in = null;
        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            in = new BufferedInputStream(fis);
            in.mark(4);
            byte[] first3bytes = new byte[3];

            in.read(first3bytes);
            in.reset();

            if (first3bytes[0] == (byte)0xef && first3bytes[1] == (byte)0xbb && first3bytes[2] == (byte)0xbf){
                code = "utf-8";
            }else if (first3bytes[0] == (byte) 0xFF && first3bytes[1] == (byte) 0xFE){
                code = "unicode";
            }else if (first3bytes[0] == (byte) 0xFE && first3bytes[1] == (byte) 0xFF){
                code = "utf-16be";
            }else if (first3bytes[0] == (byte) 0xFF && first3bytes[1] == (byte) 0xFF){
                code = "utf-16le";
            }else {
                code = "GBK";
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return code;
    }

}













