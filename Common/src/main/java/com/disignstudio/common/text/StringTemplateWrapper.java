package com.disignstudio.common.text;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Created by ohadbenporat on 1/11/16.
 */
public class StringTemplateWrapper {

    public String print(String fileName,String instance,String dataName,Object data){
        STGroup stg = new STGroupFile(fileName, '$', '$');
        ST st = stg.getInstanceOf(instance);
        st.add(dataName,data);
        return st.render();
    }
}
