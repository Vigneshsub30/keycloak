/**
 * MCP Server function for Update a role by name
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

class Put_Realm_Roles_Role_NameMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_Realm_Roles_Role_NameHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("name")) {
            queryParams.add("name=" + args.get("name"));
        }
        if (args.containsKey("containerId")) {
            queryParams.add("containerId=" + args.get("containerId"));
        }
        if (args.containsKey("description")) {
            queryParams.add("description=" + args.get("description"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("clientRole")) {
            queryParams.add("clientRole=" + args.get("clientRole"));
        }
        if (args.containsKey("composite")) {
            queryParams.add("composite=" + args.get("composite"));
        }
        if (args.containsKey("attributes")) {
            queryParams.add("attributes=" + args.get("attributes"));
        }
        if (args.containsKey("composites")) {
            queryParams.add("composites=" + args.get("composites"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm_roles_role_name" + queryString;
                
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
    
    public static MCPServer.Tool createPut_Realm_Roles_Role_NameTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> nameProperty = new HashMap<>();
        nameProperty.put("type", "string");
        nameProperty.put("required", false);
        nameProperty.put("description", "");
        properties.put("name", nameProperty);
        Map<String, Object> containerIdProperty = new HashMap<>();
        containerIdProperty.put("type", "string");
        containerIdProperty.put("required", false);
        containerIdProperty.put("description", "");
        properties.put("containerId", containerIdProperty);
        Map<String, Object> descriptionProperty = new HashMap<>();
        descriptionProperty.put("type", "string");
        descriptionProperty.put("required", false);
        descriptionProperty.put("description", "");
        properties.put("description", descriptionProperty);
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> clientRoleProperty = new HashMap<>();
        clientRoleProperty.put("type", "string");
        clientRoleProperty.put("required", false);
        clientRoleProperty.put("description", "");
        properties.put("clientRole", clientRoleProperty);
        Map<String, Object> compositeProperty = new HashMap<>();
        compositeProperty.put("type", "string");
        compositeProperty.put("required", false);
        compositeProperty.put("description", "");
        properties.put("composite", compositeProperty);
        Map<String, Object> attributesProperty = new HashMap<>();
        attributesProperty.put("type", "string");
        attributesProperty.put("required", false);
        attributesProperty.put("description", "");
        properties.put("attributes", attributesProperty);
        Map<String, Object> compositesProperty = new HashMap<>();
        compositesProperty.put("type", "string");
        compositesProperty.put("required", false);
        compositesProperty.put("description", "");
        properties.put("composites", compositesProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm_roles_role_name",
            "Update a role by name",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_Realm_Roles_Role_NameHandler(config));
    }
    
}