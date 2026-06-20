import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction extends BaseEntity {
    Wallet wallet;
    Currency currency;
    BigDecimal quantity;
    BigDecimal priceAtTime;
    TransactionType type;
    LocalDateTime executedAt;

    public Transaction(Wallet wallet, Currency currency, BigDecimal quantity, BigDecimal priceAtTime, TransactionType type){
        this.id = UUID.randomUUID();

        this.wallet = wallet;
        this.currency = currency;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
        this.type = type;

        this.executedAt = LocalDateTime.now();
    }

    public Transaction(UUID id, Wallet wallet, Currency currency, BigDecimal quantity, BigDecimal priceAtTime, TransactionType type, LocalDateTime executedAt){
        this.id = id;
        this.executedAt = executedAt;

        this.wallet = wallet;
        this.currency = currency;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
        this.type = type;
    }

    public BigDecimal getTotalValue(){
        return (quantity.multiply(priceAtTime));
    }

    @Override
    public String toString() {
        return "Transaction{id=" + id +
                ", walletId=" + wallet.getId() +
                ", currency=" + currency.getSymbol() +
                ", quantity=" + quantity +
                ", priceAtTime=" + priceAtTime +
                ", type=" + type +
                ", executedAt=" + executedAt + "}";
    }
}
