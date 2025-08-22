/**
 * MCP Server function for Update required action
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

class Put_Realm_Authentication_Required_Actions_AliasMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_Realm_Authentication_Required_Actions_AliasHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("alias")) {
            queryParams.add("alias=" + args.get("alias"));
        }
        if (args.containsKey("name")) {
            queryParams.add("name=" + args.get("name"));
        }
        if (args.containsKey("providerId")) {
            queryParams.add("providerId=" + args.get("providerId"));
        }
        if (args.containsKey("priority")) {
            queryParams.add("priority=" + args.get("priority"));
        }
        if (args.containsKey("defaultAction")) {
            queryParams.add("defaultAction=" + args.get("defaultAction"));
        }
        if (args.containsKey("enabled")) {
            queryParams.add("enabled=" + args.get("enabled"));
        }
        if (args.containsKey("config")) {
            queryParams.add("config=" + args.get("config"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm_authentication_required_actions_alias" + queryString;
                
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
    
    public static MCPServer.Tool createPut_Realm_Authentication_Required_Actions_AliasTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> aliasProperty = new HashMap<>();
        aliasProperty.put("type", "string");
        aliasProperty.put("required", false);
        aliasProperty.put("description", "");
        properties.put("alias", aliasProperty);
        Map<String, Object> nameProperty = new HashMap<>();
        nameProperty.put("type", "string");
        nameProperty.put("required", false);
        nameProperty.put("description", "");
        properties.put("name", nameProperty);
        Map<String, Object> providerIdProperty = new HashMap<>();
        providerIdProperty.put("type", "string");
        providerIdProperty.put("required", false);
        providerIdProperty.put("description", "");
        properties.put("providerId", providerIdProperty);
        Map<String, Object> priorityProperty = new HashMap<>();
        priorityProperty.put("type", "string");
        priorityProperty.put("required", false);
        priorityProperty.put("description", "");
        properties.put("priority", priorityProperty);
        Map<String, Object> defaultActionProperty = new HashMap<>();
        defaultActionProperty.put("type", "string");
        defaultActionProperty.put("required", false);
        defaultActionProperty.put("description", "");
        properties.put("defaultAction", defaultActionProperty);
        Map<String, Object> enabledProperty = new HashMap<>();
        enabledProperty.put("type", "string");
        enabledProperty.put("required", false);
        enabledProperty.put("description", "");
        properties.put("enabled", enabledProperty);
        Map<String, Object> configProperty = new HashMap<>();
        configProperty.put("type", "string");
        configProperty.put("required", false);
        configProperty.put("description", "");
        properties.put("config", configProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm_authentication_required_actions_alias",
            "Update required action",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_Realm_Authentication_Required_Actions_AliasHandler(config));
    }
    
}