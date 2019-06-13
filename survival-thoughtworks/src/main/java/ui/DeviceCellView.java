package ui;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import ui.bean.Device;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class DeviceCellView extends StackPane {

    private AtomicReference<Device> hookedDeviceGetter = new AtomicReference<>();
    private MainView mainView = new MainView(1);
    private int id;
    private int groupId;
    private int deviceId;

    @FXML
    private Tab tabStatus;

    @FXML
    private JFXButton charge;


    DeviceCellView(MainView mainView, int i) {
        loadFxml();
    }

    /*
      点击按钮事件
     */
    @FXML
    void chargeAction() {
        Device device = hookedDeviceGetter.get();
        System.out.println("device getGroupId  :  " + device.getGroupId() + "   getDeviceId :   " + device.getDeviceId() + "  getE_id :  " + device.getE_id());
        mainView.RefreshDeviceCell(device.getGroupId(), device.getDeviceId(), 0);
    }


    /**
     * 点击按钮  调用 数组赋值
     * 红色代表初始化  0
     * 蓝色代表选中     1
     */
    public void refreshCell(Device device) {
        Platform.runLater(() -> {
            int row = device.getDeviceId() / 72;
            int col = (device.getDeviceId() + 71) % 72;
            if (col == 71) {
                row--;
            }
            System.out.println("refreshCell  :  row : " + row + "    " + "col  : " + col);
            String value = charge.getStyle();
            if ("-fx-background-color: #1495F7;".equals(value)) {
                //取消选中
                ArrayOne.nextMatrix[row][col] = 0;
                System.out.println("arrayOne.nextMatrix[row][col]:   " + ArrayOne.nextMatrix[row][col]);
                charge.setStyle("-fx-background-color: #ed3942;");
            } else {
                //选中数组
                ArrayOne.nextMatrix[row][col] = 1;
                System.out.println("arrayOne.nextMatrix[row][col]:   " + ArrayOne.nextMatrix[row][col]);
                charge.setStyle("-fx-background-color: #1495F7;");
            }
        });
    }


    /**
     * 加载时的初始化
     *
     * @param
     */
    public void RefreshCell(Device device, int flag) {
            int row = device.getDeviceId() / 72;
            int col = (device.getDeviceId() + 71) % 72;
            if (col == 71) {
                row--;
            }
            if (flag == 1) {//需要进行 数组清零
                ArrayOne.nextMatrix[row][col] = 0;
            }else if(flag==2){
                ArrayOne.nextMatrix[row][col] = 0;
                ArrayOne.afterMatrix[row][col] = 0;
                ArrayOne.middleMatrix[row][col] = 0;
            }
            charge.setStyle("-fx-background-color: #ed3942;");
    }


    /**
     * 开始按钮
     */
    public void StartrefreshCell(Device device) {
        int row = device.getDeviceId() / 72;
        int col = (device.getDeviceId() + 71) % 72;
        if (col == 71) {
            row--;
        }

//            System.out.println("refreshCell  :  row : " + row + "    " + "col  : " + col);
//            String value = charge.getStyle();
//            if ("-fx-background-color: #1495F7;".equals(value)) {
//                //取消选中
//                ArrayOne.nextMatrix[row][col]=0;
//                System.out.println("arrayOne.nextMatrix[row][col]:   "+ArrayOne.nextMatrix[row][col]);
//                charge.setStyle("-fx-background-color: #ed3942;");
//            } else {
        //选中数组
        ArrayOne.middleMatrix[row][col] = 1;//赋值
        System.out.println(ArrayOne.middleMatrix[row][col]);
        charge.setStyle("-fx-background-color: #1495F7;");

    }


    //创建发送的ID
    private Device creatID(Device device) {
        //1  1   2   1
        int id = (device.getGroupId() - 1) * 100 + device.getDeviceId();
        device.setE_id(id);
        return device;
    }

    void resetCell(int groupId, int deviceId) {
        Platform.runLater(() -> {
            this.groupId = groupId;
            this.deviceId = deviceId;
        });
    }

    void refreshHook(Device device) {
        Device deviceOld = hookedDeviceGetter.get();
        if (deviceOld != null) {
            deviceOld.removeHook();
        }
        device.bindHook(this);
        hookedDeviceGetter.set(device);
    }

    private void loadFxml() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/device2.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
