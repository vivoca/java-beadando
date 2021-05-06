package hu.egyudv.beadando.ui.component;

import hu.egyudv.beadando.repository.entity.Hiking;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class HikingTableModel extends AbstractTableModel {

    private List<Hiking> hikingList = new ArrayList();
    private String[] columnNames = {
            "Name",
            "Length",
            "Location",
            "Difficulty",
            "Description"
    };

    public HikingTableModel(List<Hiking> list){
        this.hikingList = list;
    }

    @Override
    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return hikingList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hiking hiking = hikingList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return hiking.getName();
            case 1:
                return hiking.getLength();
            case 2:
                return hiking.getLocation();
            case 3:
                return hiking.getDifficulty();
            case 4:
                return hiking.getDescription();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
//            case 0:
//                return String.class;
            case 1:
                return Double.class;
//            case 2:
//                return String.class;
//            case 3:
//                return Double.class;
//            case 4:
//                return Double.class;
//            case 5:
//                return String.class;
//            case 6:
//                return Boolean.class;
            default:
                return String.class;
        }
    }

}