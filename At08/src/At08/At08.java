package At08;
public class At08 {

    public static boolean cyk(String palavra, String[] gramatica, String simboloInicial) {
        int n = palavra.length();

        // Cria a matriz CYK
        String[][] cyk = new String[n][n];

        // Inicializa a matriz CYK
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cyk[i][j] = "";
            }
        }     
       
        // Preenche a diagonal principal da matriz CYK com os terminais correspondentes
        for (int i = 0; i < n; i++) {
            char c = palavra.charAt(i);
            for (String regra : gramatica) {
                String[] partes = regra.split("->");
                if (partes[1].equals(String.valueOf(c))) {
                    cyk[i][i] = partes[0];
                }
            }
        }
        
        // Preenche as células da matriz CYK
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                for (int k = i; k <= j - 1; k++) {
                    for (String regra : gramatica) {
                        String[] partes = regra.split("->");
                        if (!cyk[i][k].isEmpty() && !cyk[k + 1][j].isEmpty() && partes[1].equals(cyk[i][k] + cyk[k + 1][j])) {
                            cyk[i][j] = partes[0];
                        }
                    }
                }
            }
        }

        // Verifica se o símbolo inicial está na célula da matriz CYK correspondente à palavra completa
        return cyk[0][n - 1].equals(simboloInicial);
    }

    public static void main(String[] args) {
        String palavra = "aaaaaabbbbbb";

        boolean vai = true;

        if (palavra.length() <= 1)
            vai = false;

        if (vai) {

            String[] gramatica = new String[5];
            gramatica[0] = "S->CB";
            gramatica[1] = "S->AB";
            gramatica[2] = "A->a";
            gramatica[3] = "B->b";
            gramatica[4] = "C->AS";

            String simboloInicial = "S";

            boolean pertence = cyk(palavra, gramatica, simboloInicial);

            if (pertence) {
                System.out.println("A palavra pertence à linguagem gerada pelas regras.");
            } else {
                System.out.println("A palavra não pertence à linguagem gerada pelas regras.");
            }

        } else {
            System.out.println("A cadeia não é aceita.");
        }
    }
}