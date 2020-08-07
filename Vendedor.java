package SouthSystem;

public class Vendedor {
    String Vendedor_cpf;
    String Vendedor_nome;
    String Vendedor_Salario;

    public String getVendedor_cpf() {
        return Vendedor_cpf;
    }

    public void setVendedor_cpf(String vendedor_cpf) {
        Vendedor_cpf = vendedor_cpf;
    }

    public String getVendedor_nome() {
        return Vendedor_nome;
    }

    public void setVendedor_nome(String vendedor_nome) {
        Vendedor_nome = vendedor_nome;
    }

    public String getVendedor_Salario() {
        return Vendedor_Salario;
    }

    public void setVendedor_Salario(String vendedor_Salario) {
        Vendedor_Salario = vendedor_Salario;
    }

    public Vendedor(String vendedor_cpf, String vendedor_nome, String vendedor_Salario) {
        Vendedor_cpf = vendedor_cpf;
        Vendedor_nome = vendedor_nome;
        Vendedor_Salario = vendedor_Salario;
    }
}
