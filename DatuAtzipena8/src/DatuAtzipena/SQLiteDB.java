package DatuAtzipena;

import java.sql.*;

/**
 * SQLiteDB klaseak SQLite datu-basearekin konektatzeko eta datuak kudeatzeko funtzioak eskaintzen ditu.
 * Klase honek SQLite datu-basean eragiketak (insert, delete, select) egin ditzake.
 */
public class SQLiteDB {

    private Connection connection;

    /**
     * SQLiteDB konstruktorea.
     * Hasieran, konexioa null balioarekin hasten da.
     */
    public SQLiteDB() {
        this.connection = null;
    }

    /**
     * SQLite datu-basearekin konektatzen saiatzen da.
     * Konektatzeko helbidea "jdbc:sqlite" formatua erabiliz adierazten da.
     * 
     * @throws SQLException SQL errorea gertatzen bada konexioa ezartzean
     */
    public void konektatu() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:./DatuAtzipenaDB/Enpresa.db");
            System.out.println("SQLite-ra konexioa eginda.");
        } catch (SQLException e) {
            System.out.println("Konexio errorea: " + e.getMessage());
        }
    }

    /**
     * Enpresa eta langileak taulatan datuak sartzen ditu.
     * SQLite datu-baseko "Enpresa" eta "Langileak" taulak betetzen ditu.
     * 
     * @throws SQLException SQL errorea gertatzen bada datuak sartzean
     */
    public void datuenInsert() {
        try {
            // Enpresa taularen datuak
            String sqlEnpresa = "INSERT INTO Enpresa (enpresa_id, izena, sektorea, langile_kop, telefonoa, korreoa, helbidea, hiria) VALUES " +
                                "(1, 'TecnoSoluciones S.A.', 'Tecnología', 120, '+52 55 1234 5678', 'contacto@tecnosoluciones.com', 'Av. Insurgentes Sur 1234, Piso 4', 'Ciudad de México'), " +
                                "(2, 'VerdeVida Agroindustrial', 'Agricultura', 80, '+57 1 9876 5432', 'info@verdevida.co', 'Calle 45 No. 23-56', 'Bogotá'), " +
                                "(3, 'SaludVital Clínicas', 'Salud', 50, '+34 910 234 567', 'contacto@saludvital.es', 'Calle Mayor 89, Planta 2', 'Madrid'), " +
                                "(4, 'Logística Global Express', 'Transporte y Logística', 150, '+1 212 345 6789', 'support@globalexpress.com', '456 7th Avenue', 'Nueva York');";

            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(sqlEnpresa);
                System.out.println("Enpresa taulan datuak sartuta");
            }

            // Langileak taularen datuak
            String sqlLangilea = "INSERT INTO Langileak (langile_id, abizena, izena, postua, tratua, jaiotze_data, kontratazio_data, helbidea, hiria, lanean, enpresa_id) VALUES " +
                                 "(1, 'Davolio', 'Nancy', 'Representante de ventas', 'Stra.', '1968-12-08', '1992-05-01', '507-20th Ave.E.', 'Seattle', 1, 4), " +
                                 "(2, 'Fuller', 'Andrew', 'Vicepresidente comercial', 'Dr.', '1952-02-19', '1992-08-14', '908 W. Capital Way', 'Tacoma', 1, 3), " +
                                 "(3, 'Leverling', 'Janet', 'Representante de ventas', 'Stra.', '1963-08-30', '1992-04-01', '722 Moss Bay Blvd.', 'Kirkland', 1, 3), " +
                                 "(4, 'Peacock', 'Margaret', 'Representante de ventas', 'Sra.', '1958-09-19', '1993-05-03', '4110 Old Redmond Rd.', 'Redmond', 0, 4), " +
                                 "(5, 'Buchanan', 'Steven', 'Gerente de ventas', 'Sr.', '1955-03-04', '1993-10-17', '14 Garrett Hill', 'Londres', 1, 2), " +
                                 "(6, 'Suyama', 'Michel', 'Representante de ventas', 'Sr.', '1963-07-02', '1993-10-17', 'Coventry House', 'Londres', 1, 1), " +
                                 "(7, 'King', 'Robert', 'Representante de ventas', 'Sr.', '1960-05-29', '1994-01-02', 'Edgeham Hollow', 'Londres', 0, 1), " +
                                 "(8, 'Callahan', 'Laura', 'Coordinador ventas internacionales', 'Stra.', '1958-01-09', '1994-03-05', '4726-11th Ave.N.E.', 'Seattle', 1, 4), " +
                                 "(9, 'Dodsworth', 'Anne', 'Representante de ventas', 'Stra.', '1969-07-02', '1994-11-02', '7 Houndstooth Rd.', 'Londres', 1, 2);";

            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(sqlLangilea);
                System.out.println("Langileak taulan datuka sartuta");
            }

        } catch (SQLException e) {
            System.out.println("INSERT egitean errorea: " + e.getMessage());
        }
    }

    /**
     * Enpresa eta langileak taulako datuak ezabatzen ditu.
     * "Enpresa" eta "Langileak" taulak hutsik uzten ditu.
     * 
     * @throws SQLException SQL errorea gertatzen bada datuak ezabatzean
     */
    public void datuenDelete() {
        String sqlEnpresa = "DELETE FROM Enpresa";
        String sqlLangilea = "DELETE FROM Langileak";
        try (Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(sqlEnpresa);
            System.out.println("Enpresa taulatik datuak ezabatuta");

            stmt.executeUpdate(sqlLangilea);
            System.out.println("Langieak taulatik datuak ezabatuta");
        } catch (SQLException e) {
            System.out.println("DELETE egitean errora: " + e.getMessage());
        }
    }

    /**
     * Langileak eta Enpresa taulen datuak aukeratzen ditu eta inprimatzen ditu.
     * Langile bakoitza enpresaren arabera antolatzen da.
     * 
     * @throws SQLException SQL errorea gertatzen bada datuak aukeratzean
     */
    public void datuenSelect() {
        String query = "SELECT " +
                       "Langileak.langile_id, " +
                       "Langileak.abizena, " +
                       "Langileak.izena, " +
                       "Langileak.postua, " +
                       "Langileak.tratua, " +
                       "Langileak.jaiotze_data, " +
                       "Langileak.kontratazio_data, " +
                       "Langileak.helbidea AS langilea_helbidea, " +
                       "Langileak.hiria AS langilea_hiria, " +
                       "Langileak.lanean, " +
                       "Langileak.enpresa_id AS langilea_enpresa_id, " + 
                       "Enpresa.enpresa_id AS enpresa_enpresa_id, " + 
                       "Enpresa.izena AS enpresa_izena, " +
                       "Enpresa.sektorea, " +
                       "Enpresa.langile_kop, " +
                       "Enpresa.telefonoa, " +
                       "Enpresa.korreoa, " +
                       "Enpresa.helbidea AS enpresa_helbidea, " +
                       "Enpresa.hiria AS enpresa_hiria " +
                       "FROM Langileak " +
                       "INNER JOIN Enpresa ON Langileak.enpresa_id = Enpresa.enpresa_id " +
                       "ORDER BY enpresa_izena";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            String azkenEnpresa = "";

            while (rs.next()) {
                String unekoEnpresa = rs.getString("enpresa_izena");

                if (!unekoEnpresa.equals(azkenEnpresa)) {
                    if (!azkenEnpresa.isEmpty()) {
                        System.out.println("---------------------------------------------------------");
                    }

                    System.out.println("******************** ENPRESA: " + unekoEnpresa + " ********************");
                    azkenEnpresa = unekoEnpresa;
                }

                System.out.println();
                System.out.println("\tLangilea ID: " + rs.getInt("langile_id"));
                System.out.println("\tLangilearen Abizena: " + rs.getString("abizena"));
                System.out.println("\tLangilearen Izena: " + rs.getString("izena"));
                System.out.println("\tLangilearen Postua: " + rs.getString("postua"));
                System.out.println("\tLangilearen Tratua: " + rs.getString("tratua"));
                System.out.println("\tLangilearen Jaiotze Data: " + rs.getString("jaiotze_data"));
                System.out.println("\tLangilearen Kontratazio Data: " + rs.getString("kontratazio_data"));
                System.out.println("\tLangilearen Helbidea: " + rs.getString("langilea_helbidea"));
                System.out.println("\tLangilearen Hiria: " + rs.getString("langilea_hiria"));
                System.out.println("\tLangilea Landean Dago: " + rs.getString("lanean"));
            }

            if (azkenEnpresa.isEmpty()) {
                System.out.println("Ez da erregistrorik aurkitu.");
            } else {
                System.out.println("---------------------------------------------------------");
                System.out.println("SELECT kontsulta eginda");
            }

        } catch (SQLException e) {
            System.out.println("SELECT egitean errorea: " + e.getMessage());
        }
    }

    /**
     * Datu-basearekin egiten den konexioa itxi egiten du.
     * Konexioa ixten da, SQL erroreak ezabatzen saiatuz.
     * 
     * @throws SQLException SQL errorea gertatzen bada konexioa ixtean
     */
    public void itxi() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Konexioa ixten.");
            }
        } catch (SQLException e) {
            System.out.println("Konexioa ixtean erroera: " + e.getMessage());
        }
    }
}
