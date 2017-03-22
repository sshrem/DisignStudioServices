package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.VideoDetails;

import java.util.List;

/**
 * Created by shrem on 20/03/2017.
 */
public interface IVideoDetailsDao {
    public VideoDetails findById(long id);
    public List<VideoDetails> findByProject(long projectId);
}
