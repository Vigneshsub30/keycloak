/**
 * MCP Server function for Add a mapper to identity provider
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

class Post_Realm_Identity_Provider_Instances_Alias_MappersMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_Identity_Provider_Instances_Alias_MappersHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("identityProviderMapper")) {
            queryParams.add("identityProviderMapper=" + args.get("identityProviderMapper"));
        }
        if (args.containsKey("name")) {
            queryParams.add("name=" + args.get("name"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("identityProviderAlias")) {
            queryParams.add("identityProviderAlias=" + args.get("identityProviderAlias"));
        }
        if (args.containsKey("config")) {
            queryParams.add("config=" + args.get("config"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_identity_provider_instances_alias_mappers" + queryString;
                
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
    
    public static MCPServer.Tool createPost_Realm_Identity_Provider_Instances_Alias_MappersTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> identityProviderMapperProperty = new HashMap<>();
        identityProviderMapperProperty.put("type", "string");
        identityProviderMapperProperty.put("required", false);
        identityProviderMapperProperty.put("description", "");
        properties.put("identityProviderMapper", identityProviderMapperProperty);
        Map<String, Object> nameProperty = new HashMap<>();
        nameProperty.put("type", "string");
        nameProperty.put("required", false);
        nameProperty.put("description", "");
        properties.put("name", nameProperty);
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> identityProviderAliasProperty = new HashMap<>();
        identityProviderAliasProperty.put("type", "string");
        identityProviderAliasProperty.put("required", false);
        identityProviderAliasProperty.put("description", "");
        properties.put("identityProviderAlias", identityProviderAliasProperty);
        Map<String, Object> configProperty = new HashMap<>();
        configProperty.put("type", "string");
        configProperty.put("required", false);
        configProperty.put("description", "");
        properties.put("config", configProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_identity_provider_instances_alias_mappers",
            "Add a mapper to identity provider",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_Identity_Provider_Instances_Alias_MappersHandler(config));
    }
    
}