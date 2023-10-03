package com.poly.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.poly.entity.Account;
import com.poly.entity.CartDetail;
import com.poly.entity.Product;
import com.poly.entity.RolesDetail;
import com.poly.model.ProductModel;
import com.poly.model.ProductResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.expression.Strings;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

public class Utils {
    public static String convertObjectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(object);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String convertObjectToJsonObject(Object object) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertListObjectToJsonArray(List<?> objects) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            mapper.writeValue(out, objects);
            final byte[] data = out.toByteArray();
            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> String convertObjectToJsonString(Object data) throws Exception {

        if (data == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(data);
        return result;
    }

    public static boolean createFolder(String path) {
        File theDir = new File(path);
        if (!theDir.exists()) {
            boolean result = false;

            try {
                System.out.println("Begin make dir: " + path);
                theDir.setWritable(true, false);
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                result = false;
                se.printStackTrace();
            }
            return result;
        } else {
            return true;
        }
    }
    public static String saveUploadedFile(MultipartFile image, String resourcePath, String filePath) {
        String imageUrl = "";
        if (!image.isEmpty()) {
            try {
                createFolder(resourcePath + filePath);
                byte[] bytes = image.getBytes();
                filePath = filePath + Calendar.getInstance().getTimeInMillis() + image.getOriginalFilename();
                String fPath = resourcePath + filePath;
                Path path = Paths.get(fPath);
                Files.write(path, bytes);
                imageUrl = filePath;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return imageUrl;
    }

    public static String convertStringToSlug(String str) {
        String slug = "";
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            slug = pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
        } catch (Exception e) {
            e.printStackTrace();
            slug = "";
        }
        return slug;
    }

    public static String getDateFormatVN(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("dd/MM/yyyy").format(date));
        }
    }

    public static String getMonthYearFormatVN(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("MM/yyyy").format(date));
        }
    }

    public static String getDayFormatVN(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("dd").format(date));
        }
    }

    public static String getMonthFormatVN(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("MM").format(date));
        }
    }

    public static String getDateToCompare(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("yyyy/MM/dd").format(date));
        }
    }

    public static String getMonthToCompare(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("yyyy/MM").format(date));
        }
    }

    public static String getDateFormatVNForTopicFirebase(Date date) {
        if (date == null) {
            date = new Date();
            return (new SimpleDateFormat("ddMMyyyy").format(date));
        } else {
            return (new SimpleDateFormat("ddMMyyyy").format(date));
        }
    }

    public static String getDatetimeFormatVN(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
        }
    }

    public static String getFullDateTimeWithMilisecondFormatVN(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date));
        }
    }

    public static String getHourMinuteSecond(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("HH:mm:ss").format(date));
        }
    }

    public static String getFullDatetimeFormatVN(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date));
        }

    }

    public static String getDatetimeString(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
        }
    }

    public static String getDateString(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("yyyy-MM-dd").format(date));
        }
    }

    public static int getHourOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static Date addDate(Date date, int totalDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, totalDate);
        return calendar.getTime();
    }

    public static int getDateOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonthOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Date getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance(); // this takes current date
        if (date != null) {
            c.setTime(date);
        }
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance(); // this takes current date
        c.setTime(getFirstDateOfMonth(date));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static Date getFirstDateOfYear(Date date) {
        Calendar c = Calendar.getInstance(); // this takes current date
        if (date != null) {
            c.setTime(date);
        }

        c.add(Calendar.YEAR, 1);
        c.set(Calendar.DAY_OF_YEAR, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date gotoPreviousYear(Date date, int totalDate) {
        Calendar c = Calendar.getInstance(); // this takes current date
        if (date != null) {
            c.setTime(date);
        }

        c.add(Calendar.YEAR, -totalDate);
        c.set(Calendar.DAY_OF_YEAR, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getLastDateOfYear(Date date) {
        Calendar c = Calendar.getInstance(); // this takes current date
        if (date != null) {
            c.setTime(date);
        }
        c.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c.set(Calendar.MONTH, 11); // 11 = december
        c.set(Calendar.DAY_OF_MONTH, 31); // new years eve

        return c.getTime();
    }

    public static String convertStringToDate(Date indate) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");

        try {
            dateString = sdfr.format(indate);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

    public static String convertDateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    public static String convertDateTimeToString(Timestamp dataTime){
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateTimeFormat.format(dataTime);
    }

    public static Timestamp covertStringToTimestamp(String timestampString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date parsedDate = dateFormat.parse(timestampString);//my string
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (Exception e) { //this generic but you can control another types of exception
            return new Timestamp(System.currentTimeMillis());// Trả về thời gian hiện tại
        }
    }

    public static int getTotalPage(int soSanPham,int tongSoSanPham) {
        int tongSoTrang = 1;
        double tempDouble = (double) tongSoSanPham / soSanPham;
        int tempInt = (int) tempDouble;
        if (tempDouble - tempInt > 0) {
            tongSoTrang = tempInt + 1;
        } else {
            tongSoTrang = tempInt;
        }
        return tongSoTrang;
    }

    public static Date converStringToDate(String dateString) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateResult = (Date)formatter.parse(dateString);
        return dateResult;
    }

    //Paypal
    public static String getBaseURL(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuffer url =  new StringBuffer();
        url.append(scheme).append("://").append(serverName);
        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        if(url.toString().endsWith("/")){
            url.append("/");
        }
        return url.toString();
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    public static boolean checkRole(List<RolesDetail> rolesDetailList){
        boolean check=false;
        for(RolesDetail rolesDetail:rolesDetailList){
            if(rolesDetail.getRoleId()==1 || rolesDetail.getRoleId()==2){
                check=true;
                break;
            }
        }
        return check;
    }
    public static boolean checkIsNumber(String numberString){
        try{
            Integer.parseInt(numberString);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Format time "HH:mm:ss"
    public static String getLocalTimeString(LocalTime localTime) {
        if (localTime == null) {
            return "";
        } else {
            String result=localTime.toString();
            if(result.length()<6){
                result=result+":00";
            }
            return result;
        }
    }

    //Date format yyyy-MM-dd
    public static String getDateAndMonthString(String dateString) {
        if (dateString == null) {
            return "";
        } else {
            String day=dateString.substring(dateString.length()-2,dateString.length());
            String month=dateString.substring(dateString.indexOf("-")+1,dateString.indexOf("-")+3);
            String dayMonth=day+"."+month;
            return dayMonth;
        }
    }

    //Datetime format "yyyy-MM-dd HH:mm:ss"
    public static Date getDateTimeFromDateAndTimeString(String dateString,String timeString) throws ParseException {
        String dateTimeString=dateString+" "+timeString;
        Date result=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeString);
        return result;
    }

    public static double date1MinusDate2ToHours(Date date1,Date date2){
        double result=(date1.getTime()-date2.getTime())/3600000.0;
        return result;
    }

    public static Date getDateFromDateTimeLocalString(String dateTimeLocalString) throws ParseException {
        String dateString=dateTimeLocalString.substring(0,dateTimeLocalString.indexOf("T"));
        Date result=new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        return result;
    }

    // datetimelocal có dạng "22022-09-22T23:01"
    public static LocalTime getLocalTimeFromDateTimeLocalString(String dateTimeLocalString){
        String timeString=dateTimeLocalString.substring(dateTimeLocalString.indexOf("T")+1,dateTimeLocalString.length());
        LocalTime localTime=LocalTime.parse(timeString);
        return localTime;
    }
    public static String getDateStringVN(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("dd-MM-yyyy").format(date));
        }
    }
    public static String plusDateIntoDateString(String dateString,int numberDay) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date)formatter.parse(dateString);
        long millisecond=date.getTime() + (long)numberDay*24*60*60*1000;
        Date dateResult=new Date(millisecond);
        String result= formatter.format(dateResult);
        return result;
    }

    public static int getNumberOfDaysInMonth(String year, String month){
        YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
        int daysInMonth = yearMonthObject.lengthOfMonth();
        return daysInMonth;
    }

    public static int getDistanceBetweenTwoDateString(String dateString1,String dateString2) throws ParseException {
        //Date yyyy-mm-dd
        Date date1=converStringToDate(dateString1);
        Date date2=converStringToDate(dateString2);
        int diff = (int) ((date2.getTime() - date1.getTime())/1000/60/60/24);
        return diff;
    }
    public static String getYearFromDateString(String dateString) throws ParseException {
        Date date=converStringToDate(dateString);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        return String.valueOf(year);
    }
    public static String getMonthFromDateString(String dateString) throws ParseException {
        Date date=converStringToDate(dateString);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        return String.valueOf(month);
    }
    public static String getDayFromDateString(String dateString) throws ParseException {
        Date date=converStringToDate(dateString);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int day   = localDate.getDayOfMonth();
        return String.valueOf(day);
    }

    //Date string có dạng yyyy-mm-dd
    public static String formatDateString(String dateString){
        if (dateString == null) {
            return "";
        } else {
            String day=dateString.substring(dateString.length()-2,dateString.length());
            String month=dateString.substring(dateString.indexOf("-")+1,dateString.indexOf("-")+3);
            String year=dateString.substring(0,dateString.indexOf("-"));
            return day+"/"+month+"/"+year;
        }
    }
    public static long getSecondBetweenTwoDate(Date date1, Date date2) {
        long diffInMinutes = (date2.getTime() - date1.getTime())/1000;
        return diffInMinutes;
    }

    public static Date getDeliveryDate(){
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 3);
        dt = c.getTime();
        return dt;
    }

    public boolean checkAccountExist(String username, List<Account> list) {
        boolean check=false;
        for (Account account : list) {
            if (username.equalsIgnoreCase(account.getUsername())) {
                check=true;
                break;
            }
        }
        return check;
    }
    public boolean checkPhoneExist(String phone, List<Account> list) {
        boolean check=false;
        for (Account account : list) {
            if (phone.equalsIgnoreCase(account.getPhone())) {
                check=true;
                break;
            }
        }
        return check;
    }
    public boolean checkEmailExist(String email, List<Account> list) {
        boolean check=false;
        for (Account account : list) {
            if (email.equalsIgnoreCase(account.getEmail())) {
                check=true;
                break;
            }
        }
        return check;
    }

    public String getTime(long second){
        String time="";
        long min=second/60;
        long hours=min/60;
        long day=hours/24;
        long month=day/30;
        long year=month/365;
        if(second < 60){
            time=second + " giây trước";
        }else if(min < 60){
            time=min + " phút trước";
        }else if(hours < 24){
            time=hours + " giờ trước";
        }else if(day < 30){
            time=day + " ngày trước";
        }else{
            time=year + " năm trước";
        }
        return time;
    }

    public static int getTotalPage(int soSanPham, List<?> list) {
        int tongSoSanPham = list.size();
        int tongSoTrang = 1;
        float tempFloat = (float) tongSoSanPham / soSanPham;
        int tempInt = (int) tempFloat;
        if (tempFloat - tempInt > 0) {
            tongSoTrang = tempInt + 1;
        } else {
            tongSoTrang = tempInt;
        }
        return tongSoTrang;
    }

    private Path getPath(String filename) {
        File dir = Paths.get("src/main/resources/static/assets/img/products").toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return Paths.get(dir.getAbsolutePath(), filename);
    }

    public String save(MultipartFile file) {
//        String name = System.currentTimeMillis() + file.getOriginalFilename();
//        String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
        String filename =file.getOriginalFilename();
        Path path = this.getPath(filename);
        try {
            // upload file to server folder path
            file.transferTo(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;
    }
    public String uploadImage(HttpServletRequest req) throws IOException, ServletException {
        // đường dẫn thư mục tính từ gốc của website
        File dir = new File(req.getServletContext().getRealPath("/assets/img/products"));
        if (!dir.exists()) { // tạo nếu chưa tồn tại
            dir.mkdirs();
        }
        // lưu các file upload vào thư mục sp
        Part photo = req.getPart("images"); // file hình
        String filePath = photo.getSubmittedFileName();
        Path p = Paths.get(filePath); // creates a Path object
        String tenHinhAnh = p.getFileName().toString();
        File photoFile = new File(dir, filePath);
        if (!photoFile.exists()) {
            photo.write(photoFile.getAbsolutePath());
        }
        return tenHinhAnh;
    }
    public List<ProductModel> getListProductMVCResult(List<Product> list) {
        List<ProductModel> listProductMVCResult = new ArrayList<>();
        for (Product item : list) {
            ProductModel productMVCResult = new ProductModel();
            productMVCResult.setProduct(item);
            if(item.getPrice()*(100-item.getDiscount())/100 <= 2000000){
                productMVCResult.setMyClass("price1");
            }else if(item.getPrice()*(100-item.getDiscount())/100 <= 3000000){
                productMVCResult.setMyClass("price2");
            }else if(item.getPrice()*(100-item.getDiscount())/100 <= 4000000){
                productMVCResult.setMyClass("price3");
            }else if(item.getPrice()*(100-item.getDiscount())/100 > 4000000){
                productMVCResult.setMyClass("price4");
            }
            listProductMVCResult.add(productMVCResult);
        }
        return listProductMVCResult;
    }

    public static int getNumberOfListCart(List<CartDetail> list){
        int numberOfCart=0;
        if(list == null || list.isEmpty()){
            return 0;
        }
        for(CartDetail item:list){
            numberOfCart+=item.getAmount();
        }
        return  numberOfCart;
    }

    public static int getTotalMoneyOfOneProduct(CartDetail cartDetail){
        int result = cartDetail.getAmount()*cartDetail.getProductByProductId().getPrice();
        if(cartDetail.getProductByProductId().getDiscount() > 0){
            double temp = (double)cartDetail.getAmount() * cartDetail.getProductByProductId().getPrice() * (100 - cartDetail.getProductByProductId().getDiscount()) / (double) 100;
            result = (int) temp;
        }
        return result;
    }

    public static List<ProductResult> getProductResult(List<Product> list){
        List<ProductResult> listResult = new ArrayList<>();
        if(list != null){
            for(Product p : list){
                ProductResult productResult = new ProductResult();
                productResult.setId(p.getId());
                productResult.setProductName(p.getProductName());
                productResult.setPrice(p.getPrice());
                productResult.setDiscount(p.getDiscount());
                productResult.setDeleted(p.getDeleted());
                productResult.setNote(p.getNote());
                productResult.setImages(p.getImages());
                productResult.setNumberOfSale(p.getNumberOfSale());
                productResult.setBrandId(p.getBrandId());
                productResult.setCategoryId(p.getCategoryId());
                productResult.setCategoriesByCategoryId(p.getCategoriesByCategoryId());
                productResult.setBrandByBrandId(p.getBrandByBrandId());
                productResult.setCreatedAt(p.getCreatedAt());
                productResult.setUpdatedAt(p.getUpdatedAt());
                int price = (int) (p.getPrice() * (1 - p.getDiscount()/(double)100));
                productResult.setPriceSale(price);
                listResult.add(productResult);
            }
        }
        return listResult;
    }


}