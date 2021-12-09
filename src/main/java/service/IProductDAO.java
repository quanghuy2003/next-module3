package service;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    List<Product> findAll() throws SQLException;
    void add (Product product) throws SQLException;
    void edit (int id,Product product) throws SQLException;
    void delete (int id) throws SQLException;
    Product findById(int id) throws SQLException;
    List<Product> findByName(String name) throws SQLException;
}
