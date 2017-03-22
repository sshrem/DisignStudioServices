package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.VideoDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shrem on 20/03/2017.
 */
public class VideoDetailsRowMapper implements RowMapper<VideoDetails> {
    @Override
    public VideoDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new VideoDetails(rs.getLong("dsvd_id"), rs.getLong("dsvd_project_id"), rs.getLong("dsvd_apartment_template_id"),
            rs.getLong("dsvd_room_id"), rs.getLong("dsvd_video_length"));
    }
}
