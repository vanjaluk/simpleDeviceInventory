package inventory;

/**
 * @project STP_projekt
 * @author vanja
 * @time 18-01-2019 and 4:01 PM
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import inventory.datamodel.Device;
import inventory.datamodel.DeviceData;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainPanel;

    @FXML
    private TableView<Device> devicesTable;

    private DeviceData data;

    public void initialize() {
        data = new DeviceData();
        data.loadDevices();
        devicesTable.setItems(data.getDevices());
    }

    @FXML
    public void showAddDeviceDialog() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Device");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("devicedialog.fxml"));
        if (loadDialog(dialog, fxmlLoader)) return;

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DeviceController deviceController = fxmlLoader.getController();
            Device newDevice = deviceController.getNewDevice();
            data.addDevice(newDevice);
            data.saveDevices();
        }
    }

    private boolean loadDialog(Dialog<ButtonType> dialog, FXMLLoader fxmlLoader) {
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return true;
        }
        return false;
    }

    @FXML
    public void showEditDeviceDialog() {
        Device selectedDevice = devicesTable.getSelectionModel().getSelectedItem();
        if (selectedDevice == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Device Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the device you want to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Edit Device");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("devicedialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;

        }
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DeviceController deviceController = fxmlLoader.getController();
        deviceController.editDevice(selectedDevice);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            deviceController.updateDevice(selectedDevice);
            data.saveDevices();
        }
    }

    @FXML
    public void deleteDevice() {
        Device selectedDevice = devicesTable.getSelectionModel().getSelectedItem();
        if (selectedDevice == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Device Selected");
            alert.setHeaderText(null);
            alert.setContentText("PLease select the Device you want to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Device");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected Device: " +
                selectedDevice.getDeviceName() + " " + selectedDevice.getDeviceIpAddress());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            data.deleteDevice(selectedDevice);
            data.saveDevices();
        }
    }
}













