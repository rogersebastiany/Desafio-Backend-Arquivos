package SouthSystem;

public class Venda {
    String Venda_id;
    String Venda_dados;
    String Venda_vendedor;

    public String getVenda_id() {
        return Venda_id;
    }

    public void setVenda_id(String venda_id) {
        Venda_id = venda_id;
    }

    public String getVenda_dados() {
        return Venda_dados;
    }

    public void setVenda_dados(String venda_dados) {
        Venda_dados = venda_dados;
    }

    public String getVenda_vendedor() {
        return Venda_vendedor;
    }

    public void setVenda_vendedor(String venda_vendedor) {
        Venda_vendedor = venda_vendedor;
    }

    public Venda(String venda_id, String venda_dados, String venda_vendedor) {
        Venda_id = venda_id;
        Venda_dados = venda_dados;
        Venda_vendedor = venda_vendedor;
    }
}
