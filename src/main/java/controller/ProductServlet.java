package controller;

import model.Product;
import service.IProductDAO;
import service.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    IProductDAO productDAO = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action== null){
            action= "";
        }
        switch (action){
            case "find":
                try {
                    showFind(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "create" :
                showCreate(request,response);
                break;
            case"edit":
                try {
                    showEdit(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    showList(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    private void showFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        productDAO.findByName(name);
        response.sendRedirect("/products");
    }


    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.findById(id);
        request.setAttribute("product", product);
        requestDispatcher.forward(request, response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
//        List<Product> products= productDAO.findAll();
//        request.setAttribute("products", products);
//        requestDispatcher.forward(request, response);
        String name = request.getParameter("name");
        if (name == null) {
            List<Product> products = productDAO.findAll();
            request.setAttribute("products", products);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
            requestDispatcher.forward(request, response);
        } else {
            List<Product> products = productDAO.findByName(name);
            request.setAttribute("products", products);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                try {
                    create(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    edit(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        Product product = new Product(id, name, price, quantity,color,description,category);
        productDAO.edit(id,product);
        response.sendRedirect("/products");
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");

        Product product = new Product(id, name, price, quantity,color,description,category);
        productDAO.add(product);
        response.sendRedirect("/products");
    }

}
