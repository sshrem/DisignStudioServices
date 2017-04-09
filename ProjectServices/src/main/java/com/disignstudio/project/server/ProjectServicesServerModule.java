package com.disignstudio.project.server;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.common.cache.InProcCacheClient;
import com.disignstudio.common.cache.RedisCacheClient;
import com.disignstudio.common.configuration.ConfigurationProvider;
import com.disignstudio.project.db.dao.*;
import com.disignstudio.project.db.mapper.*;
import com.disignstudio.project.mongo.dao.*;
import com.google.gson.Gson;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

import java.net.URI;

/**
 * Created by ohadbenporat on 1/13/16.
 */
public class ProjectServicesServerModule implements Module {

    private static final int TWENTY_FOUR_HOURS = 24 * 60 * 60;
    private final ConfigurationProvider configurationProvider;

    public ProjectServicesServerModule(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(ConfigurationProvider.class).toInstance(configurationProvider);

        binder.bind(EntrepreneurRowMapper.class).toInstance(new EntrepreneurRowMapper());
        binder.bind(ProjectRowMapper.class).toInstance(new ProjectRowMapper());
        binder.bind(ApartmentTemplateRowMapper.class).toInstance(new ApartmentTemplateRowMapper());
        binder.bind(DesignerRowMapper.class).toInstance(new DesignerRowMapper());
        binder.bind(DesignRowMapper.class).toInstance(new DesignRowMapper());
        binder.bind(RFFeatureRowMapper.class).toInstance(new RFFeatureRowMapper());
        binder.bind(ProjectFeatureRowMapper.class).toInstance(new ProjectFeatureRowMapper());
        binder.bind(ContactRowMapper.class).toInstance(new ContactRowMapper());
        binder.bind(UserRowMapper.class).toInstance(new UserRowMapper());

        binder.bind(IEntrepreneurDao.class).to(EntrepreneurDaoImpl.class);
        binder.bind(IProjectDao.class).to(ProjectDaoImpl.class);
        binder.bind(IApartmentTemplateDao.class).to(ApartmentTemplateDaoImpl.class);
        binder.bind(IDesignDao.class).to(DesignDaoImpl.class);
        binder.bind(IDesignerDao.class).to(DesignerDaoImpl.class);
        binder.bind(IRFFeatureDao.class).to(RFFeatureDaoImpl.class);
        binder.bind(IProjectFeatureDao.class).to(ProjectFeatureDaoImpl.class);
        binder.bind(IContactDao.class).to(ContactDaoImpl.class);
        binder.bind(IUserDao.class).to(UserDaoImpl.class);
        binder.bind(ISupplierDao.class).to(SupplierDaoImpl.class);
        binder.bind(ISupplierOfferingDao.class).to(SupplierOfferingDaoImpl.class);
        binder.bind(IDesignItemDao.class).to(DesignItemDaoImpl.class);
        binder.bind(IImagingViewDao.class).to(ImagingViewDaoImpl.class);
        binder.bind(IUserActionDao.class).to(UserActionDaoImpl.class);
        binder.bind(IViewSupplierDao.class).to(ViewSupplierDaoImpl.class);
        binder.bind(IVideoDetailsDao.class).to(VideoDetailsDaoImpl.class);
        binder.bind(IFacebookShareDao.class).to(FacebookShareDaoImpl.class);
        binder.bind(IVideoViewDao.class).to(VideoViewDaoImpl.class);
        binder.bind(Cloudinary.class).toInstance(new Cloudinary(ObjectUtils.asMap(
                "cloud_name", configurationProvider.provideProperty("cloudinary.cloud_name"),
                "api_key", configurationProvider.provideProperty("cloudinary.api_key"),
                "api_secret", configurationProvider.provideProperty("cloudinary.api_secret"))));

        // Cache
        binder.bind(ICacheClient.class).toInstance(generateCacheClient());
        binder.bindConstant().annotatedWith(Names.named("projectCacheExpiration")).to(TWENTY_FOUR_HOURS);
        binder.bindConstant().annotatedWith(Names.named("featuresCacheExpiration")).to(TWENTY_FOUR_HOURS);
        binder.bindConstant().annotatedWith(Names.named("designsDataCacheExpiration")).to(TWENTY_FOUR_HOURS);
        binder.bindConstant().annotatedWith(Names.named("numberOfDesignsToServe")).to(configurationProvider.providePropertyAsInt("project.num.designs.to.serve"));
    }

    private ICacheClient generateCacheClient() {
        try {
            return new RedisCacheClient(new URI(System.getenv("REDISCLOUD_URL")), new Gson());
        } catch (Exception e) {
            return new InProcCacheClient("projectsCache", new Gson());
        }
    }
}
