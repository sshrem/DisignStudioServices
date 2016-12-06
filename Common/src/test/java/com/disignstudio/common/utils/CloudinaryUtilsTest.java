package com.disignstudio.common.utils;

import com.cloudinary.Cloudinary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by ohadbenporat on 2/28/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CloudinaryUtilsTest {

    @Mock
    private Cloudinary cloudinary;

    private CloudinaryUtils classOnTest;

    @Before
    public void setUp() throws Exception {
        classOnTest = new CloudinaryUtils(cloudinary);
    }

    @Test
    public void testBuildProjectImagesPath() {
        String expectedPath = "projects/code/img";
        String path = classOnTest.buildProjectImagesPath("code", "img");
        assertEquals("Wrong cloudinary path been generated", expectedPath, path);
    }

    @Test
    public void testBuildApartmentTemplateImagesPath() {
        String expectedPath = "projects/pCode/aptTmplCode/img";
        String path = classOnTest.buildApartmentTemplateImagesPath("pCode", "aptTmplCode", "img");
        assertEquals("Wrong cloudinary path been generated", expectedPath, path);
    }

    @Test
    public void testBuildDesignerImagesPath() {
        String expectedPath = "designers/img";
        String path = classOnTest.buildDesignerImagesPath("img");
        assertEquals("Wrong cloudinary path been generated", expectedPath, path);
    }

    @Test
    public void testBuildEntrepreneurImagesPath() {
        String expectedPath = "entrepreneurs/img";
        String path = classOnTest.buildEntreprenuerImagesPath("img");
        assertEquals("Wrong cloudinary path been generated", expectedPath, path);
    }
}
