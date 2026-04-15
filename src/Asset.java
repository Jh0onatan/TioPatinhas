import java.math.BigDecimal;
import java.util.UUID;

public class Asset {
    // Definir variáveis
    UUID id;
    Currency currency;
    BigDecimal quantity;

    // Definir construtor

    // Definir métodos
    public BigDecimal getCurrentValue(){
        return quantity;
    }

}
