package diti.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class SwaggerController {

    /** Spec OpenAPI lue depuis le classpath (src/main/resources/openapi.json). */
    @GetMapping(value = "/openapi.json", produces = "application/json")
    @ResponseBody
    public String openApi() throws IOException {
        return StreamUtils.copyToString(
                new ClassPathResource("openapi.json").getInputStream(),
                StandardCharsets.UTF_8);
    }

    /** Page simple et autonome (aucun script) documentant l'API. Ne peut pas rester blanche. */
    @GetMapping(value = "/swagger-ui.html", produces = "text/html")
    @ResponseBody
    public String swaggerUi() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>diti4_spring_mvc API</title>\n" +
                "  <style>\n" +
                "    body{font-family:system-ui,Arial,sans-serif;margin:40px;color:#222;background:#fff}\n" +
                "    h1{margin-bottom:4px} h2{margin-top:32px;border-bottom:2px solid #eee;padding-bottom:4px}\n" +
                "    table{border-collapse:collapse;width:100%;margin-top:8px}\n" +
                "    th,td{border:1px solid #ddd;padding:8px 10px;text-align:left;font-size:14px}\n" +
                "    th{background:#f6f6f6} code{background:#f2f2f2;padding:2px 6px;border-radius:4px}\n" +
                "    .m{font-weight:bold} .get{color:#0a7d28} .post{color:#0b6bcb} .del{color:#b3261e}\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <h1>diti4_spring_mvc &mdash; API REST</h1>\n" +
                "  <p>Documentation des endpoints. Spec brute&nbsp;: <a href=\"openapi.json\">openapi.json</a></p>\n" +
                "  <h2>Produit</h2>\n" +
                "  <table>\n" +
                "    <tr><th>Methode</th><th>URL</th><th>Description</th></tr>\n" +
                "    <tr><td class=\"m get\">GET</td><td><code>/api/produit</code></td><td>Lister les produits</td></tr>\n" +
                "    <tr><td class=\"m get\">GET</td><td><code>/api/produit/{id}</code></td><td>Detail d'un produit</td></tr>\n" +
                "    <tr><td class=\"m post\">POST</td><td><code>/api/produit</code></td><td>Creer un produit</td></tr>\n" +
                "    <tr><td class=\"m del\">DELETE</td><td><code>/api/produit/delete/{id}</code></td><td>Supprimer un produit</td></tr>\n" +
                "  </table>\n" +
                "  <h2>TypeProduit</h2>\n" +
                "  <table>\n" +
                "    <tr><th>Methode</th><th>URL</th><th>Description</th></tr>\n" +
                "    <tr><td class=\"m get\">GET</td><td><code>/api/typeproduit</code></td><td>Lister les types</td></tr>\n" +
                "    <tr><td class=\"m get\">GET</td><td><code>/api/typeproduit/{id}</code></td><td>Detail d'un type</td></tr>\n" +
                "    <tr><td class=\"m post\">POST</td><td><code>/api/typeproduit</code></td><td>Creer un type</td></tr>\n" +
                "    <tr><td class=\"m del\">DELETE</td><td><code>/api/typeproduit/delete/{id}</code></td><td>Supprimer un type</td></tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";
    }
}
