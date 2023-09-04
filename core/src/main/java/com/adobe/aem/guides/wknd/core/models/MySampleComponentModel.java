package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import javax.inject.Inject;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MySampleComponentModel {

    @Inject
    private String title;

    @Inject
    private String description;

    @Inject
    private String image;

    @Inject
    private String imagePath;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getImagePath(){
        return imagePath;
    }
}