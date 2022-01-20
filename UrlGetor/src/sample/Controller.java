package sample;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.component.StartUpConfig;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;


public class Controller {

    /*
    * 部件
    *
    *
    * */
    public TextField port,path;
    public TextArea showUrl,showStatus;
    public Button get,start,clear,open;
    public CheckMenuItem setSimpleMode;
    public GridPane gridPane;
    public MenuBar menuBar;
    public ColorPicker colorPicker;
    /*
     *  局部变量
     *
     * */
    public String notice="";
    public  Desktop desktop=Desktop.getDesktop();
    private TomacatURL tomacatURL=new TomacatURL();
    private String url;
    private boolean simpleMode=false;


    //    startup.bat启动路径
    private static String startUpPath;
    static{
       readConfigProperties();
    }

//    实时显示url
    public void getURL(){
        tomacatURL.setPort(port.getText());
        tomacatURL.setRelativePath(path.getText());
        url=tomacatURL.getUrl();
        showUrl.setText(url);
    }
    public static void readConfigProperties(){
        Properties properties=ConfigProperties.getInputProperties();
        startUpPath=properties.getProperty("startUpPath");
        ConfigProperties.closeAllStream();
    }
//    打开tomcat服务器
    public void start(){
        readConfigProperties();
        System.out.println("startUpPath:"+startUpPath);
        if(startUpPath!=null && !startUpPath.equals("")){
            try {
                File file=new File(startUpPath);
                if(file.exists()){
                    desktop.open(file);
                    this.addNewMessage("服务器启动成功...\n");
                }else{
                    this.addNewMessage("服务器启动失败...\n配置路径文件不存在");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            this.addNewMessage("服务器启动失败...\nstartup配置路径为null或空值");
        }

    }
//    open按钮绑定方法，默认网页打开url
    public void open(){
        try {
            if(!this.check()){
                throw new NullPointerException("端口号/相对路径异常");
            }
            desktop.browse(new URI(tomacatURL.getUrl()));
        } catch (IOException | URISyntaxException | NullPointerException e ) {
            this.addNewMessage("打开失败...\n请检查PORT/Relative Path是否存在空值");
            e.printStackTrace();
        }
    }

//    get按钮绑定方法，显示当前url
    public void showURL(){
        if(url==null){
            url=tomacatURL.getUrl();
        }
        if(this.check()){
            this.addNewMessage(url);
        }else{
            this.addNewMessage("获取失败...\n请检查PORT/Relative Path是否存在空值");
        }

    }
    public void addNewMessage(String str){
        this.notice+=str+"\n\n";
        showStatus.setText(notice);
    }

//    判断端口号和相对路径是非为  null和空
    public boolean check(){
        String port,relativePath;
        port=tomacatURL.getPort();
        relativePath=tomacatURL.getRelativePath();
        if(port!=null && relativePath!=null && !port.equals("") && !relativePath.equals("")){
            return true;
        }else{
            return false;
        }
    }

//    清除状态显示

    public void clearStatus(){
        this.notice="";
        this.addNewMessage("");
    }
    public void setStartUpPath(){
//        配置startup.bat窗口组件
        StartUpConfig startUpConfig=new StartUpConfig();
        if(startUpConfig.show()){
            addNewMessage("配置成功!");
        }
    }
    public void aboutAuthor(){
        this.clearStatus();
        showStatus.setText("作者:YouLiBin\n联系方式:ylb305042026(wx)\n时间:2022.1.19\n版本:1.2\n欢迎使用本软件");
    }

//    关于-源代码
    public void myDemo(){
        try {
            desktop.browse(new URI("https://github.com/AlisonYou/tomcat"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void setSimpleMode(){
        if(setSimpleMode.isSelected()){
            gridPane.setStyle("-fx-background-color:white");
            menuBar.setStyle("-fx-background-color:white");
        }else{

            gridPane.setStyle("-fx-background-color:#A94DC1;");
            menuBar.setStyle("-fx-background-color:#A94DC1;");
        }


    }
    public void changeColor(){
        Color color=colorPicker.getValue();

        double red=color.getRed()*255;
        double green=color.getGreen()*255;
        double blue=color.getBlue()*255;

        String rgb="rgb("+red+","+green+","+blue+");";
        String fontColor="black;";

        System.out.println(red+","+green+","+blue);
        gridPane.setStyle("-fx-background-color:"+rgb);
        menuBar.setStyle("-fx-background-color:"+rgb);

    }

}
