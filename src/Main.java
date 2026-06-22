import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{
    public static void main(String[] args) {
        try {
            // Initialize users
            User user1 = new User("user1@gmail.com", "1234", "user1");
            User user2 = new User("user2@gmail.com", "1234", "user2");

            ArrayList<User> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);

            System.out.println("Users: " + users);

            // Initialize currencies
            ArrayList<Currency> supportedCurrencies = new ArrayList<>();

            Currency BRL = new Currency("BRL", "Real Brasileiro", true);
            Currency USD = new Currency("USD", "Dólar dos Estados Unidos", true);

            ArrayList<Currency> fiatCurrencies = new ArrayList<>();
            fiatCurrencies.add(BRL);
            fiatCurrencies.add(USD);

            supportedCurrencies.addAll(fiatCurrencies);

            Currency BTC = new Currency("BTC", "Bitcoin", false);
            Currency ETH = new Currency("ETH", "Ethereum", false);

            ArrayList<Currency> cryptoCurrencies = new ArrayList<>();
            cryptoCurrencies.add(BTC);
            cryptoCurrencies.add(ETH);

            supportedCurrencies.addAll(cryptoCurrencies);

            HashMap<String, Currency> currenciesByCode = new HashMap<>();
            for (Currency c : supportedCurrencies) {
                currenciesByCode.put(c.getSymbol(), c);
            }

            // Initialize wallets
            HashMap<User, ArrayList<Wallet>> walletsByUser = new HashMap<>();

            ArrayList<Wallet> user1Wallets = new ArrayList<>();
            user1Wallets.add(new Wallet(user1, BRL));
            user1Wallets.add(new Wallet(user1, USD));
            walletsByUser.put(user1, user1Wallets);

            ArrayList<Wallet> user2Wallets = new ArrayList<>();
            user2Wallets.add(new Wallet(user2, BRL));
            user2Wallets.add(new Wallet(user2, USD));
            walletsByUser.put(user2, user2Wallets);

            // Start operations
            System.out.println("User 1:");
            System.out.println(user1);

            walletsByUser.get(user1).get(0).deposit(currenciesByCode.get("BTC"), new BigDecimal("0.0002"));
            walletsByUser.get(user1).get(1).deposit(currenciesByCode.get("BTC"), new BigDecimal("0.0001"));

            System.out.println("Wallet 1:");
            System.out.println(walletsByUser.get(user1).get(0));
            System.out.println("BRL: " + walletsByUser.get(user1).get(0).getBalance(BRL));
            System.out.println("BTC: " + walletsByUser.get(user1).get(0).getBalance("BTC"));

            System.out.println("Wallet 2:");
            System.out.println(walletsByUser.get(user1).get(1));
            System.out.println("BRL: " + walletsByUser.get(user1).get(1).getBalance(BRL));
            System.out.println("BTC: " + walletsByUser.get(user1).get(1).getBalance("BTC"));

            System.out.println("User 2:");
            System.out.println(user2);

            walletsByUser.get(user2).get(0).deposit(currenciesByCode.get("ETH"), new BigDecimal("0.0003"));
            walletsByUser.get(user2).get(1).deposit(currenciesByCode.get("ETH"), new BigDecimal("0.0005"));

            System.out.println("Wallet 1:");
            System.out.println(walletsByUser.get(user2).get(0));
            System.out.println("BRL: " + walletsByUser.get(user2).get(0).getBalance(BRL));
            System.out.println("ETH: " + walletsByUser.get(user2).get(0).getBalance("ETH"));

            System.out.println("Wallet 2:");
            System.out.println(walletsByUser.get(user2).get(1));
            System.out.println("BRL: " + walletsByUser.get(user2).get(1).getBalance(BRL));
            System.out.println("ETH: " + walletsByUser.get(user2).get(1).getBalance("ETH"));

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}