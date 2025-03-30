import model.Adress;
import service.SearchCep;
import util.FileGenerator;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Digite o CEP para buscar o endereço: ");
            String usercep = scanner.nextLine();

            try {
                SearchCep searchCep = new SearchCep();
                Adress newAdress = searchCep.findAdress(usercep);
                FileGenerator generator = new FileGenerator();
                generator.jsonGenerator(newAdress);
                System.out.println("Endereço salvo com sucesso!");
                break; // Sai do loop apenas se o CEP for válido
            } catch (IOException | InterruptedException e) {
                System.out.println("Erro ao buscar o endereço: " + e.getMessage());
                System.out.println("Tente novamente com um CEP válido.\n");
            }
        }
        scanner.close();  // Fechar o scanner após o uso
    }
}