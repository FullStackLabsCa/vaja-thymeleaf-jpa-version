package thymeleafsandbox.stsm.business;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import thymeleafsandbox.stsm.business.entities.Feature;
import thymeleafsandbox.stsm.business.entities.SeedStarter;
import thymeleafsandbox.stsm.business.entities.repositories.SeedStarterRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SeedStarterTestForRepository {

    @Autowired
    SeedStarterRepository seedStarterRepository;

    List<SeedStarter> seedStarters = new ArrayList<>();

    @BeforeEach
    void prepareForTest(){
        Collection<Feature> features =new ArrayList<>();
        features.add(Feature.FERTILIZER);
        SeedStarter seedStarter = SeedStarter.builder().covered(true).features(features).build();
        seedStarters.add(seedStarter);
        seedStarterRepository.save(seedStarter);
        Collection<Feature> features1 =new ArrayList<>();
        features.add(Feature.FERTILIZER);
        SeedStarter seedStarter1 = SeedStarter.builder().covered(true).features(features1).build();
        seedStarters.add(seedStarter1);
        seedStarterRepository.save(seedStarter1);
    }

    @Test
    void testSaveSeedStarter(){
        Collection<Feature> features =new ArrayList<>();
        features.add(Feature.FERTILIZER);
        SeedStarter seedStarter = SeedStarter.builder().covered(true).features(features).build();
        SeedStarter save = seedStarterRepository.save(seedStarter);
        Assertions.assertThat(seedStarterRepository.findById(save.getId())).isEqualTo(Optional.of(seedStarter));
    }

    @Test
    void findAllSeedStarter(){
        List<SeedStarter> all = seedStarterRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
    }
}
