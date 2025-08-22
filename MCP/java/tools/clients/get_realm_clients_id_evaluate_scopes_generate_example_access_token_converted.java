/**
 * MCP Server function for Create JSON with payload of example access token
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

class Get_Realm_Clients_Id_Evaluate_Scopes_Generate_Example_Access_TokenMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Realm_Clients_Id_Evaluate_Scopes_Generate_Example_Access_TokenHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("scope")) {
            queryParams.add("scope=" + args.get("scope"));
        }
        if (args.containsKey("userId")) {
            queryParams.add("userId=" + args.get("userId"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/get_realm_clients_id_evaluate_scopes_generate_example_access_token" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Realm_Clients_Id_Evaluate_Scopes_Generate_Example_Access_TokenTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> scopeProperty = new HashMap<>();
        scopeProperty.put("type", "string");
        scopeProperty.put("required", false);
        scopeProperty.put("description", "");
        properties.put("scope", scopeProperty);
        Map<String, Object> userIdProperty = new HashMap<>();
        userIdProperty.put("type", "string");
        userIdProperty.put("required", false);
        userIdProperty.put("description", "");
        properties.put("userId", userIdProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_realm_clients_id_evaluate_scopes_generate_example_access_token",
            "Create JSON with payload of example access token",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Realm_Clients_Id_Evaluate_Scopes_Generate_Example_Access_TokenHandler(config));
    }
    
}