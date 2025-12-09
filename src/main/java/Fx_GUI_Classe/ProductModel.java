package Fx_GUI_Classe;//Datenmodell (JavaFX Properties)

import ProductImportClasses.Product;
import javafx.beans.property.*;

import java.math.BigDecimal;

public class ProductModel {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
    private final ObjectProperty<BigDecimal> price = new SimpleObjectProperty<>();
    private final BooleanProperty active = new SimpleBooleanProperty();

    public ProductModel(Product p) {
        this.id.set(p.getId());
        this.name.set(p.getName());
        this.category.set(p.getCategory());
        this.price.set(p.getPrice());
        this.active.set(p.isActive());
    }

    // ===== Getter / Setter =====
    public long getId() {
        return id.get();
    }

    public void setId(long value) {
        id.set(value);
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String value) {
        category.set(value);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public BigDecimal getPrice() {
        return price.get();
    }

    public void setPrice(BigDecimal value) {
        price.set(value);
    }

    public ObjectProperty<BigDecimal> priceProperty() {
        return price;
    }

    public boolean isActive() {
        return active.get();
    }

    public void setActive(boolean value) {
        active.set(value);
    }

    public BooleanProperty activeProperty() {
        return active;
    }
}


