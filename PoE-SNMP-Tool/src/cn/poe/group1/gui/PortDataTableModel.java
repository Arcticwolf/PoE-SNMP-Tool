package cn.poe.group1.gui;

import cn.poe.group1.entity.PortData;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sauron
 */
public class PortDataTableModel extends AbstractTableModel 
{
    
    private String[] columnNames = { "Port", "PwrMax", "PwrMaxDrawn", 
        "PwrAllocated", "PwrAvailable" , "PwrConsumption"};
    private List<PortData> data;
       
    public PortDataTableModel()
    {
        this.data = new ArrayList<PortData>();
    }

    public void clear()
    {
        this.data.clear();
        this.fireTableDataChanged();
    }
    
    public void addPortDataList(List<PortData> pdList)
    {
        this.data.addAll(pdList);
        this.fireTableDataChanged();
    }
    
    public int getColumnCount() {
      return columnNames.length;
    }

    public int getRowCount() {
      return data.size();
    }

    public PortData getRow(int row)
    {
        if( (row < 0) || (row >= data.size()) )
            return null;
        
        return this.data.get(row);
    }
    
    public List<PortData> getRowList()
    {
        return this.data;
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
                try
                {
                    return data.get(row).getPort().getPortNumber();
                }
                catch(Exception e)
                {
                    return null;
                }
            case 1:
                return data.get(row).getAvgCpeExtPsePortPwrMax();
            case 2:
                return data.get(row).getAvgCpeExtPsePortMaxPwrDrawn();
            case 3:
                return data.get(row).getAvgCpeExtPsePortPwrAllocated();
            case 4:
                return data.get(row).getAvgCpeExtPsePortPwrAvailable();
            case 5:
                return data.get(row).getAvgCpeExtPsePortPwrConsumption();
                                  
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
