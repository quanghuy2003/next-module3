package service;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{

    public ProductDAO() {
    }
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/module3?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product");) {

            ResultSet rs1 = preparedStatement.executeQuery();

            while (rs1.next()) {
                int id = rs1.getInt("id");
                String name = rs1.getString("name");
                int price = rs1.getInt("price");
                int quantity = rs1.getInt("quantity");
                String color = rs1.getString("color");
                String description = rs1.getString("description");
                String category = rs1.getString("category");
                products.add(new Product(id, name, price, quantity,color,description,category));
            }
        } catch (SQLException e) {
        }
        return products;    }



    @Override
    public void add(Product product) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into product(id,name,price,quantity,color,description,category) value (?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, product.getId());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setInt(3, product.getPrice());
        preparedStatement.setInt(4, product.getQuantity());
        preparedStatement.setString(5,product.getColor());
        preparedStatement.setString(6,product.getDescription());
        preparedStatement.setString(7,product.getCategory());
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(int id, Product product) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("update product set name=?,price=?,quantity=?,color=?,description=?,category=? where id=?");) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setString(6,product.getCategory());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?");) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id=?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int id1 = rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            int quantity = rs.getInt("quantity");
            String color = rs.getString("color");
            String description = rs.getString("description");
            String category = rs.getString("category");
            product = new Product(id1, name, price, quantity,color,description,category);
        }
        return product;    }

    @Override
    public List<Product> findByName(String name) throws SQLException {
        List<Product> productList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where  name like ?");
        preparedStatement.setString(1, "%" + name + "%");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name1 = rs.getString("name");
            int price = rs.getInt("price");
            int quantity = rs.getInt("quantity");
            String color = rs.getString("color");
            String description = rs.getString("description");
            String category = rs.getString("category");
            productList.add(new Product(id, name1, price, quantity,color,description,category));
        }
        return productList;
    }
}
