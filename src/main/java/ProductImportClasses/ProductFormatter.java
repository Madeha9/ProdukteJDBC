package ProductImportClasses;

import java.util.List;

public class ProductFormatter {
    //ProductImportClasses.Product formatierung
    //Mit List verwenden, single Responsibility konzept
    public static String format(Product product) {
        return "ID=" + product.getId() +
                " | name=" + product.getName() +
                " | price=" + product.getPrice() +
                " | Category =" + product.getCategory() +
                " | active=" + product.isActive();
    }

    public static void formatList(List<Product> products) {
        for (Product product : products) {
            System.out.println(format(product));
        }
    }
}

