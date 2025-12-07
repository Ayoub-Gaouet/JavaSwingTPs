package DataBase;

import java.sql.*;

public class EtudiantDAO {
    Connection con = null;
    Statement st = null;

    public EtudiantDAO() {
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

    public int insertEtudiant(int cin, String nom, String prenom, Double moyenne) {
        // Execution des requetes d'insertions
        String req_insertion = "insert into etudiant values (?,?,?,?)";
        if (con != null) {
            try (PreparedStatement ps = con.prepareStatement(req_insertion)) {
                ps.setInt(1, cin);
                ps.setString(2, nom);
                ps.setString(3, prenom);
                ps.setDouble(4, moyenne);

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

    public int deleteEtudiant(int cin) {
        // supprime l'étudiant identifié par son cin
        String req_delete = "DELETE FROM etudiant WHERE cin = ?";
        if (con != null) {
            try (PreparedStatement ps = con.prepareStatement(req_delete)) {
                ps.setInt(1, cin);
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

    public int modifierEtudiant(int cin, String nom, String prenom, double moyenne) {
        // modifier les données des étudiants
        String req_update = "UPDATE etudiant SET nom = ?, prenom = ?, moyenne = ? WHERE cin = ?";
        if (con != null) {
            try (PreparedStatement ps = con.prepareStatement(req_update)) {
                ps.setString(1, nom);
                ps.setString(2, prenom);
                ps.setDouble(3, moyenne);
                ps.setInt(4, cin);

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

    public ResultSet selectEtudiant(String req_selection) {
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
