package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.webpr330479.entity.Product_24162096;
import vn.hcmute.webpr330479.entity.Seller_24162096;
import vn.hcmute.webpr330479.service.IProductService_24162096;
import vn.hcmute.webpr330479.service.ISellerService_24162096;
import vn.hcmute.webpr330479.service.impl.ProductServiceImpl_24162096;
import vn.hcmute.webpr330479.service.impl.SellerServiceImpl_24162096;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/seller-products")
public class SellerProductController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ISellerService_24162096 sellerService = new SellerServiceImpl_24162096();
    private final IProductService_24162096 productService = new ProductServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Seller_24162096> sellers = sellerService.findAll();
        Map<Seller_24162096, List<Product_24162096>> sellerMap = new LinkedHashMap<>();

        for (Seller_24162096 s : sellers) {
            List<Product_24162096> pList = productService.findBySellerId(s.getSellerId());
            sellerMap.put(s, pList);
        }

        request.setAttribute("sellerMap", sellerMap);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/seller-products.jsp").include(request, response);
    }
}
