package com.poly.common;

import java.util.ArrayList;
import java.util.List;

public class Text {

    public static String PHONE_SHOP = "0909.009.090";
    public static String EMAIL_SHOP = "mobileshop193@gmail.com";

    public static String NAME_SHOP ="Phone";
    public static String NAME_SHOP_UP ="PHONE";
    public static String NAME_SHOP_FOOTER = "SHOP Điện Thoại Chính Hãng Từ Năm 2022";


    //Trang chủ
    public static String TILE_SLIDE_1 = "Lựa chọn tuyệt vời nhất";
    public static String CONTENT_SLIDE_1 = "Mobile Shop sẽ mang đến cho bạn những trải nghiệm tốt nhất";
    public static String TILE_SLIDE_2 = "Uy tín tạo nên thương hiệu";
    public static String CONTENT_SLIDE_2 = "Uy tín không phải là thứ bạn có thể mua, nó là cái chúng tôi cung cấp cho bạn";
    public static String TILE_SLIDE_3 = "Đa dạng về sản phẩm";
    public static String CONTENT_SLIDE_3 = "Phục vụ nhu cầu mua sắm đa dạng là nhiệm vụ của chúng tôi";
    public static String TILE_SLIDE_4 = "Rẻ nhất mọi thời đại";
    public static String CONTENT_SLIDE_4 = "Không cần phải suy nghĩ về giá cả khi mua hàng của chúng tôi";

    public static List<String> getListTitle(){
        List<String> listTitile = new ArrayList<>();
        listTitile.add(TILE_SLIDE_1);
        listTitile.add(TILE_SLIDE_2);
        listTitile.add(TILE_SLIDE_3);
        listTitile.add(TILE_SLIDE_4);
        return listTitile;
    }

    public static List<String> getListContent(){
        List<String> listContent = new ArrayList<>();
        listContent.add(CONTENT_SLIDE_1);
        listContent.add(CONTENT_SLIDE_2);
        listContent.add(CONTENT_SLIDE_3);
        listContent.add(CONTENT_SLIDE_4);
        return listContent;
    }


    // Trang liên hệ
    public static String LOCATION = "Lô 24, Toà nhà Innovation, Công viên phần mềm Quang Trung, Quận 12, Thành phố Hồ Chí Minh.";
    public static String TIME_OPEN = "THỨ 2 - THỨ 6: 7:00 - 21:00";
    public static String TIME_CLOSE = "THỨ 7 - CN: 7:00 - 11:00";

}
