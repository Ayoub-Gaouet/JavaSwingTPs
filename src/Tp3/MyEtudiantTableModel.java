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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            int cinIndex = columnNameToIndex("cin");
            int nomIndex = columnNameToIndex("nom");
            int prenomIndex = columnNameToIndex("prenom");
            // fallback si les noms de colonnes ne sont pas trouvés
            if (cinIndex == -1)
                cinIndex = 0;
            if (nomIndex == -1)
                nomIndex = 1;
            if (prenomIndex == -1)
                prenomIndex = 2;

            Object[] row = data.get(rowIndex);
            int cin = Integer.parseInt(row[cinIndex].toString());
            String nom = row[nomIndex].toString();
            String prenom = row[prenomIndex].toString();

            double moyenne;
            if (aValue instanceof Number) {
                moyenne = ((Number) aValue).doubleValue();
            } else {
                moyenne = Double.parseDouble(aValue.toString().trim());
            }

            int result = dao.modifierEtudiant(cin, nom, prenom, moyenne);
            if (result > 0) {
                data.get(rowIndex)[columnIndex] = moyenne;
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
