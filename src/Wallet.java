import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wallet {
    // Definindo variáveis
    private final UUID id;
    private User owner;
    private Currency baseCurrency;
    private List<Asset> assets;
    private List<Transaction> transactions;

    // Definindo o construtor
        // Carteira nova
    public Wallet(User owner, Currency baseCurrency){
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.baseCurrency = baseCurrency;
        this.assets = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

        // Carteira existente
    public Wallet(UUID id, User owner, Currency baseCurrency, List<Asset> assets, List<Transaction> transactions){
        this.id = id;
        this.owner = owner;
        this.baseCurrency = baseCurrency;
        this.assets = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }


    // Definindo os métodos
        // Geters
    public String getAsset(){
        return assets.toString();
    }



        // Métodos para ver quanto de cada moeda tem na carteira
    public String getBalance(Currency c){
        if (c == null || c.symbol == null || transactions == null){
            return BigDecimal.ZERO.toString();
        }

        BigDecimal balance = BigDecimal.ZERO;

        for (Transaction t : transactions){
            if (t.currency != null && t.currency.symbol.equals(c.symbol)){
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

        // Métodos para dizer o patrimônio total da carteira
    public String getTotalValue(List<CurrencyQuote> currentPrices){

        BigDecimal totalPatrimony = BigDecimal.ZERO;

        for (Asset asset : assets){
            if (asset.currency.symbol.equals(baseCurrency.symbol)){
                totalPatrimony = totalPatrimony.add(asset.quantity);
                continue;
            }

            for (CurrencyQuote quote : currentPrices){

                if (quote.currency.symbol.equals(asset.currency.symbol)) {
                    BigDecimal convertValue = asset.quantity.multiply(quote.price);
                    totalPatrimony = totalPatrimony.add(convertValue);

                    break;
                }
            }
        }

        return totalPatrimony.toString();
    }

        // Métodos de depósito
    public void deposit(Currency currency, BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("Erro: valor menor ou igual a zero!");
            return;
        }

        boolean assetAlreadyExist = false;

        for (Asset asset : assets){
            if (asset.currency.symbol.equals(currency.symbol)) {
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

        System.out.println("Sucesso: Depósito de " + amount + " " + currency.symbol + " realizado!");
    }
}








