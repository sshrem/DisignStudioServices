package com.disignstudio.web.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Created by ohadbenporat on 1/11/16.
 */
public class JarVersionDetector {

    private static final Logger logger = LoggerFactory.getLogger(JarVersionDetector.class);
    private List<VersionInformation> versions = null;

    /**
     * Gets the version information of all jars in the classpath.
     *
     * @return
     */
    public List<VersionInformation> getAllJarsVersion() throws Exception {

        if (versions != null) {
            return versions;
        }

        Map<String, String> allJars = getAllJarsInClasspath();
        versions = new LinkedList<>();
        for (final Map.Entry<String, String> jarEntry : allJars.entrySet()) {
            versions.add(getJarVersion(jarEntry));
        }

        Collections.sort(versions, new VersionInformationComparator());
        return versions;
    }

    static Map<String, String> getAllJarsInClasspath() {

        final Map<String, String> ret = new HashMap<>();
        URLClassLoader contextClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        collectJarPaths(contextClassLoader.getURLs(), ret);

        URLClassLoader parentClassLoader = (URLClassLoader) contextClassLoader.getParent();
        if (null != parentClassLoader) {
            collectJarPaths(parentClassLoader.getURLs(), ret);
        }

        return ret;
    }

    private static void collectJarPaths(final URL[] classpath, final Map<String, String> mapFileNameToPath) {

        for (final URL url : classpath) {
            File file;
            try {
                file = new File(url.toURI());
            } catch (final URISyntaxException e) {
                logger.error("Jar has a bad URL: ", e);
                continue;
            }

            if (file.getPath().endsWith(".jar")) { // jar file
                mapFileNameToPath.put(file.getName(), file.getAbsolutePath());
            }
        }
    }

    /**
     * Gets the version information of the specified Jar file.
     */
    private VersionInformation getJarVersion(final Map.Entry<String, String> jarEntry) throws Exception {

        if (jarEntry == null) {
            return null;
        }

        final JarFile jarfile = new JarFile(jarEntry.getValue());
        final Manifest manifest = jarfile.getManifest();
        final Attributes att = manifest == null ? null : manifest.getMainAttributes();
        final String implVersion;
        final String specVersion;
        final String createdBy;
        final String builtBy;
        final String specTitle;
        final String specVendor;
        final String buildJdk;
        final String revision;
        final String timestamp;

        if (att == null) {
            implVersion = specVersion = revision = createdBy = builtBy = specTitle = specVendor = buildJdk = timestamp = null;
        } else {
            implVersion = att.getValue("Implementation-Version");
            specVersion = att.getValue("Specification-Version");
            revision = att.getValue("svn-revision");
            createdBy = att.getValue("Created-By");
            builtBy = att.getValue("Built-By");
            specTitle = att.getValue("Specification-Title");
            specVendor = att.getValue("Specification-Vendor");
            buildJdk = att.getValue("Build-Jdk");
            timestamp = att.getValue("timestamp");
        }
        return new VersionInformation(jarEntry.getKey(), implVersion, specVersion, revision, createdBy, builtBy, specTitle, specVendor, buildJdk,
                timestamp);
    }
}
