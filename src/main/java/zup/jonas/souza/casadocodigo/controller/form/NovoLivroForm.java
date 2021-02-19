package zup.jonas.souza.casadocodigo.controller.form;

import zup.jonas.souza.casadocodigo.exception.NotFoundException;
import zup.jonas.souza.casadocodigo.model.Autor;
import zup.jonas.souza.casadocodigo.model.Categoria;
import zup.jonas.souza.casadocodigo.model.Livro;
import zup.jonas.souza.casadocodigo.repository.AutorRepository;
import zup.jonas.souza.casadocodigo.repository.CategoriaRepository;
import zup.jonas.souza.casadocodigo.validation.annotation.Exists;
import zup.jonas.souza.casadocodigo.validation.annotation.Unique;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroForm {

    @NotBlank
    @Unique(modelClass = Livro.class, field = "titulo")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @DecimalMin("20")
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroPaginas;

    @NotBlank
    @Unique(modelClass = Livro.class, field = "isbn")
    private String isbn;

    @Future
    private LocalDate dataPublicacao;

    @NotNull
    @Exists(modelClass = Categoria.class, field = "id")
    private Long categoriaId;

    @NotNull
    @Exists(modelClass = Autor.class, field = "id")
    private Long autorId;

    public NovoLivroForm(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario, @NotNull @NotEmpty @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numeroPaginas, @NotBlank String isbn, @Future LocalDate dataPublicacao, @NotNull Long categoriaId, @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public Livro toModel(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        var autor = autorRepository.findById(autorId).orElseThrow(() -> new NotFoundException(autorId));
        var categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new NotFoundException(categoriaId));
        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
    }
}
