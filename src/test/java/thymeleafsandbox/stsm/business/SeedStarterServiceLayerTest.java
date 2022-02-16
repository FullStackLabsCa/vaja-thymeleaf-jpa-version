package thymeleafsandbox.stsm.business;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import thymeleafsandbox.stsm.business.entities.Feature;
import thymeleafsandbox.stsm.business.entities.SeedStarter;
import thymeleafsandbox.stsm.business.entities.repositories.SeedStarterRepository;
import thymeleafsandbox.stsm.business.services.SeedStarterService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest
public class SeedStarterServiceLayerTest {

    @Autowired
    SeedStarterService seedStarterService;

    @MockBean
    SeedStarterRepository seedStarterRepository;

    @Test
    public void testSaveMethod(){

        Collection<Feature> features =new ArrayList<>();
        features.add(Feature.FERTILIZER);
        SeedStarter seedStarter = SeedStarter.builder().id(1).covered(true).features(features).build();
        BDDMockito.given(seedStarterRepository.save(seedStarter)).willReturn(seedStarter);
        SeedStarter add = seedStarterService.add(seedStarter);
        Assertions.assertThat(seedStarter).isEqualTo(add);

    }

    @Test
    public void testForFindAll(){

        Collection<Feature> features = new ArrayList<>();
        features.add(Feature.SEEDSTARTER_SPECIFIC_SUBSTRATE);
        SeedStarter seedStarter = SeedStarter.builder().covered(true).id(1).features(features).build();
        List<SeedStarter> seedStarters = new ArrayList<>();
        seedStarters.add(seedStarter);
        BDDMockito.given(seedStarterRepository.findAll()).willReturn( seedStarters);
        List<SeedStarter> all = seedStarterService.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);

    }
}
