package fr.itspower.tp2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@Transactional
@RestController
@RequestMapping("/user")
public class TrucController {

    @PersistenceContext
    private EntityManager entityManager;

    
}
