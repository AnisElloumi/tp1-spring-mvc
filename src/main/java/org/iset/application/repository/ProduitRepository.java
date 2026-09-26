// src/main/java/org/iset/application/repository/ProduitRepository.java
package org.iset.application.repository;

import org.iset.application.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
}