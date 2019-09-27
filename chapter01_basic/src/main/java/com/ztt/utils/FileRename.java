package com.ztt.utils;

import java.io.File;
import java.util.Base64;
import java.util.stream.BaseStream;

/**
 * Created by zhoutaotao on 2018/3/23.
 */
public class FileRename {

    public static void main(String[] args) {
        File file =new File("/Volumes/ztt/6JW+");

        listFile(file);
    }

    private static void listFile(File file) {
        File[] fileList = file.listFiles();
        for(File f :fileList){
            if(f.isDirectory() ){
                renameFile(f,f.getParent(), Base64.getEncoder().encodeToString(f.getName().getBytes()));
                listFile(f);
            }else {

                renameFile(f,f.getParent(), Base64.getEncoder().encodeToString(f.getName().getBytes()));
            }
        }
    }


    public static void renameFile(File oldFile ,String path,String newname){
            File newfile=new File(path+"/"+newname);
            if(!oldFile.exists()){
                return;//重命名文件不存在
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname+"已经存在！");
            else{
                oldFile.renameTo(newfile);
            }

    }
}
