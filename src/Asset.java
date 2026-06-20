import java.math.BigDecimal;
import java.util.UUID;

public class Asset extends BaseEntity {
    Currency currency;
    BigDecimal quantity;

    public Asset(Currency currency, BigDecimal quantity){
        this.id = UUID.randomUUID();

        this.currency = currency;
        this.quantity = quantity;
    }

    public Asset(UUID id, Currency currency, BigDecimal quantity){
        this.id = id;
        this.currency = currency;
        this.quantity = quantity;
    }

    public Currency getCurrency() { return currency; }

    public void setCurrency(Currency currency) { this.currency = currency; }

    public BigDecimal getQuantity() { return quantity; }

    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Asset{id=" + id + ", currency=" + currency + ", quantity=" + quantity + "}";
    }
}
