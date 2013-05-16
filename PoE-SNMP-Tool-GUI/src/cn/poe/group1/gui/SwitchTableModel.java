/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.poe.group1.gui;

import cn.poe.group1.entity.Switch;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sauron
 */
public class SwitchTableModel extends AbstractTableModel 
{
    
    private String[] columnNames = { "Id", "IP"};
    private List<Switch> data;
       
    public SwitchTableModel()
    {
        this.data = new ArrayList<Switch>();
    }

    public void clear()
    {
        this.data.clear();
        this.fireTableDataChanged();
    }
    
    public void addSwitchList(List<Switch> switchList)
    {
        this.data.addAll(switchList);
        this.fireTableDataChanged();
    }
    
    public int getColumnCount() {
      return columnNames.length;
    }

    public int getRowCount() {
      return data.size();
    }

    public Switch getRow(int row)
    {
        if( (row < 0) && (row >= data.size()) )
            return null;
        
        return this.data.get(row);
    }
    
    public String getColumnName(int col) {
      return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if( (row < 0) || (row > data.size() ) )
            return null;
        
        switch( col)
        {
            case 0:
                return data.get(row).getIdentifier();
            case 1:
                return data.get(row).getIpAddress();
            default:
                return null;
        }
    }

    public Class getColumnClass(int c) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    }
}
