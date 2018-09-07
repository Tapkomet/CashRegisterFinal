package ua.training.controller.util;
public interface Path {
    String MANAGER = "/api/manager";
    String ADMIN = "/api/admin";
    String ADMIN_USERS = "/api/admin/users";
    String ADMIN_EDIT_USER = "/api/admin/users/edit";
    String CASHIER = "/api/cashier";
    String CASHIER_CHECKS = "/api/cashier/checks";
    String CASHIER_CHECKS_ADD = "/api/cashier/checks/add";
    String MANAGER_PRODUCTS = "/api/manager/products";
    String MANAGER_PRODUCT = "/api/manager/product";
    String MANAGER_ADD_PRODUCT = "/api/manager/addProduct";
    String MANAGER_EDIT_PRODUCT = "/api/manager/editProduct";
    String MANAGER_DELETE_PRODUCT = "/api/manager/deleteProduct";
    String USER_LOGIN = "/api/user-login";
    String USER_LOGOUT = "/api/logout";
    String USER_REGISTER = "/api/user-register";
    String EXCEPTION = "/api/exception";

    String ADMIN_BASE = "/WEB-INF/adminbase.jsp";
    String CASHIER_BASE = "/WEB-INF/cashierbase.jsp";
    String MANAGER_BASE = "/WEB-INF/managerbase.jsp";
    String CHECK_LIST = "/WEB-INF/checklist.jsp";
    String ERROR = "/WEB-INF/error.jsp";
    String PRODUCT_LIST = "/WEB-INF/productlist.jsp";
    String CASHIER_PRODUCT_LIST = "/WEB-INF/cashierproductlist.jsp";
    String USER_LIST = "/WEB-INF/userlist.jsp";
    String PRODUCT = "/WEB-INF/product.jsp";
    String CHECK = "/WEB-INF/check.jsp";
    String INDEX = "/index.jsp";
    String LOGIN = "/login.jsp";
    String REGISTRATION = "/registration.jsp";
}
