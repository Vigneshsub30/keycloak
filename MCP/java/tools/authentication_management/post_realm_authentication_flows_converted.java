/**
 * MCP Server function for Create a new authentication flow
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

class Post_Realm_Authentication_FlowsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_Authentication_FlowsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("description")) {
            queryParams.add("description=" + args.get("description"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("providerId")) {
            queryParams.add("providerId=" + args.get("providerId"));
        }
        if (args.containsKey("alias")) {
            queryParams.add("alias=" + args.get("alias"));
        }
        if (args.containsKey("builtIn")) {
            queryParams.add("builtIn=" + args.get("builtIn"));
        }
        if (args.containsKey("topLevel")) {
            queryParams.add("topLevel=" + args.get("topLevel"));
        }
        if (args.containsKey("authenticationExecutions")) {
            queryParams.add("authenticationExecutions=" + args.get("authenticationExecutions"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_authentication_flows" + queryString;
                
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
    
    public static MCPServer.Tool createPost_Realm_Authentication_FlowsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> descriptionProperty = new HashMap<>();
        descriptionProperty.put("type", "string");
        descriptionProperty.put("required", false);
        descriptionProperty.put("description", "");
        properties.put("description", descriptionProperty);
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> providerIdProperty = new HashMap<>();
        providerIdProperty.put("type", "string");
        providerIdProperty.put("required", false);
        providerIdProperty.put("description", "");
        properties.put("providerId", providerIdProperty);
        Map<String, Object> aliasProperty = new HashMap<>();
        aliasProperty.put("type", "string");
        aliasProperty.put("required", false);
        aliasProperty.put("description", "");
        properties.put("alias", aliasProperty);
        Map<String, Object> builtInProperty = new HashMap<>();
        builtInProperty.put("type", "string");
        builtInProperty.put("required", false);
        builtInProperty.put("description", "");
        properties.put("builtIn", builtInProperty);
        Map<String, Object> topLevelProperty = new HashMap<>();
        topLevelProperty.put("type", "string");
        topLevelProperty.put("required", false);
        topLevelProperty.put("description", "");
        properties.put("topLevel", topLevelProperty);
        Map<String, Object> authenticationExecutionsProperty = new HashMap<>();
        authenticationExecutionsProperty.put("type", "string");
        authenticationExecutionsProperty.put("required", false);
        authenticationExecutionsProperty.put("description", "");
        properties.put("authenticationExecutions", authenticationExecutionsProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_authentication_flows",
            "Create a new authentication flow",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_Authentication_FlowsHandler(config));
    }
    
}