/**
 * MCP Server function for Get events   Returns all events, or filters them based on URL query parameters listed here
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

class Get_Realm_EventsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Realm_EventsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("client")) {
            queryParams.add("client=" + args.get("client"));
        }
        if (args.containsKey("dateFrom")) {
            queryParams.add("dateFrom=" + args.get("dateFrom"));
        }
        if (args.containsKey("dateTo")) {
            queryParams.add("dateTo=" + args.get("dateTo"));
        }
        if (args.containsKey("ipAddress")) {
            queryParams.add("ipAddress=" + args.get("ipAddress"));
        }
        if (args.containsKey("user")) {
            queryParams.add("user=" + args.get("user"));
        }
        if (args.containsKey("first")) {
            queryParams.add("first=" + args.get("first"));
        }
        if (args.containsKey("max")) {
            queryParams.add("max=" + args.get("max"));
        }
        if (args.containsKey("type")) {
            queryParams.add("type=" + args.get("type"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/get_realm_events" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Realm_EventsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> clientProperty = new HashMap<>();
        clientProperty.put("type", "string");
        clientProperty.put("required", false);
        clientProperty.put("description", "App or oauth client name");
        properties.put("client", clientProperty);
        Map<String, Object> dateFromProperty = new HashMap<>();
        dateFromProperty.put("type", "string");
        dateFromProperty.put("required", false);
        dateFromProperty.put("description", "From date");
        properties.put("dateFrom", dateFromProperty);
        Map<String, Object> dateToProperty = new HashMap<>();
        dateToProperty.put("type", "string");
        dateToProperty.put("required", false);
        dateToProperty.put("description", "To date");
        properties.put("dateTo", dateToProperty);
        Map<String, Object> ipAddressProperty = new HashMap<>();
        ipAddressProperty.put("type", "string");
        ipAddressProperty.put("required", false);
        ipAddressProperty.put("description", "IP address");
        properties.put("ipAddress", ipAddressProperty);
        Map<String, Object> userProperty = new HashMap<>();
        userProperty.put("type", "string");
        userProperty.put("required", false);
        userProperty.put("description", "User id");
        properties.put("user", userProperty);
        Map<String, Object> firstProperty = new HashMap<>();
        firstProperty.put("type", "string");
        firstProperty.put("required", false);
        firstProperty.put("description", "Paging offset");
        properties.put("first", firstProperty);
        Map<String, Object> maxProperty = new HashMap<>();
        maxProperty.put("type", "string");
        maxProperty.put("required", false);
        maxProperty.put("description", "Maximum results size (defaults to 100)");
        properties.put("max", maxProperty);
        Map<String, Object> typeProperty = new HashMap<>();
        typeProperty.put("type", "string");
        typeProperty.put("required", false);
        typeProperty.put("description", "The types of events to return");
        properties.put("type", typeProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_realm_events",
            "Get events   Returns all events, or filters them based on URL query parameters listed here",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Realm_EventsHandler(config));
    }
    
}