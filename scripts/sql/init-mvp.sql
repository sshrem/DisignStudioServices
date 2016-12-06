CREATE TABLE `rfct_categories` (
  `rfct_id` int unsigned NOT NULL,
  `rfct_name` varchar(64) NOT NULL,
  PRIMARY KEY (`rfct_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `rfcu_currencies` (
  `rfcu_id` int unsigned NOT NULL AUTO_INCREMENT,
  `rfcu_alias` varchar(5) NOT NULL,
  `rfcu_name` varchar(64) NOT NULL,
  PRIMARY KEY (`rfcu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `rfco_countries` (
  `rfco_id` int unsigned NOT NULL AUTO_INCREMENT,
  `rfco_name` varchar(64) NOT NULL,
  `rfco_currency_id` int unsigned NOT NULL,
  PRIMARY KEY (`rfco_id`),
  CONSTRAINT `rfco_rfcu_fk` FOREIGN KEY (`rfco_currency_id`) REFERENCES `rfcu_currencies` (`rfcu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `rfci_cities` (
  `rfci_id` int unsigned NOT NULL AUTO_INCREMENT,
  `rfci_name` varchar(64) NOT NULL,
  `rfci_country_id` int unsigned NOT NULL,
  PRIMARY KEY (`rfci_id`),
  CONSTRAINT `rfci_rfco_fk` FOREIGN KEY (`rfci_country_id`) REFERENCES `rfco_countries` (`rfco_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `rffe_features` (
  `rffe_id` int unsigned NOT NULL AUTO_INCREMENT,
  `rffe_code` varchar(64) NOT NULL,
  `rffe_name_en` varchar(128) NOT NULL,
  `rffe_name_he` varchar(128) NOT NULL,
  PRIMARY KEY (`rffe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `rfro_rooms` (
  `rfro_id` int unsigned NOT NULL AUTO_INCREMENT,
  `rfro_code` varchar(64) NOT NULL,
  `rfro_name_en` varchar(128) NOT NULL,
  `rfro_name_he` varchar(128) NOT NULL,
  PRIMARY KEY (`rfro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dsus_users` (
  `dsus_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `dsus_email` varchar(128) NOT NULL,
  `dsus_name` varchar(128) NOT NULL,
  `dsus_login_method` tinyint(3) NOT NULL,
  `dsus_last_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dsus_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dsus_id`),
  UNIQUE KEY `dsus_email_ix` (`dsus_email`),
  KEY `dsus_login_method_ix` (`dsus_login_method`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dsco_contacts` (
  `dsco_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dsco_name` varchar(128) NOT NULL,
  `dsco_email` varchar(128) NOT NULL,
  `dsco_phone` varchar(20) NOT NULL,
  `dsco_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dsco_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dsde_designers` (
  `dsde_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dsde_contact_id` bigint unsigned NOT NULL,
  `dsde_image` varchar(1024) DEFAULT NULL,
  `dsde_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dsde_id`),
  CONSTRAINT `dsde_contact_fk` FOREIGN KEY (`dsde_contact_id`) REFERENCES `dsco_contacts` (`dsco_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dsen_entrepreneurs` (
  `dsen_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dsen_name` varchar(64) NOT NULL,
  `dsen_address` varchar(1024),
  `dsen_about` text,
  `dsen_contact_id` bigint unsigned,
  `dsen_logo` varchar(64),
  `dsen_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dsen_id`),
  CONSTRAINT `dsen_dsco_fk` FOREIGN KEY (`dsen_contact_id`) REFERENCES `dsco_contacts` (`dsco_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dspr_projects` (
  `dspr_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dspr_code` varchar(32) NOT NULL,
  `dspr_entrepreneur_id` bigint unsigned NOT NULL,
  `dspr_name` varchar(64) NOT NULL,
  `dspr_about` text,
  `dspr_address` varchar(1024),
  `dspr_sales_contact` bigint unsigned,
  `dspr_city` int unsigned,
  `dspr_country` int unsigned,
  `dspr_logo` varchar(64),
  `dspr_lon` double,
  `dspr_lat` double,
  `dspr_is_active` tinyint default 1,
  `dspr_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dspr_id`),
  UNIQUE INDEX `dspr_code_ix` (`dspr_code`),
  CONSTRAINT `dspr_dsen_fk` FOREIGN KEY (`dspr_entrepreneur_id`) REFERENCES `dsen_entrepreneurs` (`dsen_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dspr_rfci_fk` FOREIGN KEY (`dspr_city`) REFERENCES `rfci_cities` (`rfci_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dspr_rfco_fk` FOREIGN KEY (`dspr_country`) REFERENCES `rfco_countries` (`rfco_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dspr_dsco_fk` FOREIGN KEY (`dspr_sales_contact`) REFERENCES `dsco_contacts` (`dsco_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dspf_project_features` (
  `dspf_project_id` bigint unsigned NOT NULL,
  `dspf_feature_id` int(10) unsigned NOT NULL,
  `dspf_description` varchar(2048),
  `dspf_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dspf_project_id`,`dspf_feature_id`),
  CONSTRAINT `dspf_dspr_fk` FOREIGN KEY (`dspf_project_id`) REFERENCES `dspr_projects` (`dspr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dspf_rffe_fk` FOREIGN KEY (`dspf_feature_id`) REFERENCES `rffe_features` (`rffe_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dsat_apartment_templates` (
  `dsat_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dsat_code` varchar(32) NOT NULL,
  `dsat_project_id` bigint unsigned NOT NULL,
  `dsat_name` varchar(64) NOT NULL,
  `dsat_image` varchar(64) NOT NULL,
  `dsat_num_of_rooms` tinyint(4) DEFAULT NULL,
  `dsat_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dsat_id`),
  UNIQUE INDEX `dsat_code_ix` (`dsat_code`),
  CONSTRAINT `dsat_dspr_fk` FOREIGN KEY (`dsat_project_id`) REFERENCES `dspr_projects` (`dspr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dsds_designs` (
  `dsds_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dsds_designer_id` bigint unsigned NOT NULL,
  `dsds_title` varchar(128) NOT NULL,
  `dsds_apartment_template_id` bigint unsigned NOT NULL,
  `dsds_imaging_code` varchar(1024) NOT NULL,
  `dsds_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dsds_id`),
  KEY `designer_id_ix`(dsds_designer_id),
  CONSTRAINT `dsds_dsde_fk` FOREIGN KEY (`dsds_designer_id`) REFERENCES `dsde_designers` (`dsde_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dsds_dsat_fk` FOREIGN KEY (`dsds_apartment_template_id`) REFERENCES `dsat_apartment_templates` (`dsat_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dssu_suppliers` (
  `dssu_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dssu_name` varchar(128) NOT NULL,
  `dssu_display_name` varchar(128),
  `dssu_address` varchar(128),
  `dssu_country_id` int unsigned NOT NULL,
  `dssu_contact_id` bigint unsigned,
  `dssu_logo` varchar(32),
  `dssu_url` varchar(1024),
  `dssu_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dssu_id`),
  CONSTRAINT `dssu_rfco_fk` FOREIGN KEY (`dssu_country_id`) REFERENCES `rfco_countries` (`rfco_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dssu_dsco_fk` FOREIGN KEY (`dssu_contact_id`) REFERENCES `dsco_contacts` (`dsco_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dsso_supplier_offerings` (
  `dsso_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dsso_supplier_id` bigint unsigned NOT NULL,
  `dsso_category` int unsigned NOT NULL,
  `dsso_name` varchar(128) NOT NULL,
  `dsso_description` varchar(1024),
  `dsso_is_standard` tinyint,
  `dsso_in_stock` tinyint DEFAULT 1,
  `dsso_image_code` varchar(128),
  `dsso_price` REAL(8,2) NOT NULL,
  `dsso_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dsso_id`),
  KEY `dsso_supplier_ix`(dsso_supplier_id),
  CONSTRAINT `dsso_dssu_fk` FOREIGN KEY (`dsso_supplier_id`) REFERENCES `dssu_suppliers` (`dssu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dsso_rfct_fk` FOREIGN KEY (`dsso_category`) REFERENCES `rfct_categories` (`rfct_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

CREATE TABLE `dsdi_design_items` (
  `dsdi_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dsdi_design_id` bigint unsigned NOT NULL,
  `dsdi_offering_id` bigint unsigned NOT NULL,
  `dsdi_room_id` int unsigned,
  `dsdi_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dsdi_id`),
  UNIQUE KEY (`dsdi_design_id`,`dsdi_offering_id`, `dsdi_room_id`),
  CONSTRAINT `dsdi_offering_supplier_offering_fk` FOREIGN KEY (`dsdi_offering_id`) REFERENCES `dsso_supplier_offerings` (`dsso_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dsdi_rfro_fk` FOREIGN KEY (`dsdi_room_id`) REFERENCES `rfro_rooms` (`rfro_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dsdi_design_fk` FOREIGN KEY (`dsdi_design_id`) REFERENCES `dsds_designs` (`dsds_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;
