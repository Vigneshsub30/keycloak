/**
 * MCP Server function for Test LDAP connection
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

class Post_Realm_Test_Ldap_ConnectionMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_Test_Ldap_ConnectionHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("connectionUrl")) {
            queryParams.add("connectionUrl=" + args.get("connectionUrl"));
        }
        if (args.containsKey("startTls")) {
            queryParams.add("startTls=" + args.get("startTls"));
        }
        if (args.containsKey("useTruststoreSpi")) {
            queryParams.add("useTruststoreSpi=" + args.get("useTruststoreSpi"));
        }
        if (args.containsKey("action")) {
            queryParams.add("action=" + args.get("action"));
        }
        if (args.containsKey("bindCredential")) {
            queryParams.add("bindCredential=" + args.get("bindCredential"));
        }
        if (args.containsKey("bindDn")) {
            queryParams.add("bindDn=" + args.get("bindDn"));
        }
        if (args.containsKey("componentId")) {
            queryParams.add("componentId=" + args.get("componentId"));
        }
        if (args.containsKey("connectionTimeout")) {
            queryParams.add("connectionTimeout=" + args.get("connectionTimeout"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_test_ldap_connection" + queryString;
                
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
    
    public static MCPServer.Tool createPost_Realm_Test_Ldap_ConnectionTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> connectionUrlProperty = new HashMap<>();
        connectionUrlProperty.put("type", "string");
        connectionUrlProperty.put("required", false);
        connectionUrlProperty.put("description", "");
        properties.put("connectionUrl", connectionUrlProperty);
        Map<String, Object> startTlsProperty = new HashMap<>();
        startTlsProperty.put("type", "string");
        startTlsProperty.put("required", false);
        startTlsProperty.put("description", "");
        properties.put("startTls", startTlsProperty);
        Map<String, Object> useTruststoreSpiProperty = new HashMap<>();
        useTruststoreSpiProperty.put("type", "string");
        useTruststoreSpiProperty.put("required", false);
        useTruststoreSpiProperty.put("description", "");
        properties.put("useTruststoreSpi", useTruststoreSpiProperty);
        Map<String, Object> actionProperty = new HashMap<>();
        actionProperty.put("type", "string");
        actionProperty.put("required", false);
        actionProperty.put("description", "");
        properties.put("action", actionProperty);
        Map<String, Object> bindCredentialProperty = new HashMap<>();
        bindCredentialProperty.put("type", "string");
        bindCredentialProperty.put("required", false);
        bindCredentialProperty.put("description", "");
        properties.put("bindCredential", bindCredentialProperty);
        Map<String, Object> bindDnProperty = new HashMap<>();
        bindDnProperty.put("type", "string");
        bindDnProperty.put("required", false);
        bindDnProperty.put("description", "");
        properties.put("bindDn", bindDnProperty);
        Map<String, Object> componentIdProperty = new HashMap<>();
        componentIdProperty.put("type", "string");
        componentIdProperty.put("required", false);
        componentIdProperty.put("description", "");
        properties.put("componentId", componentIdProperty);
        Map<String, Object> connectionTimeoutProperty = new HashMap<>();
        connectionTimeoutProperty.put("type", "string");
        connectionTimeoutProperty.put("required", false);
        connectionTimeoutProperty.put("description", "");
        properties.put("connectionTimeout", connectionTimeoutProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_test_ldap_connection",
            "Test LDAP connection",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_Test_Ldap_ConnectionHandler(config));
    }
    
}