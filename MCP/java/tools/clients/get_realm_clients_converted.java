/**
 * MCP Server function for Get clients belonging to the realm   Returns a list of clients belonging to the realm
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

class Get_Realm_ClientsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Realm_ClientsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("clientId")) {
            queryParams.add("clientId=" + args.get("clientId"));
        }
        if (args.containsKey("first")) {
            queryParams.add("first=" + args.get("first"));
        }
        if (args.containsKey("max")) {
            queryParams.add("max=" + args.get("max"));
        }
        if (args.containsKey("search")) {
            queryParams.add("search=" + args.get("search"));
        }
        if (args.containsKey("viewableOnly")) {
            queryParams.add("viewableOnly=" + args.get("viewableOnly"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/get_realm_clients" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Realm_ClientsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> clientIdProperty = new HashMap<>();
        clientIdProperty.put("type", "string");
        clientIdProperty.put("required", false);
        clientIdProperty.put("description", "filter by clientId");
        properties.put("clientId", clientIdProperty);
        Map<String, Object> firstProperty = new HashMap<>();
        firstProperty.put("type", "string");
        firstProperty.put("required", false);
        firstProperty.put("description", "the first result");
        properties.put("first", firstProperty);
        Map<String, Object> maxProperty = new HashMap<>();
        maxProperty.put("type", "string");
        maxProperty.put("required", false);
        maxProperty.put("description", "the max results to return");
        properties.put("max", maxProperty);
        Map<String, Object> searchProperty = new HashMap<>();
        searchProperty.put("type", "string");
        searchProperty.put("required", false);
        searchProperty.put("description", "whether this is a search query or a getClientById query");
        properties.put("search", searchProperty);
        Map<String, Object> viewableOnlyProperty = new HashMap<>();
        viewableOnlyProperty.put("type", "string");
        viewableOnlyProperty.put("required", false);
        viewableOnlyProperty.put("description", "filter clients that cannot be viewed in full by admin");
        properties.put("viewableOnly", viewableOnlyProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_realm_clients",
            "Get clients belonging to the realm   Returns a list of clients belonging to the realm",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Realm_ClientsHandler(config));
    }
    
}