package inventory;

/**
 * @project STP_projekt
 * @author vanja
 * @time 18-01-2019 and 4:01 PM
 */

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import inventory.datamodel.Device;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.TreeMap;

public class DeviceController {


    @FXML
    private TextField deviceNameField;

    @FXML
    private TextField deviceVendorField;

    @FXML
    private TextField deviceSerialNumberField;

    @FXML
    private TextField deviceIpAddressField;

    @FXML
    private TextField deviceSoftwareVersionField;

    @FXML
    private TextField deviceLocationField;

    @FXML
    private DatePicker dateInsertedField;


    public Device getNewDevice() {
        String deviceName = deviceNameField.getText();
        String deviceVendor = deviceVendorField.getText();
        String deviceSerialNumber = deviceSerialNumberField.getText();
        String deviceIpAddress = deviceIpAddressField.getText();
        String deviceSoftwareVersion = deviceSoftwareVersionField.getText();
        String deviceLocation = deviceLocationField.getText();
        LocalDate dateInserted = dateInsertedField.getValue();
        Device newDevice = new Device(deviceName, deviceVendor, deviceSerialNumber, deviceIpAddress, deviceSoftwareVersion, deviceLocation, dateInserted);
        return newDevice;
    }

    public void editDevice(Device device) {
        deviceNameField.setText(device.getDeviceName());
        deviceVendorField.setText(device.getDeviceVendor());
        deviceSerialNumberField.setText(device.getDeviceSerialNumber());
        deviceIpAddressField.setText(device.getDeviceIpAddress());
        deviceSoftwareVersionField.setText(device.getDeviceSoftwareVersion());
        deviceLocationField.setText(device.getDeviceLocation());
        dateInsertedField.setValue(LocalDate.parse(device.getDateInserted()));
    }


    public void updateDevice(Device device) {
        device.setDeviceName(deviceNameField.getText());
        device.setDeviceVendor(deviceVendorField.getText());
        device.setDeviceSerialNumber(deviceSerialNumberField.getText());
        device.setDeviceIpAddress(deviceIpAddressField.getText());
        device.setDeviceSoftwareVersion(deviceSoftwareVersionField.getText());
        device.setDeviceLocation(deviceLocationField.getText());
        device.setDateInserted(String.valueOf(dateInsertedField.getValue()));
    }


}





