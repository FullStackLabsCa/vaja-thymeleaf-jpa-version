package thymeleafsandbox.stsm.business.entities;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import thymeleafsandbox.stsm.business.entities.repositories.SeedStarterRepository;
import thymeleafsandbox.stsm.business.entities.repositories.VarietyRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class SeedStarterRepositoryTest {


    @Autowired
    SeedStarterRepository seedStarterRepository;

    @Autowired
    VarietyRepository varietyRepository;

    @SneakyThrows
    @Test
    public void testSaveMethod()  {

        List<Feature> features =new ArrayList<>();
        features.add(Feature.FERTILIZER);
        Feature[] featuresArray = new Feature[features.size()];
        Row row = Row.builder().seedsPerCell(12).variety(varietyRepository.findById(2)).build();
        List<Row> rows = new ArrayList<>();
        rows.add(row);
        String date1 = "Thu Feb 17 00:00:00 PST 2022";
        Date date= new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US).parse(date1);
        Type type = Type.PLASTIC;
        SeedStarter seedStarter = SeedStarter.builder().covered(true)
                .type(type).datePlanted(date).features(features.toArray(featuresArray)).rows(rows).build();
        SeedStarter add = seedStarterRepository.save(seedStarter);
        Assertions.assertNotNull(add);

    }

    @SneakyThrows
    @Test
    public void testForFindAll(){

        List<Feature> features =new ArrayList<>();
        features.add(Feature.FERTILIZER);
        Feature[] featuresArray = new Feature[features.size()];
        Row row = Row.builder().seedsPerCell(12).variety(varietyRepository.findById(2)).build();
        List<Row> rows = new ArrayList<>();
        rows.add(row);
        String date1 = "Thu Feb 17 00:00:00 PST 2022";
        Date date= new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US).parse(date1);
        Type type = Type.PLASTIC;
        SeedStarter seedStarter = SeedStarter.builder().covered(true)
                .type(type).datePlanted(date).features(features.toArray(featuresArray)).rows(rows).build();
        SeedStarter save = seedStarterRepository.save(seedStarter);
        System.out.println(save);
        List<SeedStarter> allSeedStarter = seedStarterRepository.findAllSeedStarter();
        System.out.println(allSeedStarter);
        Assertions.assertTrue(allSeedStarter.contains(save));

    }
}
