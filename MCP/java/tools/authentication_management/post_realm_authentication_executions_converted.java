/**
 * MCP Server function for Add new authentication execution
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

class Post_Realm_Authentication_ExecutionsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_Authentication_ExecutionsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("requirement")) {
            queryParams.add("requirement=" + args.get("requirement"));
        }
        if (args.containsKey("parentFlow")) {
            queryParams.add("parentFlow=" + args.get("parentFlow"));
        }
        if (args.containsKey("authenticator")) {
            queryParams.add("authenticator=" + args.get("authenticator"));
        }
        if (args.containsKey("flowId")) {
            queryParams.add("flowId=" + args.get("flowId"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("authenticatorConfig")) {
            queryParams.add("authenticatorConfig=" + args.get("authenticatorConfig"));
        }
        if (args.containsKey("priority")) {
            queryParams.add("priority=" + args.get("priority"));
        }
        if (args.containsKey("authenticatorFlow")) {
            queryParams.add("authenticatorFlow=" + args.get("authenticatorFlow"));
        }
        if (args.containsKey("autheticatorFlow")) {
            queryParams.add("autheticatorFlow=" + args.get("autheticatorFlow"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_authentication_executions" + queryString;
                
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
    
    public static MCPServer.Tool createPost_Realm_Authentication_ExecutionsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> requirementProperty = new HashMap<>();
        requirementProperty.put("type", "string");
        requirementProperty.put("required", false);
        requirementProperty.put("description", "");
        properties.put("requirement", requirementProperty);
        Map<String, Object> parentFlowProperty = new HashMap<>();
        parentFlowProperty.put("type", "string");
        parentFlowProperty.put("required", false);
        parentFlowProperty.put("description", "");
        properties.put("parentFlow", parentFlowProperty);
        Map<String, Object> authenticatorProperty = new HashMap<>();
        authenticatorProperty.put("type", "string");
        authenticatorProperty.put("required", false);
        authenticatorProperty.put("description", "");
        properties.put("authenticator", authenticatorProperty);
        Map<String, Object> flowIdProperty = new HashMap<>();
        flowIdProperty.put("type", "string");
        flowIdProperty.put("required", false);
        flowIdProperty.put("description", "");
        properties.put("flowId", flowIdProperty);
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> authenticatorConfigProperty = new HashMap<>();
        authenticatorConfigProperty.put("type", "string");
        authenticatorConfigProperty.put("required", false);
        authenticatorConfigProperty.put("description", "");
        properties.put("authenticatorConfig", authenticatorConfigProperty);
        Map<String, Object> priorityProperty = new HashMap<>();
        priorityProperty.put("type", "string");
        priorityProperty.put("required", false);
        priorityProperty.put("description", "");
        properties.put("priority", priorityProperty);
        Map<String, Object> authenticatorFlowProperty = new HashMap<>();
        authenticatorFlowProperty.put("type", "string");
        authenticatorFlowProperty.put("required", false);
        authenticatorFlowProperty.put("description", "");
        properties.put("authenticatorFlow", authenticatorFlowProperty);
        Map<String, Object> autheticatorFlowProperty = new HashMap<>();
        autheticatorFlowProperty.put("type", "string");
        autheticatorFlowProperty.put("required", false);
        autheticatorFlowProperty.put("description", "");
        properties.put("autheticatorFlow", autheticatorFlowProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_authentication_executions",
            "Add new authentication execution",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_Authentication_ExecutionsHandler(config));
    }
    
}