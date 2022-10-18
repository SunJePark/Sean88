package System.Cart;

import java.util.ArrayList;
import java.util.UUID;

public class Cart {
    private ArrayList<Product> list;
    private int totalPrice;

    public Cart() {
        this.list = new ArrayList<>();
        this.totalPrice = 0;
    }

    public ArrayList<Product> getProductListOrNull() {
        return this.list;
    }

    public Product getItemFromListOrNull(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("unavailable product id type");
        }
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getProductId() == productId) {
                return list.get(i);
            }
        }
        return null;

    }

    public void addProduct(Product product) {
        if (this.list.contains(product)) {
            return;
        } else if (product == null) {
            throw new IllegalArgumentException("product can not be null");
        }

        this.totalPrice += product.getPrice();
        this.list.add(product);
    }

    public void removeProduct(Product product) {
        if (!this.list.contains(product)) {
            return;
        } else if (product == null) {
            throw new IllegalArgumentException("product can not be null");
        }
        this.totalPrice -= product.getPrice();
        this.list.remove(product);
    }

    public int getTotalPrice() {
        int updatedTotalPrice = 0;
        for (int i = 0; i < this.list.size(); ++i) {
            updatedTotalPrice += this.list.get(i).getPrice();
        }
        this.totalPrice = updatedTotalPrice;
        return updatedTotalPrice;
    }
}
