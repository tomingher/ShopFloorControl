package hu.tomi.shopfloor.view.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.tomi.shopfloor.model.bean.LocationData;

/**
 * Created by meszarostamas on 2017. 06. 12..
 */
public class LocationDataTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -4696449875891271572L;

    private String[] columnnames = new String[] {"Warehouse name", "Storage name", "Shelf", "Shelf width", "Location", "Location width", "Free width"};

    List <LocationData> locationDataList;

    public LocationDataTableModel(List <LocationData> locationDataList) {
        super();
        this.locationDataList = locationDataList;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return locationDataList.size();
    }

    @Override
    public int getColumnCount() {
        return columnnames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LocationData locationdata = locationDataList.get(rowIndex);
        String askedColumnName = columnnames[columnIndex];

        if (askedColumnName.equals("Warehouse name")) {
            return locationdata.getWarehousename();
        }else if (askedColumnName.equals("Storage name")) {
            return locationdata.getStoragename();
        }else if (askedColumnName.equals("Shelf")) {
            return locationdata.getShelf();
        }else if (askedColumnName.equals("Shelf width")) {
            return locationdata.getShelfwidth();
        }else if (askedColumnName.equals("Location")) {
            return locationdata.getLocation();
        }else if (askedColumnName.equals("Location width")) {
            return locationdata.getLocationwidth();
        }else if (askedColumnName.equals("Free width")) {
            return locationdata.getFreewidth();
        }

        return "fakap";
    }

    @Override
    public String getColumnName(int column) {
        return columnnames[column];
    }
}