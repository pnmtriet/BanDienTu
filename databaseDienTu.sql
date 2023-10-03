use master
go
if DB_ID('ElectronicSale') is not null	
	drop database ElectronicSale
go
create database ElectronicSale
go
use ElectronicSale
go
CREATE TABLE categories
(
    id INT IDENTITY PRIMARY KEY,
    category_name NVARCHAR(50),
    is_deleted BIT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE brand
(
    id INT IDENTITY PRIMARY KEY,
    brand_name NVARCHAR(70),
    is_deleted BIT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE product
(
    id INT IDENTITY PRIMARY KEY,
    product_name NVARCHAR(MAX),
    price INT,
    discount INT,
    note NVARCHAR(MAX),
    images TEXT,
    category_id INT,
    number_of_sale INT, -- Số lần bán
    brand_id INT,
    is_deleted BIT DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (brand_id) REFERENCES brand(id)
);

-- Bảng hình ảnh của sản phẩm
CREATE TABLE product_image
(
    id INT IDENTITY PRIMARY KEY,
    product_image_name VARCHAR(50),
    product_id INT,
    is_deleted BIT DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE cart
(
    id INT IDENTITY PRIMARY KEY,
    is_deleted BIT DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cart_detail
(
    id INT IDENTITY PRIMARY KEY,
    cart_id INT,
    product_id INT,
    amount INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES cart(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE roles
(
    id INT IDENTITY PRIMARY KEY,
    role_name VARCHAR(50),
    is_deleted BIT DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE account
(
    id INT IDENTITY PRIMARY KEY,
    username VARCHAR(20),
    password TEXT,
    email VARCHAR(255) unique,
    full_name VARCHAR(50),
    phone VARCHAR(20) unique,
    gender VARCHAR(10),
    date_of_birth DATE,  
    cart_id INT,
    is_deleted BIT DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES cart(id)
);

-- Bảng chứa thông tin giao hàng
CREATE TABLE ship_detail
(
    id INT IDENTITY PRIMARY KEY,
    phone VARCHAR(20),
    province_id INT,
    province NVARCHAR(MAX),
    district_id INT,
    district NVARCHAR(MAX),
    ward_id INT,
    ward NVARCHAR(MAX),
    address_more NVARCHAR(MAX),
    address NVARCHAR(MAX),
    full_name VARCHAR(50),
    is_default BIT DEFAULT 0 ,
    account_id INT,
    is_deleted BIT DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE roles_detail
(
    id INT IDENTITY PRIMARY KEY,
    account_id INT,
    role_id INT,
    is_deleted BIT DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE orders
(
    id INT IDENTITY PRIMARY KEY,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    note NVARCHAR(MAX),
    order_status NVARCHAR(MAX),
    total_money DECIMAL(20,0),
    delivery_date DATETIME,
    payment_method NVARCHAR(MAX),
    payment_status NVARCHAR(MAX),
    ship_method NVARCHAR(MAX),
    ship_method_id INT,
    delivery_charges INT,
    account_id INT,    
    ship_detail_id INT,
    is_deleted BIT DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ship_detail_id) REFERENCES ship_detail(id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE order_detail
(
    id INT IDENTITY PRIMARY KEY,
    order_id INT,
    product_id INT,
    amount INT,
    price DECIMAL(20,0),
    is_deleted BIT DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

create table reviews
(
	id INT IDENTITY PRIMARY KEY,
	comment NVARCHAR(MAX),
	rate INT,
	product_id INT,
	account_id INT,
	order_id INT,
	is_deleted BIT DEFAULT 0,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- Flash sale
create table sale
(
	id INT IDENTITY PRIMARY KEY,
	sale_date_start date,
	sale_time_start time,
	sale_date_end date,
	sale_time_end time,
	sale_name NVARCHAR(MAX),
	is_deleted BIT DEFAULT 0,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

create table sale_detail
(
	id INT IDENTITY PRIMARY KEY,
	sale_id INT,
	product_id INT,
	sale_name NVARCHAR(MAX),
	discount_old int,
	discount_sale int,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sale_id) REFERENCES sale(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

insert into cart (is_deleted)
values (0),
(0),
(0),
(0),
(0),
(0),
(0);

INSERT INTO  account (username,password,email,date_of_birth,full_name,phone,gender,cart_id,is_deleted) values
('datlm','$2a$10$xOJFoO1uM6/r5KdydhzlOOCIuOSLntAsHtBRYfaBXrogqak4FXaOq','leminhdat20021811@gmail.com','2002-11-02','Lê Minh Đạt','0346135361',N'Nam',1,0),
('huypq','$2a$10$xOJFoO1uM6/r5KdydhzlOOCIuOSLntAsHtBRYfaBXrogqak4FXaOq','huypq@gmail.com','2002-07-05','Phan Quốc Huy','0346135362',N'Nam',2,0),
('cucttk','$2a$10$xOJFoO1uM6/r5KdydhzlOOCIuOSLntAsHtBRYfaBXrogqak4FXaOq','cucttk@gmail.com','2003-08-22','Trần Thị Kim Cúc','0346135363',N'Nữ',3,0),
('minhnv','$2a$10$xOJFoO1uM6/r5KdydhzlOOCIuOSLntAsHtBRYfaBXrogqak4FXaOq','minhnv@gmail.com','2002-07-05','Nguyễn Văn Minh','0346135364',N'Nam',4,0),
('duynpn','$2a$10$xOJFoO1uM6/r5KdydhzlOOCIuOSLntAsHtBRYfaBXrogqak4FXaOq','duynpn@gmail.com','2002-07-05','Nguyễn Phạm Nhật Duy','0346135365',N'Nam',5,0),
('tamlt','$2a$10$xOJFoO1uM6/r5KdydhzlOOCIuOSLntAsHtBRYfaBXrogqak4FXaOq','tamlt@gmail.com','2002-11-02','Lương Thế Tâm','0346135366',N'Nam',6,0),
('admin','$2a$10$0ijqrFYFWglp4DLQ5dJu9.2SWDhchnoW.09gkiW7iUBtX7Vu4xKfe','admin@gmail.com','1999-11-02','Admin','0345837123',N'Nam',7,0);
-- admin pass là 'admin', các tài khoản khác pass mặc định là '123'

INSERT INTO roles (role_name) values('ADMIN'),('STAFF'),('CUSTOMER');

INSERT INTO roles_detail (account_id,role_id,is_deleted) values (1,3,0),(2,3,0),(3,3,0),(4,3,0),(5,3,0),(6,3,0),(7,1,0),(7,2,0),(7,3,0);

insert into brand(brand_name,is_deleted)
values 
(N'Apple',0),
(N'Samsung',0),
(N'Xiaomi',0),
(N'Vivo',0),
(N'Oppo',0),
(N'Realme',0),
(N'Sony',0),
(N'Nokia',0),
(N'Asus',0);

insert into categories(category_name,is_deleted)
values 
(N'Quạt',0),
(N'Nồi Cơm Điện',0),
(N'Nồi Áp Suất',0),
(N'Máy Chiên Không Dầu',0),
(N'Nồi Lẩu Điện',0),
(N'Máy Xay Sinh Tố',0),
(N'Máy Xay Thịt',0),
(N'Bàn Ủi',0),
(N'Tủ lạnh',0),
(N'Máy giặt',0),
(N'Máy lạnh',0);

insert into product(product_name,price,discount,images,note,number_of_sale,category_id,brand_id,is_deleted)
values (N'iPhone 14 Pro Max',20500000,10,'sp1.png',
N'iPhone 14 Pro Max một siêu phẩm trong giới smartphone được nhà Táo tung ra thị trường vào tháng 09/2022. Máy trang bị con chip Apple A16 Bionic vô cùng mạnh mẽ, đi kèm theo đó là thiết kế hình màn hình mới, hứa hẹn mang lại những trải nghiệm đầy mới mẻ cho người dùng iPhone.',
100,1,1,0),
(N'Samsung Galaxy S23',20990000,20,'samsung_1.png',
N'iPhone 14 Pro Max một siêu phẩm trong giới smartphone được nhà Táo tung ra thị trường vào tháng 09/2022. Máy trang bị con chip Apple A16 Bionic vô cùng mạnh mẽ, đi kèm theo đó là thiết kế hình màn hình mới, hứa hẹn mang lại những trải nghiệm đầy mới mẻ cho người dùng iPhone.',
20,2,2,0),
(N'Xiaomi Redmi Note 12',5300000,0,'xiaomi_rm_12_1.jpg',
N'Xiaomi Redmi Note 12 8GB 128GB tỏa sáng với diện mạo viền vuông cực thời thượng cùng hiệu suất mạnh mẽ nhờ sở hữu con chip Snapdragon 685 ấn tượng. Chất lượng hiển thị hình ảnh của Redmi Note 12 Vàng cũng khá sắc nét thông qua tấm nền AMOLED 120Hz hiện đại. Chưa hết, máy còn sở hữu cụm 3 camera với độ rõ nét lên tới 50MP cùng viên pin 5000mAh và s ạc nhanh 33W giúp đáp ứng được mọi nhu cầu sử dụng của người dùng.',
20,2,3,0),
(N'Vivo V27e',9000000,10,'vivo_v27_1.png',
N'Vivo V27e sở hữu con chip MediaTek Helio G99, bộ nhớ RAM chuẩn 8GB cùng 128GB ROM, người dùng cũng có thể lựa chọn phiên bản cấu hình nâng cấp với 12GB RAM và 256GB bộ nhớ. Điện thoại sở hữu kích thước màn hình 6.62 inch, tấm nền AMOLED. Bên cạnh đó Vivo V27e còn được trangbị 3 camera, ống kính chính 64MP, dung lượng pin 4600mAh, thiết kế màn hình đục lỗ chứa camera selfie 32MP.',
20,2,4,0),
(N'OPPO Reno8 T',75000000,15,'oppo_8_1.jpg',
N'OPPO Reno8 T sở hữu hiệu năng mạnh mẽ, trang bị bộ vi xử lý thế hệ mới Snapdragon 695, tấm nền AMOLED 6.7 inch, màn hình 1 tỉ màu mang lại chất lượng hình ảnh siêu sắc nét và sống động tới từng chi tiết. Dung lượng Pin điện thoại Reno8 T 5G lên tới 4800mAh cùng thông số bộ nhớ đạt 8GB RAM và 128GB ROM giúp người dùng có thể thoải mái trải nghiệm mà không cần lo về vấn đề năng lượng hay bộ nhớ lưu trữ trong quá trình sử dụng.',
20,2,5,0),
(N'Realme 10',6500000,0,'realme_10_1.png',
N'Realme 10 là sản phẩm mới tiếp theo mà realme sở hữu một thiết kế sang trọng cùng cấu hình mạnh mẽ. Cụ thể, điện thoại realme 10 được trang bị màn hình 6.4 inch với tấm nền Super AMOLED, tần số quét 90Hz mang lại hiển thị sống động, chuyển động mượt mà. Cấu hình điện thoại cũng ấn tượng với con chip Helio G99, bộ nhớ RAM lớn cùng viên pin 5000mAh cho thời gian sử dụng lâu dài.',
20,2,6,0),
(N'Sony Xperia XZ2',2000000,5,'sony_xz2_1.jpg',
N'Sony Xperia XZ2 là mẫu điện thoại thu hút được người dùng từ vẻ mới lạ ở thiết kế. Được trang bị con chip Snapdragon 845 vô cùng mạnh mẽ cùng với đó là RAM lớn mang tới cho người dùng trải nghiệm mượt mà. Chắc chắn với sự đầu tư mạnh mẽ đây là gọi điện thoại nổi bật nhất ở trong phân khúc.',
20,2,7,0),
(N'Nokia 105 4G Pro',680000,10,'nokia_c_1.png',
N'Nokia 105 4G Pro sở hữu màn hình IPS LCD có kích thước 1.77 inch đem lại cho người dùng khả năng hiển thị chữ rõ nét hơn. Cùng với đó, dung lượng pin 1450mAh đem lại khả năng sử dụng lâu dài trong nhiều ngày. Với sự trang bị chipset Unisoc T107 và bộ nhớ trong 128MB, sản phẩm đem lại trải nghiệm ổn định cho người dùng.'
,20,3,8,0),
(N'ASUS ROG Phone 6',15000000,10,'asus_1.png',
N'Mặc dù thuộc phân khúc điện thoại giá rẻ nhưng POCO M5s 6GB lại được đánh giá cao nhờ sở hữu màn hình AMOLED thiết kế đục lỗ ấn tượng, camera 64 MP chụp ảnh sắc nét cùng hàng loạt tiện ích như loa kép và khả năng chống nước.',
20,2,9,0),

(N'iPhone 13',17000000,10,'ip13_1.jpg',
N'Về kích thước, iPhone 13 sẽ có 4 phiên bản khác nhau và kích thước không đổi so với series iPhone 12 hiện tại. Nếu iPhone 12 có sự thay đổi trong thiết kế từ góc cạnh bo tròn (Thiết kế được duy trì từ thời iPhone 6 đến iPhone 11 Pro Max) sang thiết kế vuông vắn (đã từng có mặt trên iPhone 4 đến iPhone 5S, SE).',
100,1,1,0),
(N'Samsung Galaxy A54',10000000,20,'samsung_a54_1.jpg',
N'Samsung Galaxy A54 5G có những điểm đột phá mới như: màn hình Super AMOLED 6.4 inch tràn viền vô cực Infinity-O, độ sáng đến 1000nits, tần số quét lên đến 120Hz. Samsung A54 cũng sở hữu cụm 3 camera phân giải cao 50.0 MP + 12.0 MP + 5.0 MP với tính năng ổn định kỹ thuật số và Auto-framing kết hợp chống rung OIS.',
20,2,2,0),
(N'Xiaomi 13',12000000,10,'xiaomi_13_1.png',
N'Xiaomi 13 là sản phẩm mới được trang bị màn hình OLED kích thước 6.36 inch cùng tần số quét lên đến 120Hz. Bên trong Xiaomi 13 là con chip Snapdragon 8 Gen 2 mới nhất cùng bộ nhớ RAM 8GB vượt trội. Điện thoại cũng đáp ứng tốt khả năng nhiếp ảnh với camera Leica 50MP.',
20,2,3,0),
(N'Vivo V23e',5500000,10,'vivo_v23e_1.png',
N'Tiếp nối sự thành công của các phiên bản trước đó, Vivo tiếp tục cho ra mắt mẫu smartphone tầm trung mang tên Vivo V23e. Với thiết kế mỏng nhẹ, hiệu năng ấn tượng cùng camera chất lượng và dung lượng pin khủng đây là chiếc điện thoại thông minh mang đến cho người dùng những trải nghiệm vô cùng tuyệt vời. Chắc chắn đây là sản phẩm mà rất nhiều người đang tìm kiếm.'
,20,2,4,0),
(N'OPPO Reno10',10000000,10,'oppo_10_1.png',
N'Chuyên gia chân dung thế hệ thứ 10 - Camera chân dung 32MP siêu nét, chụp xa từ 2X-5X không lo biến dạng khung hình. Thiết kế nổi bật, dẫn đầu xu hướng - Cạnh viền cong 3D, các phiên bản màu sắc phù hợp đa cá tính, thu hút mọi ánh nhìn. Đa nhiệm mạnh mẽ hơn ai hết - RAM mở rộng, ROM 128GB mang đến trải nghiệm đa tác vụ thoải mái.',
20,2,5,0),
(N'Realme C51',4000000,10,'realme_c51_1.png',
N'realme C51 sở hữu chipset Tiger Unisoc T612 mạnh mẽ, RAM 4GB + ROM 128GB và cụm camera 50MP ấn tượng. Thêm vào đó, màn hình kích thước 6.74 inch có độ phân giải FHD cho hình ảnh hiển thị sắc nét và chi tiết.',
20,2,6,0),
(N'Sony Xperia XZ1',2000000,10,'sony_xz1_1.jpg',
N'Sony Xperia XZ1 là chiếc điện thoại flagship đáng tự hào đến từ thương hiệu Sony. Smartphone sở hữu bộ cấu hình toàn diện, bao gồm con chip 8 nhân Snapdragon 835, màn hình Full HD với khả năng kháng nước và bụi bẩn vượt trội. Bên cạnh đó, chiếc điện thoại cũng đáp ứng đầy đủ các nhu cầu chụp ảnh, quay video với cảm biến chính 19MP.',
20,2,7,0),
(N'Nokia C32',2800000,0,'nokia_c32_1.png',
N'Nokia C32 là mẫu điện thoại Nokia mới ra tháng 2 năm 2023 được trang bị màn hình 6.52 inch cùng chipset Unisoc SC9863A mạnh mẽ. Nổi bật là cụm camera siêu nét với camera chính 50MP và camera phụ 2MP. Sản phẩm được chú ý với dung lượng pin 5000mAh, khá ấn tượng so với phân khúc điện thoại tầm trung.',
20,2,8,0),
(N'ASUS ROG Phone 7',25000000,20,'asus_7_1.jpg',
N'Asus ROG phone 7 Ultimate 16GB 512GB sở hữu con chip Snapdragon 8 Gen 2 với sức mạnh siêu khủng đến từ nhà Qualcomm. Màn hình được làm từ màn amoled có kích thước khủng tận 6.78 inch cho chất lượng hình ảnh Full HD Plus. Camera siêu xịn với độ phân giải lên đến 50MP đi kèm viên pin dung lượng vô đối 6000mAh và chế độ sạc HyperCharge 65W.',
20,2,9,0),

(N'iPhone 12',14000000,10,'ip12_1.jpg',
N'Dù Apple vừa giới thiệu dòng điện thoại iPhone 13 series tuy nhiên iPhone 12 vẫn đang là một trong những sự lựa chọn hàng đầu ở thời điểm hiện tại. Chiếc flagship năm 2020 của "Táo khuyết" đang nhận được rất nhiều sự quan tâm của người dùng bởi mức giá dễ tiếp cận hơn so với thời điểm ra mắt, đồng thời được trang bị cấu hình, màn hình, camera ấn tượng trong tầm giá.',
100,1,1,0),
(N'Samsung Galaxy S21',11000000,20,'samsung_s21_1.png',
N'Samsung S21 FE 8GB 128GB sở hữu chipset Exynos 2100 mạnh mẽ có thể chơi mượt mà, RAM 8GB và ROM 128GB cho khả năng đa nhiệm và lưu trữ ổn định. Thêm vào đó cụm camera chất lượng, cho hình ảnh sắc nét: 12MP+12MP+8MP và camera selfie 32MP. Không chỉ vậy, các phiên bản màu sắc thanh lịch, thời thượng giúp sản phẩm nổi bật hơn giữa hàng loạt các thương hiệu khác.',
20,2,2,0),
(N'Xiaomi Redmi Note 12S',8000000,0,'xiaomi_12s_1.jpg',
N'Xiaomi Redmi Note 12 Pro 4G là mẫu điện thoại tầm trung mới của hãng Xiaomi vừa được ra mắt vào tháng 4 năm 2023. Mẫu điện thoại Xiaomi mới này được trang bị màn hình 6.67 inch cùng tần số quét tới 120Hz giúp các chuyển động mượt mà, đặc biệt trong các trận chiến game. Xiaomi Redmi Note 12 Pro 4G cũng sẽ hoạt động ổn định với con chip Snapdragon® 732G cùng bộ nhớ RAM 6GB. Điện thoại cũng đáp ứng tốt nhu cầu nhiếp ảnh của người dùng với cụm bốn camera sau, trong đó ống kính chính sở hữu độ phân giải tới 50MP.',
20,2,3,0),
(N'Vivo V25',7000000,15,'vivo_v25_1.png',
N'Series điện thoại trung cấp mới nhất của Vivo dự kiến sẽ xuất hiện trong thời gian gần đây, và một trong những sản phẩm thuộc dòng điện thoại Vivo Y chính là điện thoại Vivo V25. Tích hợp hiệu năng mạnh cùng màn hình mượt nét, Vivo V25 là chiếc smartphone cuốn hút và trẻ trung cho thế hệ mới.',
20,2,4,0),
(N'OPPO A78',5000000,0,'oppo_a78_1.jpg',
N'Là mẫu điện thoại tầm trung đầu tiên được OPPO ra mắt trong năm 2023, OPPO A78 sở hữu một thiết kế bắt mắt cùng những cải tiến trong hiệu năng với vi xử lý Qualcomm Snapdragon 680 có khả năng hỗ trợ kết nối 4G, 8GB RAM ( mở rộng thêm 8GB), 256GB bộ nhớ và viên pin 5000mAh hỗ trợ công nghệ sạc siêu tốc 67W.',
20,2,5,0),
(N'Realme 11 Pro',12000000,10,'realme_11_1.png',
N'Realme 11 Pro là sản phẩm điện thoại được thiết kế màn hình OLED cùng tần số quét cao 120Hz. Ống kính cảm biến chính lên đến 100MP thu lại hình ảnh ghi lại rõ nét từng chi tiết nhỏ. Dung lượng bộ nhớ khủng 128GB giúp tăng lưu trữ hình ảnh, tài liệu, ứng dụng dễ dàng.',
20,2,6,0),
(N'Sony Xperia XZ3',4000000,20,'sony_xz3_1.jpg',
N'Đã khá lâu Sony vắng mặt trên thị trường Smartphone thì nay với sự trở lại của điện thoại Sony Xperia XZ3 Chính hãng cho thấy Sony vẫn đang tích cực cải thiện dòng sản phẩm phân khúc cao cấp của mình. Chiếc XZ3 được xem là bản nâng cấp mạnh mẽ của Xperia XZ2, với nhiều cải thiện và nâng cấp giúp trải nghiệm người dùng tốt hơn.',
20,2,7,0),
(N'Nokia 5710 XpressAudio',1200000,5,'nokia_c2_1.jpg',
N'Nokia không chỉ nổi tiếng với những dòng điện thoại có độ bền cao như Nokia 1080, Nokia 105, Nokia 1200 mà còn rất thành công với một dòng điện thoại thời trang, được thiết dành riêng cho việc nghe nhạc là Nokia XpressMusic. Trong năm 2022, hãng điện thoại Nokia đã quyết định hồi sinh lại dòng sản phẩm này khi cho ra mắt chiếc Nokia 5701 Xpress Audio.',
20,2,8,0),
(N'ASUS ROG Phone 6 Batman',15000000,10,'asus_2_1.png',
N'Mặc dù thuộc phân khúc điện thoại giá rẻ nhưng POCO M5s 6GB lại được đánh giá cao nhờ sở hữu màn hình AMOLED thiết kế đục lỗ ấn tượng, camera 64 MP chụp ảnh sắc nét cùng hàng loạt tiện ích như loa kép và khả năng chống nước.',
20,2,9,0),

(N'iPhone 11',12000000,10,'ip11_1.jpg',
N'iPhone 11 sở hữu hiệu năng khá mạnh mẽ, ổn định trong thời gian dài nhờ được trang bị chipset A13 Bionic. Màn hình LCD 6.1 inch sắc nét cùng chất lượng hiển thị Full HD của máy cho trải nghiệm hình ảnh mượt mà và có độ tương phản cao. Hệ thống camera hiện đại được tích hợp những tính năng công nghệ mới kết hợp với viên Pin dung lượng 3110mAh, giúp nâng cao trải nghiệm của người dùng.',
100,1,1,0),
(N'Samsung Galaxy A34',7500000,10,'samsung_a34_1.jpg',
N'Samsung Galaxy A34 là sản phẩm được đánh giá "đáng đồng tiền bát gạo" trong phân khúc tầm trung. Samsung A34 sở hữu màn hình Super AMOLED 6.6 inch với tần số quét lên đến 120Hz, độ sáng lên đến 1.000 nits. Thêm vào đó là dung lượng siêu bền bỉ 5.000mAh có thể sử dụng lên đến 2 ngày kết hợp với camera sắc nét tích hợp công nghệ chống rung OIS. Đặc biệt, thiết kế trẻ trung năng động, thời thượng với viền kim loại tinh giản và các góc được bo tròn mềm mại.',
20,2,2,0),
(N'Xiaomi Redmi Note 11 Pro',7000000,10,'xiaomi_rm_11_1.jpg',
N'Sở hữu camera có độ phân giải khủng, tích hợp công nghệ 5G ưu việt, pin trâu và chip Snapdragon 695 5G mới nhất, Redmi Note 11 Pro 5G đảm bảo sẽ là sự lựa chọn hàng đầu trong số smartphone có mức giá tầm trung siêu hấp dẫn.',
20,2,3,0),
(N'Vivo Y35',5000000,10,'vivo_y35_1.jpg',
N'Vivo Y35 là sản phẩm điện thoại Vivo Y được hãng chính thức giới thiệu vào ngày 11/8/2022. Điện thoại sở hữu thiết kế màn hình giọt nước cùng kích thước 6.58 inch mang lại không gian hiển thị lớn. Về hiệu năng, điện thoại sẽ chạy trên con chip Snapdragon 680 6nm cùng với đó là bộ nhớ RAM có thể mở rộng mang lại khả năng đa nhiệm ổn định. Vivo Y35 cũng cho thời gian hoạt động bền bỉ với viên pin 5000 mAh cùng khả năng nhiếp ảnh thông minh nhờ camera chính 50MP.',
20,2,4,0),
(N'Oppo A77s',5000000,10,'oppo_a77_1.jpg',
N'OPPO A77s là chiếc smartphone tầm trung được trang bị hệ điều hành Android 12, bộ nhớ ROM 128GB cùng bộ nhớ RAM 8GB. A77s còn mang đến khả năng xử lý đỉnh cao nhờ Chipset Snapdragon 680 4G, màn hình 6.56 inches với tần số quét 90Hz.',
20,2,5,0),
(N'Realme C33',4000000,20,'samsung_a23_1.jpg',
N'Realme C33 là sản phẩm mới được gia nhập với dòng Realme C series, chiếc điện thoại mới này có màn hình rộng 6.5 inch, màn hình sở hữu tấm nền IPS LCD, độ phân giải HD+. Vi xử lý Unisoc T612 cùng hệ điều hành Android 12 dung lượng pin 5.000 mAh'
,20,2,6,0),
(N'Samsung Galaxy Z Fold 5',24000000,0,'samsung_glx_z_1.jpg',
N'Samsung Z Fold5 được trang bị con chip Snapdragon 8 Gen 2 mạnh mẽ, bộ nhớ RAM 12GB, ống kính chính 50MP, camera selfie bên ngoài 10MP, camera màn hình chính 4MP. Bên cạnh đó, điện thoại Samsung này còn sở hữu tấm nền Dynamic AMOLED 2X, kích thước màn hình 7.6 inch khi mở, độ phân giải 2K cho hình ảnh rõ nét cùng với dung lượng pin 4.400 mAh thoải mái sử dụng.',
20,2,2,0),
(N'Nokia 110 4G',900000,20,'nokia_c4_1.jpg',
N'Nokia vẫn luôn là cái tên đáng chú ý khi nhắc đến phân khúc điện thoại phổ thông. Mới đây hãng đã ra mắt chiếc điện thoại phổ thông Nokia 110 4G với thiết kế mới cùng màu sắc vô cùng bắt mắt, hứa hẹn sẽ tạo nhiều sự đột phá trong thời gian tới. Nokia 110 4G thừa hưởng thiết kế nhỏ gọn từ các sản phẩm tiền nhiệm khác. Tuy nhiên, kiểu dáng lại khác với thiết kế đối xứng hơn cùng độ cong nhẹ ở 2 cạnh trên và dưới làm thiết bị trông tinh tế hơn.',
20,3,8,0),
(N'Nokia 8210 4G',1500000,10,'nokia_c5_1.png',
N'Nokia 8210 4G có thể coi là làn gió mới của thương hiệu Nokia. Dòng điện thoại này vừa mang xu hướng cổ điển, vừa mang một chút hiện đại và là phiên bản nâng cấp của dòng Nokia 8210 truyền thống. Đây là một sản phẩm nằm trong phân khúc giá rẻ và phù hợp với những ai chỉ sử dụng điện thoại với nhu cầu nghe, gọi, nhắn tin cơ bản.',
20,3,8,0),

(N'iPhone X',7500000,10,'ipx_1.jpg',
N'Như các bạn cũng đã biết thì đã 4 năm kể từ chiếc điện thoại iPhone 6 và iPhone 6 Plus thì Apple vẫn chưa có thay đổi nào đáng kể trong thiết kế của mình. Nhưng với iPhone X thì đó lại là 1 câu chuyện hoàn toàn khác, máy chuyển qua sử dụng màn hình tỉ lệ 19.5:9 mới mẻ với phần diện tích hiển thị mặt trước cực lớn.',
100,1,1,0),
(N'Samsung Galaxy A23',5000000,20,'samsung_a23_1.jpg',
N'Samsung A23 5G được trang bị cấu hình vượt trội với con chip Snapdragon 695 5G cùng viên pin 5000 mAh thoải mái trải nghiệm. Màn hình 6.6 inch LCD mang lại khả năng hiển thị rõ nét. Điểm đặc biệt, mẫu điện thoại Samsung này còn được trang bị kết nối 5G đầy tiện lợi.',
20,2,2,0),
(N'Xiaomi POCO M5s',5000000,0,'xiaomi_poco_1.jpg',
N'Mặc dù thuộc phân khúc điện thoại giá rẻ nhưng POCO M5s 6GB lại được đánh giá cao nhờ sở hữu màn hình AMOLED thiết kế đục lỗ ấn tượng, camera 64 MP chụp ảnh sắc nét cùng hàng loạt tiện ích như loa kép và khả năng chống nước.',
20,2,3,0),
(N'Samsung Galaxy Z Flip4',15000000,20,'samsung_glx_f_1.jpg',
N'Tiếp tục là một mẫu smartphone màn hình gập độc đáo, đầy lôi cuốn và mới mẻ từ hãng công nghệ Hàn Quốc, dự kiến sẽ có tên là Samsung Galaxy Z Flip 4 với nhiều nâng cấp. Đây hứa hẹn sẽ là một siêu phẩm bùng nổ trong thời gian tới và thu hút được sự quan tâm của đông đảo người dùng với nhiều cải tiến từ ngoại hình, camera, bộ vi xử lý và viên pin được nâng cấp.',
20,2,2,0),
(N'Samsung Galaxy S23 Plus',19000000,0,'samsung_glx_s23_1.jpg',
N'Samsung Galaxy S23 Plus được trang bị cấu hình vượt trội với con chip Snapdragon 695 5G cùng viên pin 5000 mAh thoải mái trải nghiệm. Màn hình 6.6 inch LCD mang lại khả năng hiển thị rõ nét. Điểm đặc biệt, mẫu điện thoại Samsung này còn được trang bị kết nối 5G đầy tiện lợi.',
20,2,2,0),
(N'Samsung Galaxy S22 Plus',17000000,20,'samsung_glx_s22_1.jpg',
N'Samsung Galaxy S22 Plus được trang bị cấu hình vượt trội với con chip Snapdragon 695 5G cùng viên pin 5000 mAh thoải mái trải nghiệm. Màn hình 6.6 inch LCD mang lại khả năng hiển thị rõ nét. Điểm đặc biệt, mẫu điện thoại Samsung này còn được trang bị kết nối 5G đầy tiện lợi.',
20,2,2,0),

(N'Xiaomi 11 Lite 5G',7000000,0,'xiaomi_11_lite_1.jpg',
N'Mi 11 Lite 5G NE là chiếc smartphone tầm trung mới được ra mắt. Với thiết kế trẻ trung, màu sắc đa dạng cùng với cấu hình mạnh mẽ và màn hình ấn tượng đây là chiếc điện thoại thông minh hỗ trợ mạng 5G mỏng nhẹ nhất của Xiaomi. Đặc biệt với giá thành rẻ, sản phẩm chắc chắn sẽ thu hút được đông đảo khách hàng lựa chọn.',
20,2,3,0),
(N'Xiaomi POCO X5 Pro',8000000,20,'xiaomi_poco_x5_1.jpg',
N'Chiến game mạnh mẽ - Vi xử lý Snapdragon® 778G, lõi 5G 6nm mạnh mẽ cho trải nghiệm trọn vẹn trong mọi tác vụ. Trải nghiệm đa nhiệm cực mượt - RAM mở rộng động 3.0 nhờ đó bạn có thể thoải mái sử dụng nhiều. Nâng cấp hàng đầu trên hệ thống camera - Camera chính lên đến 108MP đi kèm nhiều chế độ chụp giúp bạn tha hồ thể hiện phong cách',
20,2,3,0),
(N'Nokia G21',5000000,10,'nokia_g21_1.jpg',
N'Nokia G21 đánh dấu sự trở lại của ông hoàng thế giới điện thoại Nokia với thiết kế sang trọng, hiệu năng tối ưu hóa với chip Unisoc T606 cùng thời lượng pin 5050mAh siêu trâu. Bên cạnh đó, smartphone chỉ có mức giá bán thuộc phân khúc tầm trung, hướng đến nhiều đối tượng người dùng với những trải nghiệm đầy hứa hẹn.',
20,2,8,0);

insert into product_image (product_image_name,product_id,is_deleted)
VALUES
('ip14_2.png',1,0),
('ip14_3.png',1,0),
('ip14_4.png',1,0),

('ip13_2.jpg',1,0),
('ip13_3.jpg',1,0),
('ip13_4.jpg',1,0),

('ip12_2.jpg',1,0),
('ip12_3.jpg',1,0),
('ip14_4.jpg',1,0),

('ip11_2.jpg',1,0),
('ip11_3.jpg',1,0),
('ip11_4.jpg',1,0),

('ipx_2.jpg',1,0),
('ipx_3.jpg',1,0),
('ipx_4.jpg',1,0);

insert into product_image (product_image_name,product_id,is_deleted)
VALUES
('ip14_2.jpg',1,0),
('ip14_3.jpg',1,0),
('ip14_4.jpg',1,0),
('samsung_a23_1.jpg',2,0),
('samsung_a23_2.jpg',2,0),
('samsung_a23_3.jpg',2,0),
('xiaomi_rm_12_2.jpg',3,0),
('xiaomi_rm_12_3.jpg',3,0),
('xiaomi_rm_12_4.jpg',3,0),
('vivo_v27_2.jpg',4,0),
('oppo_8_2.jpg',5,0),
('oppo_8_3.jpg',5,0),
('oppo_8_4.png',5,0),
('realme_10_2.png',6,0),
('realme_10_3.jpg',6,0),
('realme_10_4.jpg',6,0),
('sony_xz2_2.jpg',7,0),
('sony_xz2_3.jpg',7,0),
('sony_xz2_4.jpg',7,0),
('nokia_c_2.png',8,0),
('nokia_c_3.png',8,0),
('nokia_c_4.png',8,0),
('asus_2.png',9,0);

select * from product