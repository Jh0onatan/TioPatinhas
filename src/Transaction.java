import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

public class Transaction {
    // Definindo variáveis
    UUID id;
    Wallet wallet;
    Currency currency;
    BigDecimal quantity;
    BigDecimal priceAtTime;
    TransactionType type;
    LocalDateTime executedAt;

    // Definir construtor

    // Definir métodos
    public BigDecimal getTotalValue(){
        return (quantity.multiply(priceAtTime));
    }

}
