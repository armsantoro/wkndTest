package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class TopBannerModel {

    @Inject @Optional
    private String description;
    @Inject @Optional
    private String ctaLabel;
    @Inject @Optional
    private String ctaLink;
    public String getDescription(){
        return description;
    }
    public String getCtaLabel(){
        return ctaLabel;
    }
    public String getCtaLink(){
        return ctaLink;
    }
}
