package org.fasttrackit.onlineshop.transfer.cart;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddProductsToCartRequest {

    @NotNull
    private List <Long> productsIds;

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }

    @Override
    public String toString() {
        return "AddProductsToCartRequest{" +
                "productsIds=" + productsIds +
                '}';
    }
}
