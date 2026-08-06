package diti.service.impl;


import diti.entity.Produit;
import diti.exception.ResourceNotFoundException;
import diti.repository.ProductRepository;
import diti.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository ;

    @Override
    public void save(Produit product) {
        repository.save(product);
    }

    @Override
    public List<Produit> findAll() {
        return repository.findAll();
    }

    @Override
    public Produit findById(Long id) {
        Produit produit = repository.findById(id);

        if (produit == null) {
            throw new ResourceNotFoundException("Produit introuvable avec l'id " + id);
        }

        return produit;
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}