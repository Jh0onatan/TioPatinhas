import java.math.BigDecimal;

public class Main{
    public static void main(String[] args){
        User teste = new User("teste@gmail.com", "1234", "teste");
        Currency brl = new Currency("BRL", "Real Brasileiro", true);
        Wallet carteira = new Wallet(teste, brl);

        carteira.deposit(brl, new BigDecimal("1000"));
        carteira.deposit(brl, new BigDecimal("250"));

        System.out.println(carteira.getBalance(brl));
    }
}