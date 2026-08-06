package diti.mapper;


import diti.dto.ProduitDTO;
import diti.entity.Produit;
import diti.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapper {

    @Autowired
    private TypeProduitService typeProduitService;

    public ProduitDTO toDto(Produit produit) {
        if (produit == null) {
            return null;
        }

        ProduitDTO dto = new ProduitDTO();
        dto.setId(produit.getId());
        dto.setLibelle(produit.getLibelle());
        dto.setPrix(produit.getPrix());

        if (produit.getTypeProduit() != null) {
            dto.setTypeProduitId(produit.getTypeProduit().getId());
            dto.setTypeProduitLibelle(produit.getTypeProduit().getLibelle());
        }

        return dto;
    }

    public Produit toEntity(ProduitDTO dto) {
        if (dto == null) {
            return null;
        }

        Produit produit = new Produit();
        produit.setId(dto.getId());
        produit.setLibelle(dto.getLibelle());
        produit.setPrix(dto.getPrix());

        if (dto.getTypeProduitId() != null) {
            produit.setTypeProduit(typeProduitService.findById(dto.getTypeProduitId()));
        }

        return produit;
    }
}
