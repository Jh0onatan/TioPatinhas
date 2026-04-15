import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Wallet {
    // Definindo variáveis
    UUID id;
    User owner;
    Currency baseCurrency;
    List<Asset> assets;
    List<Transaction> transactions;

    // Definindo o construtor

    // Definindo os métodos
    public String getBalance(){
        String resultado = "";
        for (int i = 0; i < assets.size(); i++){
            resultado += assets.get(i).currency.symbol + " " + assets.get(i).quantity + "\n";
        }
        return resultado;
    }

    public String getTotalValue(){
        BigDecimal resultado = BigDecimal.ZERO;
        for (int i = 0; i < assets.size(); i++){
            resultado = resultado.add(assets.get(i).quantity);
        }
        return resultado.toString();
    }
}