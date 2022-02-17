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
import lombok.*;

import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SeedStarter {

    private Integer id = null;

    private Date datePlanted = null;

    private Boolean covered = null;

    private Type type = Type.PLASTIC;

    Feature[] features;

    private List<Row> rows = new ArrayList<Row>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeedStarter that = (SeedStarter) o;
        return Objects.equals(id, that.id) && Objects.equals(datePlanted, that.datePlanted) && Objects.equals(covered, that.covered) && type == that.type && Arrays.equals(features, that.features) && Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, datePlanted, covered, type, rows);
        result = 31 * result + Arrays.hashCode(features);
        return result;
    }
}
