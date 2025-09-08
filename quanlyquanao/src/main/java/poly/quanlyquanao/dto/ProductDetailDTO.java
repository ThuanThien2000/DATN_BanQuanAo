package poly.quanlyquanao.dto;

public class ProductDetailDTO {
    private Long id;
    private String productDetailCode;
    private Long productId;
    private String style;
    private String size;
    private Integer inventoryQuantity;
    private String imgUrl;
    private Integer status;

    public ProductDetailDTO() {}

    public ProductDetailDTO(Long id, String productDetailCode, Long productId, String style, String size, Integer inventoryQuantity, String imgUrl, Integer status) {
        this.id = id;
        this.productDetailCode = productDetailCode;
        this.productId = productId;
        this.style = style;
        this.size = size;
        this.inventoryQuantity = inventoryQuantity;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductDetailCode() { return productDetailCode; }
    public void setProductDetailCode(String productDetailCode) { this.productDetailCode = productDetailCode; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getStyle() { return style; }
    public void setStyle(String style) { this.style = style; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public Integer getInventoryQuantity() { return inventoryQuantity; }
    public void setInventoryQuantity(Integer inventoryQuantity) { this.inventoryQuantity = inventoryQuantity; }
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
