package com.poly.controller;

import com.poly.common.Pagination;
import com.poly.common.Text;
import com.poly.common.Utils;
import com.poly.entity.Account;
import com.poly.entity.CartDetail;
import com.poly.entity.Product;
import com.poly.model.ProductResult;
import com.poly.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("shop")
public class ShopController {
    @Autowired
    ProductService productDAO;

    @Autowired
    CategoriesService categoriesDAO;


    @Autowired
    BrandService brandDAO;

    @Autowired
    SessionService session;

    @Autowired
    ShoppingCartService shoppingCartDAO;

    @Autowired
    CartDetailService cartDetailDAO;

    @GetMapping("")
    public String index(Model model, @RequestParam Optional<String> message,
                        @RequestParam("page") Optional<String> page,
                        @RequestParam("size") Optional<String> size,
                        @RequestParam("txtSearch") Optional<String> txtSearch) {
        if(txtSearch.isPresent() && txtSearch.get()!=null){
            model.addAttribute("mySearch", txtSearch.get());
        }else{
            model.addAttribute("mySearch", "");
        }
        int soTrang = !page.isPresent() ? 1 : Integer.parseInt(page.get());
        int soSanPham = !size.isPresent() ? 8 : Integer.parseInt(size.get());
        int tongSoTrang = txtSearch.isPresent()
                ? Utils.getTotalPage(soSanPham, productDAO.findByName(txtSearch.get()))
                : Utils.getTotalPage(soSanPham, productDAO.findProductExist2());
        if (soTrang < 1) {
            soTrang = 1;
        } else if (soTrang > tongSoTrang) {
            soTrang = tongSoTrang;
        }
        model.addAttribute("nameShop", Text.NAME_SHOP);
        model.addAttribute("nameShopUP", Text.NAME_SHOP_UP);
        model.addAttribute("nameShopFooter", Text.NAME_SHOP_FOOTER);
        model.addAttribute("phoneShopFooter", Text.PHONE_SHOP);
        model.addAttribute("soTrangHienTai", soTrang);
        model.addAttribute("soSanPhamHienTai", soSanPham);
        model.addAttribute("tongSoTrang", tongSoTrang);
        model.addAttribute("paging", Pagination.getListPage(soTrang,tongSoTrang));
        Pageable pageable = PageRequest.of(soTrang - 1, soSanPham);
        Page<Product> pageProduct = txtSearch.isPresent()
                ? productDAO.findByName(pageable, txtSearch.get())
                : productDAO.findProductExist(pageable);
        List<Product> list = pageProduct.getContent();
        List<ProductResult> listResult = Utils.getProductResult(list);
        if (message.isPresent()) {
            model.addAttribute("message", message.get());
        }
        model.addAttribute("listProduct", listResult);
        model.addAttribute("listCategory", categoriesDAO.findCategoriesExist());
        model.addAttribute("listBrand", brandDAO.findBrandExist());
        model.addAttribute("namePage","Mua sắm");
        Account khachHang = (Account) session.get("user");
        Integer cartId = khachHang != null ? khachHang.getCartId() : null;
        if (khachHang != null) {
            shoppingCartDAO.getAll().forEach(item -> {
                CartDetail cartDetail = cartDetailDAO.existByProductId(cartId, item.getId());
                if (cartDetail != null) {
                    cartDetail.setAmount(cartDetail.getAmount() + item.getSoLuong());
                    cartDetailDAO.save(cartDetail);
                } else {
                    CartDetail cartDetailNew = new CartDetail();
                    cartDetailNew.setCartId(cartId);
                    cartDetailNew.setProductId(item.getId());
                    cartDetailNew.setAmount(item.getSoLuong());
                    cartDetailDAO.save(cartDetailNew);
                }
            });
            shoppingCartDAO.clear();

            List<CartDetail> listCart = cartDetailDAO.getCartDetail(khachHang.getCartId());
            model.addAttribute("tongSoLuongGioHang", Utils.getNumberOfListCart(listCart));
            model.addAttribute("sessionUsername", khachHang.getUsername());
        } else {
            model.addAttribute("tongSoLuongGioHang", shoppingCartDAO.getCount());
        }
        return "customer/shop";
    }
}
