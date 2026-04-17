import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class CurrencyQuote {
    // Definir variáveis
    UUID id;
    Currency currency;
    Currency baseCurrency;
    BigDecimal price;
    LocalDateTime timestamp;

    // Definir construtor
    public CurrencyQuote(Currency currency, Currency baseCurrency, BigDecimal price){
        this.id = UUID.randomUUID();

        this.currency = currency;
        this.baseCurrency = baseCurrency;
        this.price = price;

        this.timestamp = LocalDateTime.now();
    }

    public CurrencyQuote(UUID id, Currency currency, Currency baseCurrency, BigDecimal price, LocalDateTime timestamp){
        this.id = id;
        this.currency = currency;
        this.baseCurrency = baseCurrency;
        this.price = price;
        this.timestamp = timestamp;
    }

    // Definir métodos
    public String getFormattedPrice(){
        // Formatando o horário/dia no padrão brasileiro.
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String dataFormatada = timestamp.format(formatador);

        // Retorno formatado do horário e moeda.
        return dataFormatada + " : " + baseCurrency.symbol + price;
    }
}