-- Đây là cơ sở dữ liệu của cửa hàng Website bán quần áo cho thương hiệu thời trang.
CREATE DATABASE dataQuanAo;
GO
USE dataQuanAo;
GO

--- Role Table
CREATE TABLE [Role] (
    id BIGINT IDENTITY(1, 1) PRIMARY KEY, -- ID Định danh
    role_name NVARCHAR(100) NOT NULL UNIQUE, -- Tên chức vụ (Một tên duy nhất - không trùng tên chức vụ)
    status INT
);
GO

--- Users Table
CREATE TABLE [Users] (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,

    username VARCHAR(120) NOT NULL UNIQUE, -- Tên đăng nhập duy nhất
    password VARCHAR(120) NOT NULL, -- Mật khẩu
    fullname NVARCHAR(255) NOT NULL, -- Tên đầy đủ
	gender BIT NOT NULL, -- Giới tính chỉ có hai giới tính
    phonenumber VARCHAR(20) NOT NULL, -- số điện thoại
    address NVARCHAR(MAX) NOT NULL, -- Địa chỉ
    email VARCHAR(120) NULL UNIQUE, -- email nên là duy nhất nếu không null

    registrationdate DATETIME, -- ngày đăng ký

    roleid BIGINT NOT NULL, -- Một người dùng phải có vai trò: ID của Role

    email_verification_token VARCHAR(MAX) NULL, -- mã xác thực gửi email token
    token_creation_time DATETIME NULL, -- ngày tạo mã token

    status INT,
    FOREIGN KEY (roleid) REFERENCES [Role](id)
);
GO

-- Category Table
CREATE TABLE [Category] (
    id BIGINT IDENTITY(1, 1) PRIMARY KEY,
    category_name NVARCHAR(255) UNIQUE NOT NULL, -- Tên danh mục, nên có độ dài cụ thể
    status INT
);
GO

--- Product Table
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

    status INT,
	FOREIGN KEY (category_id) REFERENCES Category(id) -- Thêm khóa ngoại tới bảng Category
);
GO

-- Images Table (Bảng mới)
CREATE TABLE Images (
    id BIGINT IDENTITY(1, 1) PRIMARY KEY,

    image_url VARCHAR(MAX) NOT NULL, -- Đường dẫn URL đến hình ảnh
    product_id BIGINT NOT NULL, -- Khóa ngoại tham chiếu đến Product

    is_main BIT DEFAULT 0, -- Có phải là ảnh chính không (1=Có, 0=Không)
    order_index INT NULL, -- Thứ tự hiển thị (ví dụ: 1, 2, 3...)

    FOREIGN KEY (product_id) REFERENCES Product(id)
);
GO

--- Product_detail Table: Bảng chi tiết sản phẩm hay còn gọi là biến thể sản phẩm
CREATE TABLE Product_detail(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,

    product_detail_code VARCHAR(50) UNIQUE NOT NULL, -- Mã chi tiết sản phẩm
    product_id BIGINT NOT NULL, -- ID của Product

    style NVARCHAR(50) NOT NULL, -- phong cách ám chỉ kiểu áo (ví dụ: áo màu xanh, đỏ, tím, vàng, cam, áo nhiều màu, áo có hoa văn)
    size NVARCHAR(50) NOT NULL, -- Kích cỡ (Size: S, M, L, XL, XXL, XXXL, và đôi khi có thêm XS)
    inventory_quantity INT NOT NULL DEFAULT 0, -- tổng số lượng tồn kho của một chi tiết sản phẩm mà thương hiệu đó có. Không nên null, mặc định 0 nếu không có

    img_url VARCHAR(MAX) NULL, -- link ảnh (đường dẫn ảnh)
    status INT,

    FOREIGN KEY (product_id) REFERENCES Product(id)
);
GO

--- Warehouse Table: Bảng Kho
CREATE TABLE Warehouse (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    warehouse_name NVARCHAR(255) NOT NULL UNIQUE, -- Tên địa điểm. Tên kho nên là duy nhất
    address NVARCHAR(MAX) NULL, -- Địa chỉ
    status INT
);
GO

--- Inventory Table
CREATE TABLE Inventory (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    product_detail_id BIGINT NOT NULL, -- ID của bảng Product_Detail
    warehouse_id BIGINT NOT NULL, -- ID của bảng Warehouse
    quantity INT NOT NULL, -- số lượng của một biến thể sản phẩm tại một kho chứa nào đó - dựa vào ID của thuộc tính trên
    last_updated_at DATETIME DEFAULT GETDATE(), -- lần cập nhật cuối cùng
    status INT,

    FOREIGN KEY (product_detail_id) REFERENCES Product_detail(id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse(id),
    CONSTRAINT UQ_Inventory_ProductDetail_Warehouse UNIQUE (product_detail_id, warehouse_id) -- Một biến thể sản phẩm chỉ có một lượng tồn kho tại một kho cụ thể
);
GO

--- Voucher Table
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

--- Invoice Table
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

--- Invoicedetail Table
CREATE TABLE Invoicedetail (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,

    invoice_id BIGINT NOT NULL,
    product_detail_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,

    quantity INT NOT NULL,
    price DECIMAL(18, 2) NOT NULL,
    totalprice AS (quantity * price) PERSISTED,

    status INT,

    FOREIGN KEY (invoice_id) REFERENCES Invoice(id),
    FOREIGN KEY (product_detail_id) REFERENCES Product_detail(id),
	FOREIGN KEY (product_id) REFERENCES Product(id),

    CONSTRAINT UQ_InvoiceDetail_Invoice_ProductDetail UNIQUE (invoice_id, product_detail_id) -- Mỗi sản phẩm chi tiết chỉ xuất hiện một lần trong mỗi hóa đơn
);
GO

--- Review Table
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

--- Log Table
CREATE TABLE Log (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id BIGINT NULL, -- Ai đã thực hiện hành động (có thể null nếu hành động không liên quan đến user cụ thể)
    action NVARCHAR(MAX) NOT NULL, -- Hoạt động không nên null
    description NVARCHAR(MAX) NULL, -- Mô tả có thể null
    created_at DATETIME NOT NULL DEFAULT GETDATE(), -- Không nên null, mặc định ngày tạo
    FOREIGN KEY (user_id) REFERENCES Users(id)
);
GO
