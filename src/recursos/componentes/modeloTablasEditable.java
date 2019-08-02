package recursos.componentes;

import javax.swing.table.DefaultTableModel;

public class modeloTablasEditable extends  DefaultTableModel {
	
	 
	/**
	 * pendiente mejorar la interface
	 */
	private static final long serialVersionUID = 1L;
	
	boolean[] columnEditables = new boolean[] { };
	@SuppressWarnings("rawtypes")
	Class[] columnTypes = new Class[] { };
	
	public modeloTablasEditable(boolean[] columnEditables,@SuppressWarnings("rawtypes") Class[] columnTypes) {
		
		this.columnEditables = columnEditables; 
		this.columnTypes = columnTypes;
	}
	@Override
	public final boolean isCellEditable(int row, int column) {
	        return columnEditables[column];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

}
