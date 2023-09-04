package com.adobe.aem.guides.wknd.core.servlets;

import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes="/sling/servlet/default",
        methods= "POST",
        extensions="json",
        selectors="sampleservletform")

public class FormValidationServlet extends SlingAllMethodsServlet {
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException{
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        JsonObject jsonResponse = new JsonObject();
        if (firstName == null || firstName.isEmpty())
            jsonResponse.addProperty("firstName", "First Name is required");
        if (lastName == null || lastName.isEmpty())
            jsonResponse.addProperty("lastName", "Last Name is required");
        if (email == null || email.isEmpty() || !email.contains("@"))
            jsonResponse.addProperty("email", "Valid Email is required");
        response.getWriter().write(jsonResponse.toString());
    }
}
