package br.assing.iniflex;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Principal {
    private List<Funcionario> funcionarios;
    private DateTimeFormatter dateFormatter;
    private NumberFormat numberFormat;

    public Principal() {
        funcionarios = new ArrayList<Funcionario>();
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        numberFormat = new DecimalFormat("#,##0.00");
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.executar();
    }

    public void executar() {
        // 3.1 – Inserir todos os funcionários
        inserirFuncionarios();

        // 3.2 – Remover o funcionário “João” da lista
        removerFuncionarioPorNome("João");

        // 3.3 – Imprimir todos os funcionários com todas suas informações
        System.out.println("===== 3.3 – Lista de Funcionários =====");
        imprimirFuncionarios();

        // 3.4 – Aumento de salário de 10%
        aplicarAumentoSalarial(10);

        // 3.5 – Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparFuncionariosPorFuncao();

        // 3.6 – Imprimir os funcionários, agrupados por função
        System.out.println("\n===== 3.6 – Funcionários Agrupados por Função =====");
        imprimirFuncionariosAgrupadosPorFuncao(funcionariosPorFuncao);

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\n===== 3.8 – Aniversariantes dos Meses 10 e 12 =====");
        imprimirAniversariantesDosMeses10e12();

        // 3.9 – Imprimir o funcionário com a maior idade
        System.out.println("\n===== 3.9 – Funcionário Mais Velho =====");
        imprimirFuncionarioMaisVelho();

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\n===== 3.10 – Funcionários em Ordem Alfabética =====");
        imprimirFuncionariosOrdenadosPorNome();

        // 3.11 – Imprimir o total dos salários dos funcionários
        System.out.println("\n===== 3.11 – Total dos Salários =====");
        imprimirTotalSalarios();

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("\n===== 3.12 – Salários em Salários Mínimos =====");
        imprimirSalariosEmSalariosMinimos(new BigDecimal("1212.00"));
    }

    // 3.1 – Inserir todos os funcionários
    private void inserirFuncionarios() {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }

    // 3.2 – Remover o funcionário por nome
    private void removerFuncionarioPorNome(String nome) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().equals(nome)) {
                funcionarios.remove(funcionario);
                break;
            }
        }
    }

    // 3.3 – Imprimir todos os funcionários com todas suas informações
    private void imprimirFuncionarios() {
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome() +
                    ", Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter) +
                    ", Salário: " + numberFormat.format(funcionario.getSalario()) +
                    ", Função: " + funcionario.getFuncao());
        }
    }

    // 3.4 – Aplicar aumento salarial
    private void aplicarAumentoSalarial(double percentual) {
        BigDecimal aumento = new BigDecimal(percentual / 100);
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.ONE.add(aumento));
            funcionario.setSalario(novoSalario);
        }
    }

    // 3.5 – Agrupar os funcionários por função em um MAP
    private Map<String, List<Funcionario>> agruparFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<String, List<Funcionario>>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<Funcionario>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }
        return funcionariosPorFuncao;
    }

    // 3.6 – Imprimir os funcionários, agrupados por função
    private void imprimirFuncionariosAgrupadosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("  Nome: " + funcionario.getNome() +
                        ", Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter) +
                        ", Salário: " + numberFormat.format(funcionario.getSalario())+"\n");
            }
        }
    }

    // 3.8 – Imprimir os funcionários que fazem aniversário nos meses especificados
    private void imprimirAniversariantesDosMeses10e12() {
        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.println("Nome: " + funcionario.getNome() +
                        ", Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter) +
                        ", Salário: " + numberFormat.format(funcionario.getSalario()) +
                        ", Função: " + funcionario.getFuncao());
                break;
            }
        }
    }

    // 3.9 – Imprimir o funcionário com a maior idade
    private void imprimirFuncionarioMaisVelho() {
        Funcionario maisVelho = funcionarios.get(0);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                maisVelho = funcionario;
            }
        }
        long idade = ChronoUnit.YEARS.between(maisVelho.getDataNascimento(), LocalDate.now());
        System.out.println("Nome: " + maisVelho.getNome() +
                ", Data de Nascimento: " + maisVelho.getDataNascimento().format(dateFormatter) +
                ", Idade: " + idade + " anos");
    }

    // 3.10 – Imprimir a lista de funcionários por ordem alfabética
    private void imprimirFuncionariosOrdenadosPorNome() {
        Collections.sort(funcionarios, new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario f1, Funcionario f2) {
                return f1.getNome().compareTo(f2.getNome());
            }
        });
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome() +
                    ", Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter) +
                    ", Salário: " + numberFormat.format(funcionario.getSalario()) +
                    ", Função: " + funcionario.getFuncao());
        }
    }

    // 3.11 – Imprimir o total dos salários dos funcionários
    private void imprimirTotalSalarios() {
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("Total dos Salários: " + numberFormat.format(totalSalarios));
    }

    // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
    private void imprimirSalariosEmSalariosMinimos(BigDecimal salarioMinimo) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.DOWN);
            System.out.println("Nome: " + funcionario.getNome() +
                    ", Salário: " + numberFormat.format(funcionario.getSalario()) +
                    ", Salários Mínimos: " + numberFormat.format(salariosMinimos));
        }
    }
}