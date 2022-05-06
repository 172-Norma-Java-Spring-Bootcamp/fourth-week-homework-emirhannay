package org.patikadev.orderexample.exception;

public final class BusinessServiceOperationException {

    private BusinessServiceOperationException() {
    }

    public static class CustomerNotFoundException extends BaseException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class CustomerAlreadyDeletedException extends BaseException {
        public CustomerAlreadyDeletedException(String message) {
            super(message);
        }
    }
    public static class ProductAlreadyDeletedException extends BaseException {
        public ProductAlreadyDeletedException(String message) {
            super(message);
        }
    }
    public static class BrandNotFoundException extends BaseException {

        public BrandNotFoundException(String message) {
            super(message);
        }
    }
    public static class CategoryNotFoundException extends BaseException {

        public CategoryNotFoundException(String message) {
            super(message);
        }
    }
    public static class ProductNotFoundException extends BaseException {

        public ProductNotFoundException(String message) {
            super(message);
        }
    }
    public static class SellerNotFoundException extends BaseException {

        public SellerNotFoundException(String message) {
            super(message);
        }
    }
    public static class BasketNotFoundException extends BaseException {

        public BasketNotFoundException(String message) {
            super(message);
        }
    }
    public static class BasketItemNotFound extends BaseException {

        public BasketItemNotFound(String message) {
            super(message);
        }
    }
    public static class CampaignNotFoundException extends BaseException {

        public CampaignNotFoundException(String message) {
            super(message);
        }
    }
    public static class OrderNotFoundException extends BaseException {

        public OrderNotFoundException(String message) {
            super(message);
        }
    }
    public static class FailedToCreateOrderException extends BaseException {

        public FailedToCreateOrderException(String message) {
            super(message);
        }
    }


}
