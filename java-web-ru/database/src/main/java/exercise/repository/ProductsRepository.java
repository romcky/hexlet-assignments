package exercise.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        var sql = "SELECT * FROM products WHERE id = ?";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                var product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }
            return Optional.empty();
        }
    }

    public static List<Product> getEntities() throws SQLException {
        String sql = "SELECT * FROM products";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<Product>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                Product product = new Product(title, price);
                product.setId(id);
                result.add(product);
            }
            return result;
        }
    }
    // END
}
