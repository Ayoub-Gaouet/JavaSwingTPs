package Ds;

import DataBase.EtudiantDAO;
import DataBase.FormationDAO;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyFormationTableModel extends AbstractTableModel {
    ArrayList<Object[]> data = new ArrayList<Object[]>();
    ResultSetMetaData rs;
    FormationDAO dao;

    public MyFormationTableModel(ResultSet rs, FormationDAO dao) {
        try {
            this.rs = rs.getMetaData();
            this.dao = dao;
            while (rs.next()) {
                Object[] row = new Object[this.rs.getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rs.getColumnCount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rs.getColumnName(column + 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ajoutFormation(int id,String titre,String formateur, int heures_total, int heures_realisees, String statut) {
        data.add(new Object[] { id, titre, formateur, heures_total, heures_realisees , statut });
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        int formateurIndex = columnNameToIndex("formateur");
        int heures_realiseesIndex = columnNameToIndex("heures_realisees");
        if (columnIndex == formateurIndex){
            return columnIndex == formateurIndex;
        }else  if (columnIndex == heures_realiseesIndex){
            return  columnIndex == heures_realiseesIndex;
        }
        return false;
    }

    public int columnNameToIndex(String columnName) {
        try {
            for (int i = 1; i <= rs.getColumnCount(); i++) {
                if (rs.getColumnName(i).equalsIgnoreCase(columnName)) {
                    return i - 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int id = Integer.parseInt(data.get(rowIndex)[0] + "");
        String formateur = aValue + "";
        int heures_total = Integer.parseInt(aValue + "");
        int heures_realisees = Integer.parseInt(aValue + "");
        String statut = data.get(rowIndex)[5] + "";

        int a = dao.updateFormation(id,formateur,heures_realisees, heures_total);

            if (a > 0) {
                data.get(rowIndex)[columnIndex] = aValue;
            }

    }

    // click droite sur le jtabel affiched button delete si je click sur le button
    // delete from ligne et data base
    public boolean removeRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= data.size()) {
            return false;
        }
        try {
            int id = Integer.parseInt(data.get(rowIndex)[0].toString());
            int heures_realisees = Integer.parseInt(data.get(rowIndex)[4].toString());
            int heures_total = Integer.parseInt(data.get(rowIndex)[3].toString());
            if (heures_realisees<heures_total){
                return false;
            }else {
            int res = dao.deleteFormation(id);
            if (res > 0) {
                data.remove(rowIndex);
                fireTableRowsDeleted(rowIndex, rowIndex);
                return true;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
