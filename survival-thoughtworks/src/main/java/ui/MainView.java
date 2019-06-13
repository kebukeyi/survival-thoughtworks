package ui;

import com.jfoenix.controls.JFXButton;
import config.TableSetting;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import ui.bean.Device;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainView extends StackPane {


    ExecutorService service1 = Executors.newSingleThreadExecutor();

    ArrayOne arrayOne = new ArrayOne();
    volatile boolean FLAG = true;


    @FXML
    private FlowPane flowpane;

    @FXML
    private JFXButton start;

    @FXML
    private  JFXButton refresh;

    @FXML
    private JFXButton speed1;

    @FXML
    private JFXButton speed2;

    @FXML
    private Label currentspeed;

    MainView() {
        loadFxml();
        refreshDeviceCell(1, 1);//初始化调用
    }

    MainView(int i) {

    }

    @FXML
    void refreshOntion() {
        refreshDeviceCell(1, 2);//初始化调用
        TableSetting.MAX_TIME = 1000;
        currentspeed.setText("");
    }

    @FXML
    void startOntion() throws InterruptedException {
        if ("开始".equals(start.getText())) {
            refresh.setDisable(true);
            start.setText("结束");
            currentspeed.setText(TableSetting.MAX_TIME +"ms");
            refreshDeviceCell(1, 0);
            FLAG = true;
            service1.submit(() -> {
                refreshCell();
            });
        } else {
            refresh.setDisable(false);
            start.setText("开始");
            FLAG = false;
        }

    }

    /*
       加速 1
     */
    @FXML
    void speedOntion1() {
        speed2.setDisable(false);
        TableSetting.MAX_TIME -= 200;
        if (TableSetting.MAX_TIME <= 200) {
            TableSetting.MAX_TIME = 200;
            speed1.setDisable(true);
        }
        currentspeed.setText(TableSetting.MAX_TIME +"ms");
        System.out.println("TableSetting.MAX_TIME 加速" + TableSetting.MAX_TIME);
    }


    /*
      减速 2
    */
    @FXML
    void speedOntion2() {
        speed1.setDisable(false);
        TableSetting.MAX_TIME += 200;
        if (TableSetting.MAX_TIME >= 3000) {
            TableSetting.MAX_TIME = 3000;
            speed2.setDisable(true);
        }
        currentspeed.setText(TableSetting.MAX_TIME +"ms");

        System.out.println("TableSetting.MAX_TIME 减速" + TableSetting.MAX_TIME);
    }


    public void refreshCell() {
        int times = 1;
        int[][] out = null;
        while (true) {
            if (FLAG) {
                while (true) {
                    int cellId = 0;
                    out = arrayOne.ONE(ArrayOne.nextMatrix);
                    for (int i = 0; i < 19; i++) {
                        for (int j = 0; j < 71; j++) {
                            if (out[i][j] == 1) {
                                cellId = (i * 72) + j + 1;//得出  组件的ID
                                System.out.println("cellId : " + cellId);
                                RefreshDeviceCell(1, cellId, 1);//变为蓝色
                            }
                        }
                    }
                    if (!FLAG) {//结束跳出循环
                        break;
                    }
                    times++;
                    if (times == 13) {//结束跳出循环
                        break;
                    }
                    System.out.println("times :  " + times);
                    try {
                        Thread.sleep(TableSetting.MAX_TIME);//3秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    refreshDeviceCell(1, 1);//next =0
                    for (int x = 0; x < 19; x++) {
                        for (int y = 0; y < 71; y++) {
                            ArrayOne.nextMatrix[x][y] = ArrayOne.middleMatrix[x][y];
                        }
                    }
                    init();
                }
            } else {
                continue;
            }

        }
    }


    public void init() {
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 71; j++) {
                ArrayOne.middleMatrix[i][j] = 0;
            }
        }
    }


    /*
         加载时的 初始化
     */
    private void refreshDeviceCell(int groupId, int flag) {
        for (int deviceId = 1; deviceId <= TableSetting.MAX_DEVICE_ID; deviceId++) {
            DeviceCellView cellView = DeviceCellRepo.getCellView(deviceId);
            if (cellView == null) {
                DeviceCellView newDeviceCellView = new DeviceCellView(this, deviceId);
                DeviceCellRepo.setCellView(newDeviceCellView, deviceId);
                FlowPane.setMargin(newDeviceCellView, new Insets(1, 1, 1, 1));
                Platform.runLater(() -> flowpane.getChildren().add(newDeviceCellView));
                cellView = newDeviceCellView;
            }
            Device device = DeviceCellRepo.getDevice(groupId, deviceId);
            if (device == null) {
                cellView.resetCell(groupId, deviceId);
            } else {
                cellView.refreshHook(device);
                cellView.RefreshCell(device, flag);
            }
        }
    }


    /**
     * 点击  按钮时  调用
     *
     * @param groupId
     */
    public void RefreshDeviceCell(int groupId, int deviceId, int flag) {
        DeviceCellView cellView = DeviceCellRepo.getCellView(deviceId);
        if (cellView == null) {
            System.out.println("cellView == null");
            DeviceCellView newDeviceCellView = new DeviceCellView(this, deviceId);
            DeviceCellRepo.setCellView(newDeviceCellView, deviceId);
            Platform.runLater(() -> flowpane.getChildren().add(newDeviceCellView));
            cellView = newDeviceCellView;
        }
        Device device = DeviceCellRepo.getDevice(groupId, deviceId);
        if (device == null) {
            System.out.println("device == null");
            cellView.resetCell(groupId, deviceId);
        } else {
            cellView.refreshHook(device);
            if (flag == 1) {
                cellView.StartrefreshCell(device);
            } else if (flag == 0) {
                cellView.refreshCell(device);
            }
        }
    }


    private void loadFxml() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
