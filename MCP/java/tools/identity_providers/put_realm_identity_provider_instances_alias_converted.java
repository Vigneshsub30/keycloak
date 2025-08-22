/**
 * MCP Server function for Update the identity provider
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

class Put_Realm_Identity_Provider_Instances_AliasMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_Realm_Identity_Provider_Instances_AliasHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("firstBrokerLoginFlowAlias")) {
            queryParams.add("firstBrokerLoginFlowAlias=" + args.get("firstBrokerLoginFlowAlias"));
        }
        if (args.containsKey("providerId")) {
            queryParams.add("providerId=" + args.get("providerId"));
        }
        if (args.containsKey("alias")) {
            queryParams.add("alias=" + args.get("alias"));
        }
        if (args.containsKey("internalId")) {
            queryParams.add("internalId=" + args.get("internalId"));
        }
        if (args.containsKey("postBrokerLoginFlowAlias")) {
            queryParams.add("postBrokerLoginFlowAlias=" + args.get("postBrokerLoginFlowAlias"));
        }
        if (args.containsKey("displayName")) {
            queryParams.add("displayName=" + args.get("displayName"));
        }
        if (args.containsKey("storeToken")) {
            queryParams.add("storeToken=" + args.get("storeToken"));
        }
        if (args.containsKey("trustEmail")) {
            queryParams.add("trustEmail=" + args.get("trustEmail"));
        }
        if (args.containsKey("linkOnly")) {
            queryParams.add("linkOnly=" + args.get("linkOnly"));
        }
        if (args.containsKey("enabled")) {
            queryParams.add("enabled=" + args.get("enabled"));
        }
        if (args.containsKey("addReadTokenRoleOnCreate")) {
            queryParams.add("addReadTokenRoleOnCreate=" + args.get("addReadTokenRoleOnCreate"));
        }
        if (args.containsKey("config")) {
            queryParams.add("config=" + args.get("config"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm_identity_provider_instances_alias" + queryString;
                
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
    
    public static MCPServer.Tool createPut_Realm_Identity_Provider_Instances_AliasTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> firstBrokerLoginFlowAliasProperty = new HashMap<>();
        firstBrokerLoginFlowAliasProperty.put("type", "string");
        firstBrokerLoginFlowAliasProperty.put("required", false);
        firstBrokerLoginFlowAliasProperty.put("description", "");
        properties.put("firstBrokerLoginFlowAlias", firstBrokerLoginFlowAliasProperty);
        Map<String, Object> providerIdProperty = new HashMap<>();
        providerIdProperty.put("type", "string");
        providerIdProperty.put("required", false);
        providerIdProperty.put("description", "");
        properties.put("providerId", providerIdProperty);
        Map<String, Object> aliasProperty = new HashMap<>();
        aliasProperty.put("type", "string");
        aliasProperty.put("required", false);
        aliasProperty.put("description", "");
        properties.put("alias", aliasProperty);
        Map<String, Object> internalIdProperty = new HashMap<>();
        internalIdProperty.put("type", "string");
        internalIdProperty.put("required", false);
        internalIdProperty.put("description", "");
        properties.put("internalId", internalIdProperty);
        Map<String, Object> postBrokerLoginFlowAliasProperty = new HashMap<>();
        postBrokerLoginFlowAliasProperty.put("type", "string");
        postBrokerLoginFlowAliasProperty.put("required", false);
        postBrokerLoginFlowAliasProperty.put("description", "");
        properties.put("postBrokerLoginFlowAlias", postBrokerLoginFlowAliasProperty);
        Map<String, Object> displayNameProperty = new HashMap<>();
        displayNameProperty.put("type", "string");
        displayNameProperty.put("required", false);
        displayNameProperty.put("description", "");
        properties.put("displayName", displayNameProperty);
        Map<String, Object> storeTokenProperty = new HashMap<>();
        storeTokenProperty.put("type", "string");
        storeTokenProperty.put("required", false);
        storeTokenProperty.put("description", "");
        properties.put("storeToken", storeTokenProperty);
        Map<String, Object> trustEmailProperty = new HashMap<>();
        trustEmailProperty.put("type", "string");
        trustEmailProperty.put("required", false);
        trustEmailProperty.put("description", "");
        properties.put("trustEmail", trustEmailProperty);
        Map<String, Object> linkOnlyProperty = new HashMap<>();
        linkOnlyProperty.put("type", "string");
        linkOnlyProperty.put("required", false);
        linkOnlyProperty.put("description", "");
        properties.put("linkOnly", linkOnlyProperty);
        Map<String, Object> enabledProperty = new HashMap<>();
        enabledProperty.put("type", "string");
        enabledProperty.put("required", false);
        enabledProperty.put("description", "");
        properties.put("enabled", enabledProperty);
        Map<String, Object> addReadTokenRoleOnCreateProperty = new HashMap<>();
        addReadTokenRoleOnCreateProperty.put("type", "string");
        addReadTokenRoleOnCreateProperty.put("required", false);
        addReadTokenRoleOnCreateProperty.put("description", "");
        properties.put("addReadTokenRoleOnCreate", addReadTokenRoleOnCreateProperty);
        Map<String, Object> configProperty = new HashMap<>();
        configProperty.put("type", "string");
        configProperty.put("required", false);
        configProperty.put("description", "");
        properties.put("config", configProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm_identity_provider_instances_alias",
            "Update the identity provider",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_Realm_Identity_Provider_Instances_AliasHandler(config));
    }
    
}