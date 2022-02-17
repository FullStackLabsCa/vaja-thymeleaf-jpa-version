package thymeleafsandbox.stsm.business.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import thymeleafsandbox.stsm.business.entities.repositories.SeedStarterRepository;
import thymeleafsandbox.stsm.business.services.SeedStarterService;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SeedStarterServiceLayerTest {

    @Autowired
    SeedStarterService seedStarterService;

    @MockBean
    SeedStarterRepository seedStarterRepository;

    @Test
    public void testSaveMethod(){

        List<Feature> features =new ArrayList<>();
        features.add(Feature.FERTILIZER);
        Feature[] featuresArray = new Feature[features.size()];
        SeedStarter seedStarter = SeedStarter.builder().id(1).covered(true).features(features.toArray(featuresArray)).build();
        BDDMockito.given(seedStarterRepository.save(seedStarter)).willReturn(seedStarter);
        SeedStarter add = seedStarterService.add(seedStarter);
        Assertions.assertThat(seedStarter).isEqualTo(add);

    }

    @Test
    public void testForFindAll(){

        List<SeedStarter> seedStarters = new ArrayList<>() ;
        List<Feature> features =new ArrayList<>();
        features.add(Feature.FERTILIZER);
        Feature[] featuresArray = new Feature[features.size()];
        SeedStarter seedStarter = SeedStarter.builder().id(1).covered(true).features(features.toArray(featuresArray)).build();
        seedStarters.add(seedStarter);
        BDDMockito.given(seedStarterRepository.findAllSeedStarter()).willReturn(seedStarters);
        Assertions.assertThat(seedStarterRepository.findAllSeedStarter().size()).isEqualTo(1);

    }
}
