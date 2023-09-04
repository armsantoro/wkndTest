package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class)
public class ImageGridModel {

    private static final Logger log = LoggerFactory.getLogger(ImageGridModel.class);
    @ValueMapValue
    private String ctaLabel;
    @SlingObject
    private Resource resource;

    private String pagePath = "/content/wknd/country/adventures";

   /* public List<GridItem> getGridItems() {
        List<GridItem> gridItems = new ArrayList<>();
        try {
            Session session = resource.getResourceResolver().adaptTo(Session.class);
            if (session != null) {
                Node startNode = session.getNode(pagePath);
                NodeIterator nodeIterator = startNode.getNodes();
                while (nodeIterator.hasNext()) {
                    Node childNode = nodeIterator.nextNode();
                    String title = childNode.getNode("jcr:content").getProperty("jcr:title").getString();
                    String imgPath = childNode.getNode("jcr:content/root/container/carousel").getNodes("item*").nextNode().getProperty("fileReference").getString();
                    gridItems.add(new GridItem(title, imgPath, ctaLabel, childNode.getPath() + ".html"));
                }
            }
        } catch (Exception e) {
            log.error("Error retrieving grid items", e);
        }
        return gridItems;
    }*/

    public class GridItem {
        private String title;
        private String imagePath;
        private String ctaLabel;
        private String link;

        GridItem(String title, String imagePath, String ctaLabel, String link) {
            this.title = title;
            this.imagePath = imagePath;
            this.ctaLabel = ctaLabel;
            this.link = link;
        }
    }
}
