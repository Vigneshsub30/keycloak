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

class Put_Realm_Users_Management_PermissionsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_Realm_Users_Management_PermissionsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("resource")) {
            queryParams.add("resource=" + args.get("resource"));
        }
        if (args.containsKey("enabled")) {
            queryParams.add("enabled=" + args.get("enabled"));
        }
        if (args.containsKey("scopePermissions")) {
            queryParams.add("scopePermissions=" + args.get("scopePermissions"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm_users_management_permissions" + queryString;
                
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
    
    public static MCPServer.Tool createPut_Realm_Users_Management_PermissionsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> resourceProperty = new HashMap<>();
        resourceProperty.put("type", "string");
        resourceProperty.put("required", false);
        resourceProperty.put("description", "");
        properties.put("resource", resourceProperty);
        Map<String, Object> enabledProperty = new HashMap<>();
        enabledProperty.put("type", "string");
        enabledProperty.put("required", false);
        enabledProperty.put("description", "");
        properties.put("enabled", enabledProperty);
        Map<String, Object> scopePermissionsProperty = new HashMap<>();
        scopePermissionsProperty.put("type", "string");
        scopePermissionsProperty.put("required", false);
        scopePermissionsProperty.put("description", "");
        properties.put("scopePermissions", scopePermissionsProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm_users_management_permissions",
            "No description available",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_Realm_Users_Management_PermissionsHandler(config));
    }
    
}