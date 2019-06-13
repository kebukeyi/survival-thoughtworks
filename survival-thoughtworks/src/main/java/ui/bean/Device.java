package ui.bean;

import ui.DeviceCellView;


import java.util.concurrent.atomic.AtomicReference;

public class Device {

    private AtomicReference<DeviceCellView> viewGetter = new AtomicReference<>();
    private int groupId;
    private int deviceId;
    private int e_id;//用于传送的id
    private int chargeStatus;
    private int workStatus;
    private int temp;//备用位
    private int falg=1;//
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFalg() {
        return falg;
    }

    public void setFalg(int falg) {
        this.falg = falg;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }



    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public Device() {
    }

    public Device(int groupId, int deviceId) {
        this.groupId = groupId;
        this.deviceId = deviceId;
        falg=1;

    }


    /**
     * 移除钩子
     */
    public void removeHook() {
        viewGetter.set(null);
    }

    /**
     * 绑定新的钩子
     *
     * @param deviceCellView 钩住设备显示窗格
     */
    public void bindHook(DeviceCellView deviceCellView) {
        viewGetter.set(deviceCellView);
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }


    public DeviceCellView getView() {
        return viewGetter.get();
    }

    public void setView(DeviceCellView cell) {
        viewGetter.set(cell);
    }

}
