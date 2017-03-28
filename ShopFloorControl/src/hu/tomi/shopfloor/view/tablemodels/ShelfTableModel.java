package hu.tomi.shopfloor.view.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.tomi.shopfloor.model.bean.Shelf;

public class ShelfTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 5198957703736719120L;
	
	private String[] columnNames = new String[] {"ID", "Shelf ID", "Storage ID", "Storage Name", "Height", "Width", "Depth", "Load Capacity"};
		
	List<Shelf> shelves;

	public ShelfTableModel(List<Shelf> shelves) {
		super();
		this.shelves = shelves;
	}

	@Override
	public int getRowCount() {

		return shelves.size();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Shelf shelf = shelves.get(rowIndex);
		String askedColumnName = columnNames[columnIndex];
		
		if (askedColumnName.equals("ID")) {
            return shelf.getId();
        }else if (askedColumnName.equals("Shelf ID")) {
            return shelf.getShelfID();
        }else if (askedColumnName.equals("Storage ID")) {
            return shelf.getStorageID();
        }else if (askedColumnName.equals("Storage Name")) {
            return shelf.getStorageName();
        }else if (askedColumnName.equals("Height")) {
            return shelf.getHeight();
        }else if (askedColumnName.equals("Width")) {
            return shelf.getWidth();
        }else if (askedColumnName.equals("Depth")) {
            return shelf.getDepth();
        }else if (askedColumnName.equals("Load Capacity")) {
            return shelf.getLoadCApacity();
        }	
		
		return "fakap";
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}