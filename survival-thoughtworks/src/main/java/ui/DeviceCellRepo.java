package ui;

import config.TableSetting;
import ui.bean.Device;


/**
 * 设备及设备窗格缓存
 */
public class DeviceCellRepo {

    private static final DeviceCellView[] DEVICE_CELL_VIEWS = new DeviceCellView[TableSetting.MAX_DEVICE_ID + 1];
    private static final Device[][] DEVICES = new Device[TableSetting.MAX_GROUP_ID + 1][TableSetting.MAX_DEVICE_ID + 1];

    static {
        for (int groupId = 1; groupId <=TableSetting.MAX_GROUP_ID; groupId++) {
            Device[] devices = DEVICES[groupId];
            for (int deviceId = 0; deviceId <= TableSetting.MAX_DEVICE_ID; deviceId++) {
                devices[deviceId] = new Device(groupId, deviceId);
            }
        }
    }

    public static Device getDevice(int groupId, int deviceId) {
        return DEVICES[groupId][deviceId];
    }

    public static void setDevice(Device device, int groupId, int deviceId) {
        DEVICES[groupId][deviceId] = device;
    }

    public static DeviceCellView getCellView(int id) {
        return DEVICE_CELL_VIEWS[id];
    }

    public static void setCellView(DeviceCellView deviceCellView, int id) {
//        System.out.println("setCellView");
        DEVICE_CELL_VIEWS[id] = deviceCellView;
    }
}
