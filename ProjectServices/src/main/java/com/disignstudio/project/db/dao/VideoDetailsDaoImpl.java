package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.VideoDetails;
import com.disignstudio.project.db.mapper.VideoDetailsRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by shrem on 20/03/2017.
 */
public class VideoDetailsDaoImpl implements IVideoDetailsDao {

    private static final String FIND_VIDEO_BY_ID_QUERY = "select * from dsvd_video_details where dsvd_id = %d";
    private static final String FIND_VIDEO_BY_PROJECT_ID_QUERY = "select * from dsvd_video_details where dsvd_project_id = %d";

    private JdbcTemplate jdbcTemplate;
    private VideoDetailsRowMapper rowMapper;

    @Inject
    public VideoDetailsDaoImpl(JdbcTemplate jdbcTemplate, VideoDetailsRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public VideoDetails findById(long id) {
        String query = String.format(FIND_VIDEO_BY_ID_QUERY, id);
        List<VideoDetails> result = jdbcTemplate.query(query, rowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

    @Override
    public List<VideoDetails> findByProject(long projectId) {
        String query = String.format(FIND_VIDEO_BY_PROJECT_ID_QUERY, projectId);
        List<VideoDetails> result = jdbcTemplate.query(query, rowMapper);
        return result;
    }
}
