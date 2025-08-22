/**
 * MCP Server function for Set up a new password for the user.
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

class Put_Realm_Users_Id_Reset_PasswordMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_Realm_Users_Id_Reset_PasswordHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("secretData")) {
            queryParams.add("secretData=" + args.get("secretData"));
        }
        if (args.containsKey("userLabel")) {
            queryParams.add("userLabel=" + args.get("userLabel"));
        }
        if (args.containsKey("value")) {
            queryParams.add("value=" + args.get("value"));
        }
        if (args.containsKey("credentialData")) {
            queryParams.add("credentialData=" + args.get("credentialData"));
        }
        if (args.containsKey("type")) {
            queryParams.add("type=" + args.get("type"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("createdDate")) {
            queryParams.add("createdDate=" + args.get("createdDate"));
        }
        if (args.containsKey("priority")) {
            queryParams.add("priority=" + args.get("priority"));
        }
        if (args.containsKey("temporary")) {
            queryParams.add("temporary=" + args.get("temporary"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm_users_id_reset_password" + queryString;
                
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
    
    public static MCPServer.Tool createPut_Realm_Users_Id_Reset_PasswordTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> secretDataProperty = new HashMap<>();
        secretDataProperty.put("type", "string");
        secretDataProperty.put("required", false);
        secretDataProperty.put("description", "");
        properties.put("secretData", secretDataProperty);
        Map<String, Object> userLabelProperty = new HashMap<>();
        userLabelProperty.put("type", "string");
        userLabelProperty.put("required", false);
        userLabelProperty.put("description", "");
        properties.put("userLabel", userLabelProperty);
        Map<String, Object> valueProperty = new HashMap<>();
        valueProperty.put("type", "string");
        valueProperty.put("required", false);
        valueProperty.put("description", "");
        properties.put("value", valueProperty);
        Map<String, Object> credentialDataProperty = new HashMap<>();
        credentialDataProperty.put("type", "string");
        credentialDataProperty.put("required", false);
        credentialDataProperty.put("description", "");
        properties.put("credentialData", credentialDataProperty);
        Map<String, Object> typeProperty = new HashMap<>();
        typeProperty.put("type", "string");
        typeProperty.put("required", false);
        typeProperty.put("description", "");
        properties.put("type", typeProperty);
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> createdDateProperty = new HashMap<>();
        createdDateProperty.put("type", "string");
        createdDateProperty.put("required", false);
        createdDateProperty.put("description", "");
        properties.put("createdDate", createdDateProperty);
        Map<String, Object> priorityProperty = new HashMap<>();
        priorityProperty.put("type", "string");
        priorityProperty.put("required", false);
        priorityProperty.put("description", "");
        properties.put("priority", priorityProperty);
        Map<String, Object> temporaryProperty = new HashMap<>();
        temporaryProperty.put("type", "string");
        temporaryProperty.put("required", false);
        temporaryProperty.put("description", "");
        properties.put("temporary", temporaryProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm_users_id_reset_password",
            "Set up a new password for the user.",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_Realm_Users_Id_Reset_PasswordHandler(config));
    }
    
}