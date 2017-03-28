package hu.tomi.shopfloor.view.tablemodels;

import javax.swing.table.AbstractTableModel;

import java.util.List;

import hu.tomi.shopfloor.model.bean.Manufacturer;

public class ManufacturerTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8711331528905747894L;
	
	List<Manufacturer> manufacturers;
	
	private String[] columnNames = new String[] {"id", "name", "description"};

	public ManufacturerTableModel(List<Manufacturer> manufacturers) {
		super();
		
		this.manufacturers = manufacturers;
	}

	@Override
	public int getRowCount() {
		
		return manufacturers.size();
	}

	@Override
	public int getColumnCount() {
		
		return columnNames.length;
	}
	
	public String getColumnName(int col){
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Manufacturer manufacturer = manufacturers.get(rowIndex);
		
		String askedColumnName = columnNames[columnIndex];
		
		if(askedColumnName.equals("id")){
			return manufacturer.getId();			
		}else if(askedColumnName.equals("name")){
			return manufacturer.getName();
		}else if(askedColumnName.equals("description")){
			return manufacturer.getDescription();
		}
		
		return null;
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
