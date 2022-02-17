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
package thymeleafsandbox.stsm.business.entities.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import thymeleafsandbox.stsm.business.entities.*;


@Repository
public class SeedStarterRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT_INTO_SEED_DETAILS = "INSERT INTO seed_details (datePlanted, covered, type, features) values(?,?,?,?) ";
    private static final String SEED_DETAILS = "SELECT * FROM seed_details";
    @Autowired
    RowDataRepository rowDataRepository ;

    public SeedStarterRepository() {
        super();
    }

    public void save(final SeedStarter seedStarter) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_INTO_SEED_DETAILS, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, seedStarter.getDatePlanted().toString());
            preparedStatement.setBoolean(2, seedStarter.getCovered());
            preparedStatement.setString(3, String.valueOf(seedStarter.getType().toString()));
            preparedStatement.setString(4, Arrays.stream(seedStarter.getFeatures())
                    .map(Feature::toString)
                    .collect(Collectors.joining(",")));
            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        Optional<Number> key = Optional.ofNullable(keyHolder.getKey());
        key.ifPresent(number -> seedStarter.setId(number.intValue()));

        int id = keyHolder.getKey().intValue();
        rowDataRepository.saveRow(seedStarter.getRows(), id);
    }


    public List<SeedStarter> findAllSeedStarter(){
        RowMapper<SeedStarter> rowMapper= (rs, rowNum) -> {
            SeedStarter seedStarter = new SeedStarter();
            seedStarter.setId(rs.getInt("seedId"));
            try {
                seedStarter.setDatePlanted(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
                        .parse(rs.getString("datePlanted")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            seedStarter.setCovered(rs.getBoolean("covered"));
            seedStarter.setType(Type.valueOf((rs.getString("type"))));
            String features = rs.getString("features");
            Feature[] features1 = Stream.of(features.split(",")).map(Feature::valueOf).toArray(Feature[]::new);
            seedStarter.setRows(rowDataRepository.getAllRows(seedStarter.getId()));
            return seedStarter;
        };
        return jdbcTemplate.query(SEED_DETAILS, rowMapper);
    }
}
