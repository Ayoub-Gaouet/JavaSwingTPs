package DataBase;

import java.sql.*;

public class FormationDAO {
    Connection con = null;
    Statement st = null;

    public FormationDAO() {
        // Chargement driver
        try {
            Class.forName(DataBaseConfig.DB_DRIVER);
            System.out.println("Driver is OK...");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur driver" + e.getMessage());
        }
        // Connexion à la base
        try {
            con = DriverManager.getConnection(
                    DataBaseConfig.DB_URL,
                    DataBaseConfig.DB_USER,
                    DataBaseConfig.DB_PASSWORD);
            System.out.println("Connected :) ...");
        } catch (SQLException e) {
            System.out.println("erreur connexion" + e.getMessage());
        }

        if (con != null) {
            try {
                st = con.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int insertFormation(String titre,String formateur, Integer heures_total, Integer heures_realisees, String statut) {
        String req = "INSERT INTO Formation (titre, formateur, heures_total, heures_realisees, statut) VALUES (?,?,?,?,?)";

        if (con != null) {
            try (PreparedStatement ps = con.prepareStatement(req)) {
                ps.setString(1, titre);
                ps.setString(2, formateur);
                ps.setInt(3, heures_total);
                ps.setInt(4, heures_realisees);
                ps.setString(5, statut);
                int a = ps.executeUpdate();
                System.out.println("inserted ...");

                return a;
            } catch (SQLException e) {
                System.out.println("Erreur insertion" + e.getMessage());
            }
        } else
            System.out.println("Connection null");
        return 0;
    }

    public int deleteFormation(int id) {
        String req_delete = "DELETE FROM Formation WHERE id = ?";
        if (con != null) {
            try (PreparedStatement ps = con.prepareStatement(req_delete)) {
                ps.setInt(1, id);
                int affected = ps.executeUpdate();
                System.out.println("delete: " + affected + " row(s) affected.");
                return affected;
            } catch (SQLException e) {
                System.out.println("Erreur suppression: " + e.getMessage());
            }
        } else {
            System.out.println("Connection null");
        }
        return 0;
    }
    public static String changeStatus(int heures_realisees, int heures_total) {
        if (heures_realisees == 0){
            return "Non Débutée";
        }
        if ((0<heures_realisees )&& (heures_realisees<heures_total)){
            return "En cours";
        }
        if (heures_realisees==heures_total){
            return "Terminé";
        }
        return "";
    }

    public int updateFormation(int id, String formateur ,int heures_realisees, int heures_total) {
        String statut = changeStatus(heures_realisees,heures_total);
        String req = "UPDATE Formation SET formateur = ?, heures_realisees = ?, statut = ? WHERE id_formation = ?";

        if (con != null) {
            try (PreparedStatement ps = con.prepareStatement(req)) {
                System.out.println(heures_realisees + " " + heures_total);
                ps.setString(1, formateur);
                ps.setInt(2, heures_realisees);
                ps.setString(3, statut);
                ps.setInt(4, id);
                return ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    public int modifierFormation(String titre,String formateur, Integer heures_total, Integer heures_realisees) {
        String statut = changeStatus(heures_realisees,heures_total);
        String req_update = "UPDATE Formation SET titre = ?, formateur = ?, heures_total = ?, heures_realisees = ?,statut = ? WHERE id_formation = ?";
        if (con != null) {
            try (PreparedStatement ps = con.prepareStatement(req_update)) {
                ps.setString(1, titre);
                ps.setString(2, formateur);
                ps.setInt(3, heures_total);
                ps.setInt(4, heures_realisees);
                ps.setString(5, statut);


                int affected = ps.executeUpdate();
                System.out.println("update: " + affected + " row(s) affected.");
                return affected;
            } catch (SQLException e) {
                System.out.println("Erreur mise à jour: " + e.getMessage());
            }
        } else {
            System.out.println("Connection null");
        }
        return 0;
    }


    public ResultSet selectFormation(String req_selection) {
        if (st != null) {
            try {
                ResultSet rs = st.executeQuery(req_selection);
                return rs;
            } catch (SQLException e) {
                System.out.println("erreur de selection" + e.getMessage());
            }
        } else
            System.out.println("Statement null");
        return null;
    }

    void afficheResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Afficher les en-têtes de colonnes
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println();

                // Afficher les données des lignes
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                System.out.println("Erreur de sélection : " + e.getMessage());
            }
        } else {
            System.out.println("Le ResultSet est vide ou nul.");
        }
    }

}
