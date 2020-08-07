package SouthSystem;

public class Cliente {
    String Cliente_cnpj;
    String Cliente_nome;
    String Cliente_area;

    public String getCliente_cnpj() {
        return Cliente_cnpj;
    }

    public void setCliente_cnpj(String cliente_cnpj) {
        Cliente_cnpj = cliente_cnpj;
    }

    public String getCliente_nome() {
        return Cliente_nome;
    }

    public void setCliente_nome(String cliente_nome) {
        Cliente_nome = cliente_nome;
    }

    public String getCliente_area() {
        return Cliente_area;
    }

    public void setCliente_area(String cliente_area) {
        Cliente_area = cliente_area;
    }

    public Cliente(String cliente_cnpj, String cliente_nome, String cliente_area) {
        Cliente_cnpj = cliente_cnpj;
        Cliente_nome = cliente_nome;
        Cliente_area = cliente_area;
    }
}
