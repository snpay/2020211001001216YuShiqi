package com.YuShiqi.dao;

import com.YuShiqi.model.Category;
import com.sun.istack.internal.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements IcategoryDao{
    @Override
    public List<Category> findAllCategory(Connection con) throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        String sql = "select * from category";
        PreparedStatement pt = con.prepareStatement(sql);
        ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            categoryList.add(
                    new Category(Integer.parseInt(rs.getString("categoryId")),
                            rs.getString("categoryName"),
                            rs.getString("description"),
                            rs.getBoolean("active"))
            );
        }
        return categoryList;
    }
    @Override
    public String findByCategoryId(Connection con, int categoryId) throws SQLException {
        String res = null;
        String sql = "select * from category where categoryId = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, categoryId);
        ResultSet rs = pt.executeQuery();
        if(rs.next()) {
            res = rs.getString("categoryName");
        }
        return res;
    }
}