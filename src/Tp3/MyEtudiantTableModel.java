package Tp3;

import DataBase.EtudiantDAO;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyEtudiantTableModel extends AbstractTableModel {
    ArrayList<Object[]> data = new ArrayList<Object[]>();
    ResultSetMetaData rs;
    EtudiantDAO dao;

    public MyEtudiantTableModel(ResultSet rs, EtudiantDAO dao) {
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

    public void ajoutEtudiant(int cin, String nom, String prenom, double moyenne) {
        data.add(new Object[] { cin, nom, prenom, moyenne });
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        int moyIndex = columnNameToIndex("moyenne");
        return columnIndex == moyIndex;
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

    // Quand l'utilisateur modifie la moyenne dans la JTable
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        // Récupération des anciennes valeurs
        int cin = Integer.parseInt(data.get(rowIndex)[0] + "");
        String nom = data.get(rowIndex)[1] + "";
        String prenom = data.get(rowIndex)[2] + "";
        double moyenne = Double.parseDouble(aValue + "");

        // Mise à jour dans la BDD
        int a = dao.modifierEtudiant(cin, nom, prenom, moyenne);

        // Si OK → on met à jour dans l'IHM
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
            int cin = Integer.parseInt(data.get(rowIndex)[0].toString());
            int res = dao.deleteEtudiant(cin);
            if (res > 0) {
                data.remove(rowIndex);
                fireTableRowsDeleted(rowIndex, rowIndex);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
