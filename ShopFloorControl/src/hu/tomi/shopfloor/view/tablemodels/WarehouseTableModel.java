package hu.tomi.shopfloor.view.tablemodels;

import javax.swing.table.AbstractTableModel;

import java.util.List;

import hu.tomi.shopfloor.model.bean.WareHouse;

public class WarehouseTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6719971176977039370L;
	
	private String[] columnNames = new String[] {"id", "name", "numberofstorageshelves"};
	
	List<WareHouse> warehouses;

	public WarehouseTableModel(List<WareHouse> warehouses) {
		super();
		
		this.warehouses = warehouses;
	}

	@Override
	public int getRowCount() {
		
		return warehouses.size();
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
		
		WareHouse warehouse = warehouses.get(rowIndex);
		String askedColumnName = columnNames[columnIndex];

		if (askedColumnName.equals("id")) {
            return warehouse.getId();
        }else if (askedColumnName.equals("name")) {
            return warehouse.getName();
        }else if (askedColumnName.equals("numberofstorageshelves")) {
            return warehouse.getNumberOfStorageShelves();
        }
        
        return "fakap";
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
