package com.disignstudio.web.version;

import java.util.Comparator;

/**
 * Created by ohadbenporat on 1/11/16.
 */
public class VersionInformationComparator implements Comparator<VersionInformation> {

    @Override
    public int compare(VersionInformation o1, VersionInformation o2) {

        return o1.getId().compareTo(o2.getId());
    }
}
