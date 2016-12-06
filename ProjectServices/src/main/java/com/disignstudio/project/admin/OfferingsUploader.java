package com.disignstudio.project.admin;

import com.disignstudio.project.db.bean.SupplierOffering;
import com.disignstudio.project.db.dao.ISupplierOfferingDao;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ohadbenporat on 9/22/16.
 */
public class OfferingsUploader {

    private static final String FILE_NAME = "/Users/obenporat/Downloads/offerings.tsv";

    private ISupplierOfferingDao supplierOfferingDao;

    @Inject
    public OfferingsUploader(ISupplierOfferingDao supplierOfferingDao) {
        this.supplierOfferingDao = supplierOfferingDao;
    }

    public void upload() throws IOException {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_NAME));
            br.readLine(); // Skip headers Line

            String line = br.readLine();
            while (StringUtils.isNoneBlank(line)) {
                processLine(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }

    private void processLine(String line) {
        OfferingRawData data = new OfferingRawData(line);
        SupplierOffering offering = new SupplierOffering(data.getSupplier(), data.getCategory(), data.getName(), data.getDescription(), data.getIsStandard(), true, data.getImage(), 0);
        supplierOfferingDao.saveOrUpdate(offering);
    }


}
