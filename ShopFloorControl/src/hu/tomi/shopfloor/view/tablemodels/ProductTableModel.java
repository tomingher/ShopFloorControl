package hu.tomi.shopfloor.view.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.tomi.shopfloor.model.bean.Product;

public class ProductTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 9220712701012392952L;
	
	List<Product> products;
	
	private String[] columnNames = new String[] {"id", "manufacturerid", "weight", "quantity", "description"};

	public ProductTableModel(List<Product> products	) {
		super();
		
		this.products = products;
	}

	@Override
	public int getRowCount() {

		return products.size();
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
		
		Product product = products.get(rowIndex);
		String askedColumnName = columnNames[columnIndex];
		
		if (askedColumnName.equals("id")) {
            return product.getId();
        }else if (askedColumnName.equals("manufacturerid")) {
            return product.getManufacturerID();
        }else if (askedColumnName.equals("weight")) {
            return product.getWeight();
        }else if (askedColumnName.equals("quantity")) {
            return product.getQuantity();
        }else if (askedColumnName.equals("description")) {
            return product.getDescription();
        }
		
		return null;
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
