package sample;

import java.io.*;
import java.util.Properties;

public class ConfigProperties {

//    根目录开始,src同级目录
    private static final String configPath="config.properties";
    private static final String configFileName="config.properties";
    private static InputStream in= null;
    private static OutputStream out=null;
    private static final  File file=new File(configPath);


    public static void closeAllStream(){

            try {
                if(in!=null){
                    in.close();
                }
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    public static Properties getInputProperties(){
        Properties properties=null;
        try {
            properties=new Properties();
            properties.load(getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static OutputStream getOutPutStream(){
        if(!checkExist()){
            createNewProperties();
        }
        try {
            out=new FileOutputStream("config.properties");
            return out;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    private static InputStream getInputStream() {
//        如果配置文件不存在，则创建文件
        if(!checkExist()){
            createNewProperties();
        }

        try {
            in = new FileInputStream(new File(configPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return in;

    }
    public static void createNewProperties(){
        try {

            if(file.createNewFile()){
                System.out.println("创建"+configFileName+"成功");
                System.out.println("当前绝对路径:"+file.getAbsolutePath());
            }else{
                System.out.println("创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    检查是否存在properties文件
    public static boolean checkExist(){
        if(file.exists()){

            return true;
        }else{
            return false;
        }
    }
}
