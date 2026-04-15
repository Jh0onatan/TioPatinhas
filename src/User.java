import java.util.UUID;

public class User {
    // Definindo variáveis
    private final UUID id;
    private String email;
    private String hashedPassword;
    private String name;

    // Definindo o construtor
    public User(UUID id, String email, String hashedPassword, String name){
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
    }

    // Definindo os métodos
    public String getDisplayName(){
        return name;
    }
}
