package com.adobe.aem.guides.wknd.core.utils;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.ArrayList;
import java.util.List;

public class QueryUtility {
    private static final Logger log = LoggerFactory.getLogger(QueryUtility.class);

    public static List<Resource> findResources(ResourceResolver resolver, String path, String type, String propertyName, String propertyValue) {
        List<Resource> allResources = new ArrayList<>();

        String queryLanguage = type; // uso XPath come linguaggio di query

        // costruisco la query string
        String query = getQueryXPath(path, propertyName, propertyValue);
        String querySQL2 = getQuerySQL2(path, propertyName, propertyValue);
        //decido con l'op ternario quale query usare in base al type che legge
        String finalQuery = "xpath".equalsIgnoreCase(type) ? query : "JCR-SQL2".equalsIgnoreCase(type) ? querySQL2 : null;

        if (finalQuery == null){
            log.error("Tipo di query non supportato: {}", type);
            return allResources;
        }

        try {
            Session session = resolver.adaptTo(Session.class);
            QueryManager qm = session.getWorkspace().getQueryManager();
            Query jcrQuery = qm.createQuery(query, queryLanguage);
            QueryResult result = jcrQuery.execute();
            NodeIterator nodes = result.getNodes();

            while (nodes.hasNext()) {
                Node node = nodes.nextNode();
                Resource resource = resolver.getResource(node.getPath());
                if (resource != null) {
                    allResources.add(resource);
                }
            }
        } catch (Exception x) {
            log.error("Errore di esecuzione della query", x);
        }
        return allResources;
    }

    private static String getQueryXPath(String path, String propertyName, String propertyValue) {
        return String.format("/jcr:root%s//*[@%s='%s']", path, propertyName, propertyValue);
    }
    private static String getQuerySQL2(String path, String propertyName, String propertyValue) {
        return String.format("SELECT * FROM [nt:base] AS s WHERE ISDESCENDANTNODE([" + path + "]) AND s.[" + propertyName + "] IS NOT NULL AND s.[" + propertyName + "] = '" + propertyValue + "'");
    }
}
