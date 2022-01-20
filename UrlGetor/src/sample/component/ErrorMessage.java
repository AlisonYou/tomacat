package sample.component;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class ErrorMessage {
    private String content="出错了!";
    private Parent parent;
    private double width,height;
    public ErrorMessage(){

    }
    public ErrorMessage(String content){
        this.content=content;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Stage getErrprMessage(){
        Scene scene=new Scene(parent,width,height);
        Stage stage=new Stage();

        TextArea textArea=new TextArea();
        Button btnYes=new Button("确定");
        VBox vBox=new VBox();
        textArea.setText(content);
        stage.setScene(scene);
        return null;

    }
}
