public class Courier extends Url{
    private String login;
    private String password;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }



    public Courier(String login, String password, String name){
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Courier(){
    }
}
