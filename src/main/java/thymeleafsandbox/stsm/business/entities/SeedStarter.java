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
package thymeleafsandbox.stsm.business.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "SEED_STARTERSS")
public class SeedStarter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id = null;

    @Column(name="DATE_PLANTED")
    private Date datePlanted = null;

    @Column(name="COVERED")
    private Boolean covered = null;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private Type type = Type.PLASTIC;

    @ElementCollection(targetClass = Feature.class)
    @JoinTable(name = "FEATURES", joinColumns = @JoinColumn(name = "ID"))
    @Column(name = "FEATURE_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    Collection<Feature> features;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private List<Row> rows = new ArrayList<Row>();

}
