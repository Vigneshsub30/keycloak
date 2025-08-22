/**
 * MCP Server function for Update authentication executions of a flow
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

class Put_Realm_Authentication_Flows_Flow_Alias_ExecutionsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_Realm_Authentication_Flows_Flow_Alias_ExecutionsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("displayName")) {
            queryParams.add("displayName=" + args.get("displayName"));
        }
        if (args.containsKey("requirement")) {
            queryParams.add("requirement=" + args.get("requirement"));
        }
        if (args.containsKey("alias")) {
            queryParams.add("alias=" + args.get("alias"));
        }
        if (args.containsKey("providerId")) {
            queryParams.add("providerId=" + args.get("providerId"));
        }
        if (args.containsKey("authenticationConfig")) {
            queryParams.add("authenticationConfig=" + args.get("authenticationConfig"));
        }
        if (args.containsKey("flowId")) {
            queryParams.add("flowId=" + args.get("flowId"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("level")) {
            queryParams.add("level=" + args.get("level"));
        }
        if (args.containsKey("index")) {
            queryParams.add("index=" + args.get("index"));
        }
        if (args.containsKey("authenticationFlow")) {
            queryParams.add("authenticationFlow=" + args.get("authenticationFlow"));
        }
        if (args.containsKey("configurable")) {
            queryParams.add("configurable=" + args.get("configurable"));
        }
        if (args.containsKey("requirementChoices")) {
            queryParams.add("requirementChoices=" + args.get("requirementChoices"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm_authentication_flows_flow_alias_executions" + queryString;
                
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
    
    public static MCPServer.Tool createPut_Realm_Authentication_Flows_Flow_Alias_ExecutionsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> displayNameProperty = new HashMap<>();
        displayNameProperty.put("type", "string");
        displayNameProperty.put("required", false);
        displayNameProperty.put("description", "");
        properties.put("displayName", displayNameProperty);
        Map<String, Object> requirementProperty = new HashMap<>();
        requirementProperty.put("type", "string");
        requirementProperty.put("required", false);
        requirementProperty.put("description", "");
        properties.put("requirement", requirementProperty);
        Map<String, Object> aliasProperty = new HashMap<>();
        aliasProperty.put("type", "string");
        aliasProperty.put("required", false);
        aliasProperty.put("description", "");
        properties.put("alias", aliasProperty);
        Map<String, Object> providerIdProperty = new HashMap<>();
        providerIdProperty.put("type", "string");
        providerIdProperty.put("required", false);
        providerIdProperty.put("description", "");
        properties.put("providerId", providerIdProperty);
        Map<String, Object> authenticationConfigProperty = new HashMap<>();
        authenticationConfigProperty.put("type", "string");
        authenticationConfigProperty.put("required", false);
        authenticationConfigProperty.put("description", "");
        properties.put("authenticationConfig", authenticationConfigProperty);
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
        Map<String, Object> levelProperty = new HashMap<>();
        levelProperty.put("type", "string");
        levelProperty.put("required", false);
        levelProperty.put("description", "");
        properties.put("level", levelProperty);
        Map<String, Object> indexProperty = new HashMap<>();
        indexProperty.put("type", "string");
        indexProperty.put("required", false);
        indexProperty.put("description", "");
        properties.put("index", indexProperty);
        Map<String, Object> authenticationFlowProperty = new HashMap<>();
        authenticationFlowProperty.put("type", "string");
        authenticationFlowProperty.put("required", false);
        authenticationFlowProperty.put("description", "");
        properties.put("authenticationFlow", authenticationFlowProperty);
        Map<String, Object> configurableProperty = new HashMap<>();
        configurableProperty.put("type", "string");
        configurableProperty.put("required", false);
        configurableProperty.put("description", "");
        properties.put("configurable", configurableProperty);
        Map<String, Object> requirementChoicesProperty = new HashMap<>();
        requirementChoicesProperty.put("type", "string");
        requirementChoicesProperty.put("required", false);
        requirementChoicesProperty.put("description", "");
        properties.put("requirementChoices", requirementChoicesProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm_authentication_flows_flow_alias_executions",
            "Update authentication executions of a flow",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_Realm_Authentication_Flows_Flow_Alias_ExecutionsHandler(config));
    }
    
}