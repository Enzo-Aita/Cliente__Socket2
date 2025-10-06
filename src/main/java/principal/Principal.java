package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Principal {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Digite o IP do servidor: ");
            String ip = sc.nextLine();

            System.out.print("Digite a porta do servidor: ");
            int porta = sc.nextInt();
            sc.nextLine();

            Socket servidor = new Socket(ip, porta);
            PrintWriter out = new PrintWriter(servidor.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(servidor.getInputStream()));

            String mensagem = "";
            while (!mensagem.equalsIgnoreCase("fim")) {
                System.out.print("Digite a mensagem (ex: C;A1;1 ou M;A1;1) ou 'fim' para sair: ");
                mensagem = sc.nextLine();

                if (mensagem.equalsIgnoreCase("fim")) break;

                out.println(mensagem);
                String resposta = in.readLine();

                switch (resposta) {
                    case "0":
                        System.out.println("Resposta: Voo disponível.");
                        break;
                    case "1":
                        System.out.println("Resposta: Assento indisponível.");
                        break;
                    case "2":
                        System.out.println("Resposta: Assento inexistente.");
                        break;
                    case "3":
                        System.out.println("Resposta: Voo inexistente.");
                        break;
                    case "4":
                        System.out.println("Resposta: Marcação realizada.");
                        break;
                    default:
                        System.out.println("Resposta: " + resposta);
                        break;
                }
            }

            servidor.close();
        } catch (IOException io) {
            System.err.println("Problemas de IO: " + io.getMessage());
        }
    }
}
