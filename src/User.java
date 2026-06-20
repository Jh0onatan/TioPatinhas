import java.util.UUID;

public class User extends BaseEntity {
    private String email;
    private String hashedPassword;
    private String name;

    public User(String email, String hashedPassword, String name){
        this.id = UUID.randomUUID();

        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
    }

    public User(UUID id, String email, String hashedPassword, String name){
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String newEmail){
        if (newEmail != null && newEmail.contains("@")){
            this.email = newEmail;
        } else {
            System.out.println("Erro: Email Inválido.");
        }
    }

    public void setName(String newName){
        if (newName != null && !newName.trim().isEmpty()){
            this.name = newName;
        } else {
            System.out.println("Erro: Nome inválido.");
        }
    }

    public void setHashedPassword(String newHashedPassword){
        if (newHashedPassword != null && !newHashedPassword.isEmpty()){
            this.hashedPassword = newHashedPassword;
        } else {
            System.out.println("Erro: A senha não pode ser vazia!");
        }
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
