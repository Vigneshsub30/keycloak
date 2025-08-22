/**
 * MCP Server function for Set or create child.
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

class Post_Realm_Groups_Id_ChildrenMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_Groups_Id_ChildrenHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("name")) {
            queryParams.add("name=" + args.get("name"));
        }
        if (args.containsKey("path")) {
            queryParams.add("path=" + args.get("path"));
        }
        if (args.containsKey("access")) {
            queryParams.add("access=" + args.get("access"));
        }
        if (args.containsKey("attributes")) {
            queryParams.add("attributes=" + args.get("attributes"));
        }
        if (args.containsKey("clientRoles")) {
            queryParams.add("clientRoles=" + args.get("clientRoles"));
        }
        if (args.containsKey("realmRoles")) {
            queryParams.add("realmRoles=" + args.get("realmRoles"));
        }
        if (args.containsKey("subGroups")) {
            queryParams.add("subGroups=" + args.get("subGroups"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_groups_id_children" + queryString;
                
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
    
    public static MCPServer.Tool createPost_Realm_Groups_Id_ChildrenTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> nameProperty = new HashMap<>();
        nameProperty.put("type", "string");
        nameProperty.put("required", false);
        nameProperty.put("description", "");
        properties.put("name", nameProperty);
        Map<String, Object> pathProperty = new HashMap<>();
        pathProperty.put("type", "string");
        pathProperty.put("required", false);
        pathProperty.put("description", "");
        properties.put("path", pathProperty);
        Map<String, Object> accessProperty = new HashMap<>();
        accessProperty.put("type", "string");
        accessProperty.put("required", false);
        accessProperty.put("description", "");
        properties.put("access", accessProperty);
        Map<String, Object> attributesProperty = new HashMap<>();
        attributesProperty.put("type", "string");
        attributesProperty.put("required", false);
        attributesProperty.put("description", "");
        properties.put("attributes", attributesProperty);
        Map<String, Object> clientRolesProperty = new HashMap<>();
        clientRolesProperty.put("type", "string");
        clientRolesProperty.put("required", false);
        clientRolesProperty.put("description", "");
        properties.put("clientRoles", clientRolesProperty);
        Map<String, Object> realmRolesProperty = new HashMap<>();
        realmRolesProperty.put("type", "string");
        realmRolesProperty.put("required", false);
        realmRolesProperty.put("description", "");
        properties.put("realmRoles", realmRolesProperty);
        Map<String, Object> subGroupsProperty = new HashMap<>();
        subGroupsProperty.put("type", "string");
        subGroupsProperty.put("required", false);
        subGroupsProperty.put("description", "");
        properties.put("subGroups", subGroupsProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_groups_id_children",
            "Set or create child.",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_Groups_Id_ChildrenHandler(config));
    }
    
}