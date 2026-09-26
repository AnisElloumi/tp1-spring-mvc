// src/test/java/org/iset/application/service/ProduitServiceTest.java
package org.iset.application.service;

import org.iset.application.model.Produit;
import org.iset.application.repository.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitService produitService;

    @Test
    void findAll_retourneLaListeDuRepository() {
        Produit p1 = new Produit("Clavier", 45.0, 20);
        Produit p2 = new Produit("Souris", 15.0, 50);
        when(produitRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Produit> resultat = produitService.findAll();

        assertEquals(2, resultat.size());
        assertEquals("Clavier", resultat.get(0).getLibelle());
        verify(produitRepository, times(1)).findAll();
    }

    @Test
    void save_delegueAuRepositoryEtRetourneLeProduitSauvegarde() {
        Produit produit = new Produit("Ecran", 199.0, 10);
        when(produitRepository.save(produit)).thenReturn(produit);

        Produit resultat = produitService.save(produit);

        assertNotNull(resultat);
        assertEquals("Ecran", resultat.getLibelle());
        verify(produitRepository, times(1)).save(produit);
    }

    @Test
    void findById_produitExistant_retourneLeProduit() {
        Produit produit = new Produit("Clavier", 45.0, 20);
        produit.setId(1L);
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));

        Produit resultat = produitService.findById(1L);

        assertNotNull(resultat);
        assertEquals(1L, resultat.getId());
    }

    @Test
    void findById_produitInexistant_retourneNull() {
        when(produitRepository.findById(999L)).thenReturn(Optional.empty());

        Produit resultat = produitService.findById(999L);

        assertNull(resultat);
    }

    @Test
    void delete_appelleDeleteByIdDuRepository() {
        produitService.delete(1L);

        verify(produitRepository, times(1)).deleteById(1L);
    }
}
