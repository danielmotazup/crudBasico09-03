package br.com.zup.edu.funcionariosmanager.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    @Column(nullable = false)
    private BigDecimal salario;

    public Funcionario(String nome, String cpf, String endereco, LocalDate dataAdmissao, BigDecimal salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Funcionario() {
    }

    public Long getId() {
        return id;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void atualizaSalario() throws ResponseStatusException {

        if (validaTempoDeCasa()){
            this.salario = salario.multiply(new BigDecimal("1.1"));
            return;
        }

        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY," funcionário com menos de 1 ano de casa");

    }


    public boolean validaTempoDeCasa(){
        LocalDate dataAtual = LocalDate.now();
        return this.dataAdmissao.isBefore(dataAtual.minusDays(365));


    }
}
