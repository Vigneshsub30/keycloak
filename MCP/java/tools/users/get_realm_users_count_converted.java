/**
 * MCP Server function for Returns the number of users that match the given criteria.
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

class Get_Realm_Users_CountMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Realm_Users_CountHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("email")) {
            queryParams.add("email=" + args.get("email"));
        }
        if (args.containsKey("firstName")) {
            queryParams.add("firstName=" + args.get("firstName"));
        }
        if (args.containsKey("lastName")) {
            queryParams.add("lastName=" + args.get("lastName"));
        }
        if (args.containsKey("search")) {
            queryParams.add("search=" + args.get("search"));
        }
        if (args.containsKey("username")) {
            queryParams.add("username=" + args.get("username"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/get_realm_users_count" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Realm_Users_CountTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> emailProperty = new HashMap<>();
        emailProperty.put("type", "string");
        emailProperty.put("required", false);
        emailProperty.put("description", "email filter");
        properties.put("email", emailProperty);
        Map<String, Object> firstNameProperty = new HashMap<>();
        firstNameProperty.put("type", "string");
        firstNameProperty.put("required", false);
        firstNameProperty.put("description", "first name filter");
        properties.put("firstName", firstNameProperty);
        Map<String, Object> lastNameProperty = new HashMap<>();
        lastNameProperty.put("type", "string");
        lastNameProperty.put("required", false);
        lastNameProperty.put("description", "last name filter");
        properties.put("lastName", lastNameProperty);
        Map<String, Object> searchProperty = new HashMap<>();
        searchProperty.put("type", "string");
        searchProperty.put("required", false);
        searchProperty.put("description", "arbitrary search string for all the fields below");
        properties.put("search", searchProperty);
        Map<String, Object> usernameProperty = new HashMap<>();
        usernameProperty.put("type", "string");
        usernameProperty.put("required", false);
        usernameProperty.put("description", "username filter");
        properties.put("username", usernameProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_realm_users_count",
            "Returns the number of users that match the given criteria.",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Realm_Users_CountHandler(config));
    }
    
}