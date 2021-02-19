package zup.jonas.souza.casadocodigo.controller.form;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;
import zup.jonas.souza.casadocodigo.exception.NotFoundException;
import zup.jonas.souza.casadocodigo.model.Cliente;
import zup.jonas.souza.casadocodigo.model.Estado;
import zup.jonas.souza.casadocodigo.model.Pais;
import zup.jonas.souza.casadocodigo.model.TipoPessoa;
import zup.jonas.souza.casadocodigo.repository.EstadoRepository;
import zup.jonas.souza.casadocodigo.repository.PaisRepository;
import zup.jonas.souza.casadocodigo.validation.annotation.Exists;
import zup.jonas.souza.casadocodigo.validation.annotation.Unique;
import zup.jonas.souza.casadocodigo.validation.interfaces.CnpjGroup;
import zup.jonas.souza.casadocodigo.validation.interfaces.CpfGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class NovoClienteForm {

    @Email
    @Unique(modelClass = Cliente.class, field = "email")
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @CPF(groups = CpfGroup.class)
    @CNPJ(groups = CnpjGroup.class)
    @Unique(modelClass = Cliente.class, field = "documento")
    private String documento;

    @NotNull
    private TipoPessoa tipoPessoa;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @Exists(modelClass = Pais.class, field = "id")
    private Integer paisId;

    @Exists(modelClass = Estado.class, field = "id")
    private Long estadoId;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    public NovoClienteForm(@Email String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank @CPF(groups = CpfGroup.class) @CNPJ(groups = CnpjGroup.class) String documento, @NotNull TipoPessoa tipoPessoa, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade, @NotNull Integer paisId, Long estadoId, @NotBlank String telefone, @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.tipoPessoa = tipoPessoa;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public Cliente toModel(PaisRepository paisRepository, EstadoRepository estadoRepository) {
        var pais = paisRepository.findById(paisId).orElseThrow(() -> new NotFoundException(paisId));
        var estado = estadoRepository.findById(estadoId).orElseThrow(() -> new NotFoundException(estadoId));
        return new Cliente(email, nome, sobrenome, documento, tipoPessoa, endereco, complemento, cidade, pais, estado, telefone, cep);
    }
}
