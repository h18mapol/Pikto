package piktoproject.pikto.models;

public class CartItemDTO extends CartItem{
    private String productUrl;
    private String title;

    public CartItemDTO() {
    }

    public String getProductUrl() {
        return productUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
