package inventory.datamodel;

/**
 * @project STP_projekt
 * @author vanja
 * @time 18-01-2019 and 4:01 PM
 */

import javafx.beans.property.*;

import java.time.LocalDate;


public class Device {
    private SimpleStringProperty deviceName = new SimpleStringProperty("");
    private SimpleStringProperty deviceVendor = new SimpleStringProperty("");
    private SimpleStringProperty deviceSerialNumber = new SimpleStringProperty("");
    private SimpleStringProperty deviceIpAddress = new SimpleStringProperty("");
    private SimpleStringProperty deviceSoftwareVersion = new SimpleStringProperty("");
    private SimpleStringProperty deviceLocation = new SimpleStringProperty("");

    public Device(){

    }

    public Device(String deviceName, String deviceVendor, String deviceSerialNumber, String deviceIpAddress, String deviceSoftwareVersion,String deviceLocation) {
        this.deviceName.set(deviceName);
        this.deviceVendor.set(deviceVendor);
        this.deviceSerialNumber.set(deviceSerialNumber);
        this.deviceIpAddress.set(deviceIpAddress);
        this.deviceSoftwareVersion.set(deviceSoftwareVersion);
        this.deviceLocation.set(deviceLocation);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public SimpleStringProperty deviceNameProperty() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName.set(deviceName);
    }

    public String getDeviceVendor() {
        return deviceVendor.get();
    }

    public SimpleStringProperty deviceVendorProperty() {
        return deviceVendor;
    }

    public void setDeviceVendor(String deviceVendor) {
        this.deviceVendor.set(deviceVendor);
    }

    public String getDeviceSerialNumber() {
        return deviceSerialNumber.get();
    }

    public SimpleStringProperty deviceSerialNumberProperty() {
        return deviceSerialNumber;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber.set(deviceSerialNumber);
    }

    public String getDeviceIpAddress() {
        return deviceIpAddress.get();
    }

    public SimpleStringProperty deviceIpAddressProperty() {
        return deviceIpAddress;
    }

    public void setDeviceIpAddress(String deviceIpAddress) {
        this.deviceIpAddress.set(deviceIpAddress);
    }

    public String getDeviceSoftwareVersion() {
        return deviceSoftwareVersion.get();
    }

    public SimpleStringProperty deviceSoftwareVersionProperty() {
        return deviceSoftwareVersion;
    }

    public void setDeviceSoftwareVersion(String deviceSoftwareVersion) {
        this.deviceSoftwareVersion.set(deviceSoftwareVersion);
    }

    public String getDeviceLocation() {
        return deviceLocation.get();
    }

    public SimpleStringProperty deviceLocationProperty() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation.set(deviceLocation);
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceName=" + deviceName +
                ", deviceVendor=" + deviceVendor +
                ", deviceSerialNumber=" + deviceSerialNumber +
                ", deviceIpAddress=" + deviceIpAddress +
                ", deviceSoftwareVersion=" + deviceSoftwareVersion +
                ", deviceLocation=" + deviceLocation +
                '}';
    }
//
//    private static final Pattern PATTERN = Pattern.compile(
//            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
//
//    public static boolean validate(String ip) {
//
//        return PATTERN.matcher(ip).matches();
//    }

}