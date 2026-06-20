import java.math.BigDecimal;

public class Main{
    public static void main(String[] args) {
        try {
            User test = new User("teste@gmail.com", "1234", "teste");
            Currency BRL = new Currency("BRL", "Real Brasileiro", true);
            Wallet wallet = new Wallet(test, BRL);
            Currency BTC = new Currency("BTC", "Bitcoin", false);

            wallet.deposit(BTC, new BigDecimal("0.0002"));
            wallet.deposit(BTC, new BigDecimal("0.0001"));

            System.out.println(wallet.getBalance(BRL));
            System.out.println(wallet.getBalance("BTC")); // overload

            // testar toString (override)
            System.out.println(test);
            System.out.println(wallet);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}