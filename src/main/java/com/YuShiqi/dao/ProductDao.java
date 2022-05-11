package com.YuShiqi.dao;

import com.YuShiqi.model.Product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements  IProductDao{
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "INSERT INTO Product(ProductName,ProductDescription,Picture,Price,CategoryId) VALUES (?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if(product.getPicture()!=null) {
            //for sql server
            pt.setBinaryStream(3, product.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) throws SQLException {
        int n = 0;
        String sql = "DELETE FROM Product WHERE ProductId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,productId);
        n = ps.executeUpdate();
        if (n > 0){
            return n;
        }
        return 0;
    }

    @Override
    public int update(Product instance, Connection con) throws SQLException {
        int n = 0;
        String sql = "UPDATE Product SET ProductName=?,ProductDescription=?,Pricture=?,Price=?,CategoryId=? WHERE ProductId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,instance.getProductName());
        ps.setString(2,instance.getProductDescription());
        if(instance.getPicture() != null){
            ps.setBinaryStream(3,instance.getPicture());
        }
        ps.setDouble(4,instance.getPrice());
        ps.setInt(5,instance.getCategoryId());
        ps.setInt(6,instance.getProductId());
        n = ps.executeUpdate();
        if (n > 0){
            return n;
        }
        return 0;
    }

    @Override
    public Product findById(Integer productId, Connection con) throws SQLException{
        Product product = null;
        String sql = "SELECT ProductId,ProductName,ProductDescription,Picture,Price,CategoryId FROM Product WHERE ProductId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,productId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            product = new Product();
            product.setProductId(rs.getInt("ProductId"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture(rs.getBinaryStream("Picture"));
            product.setPrice(rs.getDouble("Price"));
            product.setCategoryId(rs.getInt("CategoryId"));
        }
        if (product != null){
            return product;
        }
        return null;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT ProductId,ProductName,ProductDescription,Picture,Price,CategoryId FROM Product WHERE CategoryId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,categoryId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Product product = new Product();
            product.setProductId(rs.getInt("ProductId"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture(rs.getBinaryStream("Picture"));
            product.setPrice(rs.getDouble("Price"));
            product.setCategoryId(rs.getInt("CategoryId"));
            productList.add(product);
        }
        if(productList.isEmpty()){
            return null;
        }
        return productList;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {

        return null;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        return null;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        return null;
    }
}