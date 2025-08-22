/**
 * MCP Server function for Add a social login provider to the user
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

class Post_Realm_Users_Id_Federated_Identity_ProviderMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_Users_Id_Federated_Identity_ProviderHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("userId")) {
            queryParams.add("userId=" + args.get("userId"));
        }
        if (args.containsKey("userName")) {
            queryParams.add("userName=" + args.get("userName"));
        }
        if (args.containsKey("identityProvider")) {
            queryParams.add("identityProvider=" + args.get("identityProvider"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_users_id_federated_identity_provider" + queryString;
                
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
    
    public static MCPServer.Tool createPost_Realm_Users_Id_Federated_Identity_ProviderTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> userIdProperty = new HashMap<>();
        userIdProperty.put("type", "string");
        userIdProperty.put("required", false);
        userIdProperty.put("description", "");
        properties.put("userId", userIdProperty);
        Map<String, Object> userNameProperty = new HashMap<>();
        userNameProperty.put("type", "string");
        userNameProperty.put("required", false);
        userNameProperty.put("description", "");
        properties.put("userName", userNameProperty);
        Map<String, Object> identityProviderProperty = new HashMap<>();
        identityProviderProperty.put("type", "string");
        identityProviderProperty.put("required", false);
        identityProviderProperty.put("description", "");
        properties.put("identityProvider", identityProviderProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_users_id_federated_identity_provider",
            "Add a social login provider to the user",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_Users_Id_Federated_Identity_ProviderHandler(config));
    }
    
}