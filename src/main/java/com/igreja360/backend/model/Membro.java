package com.igreja360.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String cpf;

    private String telefone;

    private LocalDate dataNascimento;

    private String sexo;

    private String estadoCivil;

    @Column(columnDefinition = "TEXT")
    private String endereco;

    private String instagram;

    @Enumerated(EnumType.STRING)
    private TipoCadastro tipoCadastro;

    private Boolean cadastroAprovado;

    private Boolean batizado;

    private LocalDate dataBatismo;

    private LocalDate membroDesde;

    private Boolean voluntario;

    @Column(columnDefinition = "TEXT")
    private String ministeriosVoluntario;

    private Boolean liderCelula;

    private Boolean liderMinisterio;

    @ManyToOne
    @JoinColumn(name = "celula_id")
    private Celula celula;

    @ManyToOne
    @JoinColumn(name = "pai_id")
    private Membro pai;

    @ManyToOne
    @JoinColumn(name = "mae_id")
    private Membro mae;

    @OneToOne
    @JoinColumn(name = "conjuge_id")
    private Membro conjuge;

    @ManyToMany
    @JoinTable(
            name = "membro_filhos",
            joinColumns = @JoinColumn(name = "responsavel_id"),
            inverseJoinColumns = @JoinColumn(name = "filho_id")
    )
    private List<Membro> filhos = new ArrayList<>();

    @OneToOne(mappedBy = "membro")
    private Usuario usuario;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getSexo() { return sexo; }
    public String getEstadoCivil() { return estadoCivil; }
    public String getEndereco() { return endereco; }
    public String getInstagram() { return instagram; }
    public TipoCadastro getTipoCadastro() { return tipoCadastro; }
    public Boolean getCadastroAprovado() { return cadastroAprovado; }
    public Boolean getBatizado() { return batizado; }
    public LocalDate getDataBatismo() { return dataBatismo; }
    public LocalDate getMembroDesde() { return membroDesde; }
    public Boolean getVoluntario() { return voluntario; }
    public String getMinisteriosVoluntario() { return ministeriosVoluntario; }
    public Boolean getLiderCelula() { return liderCelula; }
    public Boolean getLiderMinisterio() { return liderMinisterio; }
    public Celula getCelula() { return celula; }
    public Membro getPai() { return pai; }
    public Membro getMae() { return mae; }
    public Membro getConjuge() { return conjuge; }
    public List<Membro> getFilhos() { return filhos; }
    public Usuario getUsuario() { return usuario; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setInstagram(String instagram) { this.instagram = instagram; }
    public void setTipoCadastro(TipoCadastro tipoCadastro) { this.tipoCadastro = tipoCadastro; }
    public void setCadastroAprovado(Boolean cadastroAprovado) { this.cadastroAprovado = cadastroAprovado; }
    public void setBatizado(Boolean batizado) { this.batizado = batizado; }
    public void setDataBatismo(LocalDate dataBatismo) { this.dataBatismo = dataBatismo; }
    public void setMembroDesde(LocalDate membroDesde) { this.membroDesde = membroDesde; }
    public void setVoluntario(Boolean voluntario) { this.voluntario = voluntario; }
    public void setMinisteriosVoluntario(String ministeriosVoluntario) { this.ministeriosVoluntario = ministeriosVoluntario; }
    public void setLiderCelula(Boolean liderCelula) { this.liderCelula = liderCelula; }
    public void setLiderMinisterio(Boolean liderMinisterio) { this.liderMinisterio = liderMinisterio; }
    public void setCelula(Celula celula) { this.celula = celula; }
    public void setPai(Membro pai) { this.pai = pai; }
    public void setMae(Membro mae) { this.mae = mae; }
    public void setConjuge(Membro conjuge) { this.conjuge = conjuge; }
    public void setFilhos(List<Membro> filhos) { this.filhos = filhos; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}