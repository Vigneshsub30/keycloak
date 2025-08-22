/**
 * MCP Server function for Get admin events   Returns all admin events, or filters events based on URL query parameters listed here
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

class Get_Realm_Admin_EventsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Realm_Admin_EventsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("authClient")) {
            queryParams.add("authClient=" + args.get("authClient"));
        }
        if (args.containsKey("authIpAddress")) {
            queryParams.add("authIpAddress=" + args.get("authIpAddress"));
        }
        if (args.containsKey("authRealm")) {
            queryParams.add("authRealm=" + args.get("authRealm"));
        }
        if (args.containsKey("authUser")) {
            queryParams.add("authUser=" + args.get("authUser"));
        }
        if (args.containsKey("dateFrom")) {
            queryParams.add("dateFrom=" + args.get("dateFrom"));
        }
        if (args.containsKey("dateTo")) {
            queryParams.add("dateTo=" + args.get("dateTo"));
        }
        if (args.containsKey("resourcePath")) {
            queryParams.add("resourcePath=" + args.get("resourcePath"));
        }
        if (args.containsKey("first")) {
            queryParams.add("first=" + args.get("first"));
        }
        if (args.containsKey("max")) {
            queryParams.add("max=" + args.get("max"));
        }
        if (args.containsKey("operationTypes")) {
            queryParams.add("operationTypes=" + args.get("operationTypes"));
        }
        if (args.containsKey("resourceTypes")) {
            queryParams.add("resourceTypes=" + args.get("resourceTypes"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/get_realm_admin_events" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Realm_Admin_EventsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> authClientProperty = new HashMap<>();
        authClientProperty.put("type", "string");
        authClientProperty.put("required", false);
        authClientProperty.put("description", "");
        properties.put("authClient", authClientProperty);
        Map<String, Object> authIpAddressProperty = new HashMap<>();
        authIpAddressProperty.put("type", "string");
        authIpAddressProperty.put("required", false);
        authIpAddressProperty.put("description", "");
        properties.put("authIpAddress", authIpAddressProperty);
        Map<String, Object> authRealmProperty = new HashMap<>();
        authRealmProperty.put("type", "string");
        authRealmProperty.put("required", false);
        authRealmProperty.put("description", "");
        properties.put("authRealm", authRealmProperty);
        Map<String, Object> authUserProperty = new HashMap<>();
        authUserProperty.put("type", "string");
        authUserProperty.put("required", false);
        authUserProperty.put("description", "user id");
        properties.put("authUser", authUserProperty);
        Map<String, Object> dateFromProperty = new HashMap<>();
        dateFromProperty.put("type", "string");
        dateFromProperty.put("required", false);
        dateFromProperty.put("description", "");
        properties.put("dateFrom", dateFromProperty);
        Map<String, Object> dateToProperty = new HashMap<>();
        dateToProperty.put("type", "string");
        dateToProperty.put("required", false);
        dateToProperty.put("description", "");
        properties.put("dateTo", dateToProperty);
        Map<String, Object> resourcePathProperty = new HashMap<>();
        resourcePathProperty.put("type", "string");
        resourcePathProperty.put("required", false);
        resourcePathProperty.put("description", "");
        properties.put("resourcePath", resourcePathProperty);
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
        Map<String, Object> operationTypesProperty = new HashMap<>();
        operationTypesProperty.put("type", "string");
        operationTypesProperty.put("required", false);
        operationTypesProperty.put("description", "");
        properties.put("operationTypes", operationTypesProperty);
        Map<String, Object> resourceTypesProperty = new HashMap<>();
        resourceTypesProperty.put("type", "string");
        resourceTypesProperty.put("required", false);
        resourceTypesProperty.put("description", "");
        properties.put("resourceTypes", resourceTypesProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_realm_admin_events",
            "Get admin events   Returns all admin events, or filters events based on URL query parameters listed here",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Realm_Admin_EventsHandler(config));
    }
    
}