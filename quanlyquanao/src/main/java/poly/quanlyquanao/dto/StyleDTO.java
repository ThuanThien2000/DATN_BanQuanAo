package poly.quanlyquanao.dto;

public class StyleDTO {
    private String color;
    private String imageUrl;

    public StyleDTO() {}

    public StyleDTO(String color, String imageUrl) {
        this.color = color;
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
