/**
 * MCP Server function for Partial import from a JSON file to an existing realm.
 */

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.function.Function;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

class Post_Realm_Partial_ImportMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_Partial_ImportHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("ifResourceExists")) {
            queryParams.add("ifResourceExists=" + args.get("ifResourceExists"));
        }
        if (args.containsKey("policy")) {
            queryParams.add("policy=" + args.get("policy"));
        }
        if (args.containsKey("roles")) {
            queryParams.add("roles=" + args.get("roles"));
        }
        if (args.containsKey("users")) {
            queryParams.add("users=" + args.get("users"));
        }
        if (args.containsKey("clients")) {
            queryParams.add("clients=" + args.get("clients"));
        }
        if (args.containsKey("groups")) {
            queryParams.add("groups=" + args.get("groups"));
        }
        if (args.containsKey("identityProviders")) {
            queryParams.add("identityProviders=" + args.get("identityProviders"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_partial_import" + queryString;
                
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + config.getApiKey())
                    .header("Accept", "application/json")
                    .GET()
                    .build();
                
                HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                
                if (response.statusCode() >= 400) {
                    return new MCPServer.MCPToolResult("API error: " + response.body(), true);
                }
                
                // Pretty print JSON
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(response.body());
                String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                
                return new MCPServer.MCPToolResult(prettyJson);
                
            } catch (IOException | InterruptedException e) {
                return new MCPServer.MCPToolResult("Request failed: " + e.getMessage(), true);
            } catch (Exception e) {
                return new MCPServer.MCPToolResult("Unexpected error: " + e.getMessage(), true);
            }
        };
    }
    
    public static MCPServer.Tool createPost_Realm_Partial_ImportTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> ifResourceExistsProperty = new HashMap<>();
        ifResourceExistsProperty.put("type", "string");
        ifResourceExistsProperty.put("required", false);
        ifResourceExistsProperty.put("description", "");
        properties.put("ifResourceExists", ifResourceExistsProperty);
        Map<String, Object> policyProperty = new HashMap<>();
        policyProperty.put("type", "string");
        policyProperty.put("required", false);
        policyProperty.put("description", "");
        properties.put("policy", policyProperty);
        Map<String, Object> rolesProperty = new HashMap<>();
        rolesProperty.put("type", "string");
        rolesProperty.put("required", false);
        rolesProperty.put("description", "");
        properties.put("roles", rolesProperty);
        Map<String, Object> usersProperty = new HashMap<>();
        usersProperty.put("type", "string");
        usersProperty.put("required", false);
        usersProperty.put("description", "");
        properties.put("users", usersProperty);
        Map<String, Object> clientsProperty = new HashMap<>();
        clientsProperty.put("type", "string");
        clientsProperty.put("required", false);
        clientsProperty.put("description", "");
        properties.put("clients", clientsProperty);
        Map<String, Object> groupsProperty = new HashMap<>();
        groupsProperty.put("type", "string");
        groupsProperty.put("required", false);
        groupsProperty.put("description", "");
        properties.put("groups", groupsProperty);
        Map<String, Object> identityProvidersProperty = new HashMap<>();
        identityProvidersProperty.put("type", "string");
        identityProvidersProperty.put("required", false);
        identityProvidersProperty.put("description", "");
        properties.put("identityProviders", identityProvidersProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_partial_import",
            "Partial import from a JSON file to an existing realm.",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_Partial_ImportHandler(config));
    }
    
}