/**
 * MCP Server function for Update the events provider   Change the events provider and/or its configuration
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

class Put_Realm_Events_ConfigMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_Realm_Events_ConfigHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("eventsExpiration")) {
            queryParams.add("eventsExpiration=" + args.get("eventsExpiration"));
        }
        if (args.containsKey("adminEventsDetailsEnabled")) {
            queryParams.add("adminEventsDetailsEnabled=" + args.get("adminEventsDetailsEnabled"));
        }
        if (args.containsKey("adminEventsEnabled")) {
            queryParams.add("adminEventsEnabled=" + args.get("adminEventsEnabled"));
        }
        if (args.containsKey("eventsEnabled")) {
            queryParams.add("eventsEnabled=" + args.get("eventsEnabled"));
        }
        if (args.containsKey("eventsListeners")) {
            queryParams.add("eventsListeners=" + args.get("eventsListeners"));
        }
        if (args.containsKey("enabledEventTypes")) {
            queryParams.add("enabledEventTypes=" + args.get("enabledEventTypes"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm_events_config" + queryString;
                
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
    
    public static MCPServer.Tool createPut_Realm_Events_ConfigTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> eventsExpirationProperty = new HashMap<>();
        eventsExpirationProperty.put("type", "string");
        eventsExpirationProperty.put("required", false);
        eventsExpirationProperty.put("description", "");
        properties.put("eventsExpiration", eventsExpirationProperty);
        Map<String, Object> adminEventsDetailsEnabledProperty = new HashMap<>();
        adminEventsDetailsEnabledProperty.put("type", "string");
        adminEventsDetailsEnabledProperty.put("required", false);
        adminEventsDetailsEnabledProperty.put("description", "");
        properties.put("adminEventsDetailsEnabled", adminEventsDetailsEnabledProperty);
        Map<String, Object> adminEventsEnabledProperty = new HashMap<>();
        adminEventsEnabledProperty.put("type", "string");
        adminEventsEnabledProperty.put("required", false);
        adminEventsEnabledProperty.put("description", "");
        properties.put("adminEventsEnabled", adminEventsEnabledProperty);
        Map<String, Object> eventsEnabledProperty = new HashMap<>();
        eventsEnabledProperty.put("type", "string");
        eventsEnabledProperty.put("required", false);
        eventsEnabledProperty.put("description", "");
        properties.put("eventsEnabled", eventsEnabledProperty);
        Map<String, Object> eventsListenersProperty = new HashMap<>();
        eventsListenersProperty.put("type", "string");
        eventsListenersProperty.put("required", false);
        eventsListenersProperty.put("description", "");
        properties.put("eventsListeners", eventsListenersProperty);
        Map<String, Object> enabledEventTypesProperty = new HashMap<>();
        enabledEventTypesProperty.put("type", "string");
        enabledEventTypesProperty.put("required", false);
        enabledEventTypesProperty.put("description", "");
        properties.put("enabledEventTypes", enabledEventTypesProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm_events_config",
            "Update the events provider   Change the events provider and/or its configuration",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_Realm_Events_ConfigHandler(config));
    }
    
}