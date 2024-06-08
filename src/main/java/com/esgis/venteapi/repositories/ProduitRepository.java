package com.esgis.venteapi.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.esgis.venteapi.models.Produit;

public interface ProduitRepository extends MongoRepository<Produit, String> {
  // @Query("SELECT p FROM Product p WHERE p.storeId = :storeId")
  // @Query("SELECT p FROM Product p WHERE p.nomProduit LIKE '%:nomProduit%'")
  // List<Produit> findByAllByProductNameLike(@Param("nomProduit") String productName);
  
  List<Produit> findByNomProduit(String nomProduit); 

  List<Produit> findByStoreId(String storeId);

  List<Produit> findByCategoryId(String categoryId);

}
