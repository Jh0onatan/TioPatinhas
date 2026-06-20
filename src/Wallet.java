import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wallet extends BaseEntity {
    private User owner;
    private Currency baseCurrency;
    private final List<Asset> assets;
    private final List<Transaction> transactions;

    public Wallet(User owner, Currency baseCurrency){
        this.id = UUID.randomUUID();

        this.owner = owner;
        this.baseCurrency = baseCurrency;
        this.assets = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public Wallet(UUID id, User owner, Currency baseCurrency, List<Asset> assets, List<Transaction> transactions){
        this.id = id;
        this.owner = owner;
        this.baseCurrency = baseCurrency;
        this.assets = assets;
        this.transactions = transactions;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {

        this.owner = owner;
    }

    public Currency getBaseCurrency() { return baseCurrency; }

    public void setBaseCurrency(Currency baseCurrency) { this.baseCurrency = baseCurrency; }

    public void updateBaseCurrency(Currency newBaseCurrency) {
        this.baseCurrency = newBaseCurrency;
    }

    public void appendAsset(Asset asset) {
        this.assets.add(asset);
    }

    public List<Asset> getAssets(){
        return assets;
    }

    public void appendTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public List<Transaction> getTransactions() { return transactions; }

    public String getBalance(Currency c){
        if (c == null || c.getSymbol() == null || transactions == null){
            return BigDecimal.ZERO.toString();
        }

        BigDecimal balance = BigDecimal.ZERO;

        for (Transaction t : transactions){
            if (t.currency != null && t.currency.getSymbol().equals(c.getSymbol())){
                if (t.type == TransactionType.BUY || t.type == TransactionType.DEPOSIT){
                    balance = balance.add(t.quantity);
                }
                else if (t.type == TransactionType.SELL || t.type == TransactionType.WITHDRAW){
                    balance = balance.subtract(t.quantity);
                }
            }
        }

        return balance.toString();
    }

    public String getBalance(String currencySymbol) {
        for (Asset asset : assets) {
            if (asset.getCurrency().getSymbol().equals(currencySymbol)) {
                return asset.getQuantity().toString();
            }
        }

        return BigDecimal.ZERO.toString();
    }

    public String getTotalValue(List<CurrencyQuote> currentPrices){
        BigDecimal totalPatrimony = BigDecimal.ZERO;

        for (Asset asset : assets){
            if (asset.currency.getSymbol().equals(baseCurrency.getSymbol())){
                totalPatrimony = totalPatrimony.add(asset.quantity);
                continue;
            }

            for (CurrencyQuote quote : currentPrices){
                if (quote.currency.getSymbol().equals(asset.currency.getSymbol())) {
                    BigDecimal convertValue = asset.quantity.multiply(quote.price);
                    totalPatrimony = totalPatrimony.add(convertValue);

                    break;
                }
            }
        }

        return totalPatrimony.toString();
    }

    public void deposit(Currency currency, BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("Erro: valor menor ou igual a zero!");
            return;
        }

        boolean assetAlreadyExist = false;

        for (Asset asset : assets){
            if (asset.currency.getSymbol().equals(currency.getSymbol())) {
                asset.quantity = asset.quantity.add(amount);
                assetAlreadyExist = true;
                break;
            }
        }

        if (!assetAlreadyExist){
            Asset newAsset = new Asset(currency, amount);
            assets.add(newAsset);
        }

        Transaction receipt = new Transaction(this, currency, amount, BigDecimal.ONE, TransactionType.DEPOSIT);
        transactions.add(receipt);

        System.out.println("Sucesso: Depósito de " + amount + " " + currency.getSymbol() + " realizado!");
    }

    @Override
    public String toString() {
        return "Wallet{id=" + id +
                ", owner=" + owner.getName() +
                ", baseCurrency=" + baseCurrency.getSymbol() +
                ", assets=" + assets +
                ", transactions=" + transactions + "}";
    }
}








