package DatuAtzipena;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class DatuakEguneratu {

    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/fitxategikonbinatuak";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "mysql";

    private static final String ACCESS_URL = "jdbc:ucanaccess://./DatuAtzipenaDB/Enpresa.accdb";

    private static final String SQLITE_URL = "jdbc:sqlite:./DatuAtzipenaDB/Enpresa.db";

    /**
     * SQLite datu basean dauden datuak Access datu basean sartzen ditu.
     */
    public static void SQLiteToAccess() {
        try (Connection sqliteConn = DriverManager.getConnection(SQLITE_URL);
             Connection accessConn = DriverManager.getConnection(ACCESS_URL)) {

            Set<Integer> existitzenIDak = getExistitzenDirenIDak(accessConn);

            String selectBerria = "SELECT * FROM Langileak WHERE langile_id NOT IN (" +
                    String.join(",", existitzenIDak.stream().map(String::valueOf).toArray(String[]::new)) + ")";

            try (PreparedStatement sqliteStmt = sqliteConn.prepareStatement(selectBerria);
                 ResultSet resultSet = sqliteStmt.executeQuery()) {

                while (resultSet.next()) {
                    int langileId = resultSet.getInt("langile_id");
                    String abizena = resultSet.getString("Abizena");
                    String izena = resultSet.getString("Izena");
                    String postua = resultSet.getString("postua");
                    String tratua = resultSet.getString("Tratua");
                    String jaiotzeData = resultSet.getString("jaiotze_data");
                    String kontratazioData = resultSet.getString("kontratazio_data");
                    String helbidea = resultSet.getString("Helbidea");
                    String hiria = resultSet.getString("Hiria");
                    boolean lanean = resultSet.getBoolean("Lanean");
                    int enpresId = resultSet.getInt("enpresa_id");

                    sartuDatuakAccessen(accessConn, langileId, abizena, izena, postua, tratua, jaiotzeData,
                            kontratazioData, helbidea, hiria, lanean, enpresId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Access datu basean dagoen langile id-ak itzultzen ditu.
     * @param accessConn Access datu basearekin konektatutako konexioa.
     * @return Langile ID-ak zein existitzen diren jakinarazten duen multzoa.
     * @throws SQLException SQL akatsak
     */
    private static Set<Integer> getExistitzenDirenIDak(Connection accessConn) throws SQLException {
        Set<Integer> existitzenIDak = new HashSet<>();
        String query = "SELECT idLangilea FROM langilea";
        try (PreparedStatement stmt = accessConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                existitzenIDak.add(rs.getInt("idLangilea"));
            }
        }
        return existitzenIDak;
    }

    /**
     * Langile baten datuak Access datu basean sartzen ditu.
     * @param accessConn Access datu basearekin konektatutako konexioa.
     * @param langileId Langilearen ID-a.
     * @param abizena Langilearen abizena.
     * @param izena Langilearen izena.
     * @param postua Langilearen kargua.
     * @param tratua Langilearen tratua.
     * @param jaiotzeData Langilearen jaiotze data.
     * @param kontratazioData Langilearen kontratazio data.
     * @param helbidea Langilearen helbidea.
     * @param hiria Langilearen hiria.
     * @param lanean Langileak lanean jarraitzen duen edo ez.
     * @param enpresId Langilearen enpresaren ID-a.
     * @throws SQLException SQL akatsak.
     */
    private static void sartuDatuakAccessen(Connection accessConn, int langileId, String abizena, String izena,
                                             String postua, String tratua, String jaiotzeData, String kontratazioData,
                                             String helbidea, String hiria, boolean lanean, int enpresId) throws SQLException {
        String insertQuery = "INSERT INTO langilea (idLangilea, Abizena, Izena, Kargua, Tratua, JaiotzeData, KontratazioData, " +
                "Helbidea, Hiria, Lanean, Enpresa_idEnpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = accessConn.prepareStatement(insertQuery)) {
            stmt.setInt(1, langileId);
            stmt.setString(2, abizena);
            stmt.setString(3, izena);
            stmt.setString(4, postua);
            stmt.setString(5, tratua);
            stmt.setString(6, jaiotzeData);
            stmt.setString(7, kontratazioData);
            stmt.setString(8, helbidea);
            stmt.setString(9, hiria);
            stmt.setBoolean(10, lanean);
            stmt.setInt(11, enpresId);
            stmt.executeUpdate();
        }
    }

    /**
     * Langilearen kargua eguneratzeko funtzioa.
     */
    public static void EguneratuKargua() {
        Scanner scanner = new Scanner(System.in);
        try {Connection MySQLConn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);

            System.out.print("Sartu langilearen id-a kargura aldatzeko: ");
            int langileId = scanner.nextInt();
            scanner.nextLine(); //

            String selectQuery = "SELECT Kargua FROM langilea WHERE idLangilea = ?";
            try (PreparedStatement selectStmt = MySQLConn.prepareStatement(selectQuery)) {
                selectStmt.setInt(1, langileId);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        String horaingoKargua = rs.getString("Kargua");
                        System.out.println("Horain duen kargua: " + horaingoKargua);

                        System.out.print("Sartu kargu berria: ");
                        String karguBerria = scanner.nextLine();

                        String updateQuery = "UPDATE langilea SET Kargua = ? WHERE idLangilea = ?";
                        try (PreparedStatement updateStmt = MySQLConn.prepareStatement(updateQuery)) {
                            updateStmt.setString(1, karguBerria);
                            updateStmt.setInt(2, langileId);
                            updateStmt.executeUpdate();
                            System.out.println("Kargua zuzen eguneratu da.");
                        }
                    } else {
                        System.out.println("Ez da aurkitu sartutako id-a.");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * MySQL datu basean dauden karguak Access datu basean eguneratzen ditu.
     */
    public static void MysqlToAccess() {
        try {Connection MySQLConn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
        Connection AccessConn = DriverManager.getConnection(ACCESS_URL);

        String selectQuery = "SELECT idLangilea, Kargua FROM langilea";
        try (PreparedStatement mysqlStmt = MySQLConn.prepareStatement(selectQuery);
                ResultSet rs = mysqlStmt.executeQuery()) {

            while (rs.next()) {
                int langileId = rs.getInt("idLangilea");
                String kargua = rs.getString("Kargua");

                String updateQuery = "UPDATE langilea SET Kargua = ? WHERE idLangilea = ?";
                try (PreparedStatement accessStmt = AccessConn.prepareStatement(updateQuery)) {
                    accessStmt.setString(1, kargua);
                    accessStmt.setInt(2, langileId);
                    accessStmt.executeUpdate();
                }
            }
        }

        System.out.println("Datuak eguneratuta MySQL-tik Access-era.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Langile berri bat sortzen du Access datu basean.
     */
    public static void AccessenLangileaSortu() {
        Scanner scanner = new Scanner(System.in);

        try {Connection accessConn = DriverManager.getConnection(ACCESS_URL);

            System.out.print("Sartu langilearen ID-a: ");
            int langileId = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Sartu langilearen abizena: ");
            String abizena = scanner.nextLine();

            System.out.print("Sartu langilearen izena: ");
            String izena = scanner.nextLine();

            System.out.print("Sartu langilearen kargua: ");
            String postua = scanner.nextLine();

            System.out.print("Sartu langilearen tratua: ");
            String tratua = scanner.nextLine();

            System.out.print("Sartu langilearen jaiotze data (YYYY-MM-DD): ");
            Date jaiotzeData = Date.valueOf(scanner.nextLine());

            System.out.print("Sartu langilearen kontratazio data (YYYY-MM-DD): ");
            Date kontratazioData = Date.valueOf(scanner.nextLine());

            System.out.print("Sartu langilearen helbidea: ");
            String helbidea = scanner.nextLine();

            System.out.print("Sartu langilearen hiria: ");
            String hiria = scanner.nextLine();

            System.out.print("Horain lanean dago? (true/false): ");
            boolean lanean = scanner.nextBoolean();

            System.out.print("Sartu enpresaren ID-a: ");
            int enpresId = scanner.nextInt();

            String insertQuery = "INSERT INTO langilea (idLangilea, Abizena, Izena, Kargua, Tratua, JaiotzeData, KontratazioData, " +
                    "Helbidea, Hiria, Lanean, Enpresa_idEnpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = accessConn.prepareStatement(insertQuery)) {
                stmt.setInt(1, langileId);
                stmt.setString(2, abizena);
                stmt.setString(3, izena);
                stmt.setString(4, postua);
                stmt.setString(5, tratua);
                stmt.setDate(6, jaiotzeData);
                stmt.setDate(7, kontratazioData);
                stmt.setString(8, helbidea);
                stmt.setString(9, hiria);
                stmt.setBoolean(10, lanean);
                stmt.setInt(11, enpresId);
                stmt.executeUpdate();
                System.out.println("Erregistroa zuzen sortua Access-eko datu basean.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * Access datu basean dagoen datu multzoa SQLite datu basean sartzen du.
     */
    public static void AccessToSQLite() {
        try {Connection sqliteConn = DriverManager.getConnection(SQLITE_URL);
        Connection accessConn = DriverManager.getConnection(ACCESS_URL);

            String selectQuery = "SELECT * FROM langilea";
            try (PreparedStatement accessStmt = accessConn.prepareStatement(selectQuery);
                 ResultSet rs = accessStmt.executeQuery()) {

                while (rs.next()) {
                    int langileId = rs.getInt("idLangilea");
                    String abizena = rs.getString("Abizena");
                    String izena = rs.getString("Izena");
                    String postua = rs.getString("Kargua");
                    String tratua = rs.getString("Tratua");
                    Date jaiotzeData = rs.getDate("JaiotzeData");
                    Date kontratazioData = rs.getDate("KontratazioData");
                    String helbidea = rs.getString("Helbidea");
                    String hiria = rs.getString("Hiria");
                    boolean lanean = rs.getBoolean("Lanean");
                    int enpresId = rs.getInt("Enpresa_idEnpresa");
                    String upsertQuery = "INSERT OR REPLACE INTO Langileak (langile_id, abizena, izena, postua, tratua, jaiotze_data, kontratazio_data, " +
                            "helbidea, hiria, lanean, enpresa_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    try (PreparedStatement sqliteStmt = sqliteConn.prepareStatement(upsertQuery)) {
                        sqliteStmt.setInt(1, langileId);
                        sqliteStmt.setString(2, abizena);
                        sqliteStmt.setString(3, izena);
                        sqliteStmt.setString(4, postua);
                        sqliteStmt.setString(5, tratua);
                        sqliteStmt.setDate(6, jaiotzeData);
                        sqliteStmt.setDate(7, kontratazioData);
                        sqliteStmt.setString(8, helbidea);
                        sqliteStmt.setString(9, hiria);
                        sqliteStmt.setBoolean(10, lanean);
                        sqliteStmt.setInt(11, enpresId);
                        sqliteStmt.executeUpdate();
                    }
                }
            }

            System.out.println("Datuak eguneratuta Access-etik SQLite-ra..");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
