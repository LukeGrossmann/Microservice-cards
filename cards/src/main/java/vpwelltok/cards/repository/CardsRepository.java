package vpwelltok.cards.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vpwelltok.cards.model.Cards;

@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {


    List<Cards> findByCustomerId(int customerId);

}

