package poly.quanlyquanao.dto;

public class ImageDTO {
    private Long id;
    private String imageUrl;
    private int status;

    public ImageDTO() {}

    public ImageDTO(Long id, String imageUrl, int status) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
