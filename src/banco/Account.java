package banco;
import java.util.Random;
import java.util.Scanner;

public class Account {
    private static final Random random = new Random();

    public Account(String limitChoice,String senha, float limite, Client client) {
        this.accountNumber = random.nextInt(1000) + 1;
        this.saldo = 0;
        this.limite = limite;
        this.client = client;
        this.limitChoice = limitChoice;
        this.senha = senha;
        totalAccounts += 1;
    }

    public static void menos(){
        totalAccounts-=1;
    }

    public Account(String limitChoice,String senha,Client client) {
        super();
        this.accountNumber = random.nextInt(1000) + 1;
        this.saldo = 0;
        this.client = client;
        this.limitChoice = limitChoice;
        this.senha = senha;
        Account.totalAccounts += 1;
    }

    public void deposito (float value){
        if(value > 0){
            saldo += value;
            System.out.println("Depósito de " + value + " realizado com sucesso!");
            System.out.println();
        } else {
            System.out.println("Valor de depósito inválido.");
            System.out.println();
        }
    }

    public void pagamento(float value){
        if (value>0){
            if(value <= saldo){
                if(limitChoice.equals("s")){
                    if(value <= limite){
                        saldo -= value;
                        System.out.println("__________________________________________________________");
                        System.out.println("Pagamento no valor de " + value + " realizado com sucesso!");
                        System.out.println();
                    } else {
                        System.out.println("______________________________________");
                        System.out.println("Seu limite para movimentações é de: " + getLimite());
                        System.out.println();
                    }
                } else {
                    saldo -= value;
                    System.out.println("__________________________________________________________");
                    System.out.println("Pagamento no valor de " + value + " realizado com sucesso!");
                }
            } else {
                System.out.println("_______________________");
                System.out.println("Saldo insuficiente!!!");
                System.out.println();
            }
        } else {
            System.out.println("__________________________");
            System.out.println("Valor a ser pago inválido.");
            System.out.println();
        }
    }

    public void transfer(float valor, Account contaDestino){
        if(valor > 0 && saldo >= valor){
            saldo -= valor;
            contaDestino.saldo += valor;
            System.out.println("_______________________________________________________________________");
            System.out.println("Transferência de " + valor + " realizada com sucesso para a conta de: " + contaDestino.getClient().getName());
        } else {
            System.out.println("______________________________________________________________________________");
            System.out.println("Transferência não pode ser concluída. Verifique o saldo ou o valor informado.");
        }
    }

    static String solicitarSenha(Scanner scanner) {
        System.out.print("Digite a senha: ");
        return scanner.next();
    }

    static int solicitarNumeroConta(Scanner scanner) {
        System.out.print("Conta: ");
        return scanner.nextInt();
    }

    private int accountNumber;
    private float saldo;
    private float limite;

    private String senha;

    private Client client;

    private String limitChoice;

    private static int totalAccounts;

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public String getLimitChoice() {
        return limitChoice;
    }

    public void setLimitChoice(String limitChoice) {
        this.limitChoice = limitChoice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
