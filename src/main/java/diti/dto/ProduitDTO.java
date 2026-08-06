package diti.dto;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProduitDTO {

    private Long id;

    @NotBlank(message = "Le libelle est obligatoire")
    @Size(min = 2, max = 100, message = "Le libelle doit contenir entre {min} et {max} caracteres")
    private String libelle;

    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit etre superieur a 0")
    private double prix;

    private Long typeProduitId;

    private String typeProduitLibelle;

    public ProduitDTO() {
    }

    public ProduitDTO(Long id, String libelle, double prix, Long typeProduitId, String typeProduitLibelle) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.typeProduitId = typeProduitId;
        this.typeProduitLibelle = typeProduitLibelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Long getTypeProduitId() {
        return typeProduitId;
    }

    public void setTypeProduitId(Long typeProduitId) {
        this.typeProduitId = typeProduitId;
    }

    public String getTypeProduitLibelle() {
        return typeProduitLibelle;
    }

    public void setTypeProduitLibelle(String typeProduitLibelle) {
        this.typeProduitLibelle = typeProduitLibelle;
    }

    @Override
    public String toString() {
        return "ProduitDTO{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", prix=" + prix +
                ", typeProduitId=" + typeProduitId +
                '}';
    }
}
