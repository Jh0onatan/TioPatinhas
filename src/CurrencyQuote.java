import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CurrencyQuote {
    // Definir variáveis
    UUID id;
    Currency currency;
    Currency baseCurrency;
    BigDecimal price;
    LocalDateTime timestamp;

    // Definir construtor

    // Definir métodos
    public String getFormattedPrice(){
        return (timestamp + " : " + baseCurrency.symbol + price);
    }
}
