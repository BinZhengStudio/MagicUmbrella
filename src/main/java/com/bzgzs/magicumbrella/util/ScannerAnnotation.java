package com.bzgzs.magicumbrella.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ScannerAnnotation<T> {
    private List<Class<T>> scanner;

    public List<Class<T>> getScanner() {
        return scanner;
    }

    private Class annotation;
    public ScannerAnnotation(Class annotation){
        this.scanner = new ArrayList<>();
        this.annotation = annotation;
    }
    public void scannerClasses() throws IOException, ClassNotFoundException {
        String basePack = "com.bzgzs.magicumbrella";
        String basePath = basePack.replaceAll("\\.", "/");
        ClassLoader.getSystemClassLoader();
        Enumeration<URL> dirs = ClassLoader.getSystemClassLoader().getResources(basePath);
        while(dirs.hasMoreElements()){
            URL url = dirs.nextElement();
            if (url.getProtocol().equals("file")){
                List<File> classes = new ArrayList<>();
                listFiles(new File(url.getFile()),classes);
                this.scanner = loadeClasses(classes,basePath,annotation);
            }
        }
    }
    private List<Class<T>> loadeClasses(List<File> classes,String scan,Class annotation) throws ClassNotFoundException {
        List<Class<T>> clazzes = new ArrayList<Class<T>>();
        for(File file : classes) {
            // 因为scan 就是/  ， 所有把 file的 / 转成  \   统一都是：  /
            String fPath = file.getAbsolutePath().replaceAll("\\\\","/") ;
            // 把 包路径 前面的 盘符等 去掉 ， 这里必须是lastIndexOf ，防止名称有重复的
            String packageName = fPath.substring(fPath.lastIndexOf(scan));
            // 去掉后缀.class ，并且把 / 替换成 .    这样就是  com.hadluo.A 格式了 ， 就可以用Class.forName加载了
            packageName = packageName.replace(".class","").replaceAll("/", ".");

            Class<?> tempClass = Class.forName(packageName);
            Annotation classAnnotation = tempClass.getAnnotation(annotation);
            if (classAnnotation == null ){
                continue;
            }

            // 根据名称加载类
            clazzes.add((Class<T>) tempClass);
        }
        return clazzes ;

    }
    private static void listFiles(File dir, List<File> fileList) {
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                listFiles(f, fileList);
            }
        } else {
            if(dir.getName().endsWith(".class")) {
                fileList.add(dir);
            }
        }
    }
}
