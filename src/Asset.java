import java.math.BigDecimal;
import java.util.UUID;

public class Asset {
    // Definir variáveis
    UUID id;
    Currency currency;
    BigDecimal quantity;

    // Definir construtor
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



    // Definir métodos
    public BigDecimal getCurrentValue(){
        return quantity;
    }

}
