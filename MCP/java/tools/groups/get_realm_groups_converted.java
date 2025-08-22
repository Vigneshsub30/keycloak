/**
 * MCP Server function for Get group hierarchy.
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

class Get_Realm_GroupsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Realm_GroupsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("search")) {
            queryParams.add("search=" + args.get("search"));
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
                String url = config.getBaseUrl() + "/api/v2/get_realm_groups" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Realm_GroupsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> searchProperty = new HashMap<>();
        searchProperty.put("type", "string");
        searchProperty.put("required", false);
        searchProperty.put("description", "");
        properties.put("search", searchProperty);
        Map<String, Object> firstProperty = new HashMap<>();
        firstProperty.put("type", "string");
        firstProperty.put("required", false);
        firstProperty.put("description", "");
        properties.put("first", firstProperty);
        Map<String, Object> maxProperty = new HashMap<>();
        maxProperty.put("type", "string");
        maxProperty.put("required", false);
        maxProperty.put("description", "");
        properties.put("max", maxProperty);
        Map<String, Object> briefRepresentationProperty = new HashMap<>();
        briefRepresentationProperty.put("type", "string");
        briefRepresentationProperty.put("required", false);
        briefRepresentationProperty.put("description", "");
        properties.put("briefRepresentation", briefRepresentationProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_realm_groups",
            "Get group hierarchy.",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Realm_GroupsHandler(config));
    }
    
}