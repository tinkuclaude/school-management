package com.guimotech.gui.swing.model;

import com.guimotech.config.HelperService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class MyTableModelMap extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imageTrue = new ImageIcon(getClass().getResource(
			"/com/guimotech/resources/images/icons/i16/accept_button.png"));
	private ImageIcon imageFalse = new ImageIcon(getClass().getResource(
			"/com/guimotech/resources/images/icons/i16/delete.png"));
//	private boolean BoolToImage = true;
	
	int[] vHeader = {};
	int[] width = null;
	List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
	private Integer[] sortedRow = {};

	private boolean allowEditing = false;
	private boolean allowMultipleColumnClass = false;
	private List<String> allowEditColumnName = new ArrayList<>();
	
	public MyTableModelMap() {

	}
	
//	public void setBoolToImage(boolean BoolToImage) {
//		this.BoolToImage = BoolToImage;
//	}

	public MyTableModelMap(List<HashMap<String,Object>> data, int[] vHeader) {
		try {
			
			if (data != null) {
				this.data = data;
				Integer[] table = new Integer[data.size()-1];
				for(int i=0; i<table.length; i++)
					table[i] = i+1; 
				
				this.sortedRow = table;
			}

			if (vHeader != null) {
				this.vHeader = vHeader;
			}
			this.fireTableStructureChanged() ;
			this.fireTableDataChanged();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MyTableModelMap(List<HashMap<String,Object>> data, String[] vHeader){
		try 
		{
			if (data != null && data.size()>0){
				this.data = data;
			}
			else
			{
				data = new ArrayList<HashMap<String,Object>>();
				data.add(new HashMap<String,Object>());
				if(vHeader != null)
				{
					int len = vHeader.length;
					for(int i=0; i<len; i++){
						data.get(0).put(String.valueOf(i), vHeader[i]);
					}
				}
				this.data = data;
			}

			Integer[] table = new Integer[data.size()-1];
			for(int i = 0; i<table.length; i++)
				table[i] = i+1; 
			
			this.sortedRow = table;

			if(vHeader != null)
			{
				int len = vHeader.length, col = data.get(0).size(), j;
				this.vHeader = new int[len];
				for(int i = 0; i<len; i++)
				{
					for(j = 0; j<col && !vHeader[i].equals(data.get(0).get(String.valueOf(j))); j++);
					if (j == col) continue;
					this.vHeader[i] = j;
				}
			}
			this.fireTableStructureChanged() ;
			this.fireTableDataChanged();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getColumnIndex(String colName) {
		int col = data.get(0).size(), j;
		for(j = 0; j<col && !colName.equals(data.get(0).get(String.valueOf(j))); j++);
		if (j == col) return -1;
		return j;
	}

	public MyTableModelMap(List<HashMap<String,Object>> data){

		try 
		{
			if (data == null || data.size() < 1) return;
			
			this.data = data;
			Integer[] table = new Integer[data.size()-1];
			for(int i=0; i<table.length; i++)
				table[i] = i+1;
			
			this.sortedRow = table;
			
			int len = this.data.get(0).size();
			if(len > 0)
			{
				vHeader = new int[len];
				for(int i=0; i<len; i++) vHeader[i] = i;
			}
			setWidth(null);
			
			this.fireTableStructureChanged();
			this.fireTableDataChanged();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(List<HashMap<String,Object>> data, int[] vHeader){
		
		try 
		{
			if (data != null)
			{
				this.data = data;
				Integer[] table = new Integer[data.size()-1];
				for(int i=0; i<table.length; i++)
					table[i] = i+1;
				// boxing
				this.sortedRow = table;
			}

			if (vHeader != null){
				this.vHeader = vHeader;
			}
			
			this.fireTableStructureChanged() ;
			this.fireTableDataChanged();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(List<HashMap<String,Object>> data){
		
		try 
		{
			if (data != null)
			{
				this.data = data;
				Integer[] table = new Integer[data.size()-1];
				for(int i=0; i<table.length; i++)
					table[i]=i+1;
				
				this.sortedRow = table;
			}

			int len = this.data.get(0).size();
			if(getRowCount() > 0){
				vHeader = new int[len];
				for(int i=0; i<len; i++) vHeader[i] = i;
			}

			actualiserTable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actualiserTable() {
		//this.fireTableStructureChanged() ;
		this.fireTableDataChanged();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return vHeader.length;
	}
	
	/**
	 * 
	 * @return Number of entire column in the data
	 */
	public int getColumnSize() {
		return data.get(0).size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return sortedRow.length;
	}
	
	/*
	 * Return les valeur normal sans conversion
	 * Les boolean ne sont pas convertis en image par exemple
	 * 
	 * La donnée retourné doit correspondre à une cell de la JTable
	 */
	public Object getDataAt(int arg0, int arg1) {
		if(arg0 == -1 || arg1 == -1) return null;
		return getValue(arg0, vHeader[arg1]);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		if(arg0 == -1 || arg1 == -1) return null;
		
		Object obj = getValue(arg0, vHeader[arg1]);
		
		if(obj instanceof Timestamp){
			return HelperService.dateToStringPreForm((Timestamp)obj);
		}
		if(obj instanceof Date){
			return HelperService.dateToStringPreForm((Date)obj);
		}
		if(obj instanceof Boolean && !allowEditing){
			if((Boolean)obj) {
				return imageTrue;
			} else {
				return imageFalse;
			}
		}
		return obj;
	}

	private Object getValue(int arg0, int arg1) {
		if(arg0 == -1 || arg1 == -1) return null;
		Object column = data.get(0).get(String.valueOf(arg1));
		return getValue(arg0, column);
	}
	
	public Object getValue(int arg0, Object arg1) {
		if(arg0 == -1 || arg1 == null) return null;
		return getValue(arg0, arg1.toString());
	}

	public Object getValue(int arg0, String arg1) {
		if(arg0 == -1 || arg1 == null) return null;
		return data.get(sortedRow[arg0]).get(arg1);
	}

	// On ne considère pas que les données sont triées
	private Object getDirectValue(int arg0, int arg1) {
		if(arg0 == -1 || arg1 == -1) return null;
		Object column = data.get(0).get(String.valueOf(arg1));
		return getDirectValue(arg0, column);
	}
	
	public Object getDirectValue(int arg0, Object arg1) {
		if(arg0 == -1 || arg1 == null) return null;
		return getDirectValue(arg0, arg1.toString());
	}

	public Object getDirectValue(int arg0, String arg1) {
		if(arg0 == -1 || arg1 == null) return null;
		return data.get(arg0).get(arg1);
	}

	
	/*
	 * @return Column class number c in model data
	 */
	public Class<?> getDirectColumnClass(int c) {
		try {
			int i = 0;
			int rowCount = getRowCount(); //data.size()-1
			Class<?> clas = Object.class;
			
			while(i < rowCount && getValue(i, c) == null) i++;
			if(i < rowCount) clas = getValue(i, c).getClass();
			
			if(allowMultipleColumnClass) {	
				i++;
				while(i < rowCount) {
					while(i < rowCount && getValue(i, c) == null) i++;
					if(i < rowCount && !clas.equals(getValue(i, c).getClass())) {
						return Object.class;
					}
					i++;
				}
			}
			if(clas.equals(Timestamp.class)){
				return String.class;
			}
			if(clas.equals(Date.class)){
				return String.class;
			}
			if(clas.equals(Boolean.class) && !allowEditing) {
				return ImageIcon.class;
			}
			return clas;
			
		} catch (Exception e) {
			e.printStackTrace();
			return Object.class;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int c) {
		return getDirectColumnClass(vHeader[c]);
	}
	
	/*
	 * @return Column name number col in model data
	 */
	public String getDirectColumnName( int col) {
		String h = String.valueOf(col);
		return data.get(0).get(h).toString();
	}
	
	@Override
	public String getColumnName( int col) {
		return getDirectColumnName(vHeader[col]);
	}

	public HashMap<String, Object> getRow(int arg0) {
		return data.get(sortedRow[arg0]);
	}
	
	public void addColumn(String name, Object defValue) {
		data.get(0).put(""+data.get(0).size(), name);
		int len = data.size();
		for(int i = 1; i < len; i++)
			data.get(i).put(name, defValue);
		
		len = vHeader.length+1;
		int[] vH = new int[len];
		for(int i = 0; i < len-1; i++) {
			vH[i] = this.vHeader[i];
		}
		vH[len-1] = getColumnSize() - 1;
		this.vHeader = vH;
		setData(data);
	}
	
	public int getRowOfColumnValue(String colName, Object colValue) {
		if(colName == null) return -1;
		int len = getRowCount();
		for(int i = 0; i < len; i++) {
			Object value = getValue(i, colName);
			if(value == null) {
				if(colValue == null) return i;
			} else if(colValue != null) {
				if(value.equals(colValue)) return i;
			}
		}
		return -1;
	}
	
	public void removeColumn(String name){
		int len = data.get(0).size();
		int col = len-1;
		for(int i=0; i<len; i++)
		{
			if(data.get(0).get(""+i).equals(name))
			{
				data.get(0).remove(""+i);
				col = i;
				break;
			}
		}

		len = data.size();
		for(int i=1; i<len; i++)
			data.get(i).remove(name);
		
		if(isVisible(col))
		{
			len = vHeader.length-1;
			int[] vH = new int[len];
			for(int i = 0, j=0; i<len+1; i++)
			{
				if(this.vHeader[i]!=col)
					vH[j++] = this.vHeader[i];
			}
			this.vHeader = vH;
		}
		setData(data);
	}
	
	
	/**
	 * Méthode permettant de retirer une ligne du tableau
	 * @param position
	 */
	public void removeRow(int position) 
	{
		try 
		{
			int indice = 0, nbRow = getRowCount();
			if(position<0 || position>=nbRow) return;

			position = sortedRow[position];
			List<HashMap<String, Object>> temp = new ArrayList<HashMap<String, Object>>() ;
			for( HashMap<String, Object> value : data) 
			{
				if( indice != position){
					temp.add(value);
				}
				indice++;
			}
			data = temp;
			temp = null;
			
			Integer[] table = new Integer[data.size()-1];
			for(int i=0;i<table.length;i++)
				table[i]=i+1; 
			// boxing
			this.sortedRow = table;
			
			this. fireTableDataChanged( ) ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeAddRow(int position, HashMap<String, Object> row) 
	{
		try 
		{
			int indice = 0, nbRow = getRowCount();
			if(position<0 || position>=nbRow || row == null) return;

			position = sortedRow[position];
			List<HashMap<String, Object>> temp = new ArrayList<HashMap<String, Object>>() ;
			for( HashMap<String, Object> value : data) 
			{
				if( indice != position) {
					temp.add(value);
				}
				else temp.add(row);

				indice++;
			}
			data = temp;
			temp = null;

			Integer[] table = new Integer[data.size()-1];
			for(int i=0;i<table.length;i++)
				table[i]=i+1; 
			
			this.sortedRow = table;

			this. fireTableDataChanged( ) ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void trie(int column, boolean order){
//		triFusion(0, getRowCount()-1, column, order);
		sort(column, order);
		fireTableDataChanged();
	}

	public void trie(String column, boolean order) throws Exception{
			
		int idx = getVisibleIndex(column);
		String[] vColumns = getVHeader();
		
		final int nIdx = addNewVisibleHeader(column);
		trie(nIdx, order);
		if(idx == -1) setVHeader(vColumns);
	}
	
	public static String getOperator(String value){
		if(value == null) return null;
		if(value.startsWith(">=")) return ">=";
		if(value.startsWith("<=")) return "<=";
		if(value.startsWith("<")) return "<";
		if(value.startsWith(">")) return ">";
		if(value.startsWith("=")) return "=";
		if(value.startsWith("!=")) return "!=";
		return null;
	}
	
	public static String getSqlOperator(String operator) {
		return operator.equals("!=")? "<>": operator;
	}

	public static String getValueWithoutOperator(String value){

		String operator = getOperator(value);
		if(operator!=null){
			value = value.replaceFirst(operator, "");
		}
		return value;
	}

	public void filter(int column, String value) {
		try {
			
			if(value == null || value.trim().equals("")) {
				setData(data);
				return;
			}
			value = value.toUpperCase();
			String operator = getOperator(value);
			value = getValueWithoutOperator(value);
			if(value == null || value.trim().equals("") || (operator != null && column < 0)) {
				setData(data);
				return;
			}
			
			Double valueNumber = null;
			if(operator != null) {
				try {
					valueNumber = Double.parseDouble(value);
				} catch (NumberFormatException e) {}
			}
			
			Object columnName = null;
			if(column >= 0) columnName = data.get(0).get(String.valueOf(vHeader[column]));
			int count = 0, len = data.size();
			Integer[] table = new Integer[len-1];
			for(int i = 1; i < len; i++) {
				Object obj = null;
				if(columnName==null) {
					obj = data.get(i); 
				} else {
					obj = data.get(i).get(columnName);
				}
				if(obj != null) {
					String cellValue = obj.toString().toUpperCase();
					if(operator == null && cellValue.contains(value)){
						table[count++] = i;
					}
					else if(operator != null) {
						if ( obj instanceof Float || obj instanceof Double ||
								obj instanceof Long || obj instanceof Integer )
						{
							Double cellNumber;
							try {
								cellNumber = Double.parseDouble(obj.toString());
							} catch (NumberFormatException e) {
								continue;
							}
							if(valueNumber == null) break;

							if((operator.equals("<") && (cellNumber).compareTo(valueNumber)<0) ||
									(operator.equals("<=") && (cellNumber).compareTo(valueNumber)<=0) ||
									(operator.equals(">") && (cellNumber).compareTo(valueNumber)>0) ||
									(operator.equals(">=") && (cellNumber).compareTo(valueNumber)>=0) ||
									(operator.equals("=") && (cellNumber).compareTo(valueNumber)==0) ||
									(operator.equals("!=") && (cellNumber).compareTo(valueNumber)!=0) ){
								table[count++] = i;
							}
						}
						else if((operator.equals("<") && cellValue.compareToIgnoreCase(value)<0) ||
								(operator.equals("<=") && cellValue.compareToIgnoreCase(value)<=0) ||
								(operator.equals(">") && cellValue.compareToIgnoreCase(value)>0) ||
								(operator.equals(">=") && cellValue.compareToIgnoreCase(value)>=0) ||
								(operator.equals("=") && cellValue.compareToIgnoreCase(value)==0) ||
								(operator.equals("!=") && cellValue.compareToIgnoreCase(value)!=0) ){
							table[count++] = i;
						}
					}
				}
			}
			
			sortedRow = new Integer[count];
			for(int i = 0; i < count; i++){
				sortedRow[i] = table[i];
			}

			if(count != 0) {
				sort(column, true);
			}
			fireTableDataChanged();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet d'ajouter une ligne dans le tableau
	 * @param data
	 */

	public void addRow( HashMap<String, Object> data) {
		this.data.add(data);
		Integer[] table = new Integer[this.data.size()-1];
		for(int i=0;i<table.length;i++)
			table[i]=i+1; 
		// boxing
		this.sortedRow = table;
		
		this.fireTableDataChanged( ) ;
	}
	
	public int rowIndex(Object value, String key) {
		for(int i=0; i<sortedRow.length; i++) {
			if(value.equals(data.get(sortedRow[i]).get(key))){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Permet d'ajouter une ligne dans le tableau
	 * @param data
	 */

	public void addRows(List<HashMap<String, Object>> data) {
		if(data == null) return;
		
		this.data.addAll(data);
		Integer[] table = new Integer[this.data.size()-1];
		for(int i=0;i<table.length;i++)
			table[i]=i+1; 
		// boxing
		this.sortedRow = table;
		
		this.fireTableDataChanged();
	}

	//	Interdire l'édition
	@Override
	public boolean isCellEditable(int row, int col) {
		if(allowEditing && allowEditColumnName.size() > 0) {
			String colName = getColumnName(col);
			return allowEditColumnName.contains(colName);
		}
		return allowEditing;
	}

	public void setCellEditable(boolean allowEditing){
		this.allowEditing = allowEditing;
	}
	
	public void addEditColumnName(String name) {
		allowEditColumnName.add(name);
	}
	
	public void addEditColumnName(String[] names) {
		if(names != null){
			for(int i=0; i < names.length; i++) {
				allowEditColumnName.add(names[i]);
			}
		}
	}
	
	public void setMultipleColumnClass(boolean allowMultipleColumnValues){
		this.allowMultipleColumnClass = allowMultipleColumnValues;
	}
	
	@Override
	public void setValueAt(Object value, int row, int col){
		getRow(row).put(getVHeader()[col], value);
	}
	
	public void setValueAt(Object value, int row, Object col){
		getRow(row).put(col.toString(), value);
	}
	
	public String[] getVHeader() {
		int len = vHeader.length;
		String [] vh = new String[len];
		for(int i=0; i<len; i++) vh[i] = getColumnName(i);
		return vh;
	}
	
	

	public int[] getVisibleHeader() {
		return vHeader;
	}

	public void setVHeader(String[] vh) throws Exception {
		try {
			if(vh != null) {
				int len = vh.length, col = data.get(0).size(), j;
				vHeader = new int[len];
				for(int i = 0; i < len; i++) {
					for(j = 0; j < col && !vh[i].equals(data.get(0).get(String.valueOf(j))); j++);
					if (j == col) {
						throw new Exception("Unknow Column Header");
					}
					vHeader[i] = j;
				}
			}
			this.fireTableStructureChanged();
		} catch (Exception e) {
//			ProjectCode.saveError(e);
			e.printStackTrace();
		}
	}
	
	public void updateVHeader(int vPos, String newName) throws Exception {
		int col = data.get(0).size(), j;
		for(j = 0; j < col && !newName.equals(data.get(0).get(String.valueOf(j))); j++);
		if (j == col) {
			throw new Exception("Unknow Column Header");
		}
		vHeader[vPos] = j;
		
//		int[] vH = getVisibleHeader();
//		width[j] = width[vH[vPos]];
		fireTableStructureChanged();
	}

	public void setVHeader(int[] vh) 
	{
		if(vh !=null){
			vHeader = vh;
		}

		this.fireTableStructureChanged();
	}

	/**
	 * @return the allHeader
	 */
	public String[] getHeader() 
	{
		int len = data.get(0).size();
		String [] header = new String[len];
		for(int i=0; i<len; i++) header[i]=data.get(0).get(String.valueOf(i)).toString();
		return header;
	}
	
	public Object[] getObjHeader() 
	{
		int len = data.get(0).size();
		Object [] header = new Object[len];
		for(int i = 0; i < len; i++) header[i] = data.get(0).get(String.valueOf(i));
		return header;
	}

	/**
	 * @return the allData
	 */
	public List<HashMap<String, Object>> getData() {
		return data;
	}

	/**
	 * @param data the allData to set
	 */
	public void setData(List<HashMap<String, Object>> data) 
	{
		try 
		{
			if (data != null){
				this.data = data;
			}
			else data = this.data;

			if(data!=null && data.size()!=0)
			{
				Integer[] table = new Integer[data.size()-1];
				for(int i=0;i<table.length;i++)
					table[i]=i+1; 
				// boxing
				this.sortedRow = table;
			}
			fireTableDataChanged();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int[] getWidth() {
		if(width==null) setWidth(null);
		
		return width;
	}

	public void setIWidth(Integer[] vhWidth) {
		int[] vh = new int[vhWidth.length];
		int j = 0;
		for(Integer i: vhWidth) {
			vh[j++] = i.intValue();
		}
		setWidth(vh);
	}
	
	public void setWidth(int[] vhWidth) {	
		if(getData()==null || getData().size()==0) return;
		
		int taille = getColumnSize();
		if(vhWidth != null && vhWidth.length == taille) {
			this.width = vhWidth;
			return;
		}
		this.width = new int[taille];
		for(int i = 0; i < taille; i++) {
//			if(Number.class.isAssignableFrom(getDirectColumnClass(i))){
//				this.width[i] = 70;
//			}
//			else 
			if(ImageIcon.class.isAssignableFrom(getDirectColumnClass(i))){
				this.width[i] = 50;
			}
			else if(Boolean.class.isAssignableFrom(getDirectColumnClass(i))){
				this.width[i] = 50;
			}
			else this.width[i] = 100;
		}
		if(vhWidth==null) return;
		
		int[] vH = getVisibleHeader();
		int colCount = Math.min(vhWidth.length, vH.length);
		for(int i = 0; i < colCount; i++) {
			this.width[vH[i]] = vhWidth[i];
		}
	}
	
	public int getTableWidth()
	{	
		if(this.width == null) setWidth(null);
		
		int[] vH = getVisibleHeader();
		int colCount = vH.length;
		int sum = 0;
		
		for(int i=0; i<colCount; i++){
			sum += this.width[vH[i]];
		}
		return sum;
	}

	public int[] getVisibleWidth() {
		if(width == null) setWidth(null);
		
		int[] vH = getVisibleHeader();
		int colCount = vH.length;
		int[] vWidth = new int[colCount];
		
		for(int i=0; i<colCount; i++) {
			vWidth[i] = this.width[vH[i]];
		}
		return vWidth;
	}

	public int getWidth(int col) {
		if(width == null) setWidth(null);
		return width[col];
	}
	
	public boolean isVisible(int col)
	{
		if(getVisibleIndex(col)!=-1) return true;		
		return false;
	}
	
	public boolean isVisible(String col)
	{
		if(getVisibleIndex(col)!=-1) return true;		
		return false;
	}

	public int getVisibleIndex(int col)
	{
		int len = vHeader.length;
		for(int i=0; i<len; i++) 
			if(vHeader[i]==col) return i;

		return -1;
	}
	
	public int getVisibleIndex(String col) {
		int len = vHeader.length;
		for(int i=0; i<len; i++) {
			if(data.get(0).get(String.valueOf(vHeader[i])).equals(col)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param column new column to add
	 * @return visible index of new column 
	 * @throws Exception
	 */
	private int addNewVisibleHeader(String column) throws Exception{
		
		int idx = getVisibleIndex(column);
		
		if(idx == -1) {
			String[] vColumns = getVHeader();
			// the column is not visible, add as visible column
			int len = vColumns.length;
			String[] nVColumns = new String[len+1];
			for(int i = 0; i < len; i++ ){
				nVColumns[i] = vColumns[i];
			}
			nVColumns[len] = column;
			setVHeader(nVColumns);
			
			idx = len;
		}
		
		return idx;
	}
	

	
	public void sort(final int column, final boolean order){
		
		if(sortedRow == null || sortedRow.length < 2 || column < 0) {
			return;
		}
		
		List<Object> list = Arrays.stream(sortedRow).map(elt -> (Object) elt).toList();
		Collections.sort(list, new Comparator<Object>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public int compare(Object obj1, Object obj2) {
				int signe = 0;				
				if(obj1 == null && obj2 == null) signe = 0;
				else if(obj1 == null) signe = -1;
				else if(obj2 == null) signe = 1;
				else {
					Object value1 = MyTableModelMap.this.getDirectValue(
						((Integer)obj1).intValue(), vHeader[column]);
					Object value2 = MyTableModelMap.this.getDirectValue(
							((Integer)obj2).intValue(), vHeader[column]);
					
					if(value1 == null && value2 == null) signe = 0;
					else if(value1 == null) signe = -1;
					else if(value2 == null) signe = 1;
					else signe = ((Comparable)value1).compareTo((Comparable)value2);
				}
				if(order) return signe;
				return -signe;
			}
		});
		list.toArray(sortedRow);
	}
}
