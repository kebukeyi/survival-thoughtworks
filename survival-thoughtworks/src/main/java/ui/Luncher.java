package ui;


import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Luncher extends Application {

    public void start(Stage primaryStage) throws Exception {
        JFXDecorator decorator = new JFXDecorator(primaryStage,new MainView());
        decorator.setCustomMaximize(true);
        primaryStage.setTitle("生命游戏");
        primaryStage.setResizable(false);
        Scene scene = new Scene(decorator);
        scene.getStylesheets().addAll(
                getClass().getResource("/css/main.css").toExternalForm(),
                getClass().getResource("/css/listview.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
