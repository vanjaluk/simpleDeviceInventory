package inventory.datamodel;

/**
 * @project STP_projekt
 * @author vanja
 * @time 18-01-2019 and 4:01 PM
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;


public class DeviceData {

    private static final String DEVICES_FILE = "devices.xml";

    private static final String DEVICE = "device";
    private static final String DEVICE_NAME = "device_name";
    private static final String DEVICE_VENDOR = "device_vendor";
    private static final String SERIAL_NUMBER = "serial_number";
    private static final String IP_ADDRESS = "ip_address";
    private static final String SOFTWARE_VERSION = "software_version";
    private static final String DEVICE_LOCATION = "device_location";
    private static final String DATE_INSERTED = "date_inserted";

    private ObservableList<Device> devices;

    public DeviceData() {
        devices = FXCollections.observableArrayList();
    }

    public ObservableList<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device item) {
        devices.add(item);
    }

    public void deleteDevice(Device item) {
        devices.remove(item);
    }

    public void loadDevices() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(DEVICES_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Device device = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a device item, we create a new device
                    if (startElement.getName().getLocalPart().equals(DEVICE)) {
                        device = new Device();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(DEVICE_NAME)) {
                            event = eventReader.nextEvent();
                            device.setDeviceName(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.asStartElement().getName().getLocalPart()
                                .equals(DEVICE_VENDOR)) {
                            event = eventReader.nextEvent();
                            device.setDeviceVendor(event.asCharacters().getData());
                            continue;
                        }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SERIAL_NUMBER)) {
                        event = eventReader.nextEvent();
                        device.setDeviceSerialNumber(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(IP_ADDRESS)) {
                        event = eventReader.nextEvent();
                        device.setDeviceIpAddress(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SOFTWARE_VERSION)) {
                        event = eventReader.nextEvent();
                        device.setDeviceSoftwareVersion(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(DEVICE_LOCATION)) {
                        event = eventReader.nextEvent();
                        device.setDeviceLocation(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(DATE_INSERTED)) {
                        event = eventReader.nextEvent();
                        device.setDateInserted(event.asCharacters().getData());
                        continue;
                    }

                }
                // If we reach the end of a device element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(DEVICE)) {
                        devices.add(device);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveDevices() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(DEVICES_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement devicesStartElement = eventFactory.createStartElement("",
                    "", "devices");
            eventWriter.add(devicesStartElement);
            eventWriter.add(end);

            for (Device device : devices) {
                saveDevice(eventWriter, eventFactory, device);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "devices"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Devices file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing device into inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveDevice(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Device device)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create device open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", DEVICE);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, DEVICE_NAME, device.getDeviceName());
        createNode(eventWriter, DEVICE_VENDOR, device.getDeviceVendor());
        createNode(eventWriter, SERIAL_NUMBER, device.getDeviceSerialNumber());
        createNode(eventWriter, IP_ADDRESS, device.getDeviceIpAddress());
        createNode(eventWriter, SOFTWARE_VERSION, device.getDeviceSoftwareVersion());
        createNode(eventWriter, DEVICE_LOCATION, device.getDeviceLocation());
        createNode(eventWriter, DATE_INSERTED, device.getDateInserted());

        eventWriter.add(eventFactory.createEndElement("", "", DEVICE));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

}