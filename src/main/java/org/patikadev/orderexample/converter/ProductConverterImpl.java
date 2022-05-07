package org.patikadev.orderexample.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateProductRequestDTO;
import org.patikadev.orderexample.dto.response.GetProductsResponseDTO;
import org.patikadev.orderexample.model.Brand;
import org.patikadev.orderexample.model.Category;
import org.patikadev.orderexample.model.Product;
import org.patikadev.orderexample.model.Seller;
import org.patikadev.orderexample.service.BrandService;
import org.patikadev.orderexample.service.CategoryService;
import org.patikadev.orderexample.service.SellerService;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@RequiredArgsConstructor
public class ProductConverterImpl implements ProductConverter{

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final SellerService sellerService;
    private final CategoryConverter categoryConverter;
    @Override
    public Product toProduct(CreateProductRequestDTO createProductRequestDTO) {
       Product product = new Product();
       product.setName(createProductRequestDTO.name());
       product.setPrice(createProductRequestDTO.price());
       product.setImage(createProductRequestDTO.image());
       Brand brand = brandService.getBrand(createProductRequestDTO.brandId());
       Category category = categoryService.getCategoryWithId(createProductRequestDTO.categoryId());
       Seller seller = sellerService.getSellerWithId(createProductRequestDTO.sellerId());
        product.setSeller(seller);
        product.setCategory(category);
        product.setBrand(brand);
        product.setStock(createProductRequestDTO.stock());
        product.setCreatedBy(createProductRequestDTO.createdBy());
        product.setCreatedAt(new Date());
        product.setTaxRate(createProductRequestDTO.taxRate());

       return product;

    }
    public GetProductsResponseDTO toGetProductsResponse(Product product) {
        return new GetProductsResponseDTO(product.getId(),
                product.getName(),
                product.getStock(),
                product.getBarcode(),
                product.getPrice(),
                categoryConverter.categoryToCategoryResponseDTO(product.getCategory()),
                product.getSeller(),
                product.getBrand(),
                product.getImage()
                );
    }
}
