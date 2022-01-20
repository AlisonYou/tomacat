package sample.component;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.ConfigProperties;

import java.io.IOException;
import java.util.Properties;

public class StartUpConfig {

    private String newPath;
    private boolean status=false;
    private  final static double stageWidth=300;
    private  final static double stageHeight=200;
    private  final static double btnWidth=70;
    private  final static double btnHeight=30;
//    框上面填充
    private  final static double tianchong1Width=70;
    private  final static double tianchong1Height=50;
//    框下面填充
    private  final static double tianchong2Width=70;
    private  final static double tianchong2Height=30;

//    确定/取消按钮之间填充
    private  final static double tianchong3Width=20;
    private  final static double tianchong3Height=20;

    Label label=new Label("startup路径");
    TextField textField=new TextField();
    Button btnYes=new Button("确定");
    Button btnNo=new Button("取消");
    Label tianchong1=new Label();
    Label tianchong2=new Label();
    Label tianchong3=new Label();
    VBox vBox=new VBox();
    HBox hBox1=new HBox();
    HBox hBox2=new HBox();
    Scene scene=new Scene(vBox);
    Stage stage=new Stage();

    public boolean show(){
//        路径输入框
        setLabel();

        setTextField();
        sethBox1();
//        下方确定/取消按钮
        setBtnYes();
        setBtnNo();
//        下方装按钮横盒子
        sethBox2();
//        上方填充，保持按钮居中
        setTianchong1();
        setTianchong2();
        setTianchong3();
//        竖立盒子
        setvBox();
//        窗口stage
        setStage();
        return status;
    }
    public void setStage(){
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("配置startup.bat路径");
        stage.show();
    }
    public void setBtnYes(){
        btnYes.setPrefWidth(btnWidth);
        btnYes.setPrefHeight(btnHeight);
        btnYes.setCursor(Cursor.HAND);
//        点击确定后,获取TextField内容,并写进properties文件
        btnYes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                取值
                newPath=textField.getText();
//                  输入框中存在值
                if(newPath!=null && !newPath.equals("")){
                    Properties properties= ConfigProperties.getInputProperties();

                    properties.setProperty("startUpPath",newPath);
                    try {
//                          将值存入
                        properties.store(ConfigProperties.getOutPutStream(),"startup.bat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    status=true;
                    System.out.println("startUpPath配置完成");
                    stage.close();

                }

            }
        });
    }
    public void setBtnNo(){
        btnNo.setPrefWidth(btnWidth);
        btnNo.setPrefHeight(btnHeight);
        btnNo.setCursor(Cursor.HAND);
        btnNo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
    }

//    提示标签
    public void setLabel(){
        label.setAlignment(Pos.CENTER_LEFT);
        label.setPrefWidth(70);
    }

    public void setvBox(){
        vBox.getChildren().addAll(tianchong1,hBox1,tianchong2,hBox2);
    }
    public void sethBox1(){
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(label,textField);
    }
    public void sethBox2(){
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(btnYes,tianchong3,btnNo);


    }
    public void setTextField(){
        textField.setPrefWidth(200);
        textField.setPrefHeight(30);
        textField.setPromptText("输入绝对路径");

    }
    public void setTianchong1(){
        tianchong1.setPrefSize(tianchong1Width,tianchong1Height);
    }
    public void setTianchong2(){
        tianchong2.setPrefSize(tianchong2Width,tianchong2Height);
    }
    public void setTianchong3(){
        tianchong3.setPrefSize(tianchong3Width,tianchong3Height);
    }


}
