package banco;

import java.util.*;
import java.text.SimpleDateFormat;

import static banco.Account.solicitarNumeroConta;
import static banco.Account.solicitarSenha;
public class Main {
    private static Account buscarContaPorNumero(ArrayList<Account> contas, int numeroConta) {
        for (Account conta : contas) {
            if (numeroConta == conta.getAccountNumber()) {
                return conta; // Retorna a conta encontrada
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Account> contas = new ArrayList<>();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        boolean contaEncontrada = false;

        contas.add(new Account("s","123", 7500, new Client("jhones", "5555")));
        contas.add(new Account("n", "999", new Client("Maria", "2222")));
        contas.add(new Account("s","888", 7000, new Client("Nikeli", "74444")));
        contas.add(new Account("n", "222", new Client("Deise", "11111")));

        Scanner entrada = new Scanner(System.in);
        int opcao;
        int opc;
        do {
            Date dataHoraAtual = new Date();
            System.out.println();
            System.out.println("Data e hora Atual: " + dataFormatada.format(dataHoraAtual));
            System.out.println("______________SEJA BEM VINDO!_____________");
            System.out.println("___________________AO_____________________");
            System.out.println("__________________BANCO___________________");
            System.out.println("____________SOLIDEZ FINANCEIRA____________");
            System.out.println("Digite 1 - para entrar em sua conta.");
            System.out.println("Digite 2 - para Criar uma conta.");
            System.out.println("Digite 3 - para mostrar todas as contas.");
            System.out.println("Digite 0 - para sair.");
            System.out.print("Opção: ");
            opcao = entrada.nextInt();
            home:

            switch (opcao){
                case 1:
                    int Numeroconta;
                    String pass;
                    do {
                        System.out.println();
                        System.out.println("Digite o numero da sua conta ou 0 para voltar");
                        Numeroconta = solicitarNumeroConta(entrada);
                        System.out.println();
                        Account cont = buscarContaPorNumero(contas, Numeroconta);
                        if(cont != null) {
                            if(Numeroconta == cont.getAccountNumber()){
                                System.out.println("Ola " +  cont.getClient().getName());
                                pass = solicitarSenha(entrada);

                                if (cont.getSenha().equals(pass)){
                                    do {
                                        System.out.println("________________________________");
                                        System.out.println("Olá " + cont.getClient().getName()+ ", Conta: " + cont.getAccountNumber());
                                        System.out.println("Saldo: " + cont.getSaldo() + " ");
                                        if(cont.getLimitChoice().equals("s")){
                                            System.out.println("Seu limite é de: " + cont.getLimite());
                                        }
                                        System.out.println();
                                        System.out.println("Escolha uma das opções abaixo.");
                                        System.out.println("Digite 1 - para Efetuar deposito.");
                                        System.out.println("Digite 2 - para Pagamentos.");
                                        System.out.println("Digite 3 - para Transferencias.");
                                        System.out.println("Digite 4 - para Alterar Dados Da Conta Corrente.");
                                        System.out.println("Digite 5 - para Excluir Conta Corrente.");
                                        System.out.println("Digite 0 - para voltar.");
                                        System.out.print("Opção: ");
                                        opc = entrada.nextInt();
                                        System.out.println();

                                        switch (opc){
                                            case 1:
                                                System.out.println("Digite o valor que deseja depositar ex: 100,50");
                                                System.out.print("Valor: ");
                                                float value = entrada.nextFloat();
                                                cont.deposito(value);
                                                break;
                                            case 2:
                                                System.out.println("Informe o valor do boleto");
                                                System.out.print("Valor: ");
                                                float ticket = entrada.nextFloat();
                                                cont.pagamento(ticket);
                                                break;
                                            case 3:
                                                System.out.println("Qual valor a ser Transferido?");
                                                System.out.print("Valor: ");
                                                float valorParaTranferencia = entrada.nextFloat();
                                                System.out.println("Para qual conta deseja transferir?");
                                                System.out.print("Digite o numero da conta: ");
                                                int transferAccountNumber = entrada.nextInt();
                                                Account contaDestino = null;
                                                Account conts = buscarContaPorNumero(contas, transferAccountNumber);
                                                if(conts != null){
                                                    if (transferAccountNumber == conts.getAccountNumber()){
                                                        System.out.println("___________________________________");
                                                        System.out.println("CONFIRME AS INFORMAÇOES ABAIXO!!!");
                                                        System.out.println("___________________________________");
                                                        System.out.println("Voce deseja transferir: " + valorParaTranferencia);
                                                        System.out.println("Para conta de: " + conts.getClient().getName());
                                                        System.out.println("____________________________________");
                                                        System.out.print("Confirma? S para sim ou N para nao: ");
                                                        String confirm = entrada.next().toLowerCase();
                                                        if(confirm.equals("s")){
                                                            System.out.println("Digite a sua senha para confirmar a transação.");
                                                            String passConfirm = solicitarSenha(entrada);
                                                            if (Objects.equals(passConfirm, cont.getSenha())){
                                                                if(cont.getLimitChoice().equals("s")){
                                                                    if(valorParaTranferencia <= cont.getLimite()){
                                                                        contaDestino = conts;
                                                                        cont.transfer(valorParaTranferencia, contaDestino);
                                                                        break;
                                                                    } else {
                                                                        System.out.println("________________________________________________");
                                                                        System.out.println("OPs!");
                                                                        System.out.println("Valor da transferencia esta acima do seu limite.");
                                                                    }
                                                                } else {
                                                                    contaDestino = conts;
                                                                    cont.transfer(valorParaTranferencia, contaDestino);
                                                                    break;
                                                                }

                                                            } else {
                                                                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
                                                                System.out.println("Senha invalida!");
                                                                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                                    System.out.println("Conta de destino não encontrada.");
                                                }
                                                break;
                                            case 4:
                                                System.out.println("Deseja altetar seu Nome ou o Limite?");
                                                System.out.print("Digte N ou L: ");
                                                String op = entrada.next().toLowerCase();
                                                if(op.equals("n")){
                                                    System.out.println("Insira seu novo nome!");
                                                    System.out.print("Nome: ");
                                                    String newName = entrada.next();
                                                    cont.getClient().setName(newName);
                                                } else if (op.equals("l")){
                                                    if(cont.getLimitChoice().equals("n")){
                                                        System.out.println("Sua conta esta com limite desativado, deseja ativar ?");
                                                        System.out.print("S para sim N para nao: ");
                                                        String lim = entrada.next().toLowerCase();
                                                        if(lim.equals("s")){
                                                            cont.setLimitChoice("s");
                                                            System.out.println("Opção de limite ativado.");
                                                            System.out.println("Agora defina um valor para o limite");
                                                            System.out.print("Limite: ");
                                                            float limit = entrada.nextFloat();
                                                            cont.setLimite(limit);
                                                            System.out.println("Limite Definido com sucesso!!!");
                                                        }
                                                    } else {
                                                        System.out.println("Defina seu novo limite.");
                                                        System.out.print("Limite: ");
                                                        float newLimit = entrada.nextFloat();
                                                        cont.setLimite(newLimit);
                                                    }
                                                }
                                                break;
                                            case 5:
                                                System.out.println("Deseja excluir a sua conta?");
                                                System.out.print("Digite S ou N: ");
                                                String opp = entrada.next().toLowerCase();
                                                if (opp.equals("s")){
                                                    System.out.println("Digite a sua senha para confirmar a exclusão.");
                                                    String passWord = solicitarSenha(entrada);

                                                    Iterator<Account> iterator = contas.iterator();
                                                    while (iterator.hasNext()){
                                                        Account acc = iterator.next();
                                                        if(passWord.equals(acc.getSenha())){
                                                            iterator.remove();
                                                            Account.menos();
                                                            System.out.println("###_Conta excluída com sucesso_###");
                                                            break home;
                                                        }
                                                    }
                                                }
                                                break;
                                            case 0:
                                                break home;
                                            default:
                                                System.out.println("Opção inválida. Tente novamente.");
                                        }
                                    } while (true);

                                } else {
                                    System.out.println("Senha invalida!!!");
                                    break;
                                }
                            } else if(Numeroconta == 0){
                                System.out.println("Volte sempre!");
                                break;
                            }
                        } else {
                            System.out.println();
                            System.out.println("Conta não encontrada!");
                            System.out.println();
                        }
                    }while (Numeroconta!=0);
                    break;
                case 2:
                    String nome;
                    String cpf;
                    float limite = 0;
                    String escolha;
                    String senha;
                    do {
                        System.out.println();
                        System.out.println("Olá vamos criar uma conta para Você.");
                        System.out.println("Digite Seu nome ou 0 para voltar");
                        System.out.print("Nome: ");
                        nome = entrada.next();
                        if (nome.equals("0")) {
                            break;
                        }
                        System.out.println("Digite Seu CPF");
                        System.out.print("CPF: ");
                        cpf = entrada.next();

                        while (true) {
                            System.out.println("Você deseja definir um limite?");
                            System.out.print("S para sim ou N para não: ");
                            escolha = entrada.next().toLowerCase();
                            if(escolha.equals("s")){
                                System.out.println("Defina um limite ex: 10000");
                                System.out.print("Limite: ");
                                limite = entrada.nextFloat();
                                break;
                            } else if(escolha.equals("n")){
                                break;
                            } else {
                                System.out.println("Escolha inválida. Digite 's' para sim ou 'n' para não.");
                            }
                        }

                        System.out.println("Agora vamos Definir uma senha, digite uma senha");
                        System.out.println("pode conter numeros e letras");
                        System.out.print("Senha: ");
                        senha = entrada.next();

                        if(escolha.equals("s")){
                            contas.add(new Account(escolha,senha, limite, new Client(nome, cpf)));
                        } else {
                            contas.add(new Account(escolha, senha, new Client(nome, cpf)));
                        }

                        System.out.println();
                        System.out.println("Parabéns conta criada com sucesso!!!");
                        for (Account cont:contas) {
                            if(cpf.equals(cont.getClient().getCPF())){
                                System.out.println(cont.getClient().getName() + ", anote o numero da sua conta");
                                System.out.println("Numero da conta: " + cont.getAccountNumber());
                            }
                        }
                        System.out.println();
                        break;

                    }while (true);
                    break;
                case 3:
                    for (Account cont:contas) {
                        System.out.println();
                        System.out.println("Número da conta: " + cont.getAccountNumber());
                        System.out.println("Saldo da conta: " + cont.getSaldo());
                        if(cont.getLimitChoice().equals("s")){
                            System.out.println("Limite da conta: " + cont.getLimite());
                        }
                        System.out.println("Nome do cliente: " + cont.getClient().getName());
                        System.out.println("CPF do cliente: " + cont.getClient().getCPF());
                        System.out.println();
                    }
                    System.out.println("****************************");
                    System.out.println("Total de correntistas: " + Account.getTotalAccounts());
                    System.out.println("****************************");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }while (opcao!=0);
        System.out.println("Volte Sempre !!!");
    }
}