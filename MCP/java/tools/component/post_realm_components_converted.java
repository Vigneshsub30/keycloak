/**
 * MCP Server function for No description available
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

class Post_Realm_ComponentsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_ComponentsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("parentId")) {
            queryParams.add("parentId=" + args.get("parentId"));
        }
        if (args.containsKey("providerId")) {
            queryParams.add("providerId=" + args.get("providerId"));
        }
        if (args.containsKey("providerType")) {
            queryParams.add("providerType=" + args.get("providerType"));
        }
        if (args.containsKey("subType")) {
            queryParams.add("subType=" + args.get("subType"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("name")) {
            queryParams.add("name=" + args.get("name"));
        }
        if (args.containsKey("config")) {
            queryParams.add("config=" + args.get("config"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_components" + queryString;
                
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
    
    public static MCPServer.Tool createPost_Realm_ComponentsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> parentIdProperty = new HashMap<>();
        parentIdProperty.put("type", "string");
        parentIdProperty.put("required", false);
        parentIdProperty.put("description", "");
        properties.put("parentId", parentIdProperty);
        Map<String, Object> providerIdProperty = new HashMap<>();
        providerIdProperty.put("type", "string");
        providerIdProperty.put("required", false);
        providerIdProperty.put("description", "");
        properties.put("providerId", providerIdProperty);
        Map<String, Object> providerTypeProperty = new HashMap<>();
        providerTypeProperty.put("type", "string");
        providerTypeProperty.put("required", false);
        providerTypeProperty.put("description", "");
        properties.put("providerType", providerTypeProperty);
        Map<String, Object> subTypeProperty = new HashMap<>();
        subTypeProperty.put("type", "string");
        subTypeProperty.put("required", false);
        subTypeProperty.put("description", "");
        properties.put("subType", subTypeProperty);
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> nameProperty = new HashMap<>();
        nameProperty.put("type", "string");
        nameProperty.put("required", false);
        nameProperty.put("description", "");
        properties.put("name", nameProperty);
        Map<String, Object> configProperty = new HashMap<>();
        configProperty.put("type", "string");
        configProperty.put("required", false);
        configProperty.put("description", "");
        properties.put("config", configProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_components",
            "No description available",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_ComponentsHandler(config));
    }
    
}