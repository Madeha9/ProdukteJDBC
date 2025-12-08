package ProductImportClasses;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    //Methoden
//    Methode void insertAll(List products)
//    Methode List findAll()
//    Optional: List findActive(), List findByMinPrice(BigDecimal minPrice)
    //Methode um einzufügen oder update
    private List<Product> mapResultSetToProducts(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setCategory(rs.getString("category"));
            product.setActive(rs.getBoolean("active"));
//            product.setCreatedBy(rs.getString("created_by"));
            products.add(product);
        }
        return products;
    }

    void insertAll(List<Product> products, Connection conn) throws SQLException {

        // UPSERT SQL (PostgreSQL)
        String sql = """
                    INSERT INTO product (id, name, price, category,active)
                    VALUES (?, ?, ?, ?,?)
                    ON CONFLICT (id) DO UPDATE
                
                    SET
                         name = EXCLUDED.name,
                        price = EXCLUDED.price,
                        category = EXCLUDED.category,
                        active  = Excluded.active
                """;

        // try-with-resources für PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false); // Start Transaktion

            // Batch vorbereiten
            for (Product product : products) {
                ps.setInt(1, product.getId());
                ps.setString(2, product.getName());
                ps.setBigDecimal(3, product.getPrice());
                ps.setString(4, product.getCategory());
                ps.setBoolean(5, product.isActive());
                ps.addBatch();


            // Batch ausführen
            ps.executeBatch();
            conn.commit();
            System.out.println("✔ Produkte erfolgreich importiert oder aktualisiert!");
            }
        } catch (SQLException ex) {
            conn.rollback();
            System.out.println("❌ Fehler beim Importieren/Aktualisieren: " + ex.getMessage());
        } finally {
            conn.setAutoCommit(true); // AutoCommit wieder aktivieren
        }
    }

    //Daten aus datenbank lesen
    public List<Product> findAll(Connection conn) throws SQLException {
        String sql = """
                    SELECT * FROM product;
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapResultSetToProducts(rs);
        } catch (SQLException e) {
            System.out.println("❌ Fehler beim Abrufen aller Produkte: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Product> findActive(Connection conn) throws SQLException {
        String sql = """
                    SELECT * FROM product where active = true;
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapResultSetToProducts(rs);
        } catch (SQLException e) {
            System.out.println("❌ Fehler beim Abrufen der aktiven Produkte: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Product> findActiveByMinPrice(Connection conn, BigDecimal minPrice) throws SQLException {
        String sql = "SELECT * FROM product WHERE active = TRUE AND price >= ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, minPrice); // Parameter für Mindestpreis setzen
            try (ResultSet rs = ps.executeQuery()) {
                return mapResultSetToProducts(rs); // deine Methode zum Mapping
            }
        } catch (SQLException e) {
            System.out.println("❌ Fehler beim Abrufen aktiver Produkte nach minPrice: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Product> findByMinPrice(Connection conn, BigDecimal minPrice) throws SQLException {
        String sql = "SELECT * FROM product WHERE price >= ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, minPrice); // minPrice aus Parameter
            try (ResultSet rs = ps.executeQuery()) {
                return mapResultSetToProducts(rs);
            }
        } catch (SQLException e) {
            System.out.println("❌ Fehler beim Abrufen der Produkte nach minPrice: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}



