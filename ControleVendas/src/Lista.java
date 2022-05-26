import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.DoubleSummaryStatistics;

public class Lista {
    public static void main(String[] args) throws Exception {
     
        Scanner scanner = new Scanner(System.in);
        List<Produto> lista_produtos = new ArrayList<>();
        List<Venda> lista_vendas = new ArrayList<>(); 


        String nomeProduto;
        int quantidadeProduto=0;
        double valor=0,valorTotal=0;
        boolean achei = false;
        Produto buscandoProduto = null;
        //int posicao = 0;

        String opcao;
        do{
            limparTela();
            System.out.println("########## PAPELARIA CRUZEIROS #########");
            System.out.println("########### MENU PRINCIPAL #############");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.println("SELECIONE A OPÇAO DESEJADA: ");
            System.out.println("");
            System.out.println("[1] - INCLUIR PRODUTO"); 
            System.out.println("[2] - CONSULTAR PRODUTO");
            System.out.println("[3] - LISTAR PRODUTOS");
            System.out.println("[4] - REMOVER PRODUTO CADASTRADO");
            System.out.println("[5] - REALIZAR VENDAS");
            System.out.println("[6] - RELATORIO - VENDAS POR PERIODO");
            System.out.println("[0] - SAIR DO MENU");

            System.out.print("OPCÃO: ");
            opcao = scanner.next();
            scanner.nextLine(); 

            Produto produto = new Produto();
            Venda   venda1 = new Venda();
         

            switch (opcao) {
                case "1":
                    System.out.print("Informe o código do Produto: ");
                    produto.setCodigo(scanner.nextInt() );
                    System.out.print("Informe o nome do Produto: ");
                    produto.setNome( scanner.next() );
                    System.out.print("Informe o valor do Produto: ");
                    produto.setValor(scanner.nextDouble());
                    System.out.print("Quantidade em Estoque: ");
                    produto.setQuantidade(scanner.nextInt());

                    lista_produtos.add(produto);

                    System.out.println("");
                    System.out.println("Produto cadastrado com sucesso!"); 
                    System.out.println(""); 

                   quantidadeProduto = produto.getQuantidade();
                   valor =produto.getValor();
                  
                    
                    System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine(); 
                                   
                    break;

                case "2":

                    System.out.print("Qual o nome do Produto você deseja consultar? ");
                    nomeProduto = scanner.nextLine();
                    achei = false;
                    buscandoProduto = null;
                        for (Produto item : lista_produtos) {
                            if ( item.getNome().equalsIgnoreCase( nomeProduto ) ) {
                                achei = true;
                                buscandoProduto = item;
                            } 
                        }
                    if (achei) {
                        System.out.println("Produto encontrado: " + buscandoProduto);
                    } else {
                        System.out.println("Produto não encontrado ... ");
                    }
                    System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine();    
                break;                
               
                case "3":
                  DoubleSummaryStatistics resumo = lista_produtos.stream()
                  .collect(Collectors.summarizingDouble(Produto::getValor));

                    System.out.println("####### LISTAGEM DE PRODUTOS #######");
                    System.out.println("------------------------------------");
                    if ( lista_produtos.isEmpty() ) {
                        System.out.println("Lista vazia, não há produtos cadastrados!");
                    } else { 
                        for (Produto item : lista_produtos) {
                            System.out.println( item );
                        }
                        System.out.println("");
                        System.out.println("Resumo: ");
                        System.out.println("---------------------------------------------------------------------------------------------------------");
                        System.out.printf("Valor Minimo Produto %.2f  - Valor Maximo Produto %.2f - Valor Médio Produto %.2f:\n ",resumo.getMin(),resumo.getMax(),resumo.getAverage());
                    }
                    System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine();   

                break;

                case "4":
                    System.out.print("Informe o nome do Produto para remover:");
                    nomeProduto = scanner.nextLine();

                    achei = false;
                    buscandoProduto = null;

                    for (Produto item : lista_produtos) {
                        if ( item.getNome().equalsIgnoreCase( nomeProduto ) ) {
                            achei = true;
                            buscandoProduto = item;
                        } 
                    }

                    if (achei) {
                        lista_produtos.remove(buscandoProduto);
                        System.out.println("Produto removido");
                    } else {
                        System.out.println("Produto não encontrado ... ");
                    }
                    
                    System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine();    

                break;

                case "5":
                    System.out.println("#SISTEMA DE VENDAS#"); 
                    System.out.print("Informe o codigo do Produto: ");
                    venda1.setCodigo(scanner.nextInt());
                    System.out.print("Informe o nome do Produto: ");
                    venda1.setNome(scanner.next());
                    System.out.print("Informe a  quantidade do produto: ");
                    venda1.setQuantidade(scanner.nextInt());
                    
                    venda1.setValor(valor);
                    valorTotal = valor*venda1.getQuantidade();
                    venda1.setValorTotal(valorTotal);

                    if(venda1.getQuantidade()> quantidadeProduto){
                        System.out.println("----------------------------------------");

                        System.out.println("Não há quantidade suficiente no estoque.");

                    }else{

                    System.out.println("");

                    achei = false;
                    buscandoProduto = null;
                        for (Produto item : lista_produtos) {
                            if ( item.getNome().equalsIgnoreCase( venda1.getNome() ) ) {
                                achei = true;
                                buscandoProduto = item;
                            } 
                        }

                    if (achei) {
                        
                        System.out.println("Produto encontrado: " + buscandoProduto);
                        
                        try {
                            System.out.print("DIGITE A DATA DA VENDA (dd/MM/yyyy): ");   
                            venda1.setData(scanner.next());
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");                         
                            LocalDate data1LocalDate = LocalDate.parse(venda1.getData(), dtf); 
                            System.out.println("");
                            lista_vendas.add(venda1);
                            System.out.println("Venda realizada com sucesso!!");     

                        } catch (Exception e) {
                            if( e.getMessage().contains("DayOfMonth") ){
                                System.out.println("DIA INCORRETO 1-31");    
                            } else if( e.getMessage().contains("MonthOfYear") ){
                                System.out.println("MES INCORRETO 1-12");   
                            } else{
                                System.out.println("ANO INCORRETO 1-12");  
                            }
                        } 

                    } else {
                        System.out.println("Produto não encontrado ... ");
                    }
                }
                    System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine(); 
                break;

                case "6": 
                    List<Venda> vendas = new ArrayList<>();

                    System.out.print("Informe a data Inicial: ");
                    String dataInicioString = scanner.next();    
                    System.out.print("informe a data Final: ");
                    String dataFimString = scanner.next();   
                    System.out.println("");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");                         
                    LocalDate dataInicioLocalDate = LocalDate.parse(dataInicioString, dtf);
                    LocalDate dataFimLocalDate = LocalDate.parse(dataFimString, dtf);

                    System.out.println("Periodo de  Emissão: "+dataInicioString+" a "+dataFimString);
                    System.out.println("Resumo das Vendas: ");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

                    vendas.stream()
                    .filter(venda -> venda.getDataVenda().compareTo(dataInicioLocalDate) > -1
                                && venda.getDataVenda().compareTo(dataFimLocalDate) < 1)
                    .collect(Collectors.groupingBy(Venda::getDataVenda));


                    for (Venda item: lista_vendas) {
                     System.out.println(item);

                     } 

                break;
                }   
                    
            }while(!opcao.equals("0"));

            scanner.close();
    }
    public static void limparTela(){
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
    }

}
