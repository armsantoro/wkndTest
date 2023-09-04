$(document).ready(function() {
    console.log("ciao Servlet");
    var url = "/content/wknd.samplesearchservlet.json"; // URL della tua servlet

    var payload = {
        Type: "JCR-SQL2",
        //Type: "xpath",
        Path: "/content/wknd/language-masters",
        PropertyName:"cq:template",
        PropertyValue:"/conf/wknd/settings/wcm/templates/landing-page-template"
    };
    console.log("PAYLOAD INVIATO:", payload);
    $.post(url, payload, function(response) {
        console.log(response);
    }).fail(function (jqXHR, textStatus, errorThrow){
        console.log("Errore nella chiamata", textStatus, errorThrow);
    });
});
