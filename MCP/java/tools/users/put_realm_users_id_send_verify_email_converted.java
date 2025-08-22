/**
 * MCP Server function for Send an email-verification email to the user   An email contains a link the user can click to verify their email address.
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

class Put_Realm_Users_Id_Send_Verify_EmailMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_Realm_Users_Id_Send_Verify_EmailHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("client_id")) {
            queryParams.add("client_id=" + args.get("client_id"));
        }
        if (args.containsKey("redirect_uri")) {
            queryParams.add("redirect_uri=" + args.get("redirect_uri"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm_users_id_send_verify_email" + queryString;
                
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
    
    public static MCPServer.Tool createPut_Realm_Users_Id_Send_Verify_EmailTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> client_idProperty = new HashMap<>();
        client_idProperty.put("type", "string");
        client_idProperty.put("required", false);
        client_idProperty.put("description", "Client id");
        properties.put("client_id", client_idProperty);
        Map<String, Object> redirect_uriProperty = new HashMap<>();
        redirect_uriProperty.put("type", "string");
        redirect_uriProperty.put("required", false);
        redirect_uriProperty.put("description", "Redirect uri");
        properties.put("redirect_uri", redirect_uriProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm_users_id_send_verify_email",
            "Send an email-verification email to the user   An email contains a link the user can click to verify their email address.",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_Realm_Users_Id_Send_Verify_EmailHandler(config));
    }
    
}