package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Project;
import com.disignstudio.project.db.bean.extended.ProjectExtended;
import com.disignstudio.project.db.mapper.ProjectExtendedRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by ohadbenporat on 1/5/16.
 */
public class ProjectDaoImpl implements IProjectDao {

    private static final String ADD_PROJECT_QUERY = "insert into dspr_projects (dspr_code,dspr_about,dspr_logo,dspr_name,dspr_entrepreneur_id,dspr_address,dspr_city,dspr_country,dspr_lon,dspr_lat,dspr_is_active) values('%s','%s','%s','%s', %d ,'%s',%d,%d,%f,%f,%b)";
    private static final String UPDATE_PROJECT_QUERY = "update dspr_projects set dspr_about = '%s',dspr_logo = '%s', dspr_name='%s',dspr_entrepreneur_id = %d, dspr_address='%s',dspr_city=%d,dspr_country=%d,dspr_lon=%f,dspr_lat=%f,dspr_is_active=%b) where dspr_id = %d";
    private static final String FIND_PROJECT_EXTENDED_BY_ID_QUERY = "select * from dspr_projects p join rfci_cities ci on dspr_city = rfci_id join rfco_countries co on dspr_country = rfco_id where p.dspr_id = %d";
    private static final String FIND_PROJECT_EXTENDED_BY_CODE = "select * from dspr_projects p join rfci_cities ci on dspr_city = rfci_id join rfco_countries co on dspr_country = rfco_id where p.dspr_code = '%s'";

    private JdbcTemplate jdbcTemplate;
    private ProjectExtendedRowMapper rowMapper;

    @Inject
    public ProjectDaoImpl(JdbcTemplate jdbcTemplate, ProjectExtendedRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void saveOrUpdate(Project project) {

        String query = (project.getId() > 0L) ?
                String.format(UPDATE_PROJECT_QUERY, project.getAbout(), project.getLogo(), project.getName(), project.getEntrepreneur(), project.getAddress(), project.getCityId(), project.getCountryId(), project.getLon(), project.getLat(), project.isActive(), project.getId()) :
                String.format(ADD_PROJECT_QUERY, project.getCode(), project.getAbout(), project.getLogo(), project.getName(), project.getEntrepreneur(), project.getAddress(), project.getCityId(), project.getCountryId(), project.getLon(), project.getLat(), project.isActive());
        jdbcTemplate.execute(query);
    }

    @Override
    public ProjectExtended findById(long id) {
        String query = String.format(FIND_PROJECT_EXTENDED_BY_ID_QUERY, id);
        List<ProjectExtended> result = jdbcTemplate.query(query, rowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

    @Override
    public ProjectExtended findByCode(String code) {
        String query = String.format(FIND_PROJECT_EXTENDED_BY_CODE, code);
        List<ProjectExtended> result = jdbcTemplate.query(query, rowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
