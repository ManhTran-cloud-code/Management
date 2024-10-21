package manh.com.project.SaleManagement.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException() {

    }
}
