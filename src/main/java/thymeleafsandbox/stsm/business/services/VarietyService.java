/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package thymeleafsandbox.stsm.business.services;

import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thymeleafsandbox.stsm.business.entities.Variety;
import thymeleafsandbox.stsm.business.entities.repositories.VarietyRepository;

@Service
public class VarietyService {
    
    @Autowired
    private VarietyRepository varietyRepository;

    
    public VarietyService() {
        super();
    }
    
    
    
    public List<Variety> findAll() {
        return this.varietyRepository.findAll();
    }

    public Variety findById(final Integer id) {
        Optional<Variety> byId = this.varietyRepository.findById(id);
        if(byId==null){
            throw new RuntimeException();
        }

        return byId.get();
    }

    public void saveValues(){
        if(varietyRepository.findAll().isEmpty()){
            final Variety var1 = new Variety();
            var1.setName("Thymus vulgaris");
            varietyRepository.save(var1);
            final Variety var2 = new Variety();
            var2.setName("Thymus x citriodorus");
            varietyRepository.save(var2);
            final Variety var3 = new Variety();
            var3.setName("Thymus herba-barona");
            varietyRepository.save(var3);
            final Variety var4 = new Variety();
            var4.setName("Thymus pseudolaginosus");
            varietyRepository.save(var4);
            final Variety var5 = new Variety();
            var5.setName("Thymus serpyllum");
            varietyRepository.save(var5);
        }
    }

    @PostConstruct
    public void saveFirst(){
            saveValues();
    }
}
