package Fx_GUI_Classe;//Datenmodell (JavaFX Properties)

import javafx.beans.property.*;

import java.math.BigDecimal;

public class ProductModel {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
    private final ObjectProperty<BigDecimal> price = new SimpleObjectProperty<>();
    private final BooleanProperty active = new SimpleBooleanProperty();

    public ProductModel(long id, String name, String category, BigDecimal price, boolean active) {
        this.id.set(id);
        this.name.set(name);
        this.category.set(category);
        this.price.set(price);
        this.active.set(active);
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


