package vpwelltok.cards.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vpwelltok.cards.config.CardsServiceConfig;
import vpwelltok.cards.model.Properties;
import vpwelltok.cards.model.Cards;
import vpwelltok.cards.model.Customer;
import vpwelltok.cards.repository.CardsRepository;



@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    CardsServiceConfig cardsConfig;

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestHeader("vpwelltok-correlation-id") String correlationid, @RequestBody Customer customer) {
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        if (cards != null) {
            return cards;
        } else {
            return null;
        }

    }

    @GetMapping("/cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
                cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

}

