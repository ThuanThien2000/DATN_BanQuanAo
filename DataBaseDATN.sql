-- Đây là cơ sở dữ liệu của cửa hàng Website bán quần áo cho thương hiệu thời trang.
CREATE DATABASE dataQuanAo;
GO
USE dataQuanAo;
GO

--- Role Table.
CREATE TABLE [Role] (
    id BIGINT IDENTITY(1, 1) PRIMARY KEY, -- ID Định danh
    role_name NVARCHAR(100) NOT NULL UNIQUE, -- Tên chức vụ (Một tên duy nhất - không trùng tên chức vụ)
    status INT
);
GO

--- Users Table.
CREATE TABLE [Users] (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
	-- thông tin cá nhân
    username VARCHAR(120) NOT NULL UNIQUE, -- Tên đăng nhập duy nhất
    password VARCHAR(120) NOT NULL, -- Mật khẩu
    fullname NVARCHAR(255) NOT NULL, -- Tên đầy đủ
	gender BIT NOT NULL, -- Giới tính chỉ có hai giới tính
    phonenumber VARCHAR(20) NOT NULL, -- số điện thoại
    address NVARCHAR(MAX) NOT NULL, -- Địa chỉ
    email VARCHAR(120) NOT NULL UNIQUE, -- email nên là duy nhất

    registrationdate DATETIME, -- ngày đăng ký

    roleid BIGINT NOT NULL, -- Một người dùng khi đăng ký/đăng nhập phải có vai trò: ID của Role

    email_verification_token VARCHAR(MAX) NULL, -- mã xác thực gửi email token để xác minh tài khoản người dùng kích hoạt tài khoản
    token_creation_time DATETIME NULL, -- ngày tạo mã token

    status INT,-- Trạng thái (0: chưa kích hoạt; 1: đã kích hoạt)
    FOREIGN KEY (roleid) REFERENCES [Role](id)
);
GO

-- ..Category Table: bảng danh mục phân loại sản phẩm.
CREATE TABLE [Category] (
    id BIGINT IDENTITY(1, 1) PRIMARY KEY,
    category_name NVARCHAR(255) UNIQUE NOT NULL, -- Tên danh mục, nên có độ dài cụ thể
    status INT
);
GO

--- Product Table: Bảng sản phẩm.
CREATE TABLE Product (
    id BIGINT IDENTITY(1, 1) PRIMARY KEY,

    product_code VARCHAR(50) UNIQUE NOT NULL, -- Mã sản phẩm
    product_name NVARCHAR(MAX) NOT NULL, -- Tên sản phẩm không nên null

    brand NVARCHAR(50) NOT NULL, -- Thương hiệu 
    category_id BIGINT NOT NULL, -- ID loại (sản phẩm)
    user_type NVARCHAR(50) NOT NULL, -- Loại người dùng

	material NVARCHAR(50) NOT NULL, -- Chất liệu
	description NVARCHAR(MAX) NULL, -- Mô tả sản phẩm
    price DECIMAL(18, 2) NOT NULL, -- đơn giá

	is_featured BIT NULL, -- Sản phẩm được giới thiệu để quảng bá?

    status INT,
	FOREIGN KEY (category_id) REFERENCES Category(id) -- Thêm khóa ngoại tới bảng Category
);
GO

-- ..Images Table: bảng chưa dữ liệu ảnh
CREATE TABLE Images (
    id BIGINT IDENTITY(1, 1) PRIMARY KEY,
    image_url VARCHAR(MAX) NOT NULL, -- Đường dẫn URL đến hình ảnh
    product_id BIGINT NOT NULL, -- Khóa ngoại tham chiếu đến Product
    status INT

    FOREIGN KEY (product_id) REFERENCES Product(id)
);
GO

--- .Product_detail Table: Bảng chi tiết sản phẩm hay còn gọi là biến thể sản phẩm
CREATE TABLE Product_detail(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,

    product_detail_code VARCHAR(50) UNIQUE NOT NULL, -- Mã chi tiết sản phẩm
    product_id BIGINT NOT NULL, -- ID của Product

    style NVARCHAR(50) NOT NULL, -- phong cách ám chỉ kiểu áo (ví dụ: áo màu xanh, đỏ, tím, vàng, cam, áo nhiều màu, áo có hoa văn)
    size NVARCHAR(50) NOT NULL, -- Kích cỡ (Size: S, M, L, XL, XXL, XXXL, và đôi khi có thêm XS)
    inventory_quantity INT NOT NULL, -- tổng số lượng tồn kho của một chi tiết sản phẩm mà thương hiệu đó có.

    img_url VARCHAR(MAX) NULL, -- link ảnh (đường dẫn ảnh)
    status INT,

    FOREIGN KEY (product_id) REFERENCES Product(id)
);
GO



--- Voucher Table.
CREATE TABLE Voucher (
    id BIGINT IDENTITY(1, 1) PRIMARY KEY,
    voucher_code VARCHAR(20) UNIQUE NOT NULL, -- Mã giảm giá, mã không được trùng với mã đã có trước đó.
    startdate DATE NOT NULL, -- Ngày bắt đầu 
    enddate DATE NOT NULL, -- Ngày kết thúc 
    discount_percentage INT NULL, -- phần trăm giảm giá
    max_amount DECIMAL(18, 2) NULL, -- Số tiền có thể giảm lớn nhất
    min_amount DECIMAL(18, 2) NULL, -- Số tiền có thể giảm ít nhất
    usage_count INT NULL, -- số lượng sử dụng
    status INT
); -- Và có thể tái sử dụng mã tùy vào cập nhật lại ngày tháng
GO

--- Payment_method Table: bảng phương thức thanh toán
CREATE TABLE Payment_method (
    id BIGINT IDENTITY(1, 1) PRIMARY KEY,
    name_payment_method NVARCHAR(200) NOT NULL UNIQUE, -- Tên phương thức thanh toán không trùng
    status INT NOT NULL DEFAULT 1 -- Trạng thái tự động hoạt động
);
GO

--- Invoice Table.
CREATE TABLE Invoice (

    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    invoice_code VARCHAR(50) UNIQUE NOT NULL, -- Mã hóa đơn

    user_id BIGINT NULL, -- NULL cho phép khách vãng lai, nếu không thì NOT NULL
    name_of_customer NVARCHAR(MAX) NOT NULL, -- Thông tin khách hàng cho hóa đơn không nên null
    phonenumber VARCHAR(20) NOT NULL,
    email VARCHAR(120) NULL, -- Có thể null nếu khách không cung cấp email
    delivery_address NVARCHAR(MAX) NOT NULL,

    payment_method_id BIGINT NOT NULL, -- Một hóa đơn phải có hai kiểu phương thức thanh toán
    creation_date DATETIME NOT NULL DEFAULT GETDATE(), 

    shipping_fee DECIMAL(18, 2) NOT NULL DEFAULT 0,  -- Phí vận chuyển
	discount_amount DECIMAL(18, 2) NOT NULL DEFAULT 0, -- số tiền giảm giá
    voucher_id BIGINT NULL, -- Số tiền giảm giá
    total_amount DECIMAL(18, 2) NOT NULL, -- Tổng giá tiền

	assigned_staff_id BIGINT NULL, -- Bổ sung thêm. Nhân viên được chỉ định

    status INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES Users(id),
	FOREIGN KEY (assigned_staff_id) REFERENCES Users(id),
    FOREIGN KEY (voucher_id) REFERENCES Voucher(id),

    CONSTRAINT FK_Invoice_PaymentMethod FOREIGN KEY (payment_method_id) REFERENCES Payment_method(id)
);
GO

--- .Invoicedetail Table: bảng chi tiết hóa đơn lưu trữ thông tin chi tiết của sản phẩm và tổng số lượng tính thành tổng giá tiền
CREATE TABLE Invoicedetail (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,

    invoice_id BIGINT NOT NULL,
    product_detail_id BIGINT NOT NULL,

    quantity INT NOT NULL,
    price DECIMAL(18, 2) NOT NULL,
    totalprice AS (quantity * price) PERSISTED,

    status INT,

    FOREIGN KEY (invoice_id) REFERENCES Invoice(id),
    FOREIGN KEY (product_detail_id) REFERENCES Product_detail(id),
	

    CONSTRAINT UQ_InvoiceDetail_Invoice_ProductDetail UNIQUE (invoice_id, product_detail_id) -- Mỗi sản phẩm chi tiết chỉ xuất hiện một lần trong mỗi hóa đơn
);
GO

--- Review Table..
CREATE TABLE Review (
    ID BIGINT PRIMARY KEY IDENTITY(1,1),

    user_id BIGINT NOT NULL, -- Bắt buộc phải có người đánh giá
    product_id BIGINT NOT NULL, 
    Rating INT CHECK (Rating BETWEEN 1 AND 5) NOT NULL, -- Nếu đã tiến hành đánh giá Rating bắt buộc phải có
    Comment NVARCHAR(MAX) NULL, -- Bình luận
    review_date DATETIME DEFAULT GETDATE(), -- Ngày bình luận
    Status INT DEFAULT 1,

    CONSTRAINT FK_Review_User FOREIGN KEY (user_id) REFERENCES Users(id),
    CONSTRAINT FK_Review_Product FOREIGN KEY (product_id) REFERENCES Product(id),
    CONSTRAINT UQ_User_Product UNIQUE (user_id, product_id)
);
GO
--- Log Table: khi chú các thao tác của người dùng..
CREATE TABLE Log (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id BIGINT NULL, -- Ai đã thực hiện hành động (có thể null nếu hành động không liên quan đến user cụ thể)
    action NVARCHAR(MAX) NOT NULL, -- Hoạt động không nên null
    description NVARCHAR(MAX) NULL, -- Mô tả có thể null
    created_at DATETIME NOT NULL DEFAULT GETDATE(), -- mặc định ngày tạo
    FOREIGN KEY (user_id) REFERENCES Users(id)
);
GO

INSERT INTO [Role](role_name, status) VALUES (N'Admin', 1);
INSERT INTO [Role](role_name, status) VALUES (N'Staff', 1);
INSERT INTO [Role](role_name, status) VALUES (N'Customer', 1);
INSERT INTO [Role](role_name, status) VALUES (N'Shipper', 1);
INSERT INTO [Role](role_name, status) VALUES (N'Manager', 1);


INSERT INTO [Users](username, password, fullname, gender, phonenumber, address, email, registrationdate, roleid, status)
VALUES 
('user0', 'pass0', N'Họ Tên 0', 0, '0900000000', N'Địa chỉ 0', 'user0@mail.com', '2022-11-06', 1, 1),
('user1', 'pass1', N'Họ Tên 1', 1, '0900000001', N'Địa chỉ 1', 'user1@mail.com', '2021-06-23', 2, 1),
('user2', 'pass2', N'Họ Tên 2', 0, '0900000002', N'Địa chỉ 2', 'user2@mail.com', '2024-01-30', 3, 1),
('user3', 'pass3', N'Họ Tên 3', 1, '0900000003', N'Địa chỉ 3', 'user3@mail.com', '2023-10-11', 4, 1),
('user4', 'pass4', N'Họ Tên 4', 0, '0900000004', N'Địa chỉ 4', 'user4@mail.com', '2020-12-15', 5, 1);

INSERT INTO [Category](category_name, status) VALUES (N'Áo thun', 1);
INSERT INTO [Category](category_name, status) VALUES (N'Quần jean', 1);
INSERT INTO [Category](category_name, status) VALUES (N'Váy', 1);
INSERT INTO [Category](category_name, status) VALUES (N'Áo sơ mi', 1);
INSERT INTO [Category](category_name, status) VALUES (N'Phụ kiện', 1);


INSERT INTO Product(product_code, product_name, brand, category_id, user_type, material, price, status)
VALUES 
('P000', N'Sản phẩm 0', N'Thương hiệu 0', 1, N'Nam', N'Cotton', 150000, 1),
('P001', N'Sản phẩm 1', N'Thương hiệu 1', 2, N'Nam', N'Cotton', 180000, 1),
('P002', N'Sản phẩm 2', N'Thương hiệu 2', 3, N'Nam', N'Cotton', 300000, 1),
('P003', N'Sản phẩm 3', N'Thương hiệu 3', 4, N'Nam', N'Cotton', 400000, 1),
('P004', N'Sản phẩm 4', N'Thương hiệu 4', 5, N'Nam', N'Cotton', 200000, 1);


INSERT INTO Images(image_url, product_id, status)
VALUES 
('http://example.com/image0.jpg', 1, 1),
('http://example.com/image1.jpg', 2, 1),
('http://example.com/image2.jpg', 3, 1),
('http://example.com/image3.jpg', 4, 1),
('http://example.com/image4.jpg', 5, 1);


INSERT INTO Product_detail(product_detail_code, product_id, style, size, inventory_quantity, status)
VALUES 
('PD000', 1, N'Phong cách 0', 'M', 50, 1),
('PD001', 2, N'Phong cách 1', 'M', 60, 1),
('PD002', 3, N'Phong cách 2', 'M', 70, 1),
('PD003', 4, N'Phong cách 3', 'M', 80, 1),
('PD004', 5, N'Phong cách 4', 'M', 90, 1);


INSERT INTO Voucher(voucher_code, startdate, enddate, discount_percentage, max_amount, min_amount, usage_count, status)
VALUES 
('VCODE0', '2022-05-21', '2026-02-27', 15, 50000, 100000, 5, 1),
('VCODE1', '2021-07-17', '2026-04-15', 20, 50000, 100000, 3, 1),
('VCODE2', '2020-09-09', '2026-01-01', 10, 50000, 100000, 4, 1),
('VCODE3', '2023-01-12', '2026-05-10', 30, 50000, 100000, 2, 1),
('VCODE4', '2024-06-01', '2026-03-20', 25, 50000, 100000, 6, 1);


INSERT INTO Payment_method(name_payment_method) VALUES (N'Tiền mặt');
INSERT INTO Payment_method(name_payment_method) VALUES (N'Chuyển khoản');
INSERT INTO Payment_method(name_payment_method) VALUES (N'Ví điện tử');
INSERT INTO Payment_method(name_payment_method) VALUES (N'Thẻ tín dụng');
INSERT INTO Payment_method(name_payment_method) VALUES (N'COD');


INSERT INTO Invoice(invoice_code, user_id, name_of_customer, phonenumber, delivery_address, payment_method_id, shipping_fee, discount_amount, total_amount, status)
VALUES 
('INV000', 1, N'Khách hàng 0', '0912345670', N'Địa chỉ giao hàng 0', 1, 10000, 20000, 500000, 1),
('INV001', 2, N'Khách hàng 1', '0912345671', N'Địa chỉ giao hàng 1', 2, 10000, 20000, 600000, 1),
('INV002', 3, N'Khách hàng 2', '0912345672', N'Địa chỉ giao hàng 2', 3, 10000, 20000, 700000, 1),
('INV003', 4, N'Khách hàng 3', '0912345673', N'Địa chỉ giao hàng 3', 4, 10000, 20000, 800000, 1),
('INV004', 5, N'Khách hàng 4', '0912345674', N'Địa chỉ giao hàng 4', 5, 10000, 20000, 900000, 1);


INSERT INTO Invoicedetail(invoice_id, product_detail_id, quantity, price, status)
VALUES 
(1, 1, 2, 150000, 1),
(2, 2, 3, 180000, 1),
(3, 3, 1, 300000, 1),
(4, 4, 4, 400000, 1),
(5, 5, 2, 200000, 1);

SET IDENTITY_INSERT Invoicedetail OFF;



INSERT INTO Review(user_id, product_id, Rating, Comment)
VALUES 
(1, 1, 5, N'Nhận xét sản phẩm 0'),
(2, 2, 4, N'Nhận xét sản phẩm 1'),
(3, 3, 3, N'Nhận xét sản phẩm 2'),
(4, 4, 5, N'Nhận xét sản phẩm 3'),
(5, 5, 4, N'Nhận xét sản phẩm 4');


INSERT INTO Log(user_id, action, description)
VALUES 
(1, N'Thao tác 0', N'Mô tả thao tác 0'),
(2, N'Thao tác 1', N'Mô tả thao tác 1'),
(3, N'Thao tác 2', N'Mô tả thao tác 2'),
(4, N'Thao tác 3', N'Mô tả thao tác 3'),
(5, N'Thao tác 4', N'Mô tả thao tác 4');
