package thymeleafsandbox.stsm.web;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import thymeleafsandbox.stsm.business.entities.Feature;
import thymeleafsandbox.stsm.business.entities.SeedStarter;
import thymeleafsandbox.stsm.business.entities.Type;
import thymeleafsandbox.stsm.business.entities.Variety;
import thymeleafsandbox.stsm.business.services.SeedStarterService;
import thymeleafsandbox.stsm.business.services.VarietyService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
public class SeedStarterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    List<Type> list = new ArrayList<>();
    List<Variety> listVariety = new ArrayList<>();
    List<Feature> listFeature = new ArrayList<>();
    List<SeedStarter> seedStartersList = new ArrayList<>();

    @MockBean
    SeedStarterService seedStarterService;

    @MockBean
    VarietyService varietyService;

    @Test
    @SneakyThrows
    public void seedStarterHomePage() {

        BDDMockito.given(seedStarterService.findAll()).willReturn(seedStartersList);
        BDDMockito.given(varietyService.findAll()).willReturn(listVariety);

        mockMvc.perform(MockMvcRequestBuilders.get("/seedstartermng"))
                .andExpect(view().name("seedstartermng"))
                .andExpect(model().attribute("allTypes",Arrays.asList(Type.ALL)))
                .andExpect(model().attribute("allVarieties",varietyService.findAll()))
                .andExpect(model().attribute("allFeatures", Arrays.asList(Feature.ALL)))
                .andExpect(model().attribute("allSeedStarters", seedStarterService.findAll()));

    }

}