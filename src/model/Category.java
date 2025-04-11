package model;

abstract class Category{
    int id;
    String name;

    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
class ProductCategory{
    int productCategoryId;
    Category category;
    Product product;

    ProductCategory(int productCategoryId, Category category, Product product) {
        this.productCategoryId = productCategoryId;
         this.category = category;
         this.product = product;

    }

}
