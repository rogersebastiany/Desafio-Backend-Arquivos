package SouthSystem;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * A classe Main é o nodo central da aplicação, e por isso é responsável pelas entradas válidas, atribuição dos dados, e gravação da saída.
 *
 *      Entradas:
 *
 *          - Dados do diretório padrão, localizado em %HOMEPATH%/data/in.
 *            * O sistema deve ler somente arquivos .dat.
 *
 *      Dados:
 *
 *          - Depois de processar todos os arquivos dentro do diretório padrão de entrada,
 *            o sistema deve criar um arquivo dentro do diretório de saída padrão,
 *            localizado em %HOMEPATH%/data/out.
 *            * O nome do arquivo deve seguir o padrão, {flat_file_name} .done.dat
 *
 *      Saída:
 *
 *
 *          - Quantidade de clientes no arquivo de entrada
 *          - Quantidade de vendedor no arquivo de entrada
 *          - ID da venda mais cara
 *          - O pior vendedor
 *
 *      Obrigações:
 *
 *          - O sistema deve estar funcionando o tempo todo.
 *          - Todos os arquivos novos estarem disponíveis, tudo deve ser executado.
 *
 *
 */

public class Main {

    static String input_path = "data/in/";
    static String output_path = "data/out/";

    private static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {

        System.out.println("desafio-backend-arquivo");
        System.out.println("Autor: Roger Sebastiany");

        File input_data = new File(input_path);
        if(!input_data.exists()) {
            System.out.println("Pasta "+input_path+" não encontrada. A pasta será criada agora.");
            input_data.mkdirs();
        }

        File output_data = new File(output_path);
        if(!output_data.exists()) {
            System.out.println("Pasta "+output_path+" não encontrada. A pasta será criada agora.");
            output_data.mkdirs();
        }

        int run = 1;

        while(run == 1){

            System.out.println("Digite:"
                    + System.lineSeparator() + "1 para ler arquivos."
                    + System.lineSeparator() + "2 para imprimir relatório."
                    + System.lineSeparator() + "0 para sair.");

            String comando = Perguntar("Opção: ");

            switch(comando) {
                case "1" : {
                    String[] input_files = new File(input_path).list((dir, name) -> name.endsWith(".dat"));
                    if(!(input_files.length == 0)){
                        //Ler arquivos na lista input_files
                        run = LerDados(input_files);
                    } else {
                        System.out.println("Nenhum arquivo de entrada foi encontrado.");
                    }
                    break;
                }

                case "2" :{
                    String[] output_files = new File(output_path).list((dir, name) -> name.endsWith(".done.dat"));
                    if(!(output_files.length == 0)){
                        System.out.println("Relatórios disponíveis:");
                        for(String s: output_files){
                            System.out.println(s);
                        }
                        String arquivo = Perguntar("Insira o nome do arquivo desejado. ");
                        run = Relatorio(arquivo);
                    } else {
                        System.out.println("Nenhum relatório foi encontrado.");
                    }
                    break;
                }

                case "0" : {
                    System.out.println("Saindo");
                    run = 0;
                    break;
                }

                default: System.out.println("Opção inválida"); break;
            }
        }
        scanner.close();
        System.exit(run);
    }

    private static int Relatorio(String file) {

        ArrayList<Vendedor> Vendedores = new ArrayList<Vendedor>();
        ArrayList<Venda> Vendas = new ArrayList<Venda>();
        ArrayList<Cliente> Clientes = new ArrayList<Cliente>();

        try {
            File f = new File(output_path + file);
            Scanner scan = new Scanner(f);

            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                String[] result = data.split("ç");
                try {
                    switch (result[0]) {
                        case "001":
                            Vendedores.add(new Vendedor(result[1], result[2], result[3]));
                            break;
                        case "002":
                            Clientes.add(new Cliente(result[1], result[2], result[3]));
                            break;
                        case "003":
                            Vendas.add(new Venda(result[1],
                                    result[2],
                                    result[3]));
                            break;
                        default: {
                            System.out.println("Definição dos dados incorreta.");
                            break;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Arquivo corrompido.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Relatório não encontrado.");
            return 1;
        }


        int num_clientes = VerificarNumClientes(Clientes);
        int num_vendedores = VerificarNumVendedores(Vendedores);
        String id_max_venda = VerificarMaxVenda(Vendas);
        String nome_pior_venvedor = VerificarPiorVendedor(Vendas);

        System.out.println("Número de clientes: "+num_clientes);
        System.out.println("Número de vendedores: "+num_vendedores);
        System.out.println("ID da venda mais cara: "+id_max_venda);
        System.out.println("Nome do pior vendedor: "+nome_pior_venvedor);

        return 1;
    }

    private static String VerificarPiorVendedor(ArrayList<Venda> vendas) {
        float aux = 999999999;
        String pior_vendedor = "";
        for (Venda v: vendas){
            float valor = CalcularValorVenda(v.getVenda_dados());
            if (aux >= valor){
                aux = valor;
                pior_vendedor = v.getVenda_vendedor();
            }
        }
        return pior_vendedor;
    }


    private static String VerificarMaxVenda(ArrayList<Venda> vendas) {
        float aux = 0;
        String id_max_venda = "";
        for(Venda v: vendas){
            float valor = CalcularValorVenda(v.getVenda_dados());
            if (aux <= valor){
                aux = valor;
                id_max_venda = v.getVenda_id();
            }
        }
        return id_max_venda;
    }

    private static float CalcularValorVenda(String v) {
        float valor = 0;
        String[] result = v.split(",");
        for(String s: result){
            String[] result2 = s.split("-");
            String item_qntd = result2[1];
            String item_val = result2[2];
            item_val = item_val.replace("]","");
            valor += Float.valueOf(item_qntd)*Float.valueOf(item_val);
        }
        return valor;
    }

    private static int VerificarNumVendedores(ArrayList<Vendedor> vendedores) {
        return vendedores.size();
    }

    private static int VerificarNumClientes(ArrayList<Cliente> clientes) {
        return clientes.size();
    }


    private static int LerDados(String[] input_files) {

        String output_file_name = Perguntar("Nome do arquivo de saída: ");
        File output_file = new File(output_path+output_file_name+".done.dat");

        Writer writer = null;

        if(output_file.exists()){
          System.out.println("Arquivo ja existe");
        } else {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                         new FileOutputStream(output_file), "utf-8"));
                for (String s : input_files) {
                    System.out.println("Lendo arquivo " + s);
                    File f = new File(input_path + s);
                    Scanner scan = new Scanner(f);
                    while(scan.hasNextLine()){
                        String data = scan.nextLine();
                        writer.write(data);
                        writer.write(System.lineSeparator());
                    }
                }
            } catch (IOException e){
                System.out.println("Um erro ocorreu");
                e.printStackTrace();
                return -1;
            } finally {
                try {writer.close();} catch (Exception ex) {/*ignore*/}
            }
        }
        System.out.println("Leitura concluida.");
        System.out.println("Arquivo "+output_file_name+".done.dat gerado com sucesso");
        return 1;
    }


    private static String Perguntar(String text) {
        System.out.print(text);
        String comando = "";
        if (scanner.hasNextLine()) {
            comando = scanner.nextLine();
        }
        return comando;
    }

}
