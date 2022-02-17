package thymeleafsandbox.stsm.business.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import thymeleafsandbox.stsm.business.entities.Variety;
import thymeleafsandbox.stsm.business.entities.repositories.VarietyRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class VarietyRepositoryTest {

    @Autowired
    VarietyRepository varietyRepository;
    Variety save;

    @BeforeEach
    void prepareDataForTest(){
         save = varietyRepository.save(Variety.builder().name("Thymus vulgarisd").build());
         Variety save1 = varietyRepository.save(Variety.builder().name("Thymus x citriodorusg").build());
    }

    @Test
    void testFindById(){
        Optional<Variety> variety = varietyRepository.findById(save.getId());
        Assertions.assertEquals(true,variety.isPresent());
    }

    @Test
    void testFindAll(){
        List<Variety> repositoryAll = varietyRepository.findAll();
        Assertions.assertEquals(7,repositoryAll.size());
    }
}














