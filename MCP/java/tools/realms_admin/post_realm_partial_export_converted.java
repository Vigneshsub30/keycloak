/**
 * MCP Server function for Partial export of existing realm into a JSON file.
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

class Post_Realm_Partial_ExportMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_Partial_ExportHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("exportClients")) {
            queryParams.add("exportClients=" + args.get("exportClients"));
        }
        if (args.containsKey("exportGroupsAndRoles")) {
            queryParams.add("exportGroupsAndRoles=" + args.get("exportGroupsAndRoles"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_partial_export" + queryString;
                
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
    
    public static MCPServer.Tool createPost_Realm_Partial_ExportTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> exportClientsProperty = new HashMap<>();
        exportClientsProperty.put("type", "string");
        exportClientsProperty.put("required", false);
        exportClientsProperty.put("description", "");
        properties.put("exportClients", exportClientsProperty);
        Map<String, Object> exportGroupsAndRolesProperty = new HashMap<>();
        exportGroupsAndRolesProperty.put("type", "string");
        exportGroupsAndRolesProperty.put("required", false);
        exportGroupsAndRolesProperty.put("description", "");
        properties.put("exportGroupsAndRoles", exportGroupsAndRolesProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_partial_export",
            "Partial export of existing realm into a JSON file.",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_Partial_ExportHandler(config));
    }
    
}