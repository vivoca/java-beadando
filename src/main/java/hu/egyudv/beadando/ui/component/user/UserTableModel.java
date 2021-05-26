package hu.egyudv.beadando.ui.component.user;

import hu.egyudv.beadando.model.UserData;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private List<UserData> userList = new ArrayList();
    private String[] columnNames = {
            "First Name",
            "Last Name",
            "Birth Date",
            "Mobile"
    };

    public UserTableModel(List<UserData> list){
        this.userList = list;
    }

    @Override
    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return userList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserData user = userList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getFirstName();
            case 1:
                return user.getLastName();
            case 2:
                return user.getFormattedBirthDate();
            case 3:
                return user.getMobile();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
//        switch (columnIndex){
//            case 0:
//                return String.class;
//            case 1:
//                return Integer.class;
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
//        }
        return String.class;
    }

}