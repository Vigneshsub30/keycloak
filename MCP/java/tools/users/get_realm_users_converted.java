/**
 * MCP Server function for Get users   Returns a list of users, filtered according to query parameters
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

class Get_Realm_UsersMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Realm_UsersHandler(MCPServer.APIConfig config) {
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
        if (args.containsKey("first")) {
            queryParams.add("first=" + args.get("first"));
        }
        if (args.containsKey("max")) {
            queryParams.add("max=" + args.get("max"));
        }
        if (args.containsKey("briefRepresentation")) {
            queryParams.add("briefRepresentation=" + args.get("briefRepresentation"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/get_realm_users" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Realm_UsersTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> emailProperty = new HashMap<>();
        emailProperty.put("type", "string");
        emailProperty.put("required", false);
        emailProperty.put("description", "");
        properties.put("email", emailProperty);
        Map<String, Object> firstNameProperty = new HashMap<>();
        firstNameProperty.put("type", "string");
        firstNameProperty.put("required", false);
        firstNameProperty.put("description", "");
        properties.put("firstName", firstNameProperty);
        Map<String, Object> lastNameProperty = new HashMap<>();
        lastNameProperty.put("type", "string");
        lastNameProperty.put("required", false);
        lastNameProperty.put("description", "");
        properties.put("lastName", lastNameProperty);
        Map<String, Object> searchProperty = new HashMap<>();
        searchProperty.put("type", "string");
        searchProperty.put("required", false);
        searchProperty.put("description", "A String contained in username, first or last name, or email");
        properties.put("search", searchProperty);
        Map<String, Object> usernameProperty = new HashMap<>();
        usernameProperty.put("type", "string");
        usernameProperty.put("required", false);
        usernameProperty.put("description", "");
        properties.put("username", usernameProperty);
        Map<String, Object> firstProperty = new HashMap<>();
        firstProperty.put("type", "string");
        firstProperty.put("required", false);
        firstProperty.put("description", "");
        properties.put("first", firstProperty);
        Map<String, Object> maxProperty = new HashMap<>();
        maxProperty.put("type", "string");
        maxProperty.put("required", false);
        maxProperty.put("description", "Maximum results size (defaults to 100)");
        properties.put("max", maxProperty);
        Map<String, Object> briefRepresentationProperty = new HashMap<>();
        briefRepresentationProperty.put("type", "string");
        briefRepresentationProperty.put("required", false);
        briefRepresentationProperty.put("description", "");
        properties.put("briefRepresentation", briefRepresentationProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_realm_users",
            "Get users   Returns a list of users, filtered according to query parameters",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Realm_UsersHandler(config));
    }
    
}